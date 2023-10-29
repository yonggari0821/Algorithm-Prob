import java.io.*;
import java.util.*;
/*
10986 풀이
연속된 구간의 합이 M으로 나누어 떨어지는 구간의 개수 구하기
숫자들을
1) 누적합 해놓고 구간 합 구해서 나눌것인가?
2) 누적합 할 때 아예 M으로 나누면서 할 것인가?
// 안의 수들은 최대 10억까지 => 3개부터 바로 int형 범위 벗어남 => 더 할 때 마다 M으로 나눌 것인가? 아니면 long으로 할 것인가?

[예시]
1 2 3 1 2

[그냥 누적합 방식]
1 3 6 7 9

[누적합 with 나눗셈]
1 3 6 0 2

2~4번째 구간합
그냥 누적합 16 => 2
누적합 with 나눗셈 9 => 2
=> 결과는 같음

그럼 더 연산이 적은 1)번 방식이 유리
단 1번 방식으로 할 경우 누적합 배열은 무조건 Long으로 만들어야 함!

구간 합
이 중 for문으로
left랑 right를 결정해서 right에서 left - 1값을 빼서 구간 합 구하기
left 1 ~ N
right 1 ~ N
=> N 제곱

그런데 N의 범위가 10만이라서 제곱하면 1000억이 되어버림 => 불가능

따라서, 모든 구간합을 고려할 수 없음
누적합 with 나눗셈 & 슬라이딩 윈도우 방식을 고려해보자
=> 슬라이딩 윈도우는 이럴 때 쓰는 방법이 아님!

결국 아래 힌트 참고 함
1) (S[j] − S[i−1]) % M = 0
2) (S[j] % M) − (S[i−1] % M) = 0
3) S[j] % M = S[i−1] % M
=> M은 범위가 1000이므로 M으로 나눈 나머지를 기준으로 무언가를 기록해두면 될 거 같음

누적합 배열을 만들면서 아예 M으로 나눠가면서 기록해두고
1) 0의 갯수 => 자체적으로 M으로 나눴을 때 나누어 떨어지는 애들 세주기
2) m을 0 ~ M - 1 범위안에서 하나씩 늘려가면서 갯수 세기 (1000 x 100000) => 1억이므로 연산 횟수 괜찮음
3) 2번에서 세놓은 갯수를 기준으로 구할 수 있는 쌍의 갯수를 구해서 더해주기
2개 => 1쌍
3개 => 3쌍
4개 => 6쌍
.
.
.
n개 => n x (n - 1) / 2 쌍
 */


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		long cnt = 0;
		int N = Integer.parseInt(st.nextToken()); // 최대 100000
		int M = Integer.parseInt(st.nextToken()); // 최대 1000
		int[] dp = new int[N + 1];
		long[] remain = new long[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			dp[i] = (dp[i - 1] + Integer.parseInt(st.nextToken())) % M; // 이렇게 하면 dp 배열의 값은 죽어도 M을 넘을 수 없음
			if (dp[i] == 0) cnt++;
			remain[dp[i]]++;
		}
		for (int m = 0; m < M; m++) cnt += (remain[m] * (remain[m] - 1)) / 2;
		ans.append(cnt);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}