import java.io.*;
import java.util.*;

/*

i =    0 1 2 3 4 5 6 7
A[i] = 1 2 3 4 5 5 7 7

n의 범위가 10의 12승이므로 일반적인 배열을 활용한 다이나믹 프로그래밍은 불가능
그러나 p와 q가 2이상이므로
log 2 n와 log 3 n을 씌운다면 훨씬 적은 계산으로 계산 가능!
맵을 활용할 것!


 */

public class Main {
    static long n, p, q;
    static HashMap<Long, Long> map = new HashMap<>();
    static long func(long cur) {
        if (!map.containsKey(cur)) map.put(cur, func(cur / p) + func(cur / q));
        return map.get(cur);
    }

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Long.parseLong(st.nextToken()); // 최소 0 // 최대 10의 12승
        p = Long.parseLong(st.nextToken());
        q = Long.parseLong(st.nextToken());

        map.put(0L, 1L);

        System.out.println(func(n));
    }
}