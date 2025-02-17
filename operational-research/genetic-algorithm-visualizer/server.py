from flask import Flask, request, jsonify, Response
from flask_cors import CORS
import json
import time

from ga_knapsack import genetic_algorithm, genetic_algorithm_stream

app = Flask(__name__)
CORS(app)

@app.route('/run_ga', methods=['POST'])
def run_ga():
    data = request.json
    if not data:
        data = {}
    
    # Pega parâmetros do front-end
    pop_size = data.get('pop_size', 30)
    max_gens = data.get('max_generations', 100)
    mutation_rate = data.get('mutation_rate', 0.01)
    crossover_rate = data.get('crossover_rate', 0.8)
    capacity = data.get('capacity', 50)

    # Lê array de itens
    items_data = data.get('items', [])  # ex.: [ { "value":60, "weight":10 }, ... ]
    items = []
    for it in items_data:
        valor = it.get('value', 0)
        peso = it.get('weight', 0)
        items.append((valor, peso))
    
    best_sol, best_fit, history = genetic_algorithm(
        items, 
        capacity,
        pop_size=pop_size,
        max_generations=max_gens,
        crossover_rate=crossover_rate,
        mutation_rate=mutation_rate,
        k=3
    )
    
    return jsonify({
        "best_solution": best_sol,
        "best_fitness": best_fit,
        "history": history
    })

@app.route('/stream_ga', methods=['GET'])
def stream_ga():
    def generate():
        items = [(60,10), (100,20), (120,30), (80,15), (90,18), (75,12)]
        W = 50
        pop_size = 30
        max_gens = 50
        crossover_rate = 0.8
        mutation_rate = 0.05
        k = 3
        
        for gen_data in genetic_algorithm_stream(
            items, W, pop_size, max_gens, crossover_rate, mutation_rate, k
        ):
            # gen_data = {
            #   "generation": ...,
            #   "best_fitness": ...,
            #   "avg_fitness": ...,
            #   "min_fitness": ...,
            #   "std_fitness": ...
            # }
            yield f"data: {json.dumps(gen_data)}\n\n"
            time.sleep(0.2)

    return Response(generate(), mimetype='text/event-stream')

if __name__ == '__main__':
    app.run(debug=True)