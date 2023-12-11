import java.io.*;
import java.util.*;

/*
[배낭문제 1106]_호텔_안상준
호텔 고객을 적어도 C명 늘리기 위해서 형택이가 투자해야 하는 돈의 최솟값을 구해야 함
각 도시별로 홍보 비용 및 얻을 수 있는 고객의 수가 주어짐
홍보 비용은 최소화하면서 고객의 수는 최대화 해야 함 => 이렇게 풀면 틀림

각



 */


public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] dp = new int[C + 1 + 100]; // 구하려는 최소 인원 수 + 1 + 100 만큼의 공간을 확보해줌
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			int client = Integer.parseInt(st.nextToken());
			for (int j = client; j < C + 101; j++)
			{
				if (dp[j - client] != Integer.MAX_VALUE) dp[j] = Math.min( dp[j], dp[j - client] + cost );
			}
		}
		int res = Integer.MAX_VALUE;
		// dp 배열이 드는 비용이 아니라 얻는 고객 수를 기준으로 만들어져 있으므로 C 이상에서 가능한 값들 중 최소값을 구해야 답!
		for (int i = C; i < C + 101; i++) res = Math.min(res, dp[i]);
		ans.append(res);
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}