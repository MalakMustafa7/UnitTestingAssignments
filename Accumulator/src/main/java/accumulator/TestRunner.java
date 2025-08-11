package accumulator;

public class TestRunner {
    int total, succeeded, failed;

    public void execute(String name, Runnable test) {
        try {
            test.run();
            succeeded++;
        } catch (RuntimeException ex) {
            failed++;
            System.out.println("Failed test case: " + name + ": " + ex.getMessage());
        }
        total++;
    }

    public void printSummary() {
        System.out.println("Testing completed total: " + total + " succeeded: " + succeeded + " failed: " + failed);
    }

}
