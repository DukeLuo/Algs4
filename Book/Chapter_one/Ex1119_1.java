/* 练习　1.1.19 */
/* Fibonacci使用记忆算法　*/

import edu.princeton.cs.algs4.StdOut;

public class Ex1119_1 {

    public static long F(int N, long[] f) {
        if (f[N] == 0) {
            if (N == 0)
                ;
            else if (N == 1)
                f[N] = 1;
            else
                f[N] = F(N-1, f) + F(N-2, f);
        }
        return f[N];
    }
    
    public static void main(String[] args) {
        for (int N = 0; N < 100; N++)
            StdOut.println(N + " " + F(N, new long[N+1]));
    }
}


