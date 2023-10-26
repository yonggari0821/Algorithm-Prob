import java.io.*;
import java.util.*;

/*
같은 수를 연속해서 두 번 이상 사용해서는 안됨

 */

public class Main {
	static int dp[][] = new int[100001][4];
	
	static {
		dp[1][1] = 1;
		dp[2][2] = 1;
		dp[3][1] = 1;
		dp[3][2] = 1;
		dp[3][3] = 1;
		for (int i = 4; i <= 100000; i++) {
			dp[i][1] = (dp[i - 1][2] + dp[i - 1][3]);
			if (dp[i][1] >= 1000000009) dp[i][1] %= 1000000009;
			dp[i][2] = (dp[i - 2][1] + dp[i - 2][3]);
			if (dp[i][2] >= 1000000009) dp[i][2] %= 1000000009;
			dp[i][3] = (dp[i - 3][1] + dp[i - 3][2]);
			if (dp[i][3] >= 1000000009) dp[i][3] %= 1000000009;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++)
		{
			int n = Integer.parseInt(br.readLine());
			if (n <= 2) ans.append(1); // 1 // 2
			else if (n == 3) ans.append(3); // 2 + 1 // 1 + 2 // 3
			else ans.append( (  (dp[n][1] + dp[n][2]) % 1000000009 + dp[n][3]  ) % 1000000009 );
			ans.append('\n');
		}
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}