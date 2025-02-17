import random
import statistics  # <-- para calcular stdev, mean etc.

def generate_individual(num_items):
    return [random.randint(0, 1) for _ in range(num_items)]

def generate_initial_population(pop_size, num_items):
    return [generate_individual(num_items) for _ in range(pop_size)]

def fitness(chromosome, items, W):
    """
    Função de fitness com penalização branda:
    Se total_weight > W, então fitness = total_value - penalty_factor * excesso
    (e truncado em 0, se ficar negativo).
    """
    total_value = 0
    total_weight = 0
    for gene, (valor, peso) in zip(chromosome, items):
        if gene == 1:
            total_value += valor
            total_weight += peso

    if total_weight > W:
        # Penalização branda
        excess = total_weight - W
        penalty_factor = 10  # você pode ajustar este fator
        penalized_value = total_value - penalty_factor * excess
        return max(0, penalized_value)
    
    return total_value

def tournament_selection(population, items, W, k=3):
    selected = random.sample(population, k)
    selected_fitness = [(ind, fitness(ind, items, W)) for ind in selected]
    best_individual = max(selected_fitness, key=lambda x: x[1])[0]
    return best_individual

def single_point_crossover(parent1, parent2):
    size = len(parent1)
    point = random.randint(1, size-1)
    child1 = parent1[:point] + parent2[point:]
    child2 = parent2[:point] + parent1[point:]
    return child1, child2

def mutation(chromosome, mutation_rate=0.01):
    for i in range(len(chromosome)):
        if random.random() < mutation_rate:
            chromosome[i] = 1 - chromosome[i]
    return chromosome

def genetic_algorithm(items, W, pop_size=50, max_generations=100, 
                      crossover_rate=0.8, mutation_rate=0.01, k=3):
    """
    AG para o problema da mochila com penalização branda para excesso de peso.
    """
    num_items = len(items)
    population = generate_initial_population(pop_size, num_items)

    best_solution = None
    best_fitness_val = 0

    history = []

    for generation in range(max_generations):
        # Fitness de cada indivíduo
        population_fitness = [fitness(ind, items, W) for ind in population]

        # Estatísticas
        gen_best_fitness = max(population_fitness)       # melhor da geração
        gen_min_fitness  = min(population_fitness)       # pior da geração
        gen_avg_fitness  = statistics.mean(population_fitness)
        
        gen_std_fitness = 0
        if len(population_fitness) > 1:
            gen_std_fitness = statistics.pstdev(population_fitness)  

        # Atualiza melhor global se necessário
        if gen_best_fitness > best_fitness_val:
            best_fitness_val = gen_best_fitness
            best_solution = population[population_fitness.index(gen_best_fitness)]

        # Salvar no histórico
        history.append({
            "generation": generation,
            "best_fitness": best_fitness_val,  # melhor global
            "avg_fitness": gen_avg_fitness,
            "min_fitness": gen_min_fitness,
            "std_fitness": gen_std_fitness
        })
        
        # Nova população
        new_population = []
        while len(new_population) < pop_size:
            parent1 = tournament_selection(population, items, W, k)
            parent2 = tournament_selection(population, items, W, k)
            if random.random() < crossover_rate:
                child1, child2 = single_point_crossover(parent1, parent2)
            else:
                child1, child2 = parent1[:], parent2[:]
            child1 = mutation(child1, mutation_rate)
            child2 = mutation(child2, mutation_rate)
            new_population.append(child1)
            if len(new_population) < pop_size:
                new_population.append(child2)
        population = new_population

    return best_solution, best_fitness_val, history

# STREAM versão com estatísticas
def genetic_algorithm_stream(items, W, pop_size=50, max_generations=100, 
                             crossover_rate=0.8, mutation_rate=0.01, k=3):
    """
    Função geradora que, a cada geração, 'yield' (generation, best_fitness_global, avg_fitness, min_fitness, std_fitness).
    """
    num_items = len(items)
    population = generate_initial_population(pop_size, num_items)

    best_solution = None
    best_fitness_val = 0

    for generation in range(max_generations):
        population_fitness = [fitness(ind, items, W) for ind in population]

        gen_best_fitness = max(population_fitness)
        gen_min_fitness  = min(population_fitness)
        gen_avg_fitness  = statistics.mean(population_fitness)
        gen_std_fitness  = statistics.pstdev(population_fitness)  # pop stdev

        if gen_best_fitness > best_fitness_val:
            best_fitness_val = gen_best_fitness
            best_solution = population[population_fitness.index(gen_best_fitness)]

        # Retornamos (yield) os dados desta geração
        yield {
            "generation": generation,
            "best_fitness": best_fitness_val,
            "avg_fitness": gen_avg_fitness,
            "min_fitness": gen_min_fitness,
            "std_fitness": gen_std_fitness
        }

        # Gera nova população
        new_population = []
        while len(new_population) < pop_size:
            parent1 = tournament_selection(population, items, W, k)
            parent2 = tournament_selection(population, items, W, k)
            if random.random() < crossover_rate:
                child1, child2 = single_point_crossover(parent1, parent2)
            else:
                child1, child2 = parent1[:], parent2[:]
            child1 = mutation(child1, mutation_rate)
            child2 = mutation(child2, mutation_rate)
            new_population.append(child1)
            if len(new_population) < pop_size:
                new_population.append(child2)
        population = new_population