import java.util.Scanner;

/**
 * The Main class is the entry point of the program.
 * It prompts the user to input an integer index and calculates
 * the Fibonacci number for that index.
 */
public class Main {

    /**
     * The main method reads the index from the user and displays the
     * corresponding Fibonacci number.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the index (integer): ");
        int index = scanner.nextInt();

        Fibonacci fibonacciNumber = new Fibonacci(index);

        System.out.printf("For index %d, the value is %d%n", index, fibonacciNumber.getValue());
    }
}
