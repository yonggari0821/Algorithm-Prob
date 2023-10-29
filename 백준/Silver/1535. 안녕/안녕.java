import java.io.*;
import java.util.*;

/*
1535번 풀이
주어지는 체력과 기쁨을 고려해서 체력 100 내에서 최대한의 기쁨을 얻어야 함
=> 배낭 문제

배낭 문제 해결법


 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] hpRequiredAndHappinessAcquired = new int[N + 1][2];
		// 잃는 체력
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) hpRequiredAndHappinessAcquired[i][0] = Integer.parseInt(st.nextToken());
		// 얻는 기쁨
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) hpRequiredAndHappinessAcquired[i][1] = Integer.parseInt(st.nextToken());
		Arrays.sort(hpRequiredAndHappinessAcquired, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(hpRequiredAndHappinessAcquired[i]));
//		}
//		System.out.println("-------------------");
		int[][] getMaximalHappiness = new int[N + 1][101];
		for (int n = 1; n <= N; n++)
		{
			for (int i = 100; i > Math.max(0, 100 - hpRequiredAndHappinessAcquired[n][0]); i--) {
				getMaximalHappiness[n][i] = getMaximalHappiness[n - 1][i];
			}
			for (int i = 100 - hpRequiredAndHappinessAcquired[n][0]; i >= 1; i--)
			{
				getMaximalHappiness[n][i] = Math.max(getMaximalHappiness[n - 1][i + hpRequiredAndHappinessAcquired[n][0]] + hpRequiredAndHappinessAcquired[n][1], getMaximalHappiness[n - 1][i]);
			}
		}
//		for (int i = 0; i <= N; i++) {
//			System.out.println(Arrays.toString(getMaximalHappiness[i]));
//		}
		ans.append(getMaximalHappiness[N][1]);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}

/*
4
98 1 1 1
10 200 10 1

 */