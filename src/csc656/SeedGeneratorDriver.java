package csc656;

/**
 * Created by ken12_000 on 9/19/2016.
 */
public class SeedGeneratorDriver {
    public static void main(String[] args) {
        SeedGenerator generator = new SeedGenerator();
        Seed seed;

        seed = generator.generateSeed(4, 1);

        System.out.println(seed.toString());
    }
}
