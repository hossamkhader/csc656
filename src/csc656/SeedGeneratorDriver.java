package csc656;

/**
 * Class author:  Kenneth Short
 *
 * Driver class to test the SeedGenerator class.
 */
public class SeedGeneratorDriver {
    public static void main(String[] args) {
        SeedGenerator generator = new SeedGenerator();
        Seed seed;

        seed = generator.generateSeed(12, 4);

        System.out.println(seed.toString());
    }
}
