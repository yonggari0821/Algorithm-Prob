import java.io.*;
import java.util.*;

/*
[1459]_걷기_안상준

가로 또는 세로로 가는 데 걸리는 시간인 W와
대각선으로 가는데 걸리는 시간인 S를 비교해서
W >= S 이면
가로 + 세로의 값이 홀수냐 짝수냐에 따라서
홀수 : (가로 세로 중 더 큰 값 - 1) * S + W
짝수 : (가로 세로 중 더 큰 값) * S
2W >= S이면 가로 세로 중 더 작은 값까지는 S로 가고 나머지만 W로 가기
2W < S이면 그냥 가로 세로 더한 값에 W 곱하기

 */

public class Main {


    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder ans = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        long X = Long.parseLong(st.nextToken());
        long Y = Long.parseLong(st.nextToken());
        long W = Long.parseLong(st.nextToken());
        long S = Long.parseLong(st.nextToken());
        long bigger = Math.max(X, Y);
        long smaller = Math.min(X, Y);
        long res = 0;
        if (W > S)
        {
            if (((X + Y) & 1) != 0) ans.append((bigger - 1) * S + W);
            else ans.append((bigger * S));
        }
        else if (2 * W >= S) ans.append(res + smaller * S + (bigger - smaller) * W );
        else ans.append( (bigger + smaller) * W);
        System.out.println(ans.toString());
    	br.close();
    }
}

/*

0 1 2 3 4 5
1 2 3 4 5 6
2 3 4 5 6 7
3 4 5 6 7 8
4 5 6 7 8 9
 */