import java.io.*;
import java.util.*;

/*
[풀이]
두 배열의 부분 배열의 합으로 T를 만들 수 있는 쌍의 갯수 구하기
T의 범위 : -10억 ~ 10억
N, M의 범위 : 1 ~ 1000
배열 내의 숫자 값의 범위 : -100만 ~ 100만

두 배열의 부분 배열 모두 만들고, 투 포인터 방식으로 풀기
부분배열의 갯수는 ?
1 2 3 4 [4개]

1
2
3
4
12
23
34
123
234
1234
[10개]

10 = 4 + 3 + 2 + 1
n개의 숫자를 갖는 배열의 부분 배열의 갯수 == n * (n + 1) / 2

배열의 최대 크기가 1000이므로 부분 배열 만들어도 됨!
 */

public class Main {


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		int T = Integer.parseInt(br.readLine()); // T(target)의 범위 : -10억 ~ 10억
		int N = Integer.parseInt(br.readLine()); // N(첫 배열의 길이) 의 범위 : 1 ~ 1000
		int[] arrN = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) arrN[n] = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(br.readLine()); // M(둘째 배열의 길이) 의 범위 : 1 ~ 1000
		int[] arrM = new int[M + 1];
		st = new StringTokenizer(br.readLine());
		for (int m = 1; m <= M; m++) arrM[m] = Integer.parseInt(st.nextToken());
		// 배열 내의 숫자 값의 범위 : -100만 ~ 100만

		// 부분 배열 합의 배열
		int[] subN = new int[N * (N + 1) / 2];
		int[] subM = new int[M * (M + 1) / 2];

		// 부분 배열 세팅 1
		int idx = 0;
		for (int n = 1; n < arrN.length; n++)
		{
			subN[idx++] = arrN[n];
			for (int i = n + 1; i < arrN.length; i++)
			{
				subN[idx] = subN[idx-1] + arrN[i];
				idx++;
			}
		}
		Arrays.sort(subN);

		// 부분 배열 세팅 2
		idx = 0;
		for (int m = 1; m < arrM.length; m++)
		{
			subM[idx++] = arrM[m];
			for (int i = m + 1; i < arrM.length; i++)
			{
				subM[idx] = subM[idx-1] + arrM[i];
				idx++;
			}
		}
		Arrays.sort(subM);


		// 부분 배열 투 포인터
		long Tcnt = 0;
		int nidx = 0;
		int midx = subM.length - 1;
		while (nidx < subN.length && midx >= 0)
		{
			int sum = subN[nidx] + subM[midx];
			if (sum == T)
			{
				int tmpNidx = nidx;
				int tmpMidx = midx;
				long tmpNcnt = 0;
				long tmpMcnt = 0;
				while (tmpNidx < subN.length && subN[tmpNidx] == subN[nidx])
				{
					tmpNcnt++;
					tmpNidx++;
				}
				nidx = tmpNidx;
				while (tmpMidx >= 0 && subM[tmpMidx] == subM[midx])
				{
					tmpMcnt++;
					tmpMidx--;
				}
				midx = tmpMidx;
				Tcnt += tmpNcnt * tmpMcnt;
			}
			else if (sum < T) nidx++;
			else midx--;
		}
		ans.append(Tcnt);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}