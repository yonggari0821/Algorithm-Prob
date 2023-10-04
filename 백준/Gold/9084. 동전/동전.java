import java.io.*;
import java.util.*;

public class Main {
	static int n, k;
	static int[] coins;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) 
		{
			int N = Integer.parseInt(br.readLine());
			int[] coins = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) coins[i] = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(br.readLine());
			int[] dp = new int[M + 1];
			for (int coinNum = 0; coinNum < N; coinNum++)
			{
				if (coins[coinNum] <= M) dp[coins[coinNum]]++;
				for (int price = coins[coinNum] + 1; price <= M; price++) dp[price] += dp[price - coins[coinNum]];
			}
			ans.append(dp[M]);
			ans.append('\n');
		}

		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}