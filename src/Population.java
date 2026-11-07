final class Population {
    private final Chromosome[] chromosomes;

    Population(int populationSize, boolean initialise) {
        chromosomes = new Chromosome[populationSize];
        if (initialise) for (int i = 0; i < populationSize; ++i) chromosomes[i] = new Chromosome();
    }

    Chromosome get(int index) {
        return chromosomes[index];
    }

    void set(int index, Chromosome chrom) {
        chromosomes[index] = chrom;
    }

    Chromosome getFittest() {
        Chromosome fittest = chromosomes[0];
        for (Chromosome chromosome : chromosomes)
            if (fittest.getFitness() > chromosome.getFitness()) fittest = chromosome;
        return fittest;
    }
}