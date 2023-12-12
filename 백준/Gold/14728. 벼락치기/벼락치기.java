import java.io.*;
import java.util.*;

/*
[배낭문제 14728]_벼락치기_안상준
1. 한 단원 당 한 문제
2. 여러 단원이 융합된 문제는 출제 X

각 단원 문제를 맞추려면 해당 단원의 예상 공부 시간 이상 공부해야 함




 */


public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 단원 갯수
		int T = Integer.parseInt(st.nextToken()); // 시험 전까지 공부할 수 있는 시간
		int[][] dp = new int[N][T + 1]; // 최대 공부할 수 있는 시간을 포함할 수 있도록 만들어주기
		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int timeToStudy = Integer.parseInt(st.nextToken());
			int points = Integer.parseInt(st.nextToken());
			if (i == 0) // 첫 단원의 문제의 경우 바로 해당 포인트로 저장 // 한 단원 당 한 문제이므로 누적합 형식이 아니라 모두 points로 저장하는 게 맞음
			{
				for (int j = timeToStudy; j <= T; j++) dp[i][j] = points;
			}
			else
			{
				// 관여할 수 없는 부분은 그냥 전의 결과 받아오고
				for (int j = 0; j <= Math.min(timeToStudy, T); j++) dp[i][j] = dp[i - 1][j];
				// 관여가 가능한 부분 부터는 현재 가능한 값과 비교해서 리뉴얼
				for (int j = timeToStudy; j <= T; j++)
				{
					dp[i][j] = Math.max(dp[i - 1][j - timeToStudy] + points, dp[i - 1][j]);
				}
			}
		}
		ans.append(dp[N - 1][T]);
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}