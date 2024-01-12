import java.io.*;
import java.util.*;

/*
[브루트포스 1476]_날짜 계산_안상준

15 / 28 / 19로 나눈 나머지에 해당하는 지구년 / 태양년 / 달년을 가지고
원래의 년도수를 뽑아내야하는 문제

15로 나눈 나머지가 0일때 오히려 15


 */

public class Main {

    static int E, S, M;
    static final int limit = 15 * 28 * 19;

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder ans = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        E = Integer.parseInt(st.nextToken()) % 15; // 15
        S = Integer.parseInt(st.nextToken()) % 28; // 28
        M = Integer.parseInt(st.nextToken()) % 19; // 19
        int num = 1;
        while (num < limit)
        {
            if (num % 15 == E && num % 19 == M && num % 28 == S) break;
            else num++;
        }
        ans.append(num);
        System.out.println(ans.toString());
    	br.close();
    }
}