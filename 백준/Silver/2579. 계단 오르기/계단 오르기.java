import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		int N = Integer.parseInt(br.readLine()); // 계단 갯수 300 이하의 자연수이므로 최소 1
		int[] stairs = new int[N + 1]; // 계단 밟았을 때 얻는 점수 배열
		int[] dp = new int[N + 1]; // dp용 배열 => 해당 계단을 밟았을 때까지의 최대 합을 메모
		for (int n = 1; n <= N; n++) stairs[n] = Integer.parseInt(br.readLine());
		dp[1] = stairs[1];
		for (int i = 2; i <= N; i++)
		{
			if (i == 2) dp[i] = Math.max(dp[i - 1], dp[i - 2]) + stairs[i];
			else dp[i] = Math.max(dp[i - 3] + stairs[i - 1], dp[i - 2]) + stairs[i];
		}
//		System.out.println(Arrays.toString(dp));
		ans.append(dp[N]);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}