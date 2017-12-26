/* 练习　1.1.19 */
/* Fibonacci函数　*/

import edu.princeton.cs.algs4.StdOut;

public class Ex1119 {
    public static long F(int N) {
        if (N == 0)
            return 0;
        if (N == 1)
            return 1;
        return F(N-1) + F(N-2);
    }
    
    public static void main(String[] args) {
        for (int N = 0; N < 100; N++)
            StdOut.println(N + " " + F(N));
    }
}


