/* 练习　1.1.20 */
/* ln(N!) */

public class Ex1120 {
    public static int factorial(int n) {
        if (n == 1 || n == 0)
            return 1;
        else 
            return n * factorial(n-1);
    }
    
    public static void main(String[] args) {
        int i = Integer.parseInt(args[0]);
        System.out.println(Math.log(factorial(i)));
    }
}


