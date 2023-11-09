import java.io.*;
import java.util.*;

/*

[백트래킹 16987]_계란으로 계란치기_안상준 풀이

1 ) 맨 왼쪽 계란 들고 시작
2 ) 현재 든 계란으로 다른 계란 아무거나 치기
3 ) 깨진 계란이 있다면 제외하고 한 칸 오른쪽에 있는 계란 들어가면서 똑같은 과정 반봅
4 ) 가장 마지막 계란을 집어든 경우에는 끝!

모든 계란의 내구도와 무게가 다르므로 완전탐색 + 백트래킹

 */


public class Main {

	static int N;
	static int[] S;
	static int[] W;
	static int maxBreak = 0;

	static void eggToEgg(int curEgg)
	{
		if (curEgg >= N) {
			int tcnt = 0;
			for (int i = 0; i < N; i++)
			{
				if(S[i] <= 0) tcnt++;
			}
			maxBreak = Math.max(maxBreak, tcnt);
			return ;
		}
		for (int i = 0; i < N; i++)
		{
			if (i == curEgg) continue;
			if (S[i] > 0)
			{
//				System.out.println(curEgg + " <=> " + i);
				S[curEgg] -= W[i]; // 현재 계란 내구도 - 대상 계란 무게
				S[i] -= W[curEgg]; // 대상 계란 내구도 - 현재 계란 무게
				int j = curEgg + 1; // 다음 현재 계란
				while (j < N && S[j] <= 0) j++; // 만약 깨진 계란이면 그 다음으로 넘김
				if (j < N) {
					eggToEgg(j);
				}
				int tcnt = 0;
				for (int k = 0; k < N; k++)
				{
					if(S[k] <= 0) tcnt++;
				}
				maxBreak = Math.max(maxBreak, tcnt);
				S[curEgg] += W[i]; // 현재 계란 내구도 원상 복구
				S[i] += W[curEgg]; // 대상 계란 내구도 원상 복구
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()); // 최대 8
		S = new int[N]; // 최대 300
		W = new int[N]; // 최대 300
		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			S[i] = Integer.parseInt(st.nextToken()); // i번째 계란 내구도
			W[i] = Integer.parseInt(st.nextToken()); // i번째 계란 무게
		}
		eggToEgg(0);
		ans.append(maxBreak);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}

/*
3
1 4
8 1
3 1
=> [2]
 */