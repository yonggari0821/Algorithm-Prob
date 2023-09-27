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
		int[] coins = new int[N]; // 동전 값어치 받아 둘 배열
		int[] dp = new int[K + 1]; // k원까지의 가능한 동전 조합 수 배열
		int[] idx = new int[K + 1]; // k원까지의 가능한 동전 조합 수 배열
		for (int coinNums = 0; coinNums < N; coinNums++) coins[coinNums] = Integer.parseInt(br.readLine());
		Arrays.sort(coins); // 오름차순 정렬
		for (int coinsNum = 0; coinsNum < N; coinsNum++) {
			if (coinsNum == 0)
			{
				// 초기값 세팅 // 주어지는 동전 중 가장 작은 동전 밸류로 나눈 몫으로 세팅
				for (int k = 1; k <= K; k++)
				{
					if (k % coins[coinsNum] == 0) dp[k] = (k / coins[coinsNum]);
					idx[k] = k;
				}
			}
			else // 그 다음 오름차순으로 동전 밸류를 높혀가면서 동전 갯수 줄일 수 있는 곳 동전 갯수 줄이기
			{
				// 친절하게 테케에서 가장 많이 틀릴 수 있는 오류를 잡아줌
				// 동전 밸류가 꼭 그리디하게 주어지지는 않을 것이기 때문에 다음과 같은 비교과정(Math.min)이 필수적!
				// cf) 15원의 경우 1+1+1+12보다 5+5+5가 더 적은 동전을 사용함!!
				// 또한 0인 경우도 고려해 줄 필요가 있음!!
				for (int k = coins[coinsNum]; k <= K; k++)
				{
					if (dp[k] != 0 && dp[k - coins[coinsNum]] != 0) dp[k] = Math.min(dp[k - coins[coinsNum]] + 1, dp[k]);
					else if (dp[k - coins[coinsNum]] == 0)
					{
						if (k % coins[coinsNum] == 0) dp[k] = k / coins[coinsNum]; 
					}
					else dp[k] = dp[k - coins[coinsNum]] + 1;
				}
			}
		}
//		System.out.println(Arrays.toString(idx));
//		System.out.println(Arrays.toString(dp));
		ans.append(dp[K] = dp[K] == 0 ? -1 : dp[K]); // 문제 잘 읽어보면 불가능한 경우 -1 출력하도록 되어 있음!!
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}

/*
[반례]
2 10
2
3
 */