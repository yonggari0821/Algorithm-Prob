import java.io.*;
import java.util.*;
public class Main {
    static StringBuilder ans = new StringBuilder();
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        if (N == 1)
        {
            System.out.println(0);
            return ;
        }
        int[] dp = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++)
        {
            int tmp = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= tmp; j++)
            {
                if (i + j > N) break;
                if (dp[i + j] == 0) dp[i + j] = dp[i] + 1;
                else dp[i + j] = dp[i + j] < dp[i] + 1 ? dp[i + j] : dp[i] + 1;
            }
            if (i != 1 && dp[i] == 0)
            {
                System.out.println(-1);
                return ;
            }
        }
//        System.out.println(Arrays.toString(dp));
        if (dp[N] == 0) ans.append(-1);
        else ans.append(dp[N]);
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}
