/* 练习　1.1.14 */
/* 静态方法　lg() */

public class Ex1114 {
    public static int lg(int n) {
        int i = 1;
        int j = 0;
        for ( ; i <= n; i *= 2)
            ++j;
        return (i > n) ? j-1 : j;
    }    

    public static void main (String[] args) {
        int n = Integer.parseInt(args[0]);
        System.out.println(lg(n));
    }
}

        
