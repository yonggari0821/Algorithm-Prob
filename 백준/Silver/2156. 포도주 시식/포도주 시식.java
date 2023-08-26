import java.io.*;
import java.util.*;
public class Main {
    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] dp0 = new int[N+1]; // 현재 잔 안 골랐을 때의 최댓값 == 전에 1개 골랐거나 2개 골랐던 거 중 큰 거
        int[] dp1 = new int[N+1]; // 현재 잔 고를 건데 바로 전의 잔 안 골랐을 때의 최댓값 ==
        int[] dp2 = new int[N+1]; // 현재 잔 고를 건데 바로 전의 값도 골랐을 때의 최댓값 == 무조건 전전 값은 안 골랐어야 함
        for (int i = 1; i <= N; i++)
        {
            int tmp = Integer.parseInt(br.readLine());
            if (i == 1)
            {
                dp0[i] = 0;
                dp1[i] = tmp;
                dp2[i] = 0;
            }
            else if (i == 2)
            {
                dp0[i] = dp1[i-1];
                dp1[i] = tmp;
                dp2[i] = dp1[i-1] + tmp;
            }
            else // i >= 3
            {
                dp0[i] = Math.max(dp2[i-1], dp1[i-1]);
                dp1[i] = tmp + Math.max(dp0[i-2],Math.max(dp1[i-2], dp2[i-2]));
                dp2[i] = dp1[i-1] + tmp;
            }
        }
//        System.out.println(Arrays.toString(dp0));
//        System.out.println(Arrays.toString(dp1));
//        System.out.println(Arrays.toString(dp2));
        ans.append(Math.max(dp0[N],Math.max(dp1[N], dp2[N])));
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}

/*
반례
10
0
0
10
0
5
10
0
0
1
10

ans : 36
wrong output : 35

 */