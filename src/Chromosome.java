import java.util.Random;

final class Chromosome {
    private static final double MUTATION_RATE = 0.015;
    private static final int GENE_LENGTH = 29;
    private int genes;
    private long fitness;

    Chromosome(Chromosome father, Chromosome mother) {
        for (int j = 0; j < GENE_LENGTH; ++j) {
            boolean gene = Math.random() < 0.5 ? father.get(j) : mother.get(j);
            if (Math.random() < MUTATION_RATE) gene = !gene;
            set(j, gene);
        }
    }

    Chromosome() {
        genes = new Random().nextInt() >>> (32 - GENE_LENGTH);
    }

    boolean get(int index) {
        return ((genes >> index) & 1L) == 1L;
    }

    void set(int index, boolean value) {
        if (value) genes |= 1 << index;
        else genes &= ~(1 << index);
        fitness = 0L;
    }

    long getFitness() {
        if (fitness == 0L) fitness = Math.abs(genes * (20 * (1L << GENE_LENGTH) + genes) - (1L << 2 * GENE_LENGTH));
        return fitness;
    }

    @Override
    public String toString() {
        return "1010." + String.format("%" + GENE_LENGTH + "s", Long.toBinaryString(genes)).replace(' ', '0');
    }
}