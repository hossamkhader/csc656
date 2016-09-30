package csc656;

/**
 * Test type checker.
 */
public class TypeCheckerDriver {
    public static void main(String[] args) {
        TypeChecker checker = new TypeChecker("100011", 4);
        System.out.println(checker.getType().toString());
    }
}
