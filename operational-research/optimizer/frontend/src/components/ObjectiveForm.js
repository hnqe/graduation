import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlus, faMinus } from '@fortawesome/free-solid-svg-icons';

// Esse componente cuida somente da parte de tipo de otimização (Max/Min) e dos 
// coeficientes da função objetivo, além de fornecer botões de adicionar/remover variáveis.

function ObjectiveForm({
  objectiveType,
  setObjectiveType,
  objCoefs,
  setObjCoefs,
  addVariable,
  removeVariable
}) {
  return (
    <div>
      {/* Tipo de Otimização */}
      <div className="mb-3">
        <label className="form-label">Tipo de Otimização:</label>
        <select
          value={objectiveType}
          onChange={(e) => setObjectiveType(e.target.value)}
          className="form-select"
        >
          <option value="maximize">Maximizar</option>
          <option value="minimize">Minimizar</option>
        </select>
      </div>
      
      {/* Função Objetivo */}
      <h2 className="text-secondary">Função Objetivo</h2>
      <div className="mb-3">
        {objCoefs.map((coef, index) => (
          <div key={index} style={{ display: "inline-block", marginRight: "1rem" }}>
            <input
              type="number"
              step="any"
              value={coef}
              onChange={(e) => {
                const newCoefs = [...objCoefs];
                newCoefs[index] = e.target.value;
                setObjCoefs(newCoefs);
              }}
              className="form-control d-inline w-75 me-2"
              placeholder={`Coef x${index + 1}`}
            />
            {/* Botão de remover variável individual */}
            {objCoefs.length > 1 && (
              <button
                type="button"
                className="btn btn-outline-danger"
                onClick={() => removeVariable(index)}
              >
                <FontAwesomeIcon icon={faMinus} />
              </button>
            )}
          </div>
        ))}

        {/* Botão de adicionar variável */}
        <button
          type="button"
          onClick={addVariable}
          className="btn btn-outline-primary ms-2"
        >
          <FontAwesomeIcon icon={faPlus} /> Variável
        </button>
      </div>
    </div>
  );
}

export default ObjectiveForm;
