import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[N + 1][10]; // 0으로 시작하는 갯수부터 9로 시작하는 갯수까지
		for (int i = 0; i < 10; i++) dp[1][i] = 1;
		for (int i = 2; i <= N; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				for (int k = j; k < 10; k++)
				{
					dp[i][j] += dp[i - 1][k];
					if (dp[i][j] > 10007) dp[i][j] %= 10007;
				}
			}
		}
		int num = 0;
		for (int i = 0; i < 10; i++) num += dp[N][i];
		ans.append(num % 10007);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}