/* 练习　1.1.22 */
/* BinarySearch */

import java.util.Arrays;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class Ex1122 {
    public static int rank(int key, int[]a, int lo, int hi, int depth) {
        int n = depth;
        if (lo > hi) {
            return -1;
        }
        int mid = lo + (hi - lo) / 2;
        while (n-- > 0)
                System.out.print(" ");
        System.out.printf("lo: %d, hi: %d, mid: %d", lo, hi, mid);
        System.out.println();

        if (key < a[mid])
            return rank(key, a, lo, mid-1, depth+1);
        else if (key > a[mid])
            return rank(key, a, mid+1, hi,depth+1);
        else 
            return mid;
    }

    public static void main(String[] args) {
        int[] whitelist = In.readInts(args[0]);

        Arrays.sort(whitelist);
        
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (rank(key, whitelist, 0, whitelist.length-1, 0) == -1)
                StdOut.println(key);
        }
    }
}


