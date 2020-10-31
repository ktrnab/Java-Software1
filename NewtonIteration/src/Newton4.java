import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Prompt the user to enter a number computes the square root using Newton
 * Iteration, user enters epsilon value manually. main program does not ask the
 * user whether they wish to calculate another square root, but instead simply
 * asks for a new value of x and interprets a negative value as an indication
 * that it's time to quit.
 *
 * @author Katrina Busgano
 *
 */
public final class Newton4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton4() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @param epsilon
     *            epsilon number entered by user
     * @return estimate of square root
     */
    private static double sqrt(double x, double epsilon) {
        final double e = epsilon;
        double r = x;

        r = (r + x / r) / 2;

        while (Math.abs(r * r - x) / x >= e * e) {
            r = (r + x / r) / 2;
        }
        return r;

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Put your main program code here; it may call myMethod as shown
         */

        double userNumber;
        double userEpsilon;

        out.println("Enter Epsilon value: ");
        userEpsilon = in.nextDouble();

        out.println("Enter a positive double type number: ");
        userNumber = Double.parseDouble(in.nextLine());

        while (userNumber > 0) {

            double userResult = sqrt(userNumber, userEpsilon);
            out.println(
                    "The square root of " + userNumber + " is: " + userResult);

            out.println();
            out.println("Enter another positive double type number: ");
            userNumber = Double.parseDouble(in.nextLine());

        }

        out.println("Goodbye!");

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
