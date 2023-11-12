import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] grid = new int[N][M];
		int[][] dp = new int[N][M];
		for (int n = 0; n < N; n++)
		{
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) grid[n][m] = Integer.parseInt(st.nextToken());
		}
		for (int r = 0; r < N; r++)
		{
			for (int c = 0; c < M; c++)
			{
				dp[r][c] += grid[r][c];
				if (r > 0 && c > 0)
				{
					dp[r][c] += Math.max(Math.max(dp[r-1][c-1], dp[r-1][c]), dp[r][c-1]);
				}
				else if (r > 0)
				{
					dp[r][c] += dp[r-1][c];
				}
				else if (c > 0)
				{
					dp[r][c] += dp[r][c-1];
				}
			}
		}
		ans.append(dp[N-1][M-1]);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}