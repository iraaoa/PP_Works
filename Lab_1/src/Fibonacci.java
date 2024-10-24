/**
 * The Fibonacci class calculates the Fibonacci number
 * for a given index, including negative indices.
 */
    public class Fibonacci {
    private int index;
    private int value;

    /**
     * Constructor to initialize the calculation of the Fibonacci number
     * for the given index.
     *
     * @param N the index (can be positive or negative).
     */
    public Fibonacci(int N) {
        index = N;
        value = calculate(N);
    }

    /**
     * Returns the Fibonacci value for the current index.
     *
     * @return the Fibonacci value.
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the index used to calculate the Fibonacci number.
     *
     * @return the index.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Calculates the Fibonacci number for the given index.
     * Uses different methods for positive and negative indices.
     *
     * @param index the index of the Fibonacci number.
     * @return the Fibonacci number for the given index.
     */
    public int calculate(int index) {
        if (index < 0) {
            return fibonacciNegative(index);
        } else {
            return fibonacciPositive(index);
        }
    }

    /**
     * Calculates the Fibonacci number for a positive index.
     * Uses an iterative approach.
     *
     * @param n the positive index.
     * @return the Fibonacci number.
     */
    private int fibonacciPositive(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int first = 0;
        int second = 1;
        for (int i = 2; i <= n; i++) {
            int next = first + second;
            first = second;
            second = next;
        }
        return second;
    }

    /**
     * Calculates the Fibonacci number for a negative index
     * using the formula F(-n) = (-1)^(n+1) * F(n).
     *
     * @param n the negative index.
     * @return the Fibonacci number for the negative index.
     */
    private int fibonacciNegative(int n) {
        int positiveN = -n;
        int positiveFibonacci = fibonacciPositive(positiveN);

        if (positiveN % 2 == 0) {
            return -positiveFibonacci;
        } else {
            return positiveFibonacci;
        }
    }
}
