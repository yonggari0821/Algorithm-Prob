import java.io.*;
import java.util.*;

public class Main {
//	static long[][] dp = new long[10000001][3];
	static long[] dp = new long[10000001];
	static {
//		dp[1][0] = 1; // 0 + 1
//		dp[2][0] = 1; // 1 + 1
//		dp[2][1] = 1; // 0 + 2
//		dp[3][0] = 2; // 1 + 1 + 1 // 2 + 1
//		dp[3][1] = 1; // 1 + 2
//		dp[3][2] = 1; // 3 + 0
//
//		for (int i = 4; i <= 1000000 ; i++)
//		{
//			dp[i][0] = ((dp[i - 1][0] + dp[i - 1][1]) + dp[i - 1][2]) % 1000000009;
//			dp[i][1] = ((dp[i - 2][0] + dp[i - 2][1]) + dp[i - 2][2]) % 1000000009;
//			dp[i][2] = ((dp[i - 3][0] + dp[i - 3][1]) + dp[i - 3][2]) % 1000000009;
//		}
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		for (int i = 4; i <= 1000000 ; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 1000000009;
		}
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		// System.out.println(dp[1][0] + dp[1][1] + dp[1][2]); // 1
		// System.out.println(dp[2][0] + dp[2][1] + dp[2][2]); // 2
		// System.out.println(dp[3][0] + dp[3][1] + dp[3][2]); // 3
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++)
		{
			int N = Integer.parseInt(br.readLine());
			ans.append(dp[N]).append('\n');
		}
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}