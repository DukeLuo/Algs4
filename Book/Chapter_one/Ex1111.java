/* 练习　1.1.11 */

public class Ex1111 {
    public static void print(boolean[][] a) {
        int i, j;
        for (i = 0; i < a[0].length; ++i)
            System.out.print(i + "--");
        System.out.println();
        for (i = 0; i < a.length; ++i) {
            for (j = 0; j < a[0].length; ++j)
                System.out.print(a[i][j] ? "*--" : " --");
            System.out.println(); 
        }
    }

    public static void main(String[] args) {
        boolean[][] a = { {true, false, false}, {true, true, true},
                          {false, true, false}, {true, false, true}};
        print(a);
    }
}


