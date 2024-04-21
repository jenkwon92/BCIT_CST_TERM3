import java.math.BigInteger;

public class driver {

    public static void main(String[] args) {
        Lab6 lab6 = new Lab6();
        // Testing recursive solution
        System.out.println("Recursive Solution:");
        lab6.RunRecursive(0, 5);
        // Testing dynamic programming solution
        System.out.println("\nDynamic Programming Solution:");
        lab6.RunDynamicProg(20, 24);

        // Calculate SW(37, 37)
        BigInteger result37 = lab6.calculateSW(37, 37);
        System.out.println("SW(37, 37) = " + result37.toString());

        BigInteger result3737 = lab6.calculateSW(3737, 3737);
        System.out.println("SW(3737, 3737) = " + result3737.toString());

        // int maxValue = 2; // 배열 크기를 줄임

        // BigInteger calculateLargestSW = lab6.calculateLargestSW(maxValue);

        // System.out.println("SW(" + maxValue + ", " + maxValue + ") = " +
        // calculateLargestSW.toString());
        // System.out.println("Number of digits: " +
        // calculateLargestSW.toString().length());

        // int m = 10000037;
        // int n = 10000037;

        // BigInteger result = lab6.calculateSW(m, n);
        // System.out.println("SW(" + m + ", " + n + ") = " + result.toString());
        // System.out.println("Number of digits: " + result.toString().length());

        // int n = 10000037;
        // int k = 10000037;

        // BigInteger result = lab6.stirlingNumberSecondKind(n, k);
        // System.out.println("SW(10000037, 10000037) = " + result);
        // System.out.println("Number of digits: " + result.toString().length());

        // int n = 10000037;
        // int m = 10000037;

        // long startTime = System.currentTimeMillis();
        // BigInteger result = lab6.vd3calculateSW(n, m);
        // long endTime = System.currentTimeMillis();

        // System.out.println("SW(10000037, 10000037) = " + result);
        // System.out.println("Number of digits: " + result.toString().length());
        // System.out.println("Execution time: " + (endTime - startTime) + "
        // milliseconds");
    }
}
