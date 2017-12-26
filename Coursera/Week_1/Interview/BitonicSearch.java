/**
 * Search in a bitonic array
 *
 * An array is bitonic if it is comprised of an increasing sequence 
 * of integers followed immediately by a decreasing sequence of integers.
 * Write a program that, given a bitonic array of n distinct integer values, 
 * determines whether a given integer is in the array.
 */

import edu.princeton.cs.algs4.StdOut;

public class BitonicSearch {
    public static int findMax(int[] a, int start, int end) {
        int mid = (start + end) / 2;
        if (start == end)
            return mid;
        else if (a[mid] < a[mid+1])
            return findMax(a, mid+1, end);
        else
            return findMax(a, start, mid);
    }

    public static int binarySearch(int key, int[] a, int lo, int hi, int mod) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key > a[mid]) {
                if (mod == 1) 
                    lo = mid + 1;
                else
                    hi = mid - 1;
            } else if (key < a[mid]) {
                if (mod == 1)
                    hi = mid - 1;
                else
                    lo = mid + 1;
            } else
                return mid;
        }
        return -1;
    }

    public static int search(int key, int[] a) {
        int i;
        int max = findMax(a, 0, a.length-1);
        if (key == a[max])
            return max;
        else if ((i = binarySearch(key, a, 0, max-1, 1)) != -1)
            return i;
        else if ((i = binarySearch(key, a, max+1, a.length-1, 0)) != 1)
            return i;
        else 
            return -1;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 15, 10, 9, 8, 7, 6};
        int key = 0;
        StdOut.println(key + ": " + search(key, a));
        key = 3;
        StdOut.println(key + ": " + search(key, a));
        key = 15;
        StdOut.println(key + ": " + search(key, a));
        key = 6;
        StdOut.println(key + ": " + search(key, a));
        key = 20;
        StdOut.println(key + ": " + search(key, a));
    }
}
    
            
