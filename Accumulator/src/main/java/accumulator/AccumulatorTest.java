package accumulator;

public class AccumulatorTest {

    public static void main(String[] args) {


        TestRunner testRunner = new TestRunner();

        testRunner.execute("Providing value greater than limit", () -> {
            Accumulator accumulator = new Accumulator();
            int result = accumulator.add(20);
            System.out.println("Value = " + result);
            if (result != 0) {
                throw new RuntimeException("should return 0 but returned " + result);
            }
        });


        testRunner.execute("Providing value less than limit", () -> {
            Accumulator accumulator = new Accumulator();
            int result = accumulator.add(5);
            System.out.println("Value = " + result);
            if (result != 5) {
                throw new RuntimeException("should return 5 but returned " + result);
            }
        });

        testRunner.execute("Providing value greater than limit #2", () -> {
            Accumulator accumulator = new Accumulator();
            int result = accumulator.add(5);
            result = accumulator.add(6);
            System.out.println("Value = " + result);
            if (result != 1) {
                throw new RuntimeException("should return 1 but returned " + result);
            }
        });

        testRunner.execute("Providing value greater than limit #3", () -> {
            // Arrange
            Accumulator accumulator = new Accumulator();
            int result = accumulator.add(5);

            // Act
            result = accumulator.add(6);

            // Assert
            if (result != 1) {
                throw new RuntimeException("should return 1 but returned " + result);
            }
        });

        testRunner.printSummary();
    }

}
