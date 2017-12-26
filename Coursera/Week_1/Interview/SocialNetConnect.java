/* Social network connectivity　*/

/***************************************************
$ more SocialNetwork.log
0 1 2015-08-14 18:00:00
1 9 2015-08-14 18:01:00
0 2 2015-08-14 18:02:00
0 3 2015-08-14 18:04:00
0 4 2015-08-14 18:06:00
0 5 2015-08-14 18:08:00
0 6 2015-08-14 18:10:00
0 7 2015-08-14 18:12:00
0 8 2015-08-14 18:14:00
1 2 2015-08-14 18:16:00
1 3 2015-08-14 18:18:00
1 4 2015-08-14 18:20:00
1 5 2015-08-14 18:22:00
2 1 2015-08-14 18:24:00
2 3 2015-08-14 18:26:00
2 4 2015-08-14 18:28:00
5 5 2015-08-14 18:30:00
$ java-algs4 SocialNetConnect 10 < SocialNetwork.log
2015-08-14 18:14:00
***************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.UF;

public class SocialNetConnect {
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        UF uf = new UF(N);
        String date, time;
        int p, q;
        
        while (!StdIn.isEmpty()) {
            p = StdIn.readInt();
            q = StdIn.readInt();
            date = StdIn.readString();
            time = StdIn.readString();
        
            uf.union(p, q);
            if (uf.count() == 1) {
                StdOut.println(date + " " + time);
                break;
            }
        }
    }
}
            
        
