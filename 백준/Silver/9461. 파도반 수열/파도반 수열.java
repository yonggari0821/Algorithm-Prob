import java.io.*;
import java.util.*;

/*
[9461]_파도반 수열_안상준

0 1 2 3 4 5 6 7 8 9
1 1 1 2 2 3 4 5 7 9

나선으로 놓이는 정삼각형의 모양을 잘 보면
위로 향함 => 아래로 향함이 반복됨
변이 맞닿아야 놓을 수 있기 때문인데
맞닿는 면의 길이는 항상 규칙성을 가지고 그 규칙성은 아래와 같다.

0번째 수 + 1번째 수 == 3번째 수
1번째 수 + 2번째 수 == 4번째 수
2번째 수 + 3번째 수 == 5번째 수
.
.
.
N-3번째 수 + N-2번째 수 == N번째 수

그 이유는
N번째 삼각형과 같은 곳을 향하는 N-2번째 삼각형의 밑/윗변의 길이와
N번째 삼각형과 다른 곳을 향하는 N-3번째 삼각형의 윗/밑변의 길이의 합이
N번째 삼각형과 N-1번째 삼각형이 맞닿는 변의 길이와 같기 때문이다.

 */


public class Main {
    static long[] spiralTriangles = new long[101];
    static {
        spiralTriangles[1] = 1;
        spiralTriangles[2] = 1;
        spiralTriangles[3] = 1;
        for (int i = 4; i <= 100; i++) spiralTriangles[i] = spiralTriangles[i - 3] + spiralTriangles[i - 2];
    }


    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder ans = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) ans.append(spiralTriangles[Integer.parseInt(br.readLine())]).append('\n');
        System.out.println(ans.toString());
    	br.close();
    }
}

/*

 */