package io.github.aronmorris;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Enter digit for value of Fibonacci at that point, or -1 to exit.");

        Scanner sc = new Scanner(System.in);

        while (true) {

            int n = sc.nextInt();

            if (n == -1) {
                break;
            }

            BigDecimal fib = fibonacci(n);

            String out = fib.toString();

            DecimalFormat formatter = new DecimalFormat("#,###");

            System.out.println("Input: " + n + "\n" +
                    "Fibonacci result: " + formatter.format(fib));

        }

    }

    static BigDecimal fibonacci(int n) {
        //Iterative to avoid overflows for large Fibonacci
        BigDecimal x = new BigDecimal(0);
        BigDecimal y = new BigDecimal(1);
        BigDecimal z = new BigDecimal(1);

        for (int i = 0; i < n; i++) {
            x = y; //x = 0, 1, 1, 2, 3, 5...
            y = z; //y = 1, 1, 2, 3, 5...
            z = x.add(y);
        }
        return x;
    }
}
