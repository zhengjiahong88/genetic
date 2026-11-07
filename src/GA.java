/* GA parameters */
private static final int TOURNAMENT_SIZE = 5, POPULATION_SIZE = 50;
private static final boolean ELITISM = true;
private static Population population = new Population(POPULATION_SIZE, true);

// Select individuals for crossover
private static Chromosome tournamentSelection() {
    // Create a tournament population
    Population tournament = new Population(TOURNAMENT_SIZE, false);
    // For each place in the tournament get a random individual
    for (int i = 0; i < TOURNAMENT_SIZE; ++i) tournament.set(i, population.get((int) (Math.random() * POPULATION_SIZE)));
    // Get the fittest
    return tournament.getFittest();
}

void main() {
    // Create an initial population
    // Evolve our population until we reach an optimum solution
    Chromosome fittest = population.getFittest();
    int generationCount = 0;
    for (; fittest.getFitness() > Integer.MAX_VALUE; fittest = population.getFittest()) {
        if (++generationCount % 2048 == 0) IO.println("Generation: " + generationCount + " Fittest: " + fittest.getFitness());
        Population newPopulation = new Population(POPULATION_SIZE, false);
        // Keep our best individual
        if (ELITISM) newPopulation.set(0, fittest);
        // Crossover population
        int elitismOffset = ELITISM ? 1 : 0;
        // Create new chromosomes with crossover
        for (int i = elitismOffset; i < POPULATION_SIZE; ++i)
            newPopulation.set(i, new Chromosome(tournamentSelection(), tournamentSelection()));
        population = newPopulation;
    }
    IO.println("Solution found!");
    IO.println("Generation: " + generationCount);
    IO.println("Genes:" + fittest);
}