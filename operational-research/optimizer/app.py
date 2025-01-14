from flask import Flask, request, jsonify
from flask_cors import CORS
import pulp

app = Flask(__name__)
CORS(app) 

@app.route('/optimize', methods=['POST'])
def optimize():
    # 1) Pegar dados do JSON que o cliente enviar
    data = request.get_json()
    
    # 2) Ler detalhes da função objetivo
    objective_type = data["objectiveFunction"]["type"]  # "maximize" ou "minimize"
    obj_coefs = data["objectiveFunction"]["coefficients"]  # ex: [5, 3] => 5x1 + 3x2
    
    # 3) Ler dados de restrições e bounds
    constraints_data = data.get("constraints", [])
    variable_bounds = data.get("variableBounds", [])
    
    # 4) Criar o modelo PuLP
    #    "pulp.LpMaximize" ou "pulp.LpMinimize" baseado no objective_type
    if objective_type == "maximize":
        prob = pulp.LpProblem("MyProblem", pulp.LpMaximize)
    else:
        prob = pulp.LpProblem("MyProblem", pulp.LpMinimize)
    
    # 5) Criar variáveis
    num_vars = len(obj_coefs)
    variables = []
    for i in range(num_vars):
        # Pega limites (se existirem). Se não existirem, coloca None
        lb = variable_bounds[i].get("min") if i < len(variable_bounds) else None
        ub = variable_bounds[i].get("max") if i < len(variable_bounds) else None
        
        # Cria variável x{i+1}, com lowBound = lb, upBound = ub
        var = pulp.LpVariable(f"x{i+1}", lowBound=lb, upBound=ub, cat=pulp.LpContinuous)
        variables.append(var)
    
    # 6) Definir a função objetivo
    #    Somatório (coef_i * x_i)
    prob += pulp.lpSum(obj_coefs[i] * variables[i] for i in range(num_vars))
    
    # 7) Definir restrições
    for i, c in enumerate(constraints_data):
        lhs = pulp.lpSum(c["coefficients"][j] * variables[j] for j in range(num_vars))
        op = c["operator"]
        rhs = c["rhs"]
        
        # Adiciona ao modelo
        if op == "<=":
            prob += (lhs <= rhs), f"Constraint_{i}"
        elif op == ">=":
            prob += (lhs >= rhs), f"Constraint_{i}"
        elif op == "==":
            prob += (lhs == rhs), f"Constraint_{i}"
    
    # 8) Resolver o problema
    prob.solve()
    
    # 9) Montar a resposta
    result_status = pulp.LpStatus[prob.status]
    result_vars = [pulp.value(var) for var in variables]
    result_obj = pulp.value(prob.objective)
    
    response = {
        "status": result_status,        # "Optimal", "Infeasible", etc.
        "variables": result_vars,       # [valor de x1, valor de x2, ...]
        "objective": result_obj         # valor da função objetivo
    }
    
    return jsonify(response)

if __name__ == '__main__':
    # Subir o servidor em modo debug
    app.run(debug=True)
