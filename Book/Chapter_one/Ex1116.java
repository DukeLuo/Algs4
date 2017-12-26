/* 练习　1.1.16 */

public class Ex1116 {
    public static String exR1(int n) {
        if (n <= 0)
            return "";
        else
            return exR1(n-3) + n + exR1(n-2) + n;
    }

    public static void main(String[] args) {
        System.out.println(exR1(6));
    }
}


