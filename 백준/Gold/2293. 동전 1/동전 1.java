import java.io.*;
import java.util.*;

public class Main {
	static int N, K;

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
		int[] coins = new int[N];
		int[] dp = new int[K + 1];
		dp[0] = 1;
		for (int coinNums = 0; coinNums < N; coinNums++) coins[coinNums] = Integer.parseInt(br.readLine());
		for (int coinNums = 0; coinNums < N; coinNums++)
		{
			if (coinNums == 0)
			{
				for (int k = 0; k <= K; k++) if (k % coins[coinNums] == 0) dp[k] = 1;
			}
			else
			{
				for (int k = coins[coinNums]; k <= K; k++)
				{
					dp[k] += dp[k - coins[coinNums]];
				}
			}
//			System.out.println(coins[coinNums]);
//			System.out.println(Arrays.toString(dp));
		}
		ans.append(dp[K]);
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}