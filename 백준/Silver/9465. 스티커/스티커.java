import java.io.*;
import java.util.*;

/*
[DP 9465]_스티커_안상준

스티커 2n 개
스티커 한 개를 뗄 경우 해당 스티커 상하좌우에 이어진 스티커는 못 쓰게 됨!
각 스티커별로 점수가 있을 때 최대로 점수를 얻게끔 스티커를 뜯었을 때 얻는 최댓값을 출력

상하좌우로 인접한 스티커를 못 쓰게 됨 == 2칸 이상부터는 쓸 수 있음
(2칸 전 두 라인 모두 or 1칸 전 다른 라인) + 현재 라인의 최댓값을 저장해 갈 것!


 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++)
		{
			int n = Integer.parseInt(br.readLine());
			int[][] stickers = new int[n + 1][2];
			for (int i = 0; i < 2; i++)
			{
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= n; j++) stickers[j][i] = Integer.parseInt(st.nextToken());
			}
			int[][] dp = new int[n + 1][2];
			dp[1][0] = stickers[1][0];
			dp[1][1] = stickers[1][1];
			for (int i = 2; i <= n; i++)
			{
				dp[i][0] = Math.max( Math.max(dp[i - 2][0], dp[i - 2][1]) , dp[i - 1][1] ) + stickers[i][0];
				dp[i][1] = Math.max( Math.max(dp[i - 2][0], dp[i - 2][1]) , dp[i - 1][0] ) + stickers[i][1];
			}
			ans.append( Math.max( Math.max(dp[n-1][0], dp[n-1][1]) , Math.max(dp[n][0] , dp[n][1]) ) );
			ans.append('\n');
		}
		System.out.println(ans.toString());
		br.close();
	}
}