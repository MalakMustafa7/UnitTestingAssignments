package accumulator;

public class Accumulator {

    private static final int LIMIT = 10;
    int sum = 0;

    public int add(int x) {
        sum = (sum + x) % LIMIT;
        return sum;
    }


}
