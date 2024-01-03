import java.io.*;
import java.util.*;

/*
[DP 1495]_기타리스트_안상준

5
0 5
0 3 2 8
.
.
.
이런 식으로 2의 50승 개를 저장해가면서 구할 수는 없음
크기가 1000으로 정해져있으므로 차라리
각 단계 마다 해당 단계 번호를 해당 단계에 기록해놨다가
탐색하면서 해당 단께에서 더하고 뺄 수 있는 볼륨을 기록하면서 진행

반례
2 1 4
1 2
4

큐를 이용한 풀이로 바꾸기

 */




public class Main {
	static int N, S, M;
	static int V[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 곡의 갯수 // 최대 50
		S = Integer.parseInt(st.nextToken()); // 시작 볼륨 // 최대 M
		M = Integer.parseInt(st.nextToken()); // 설정 가능한 볼륨 최댓값 // 최대 1000
		V = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) V[i] = Integer.parseInt(st.nextToken());
		Queue<Integer> q = new LinkedList<>();
		boolean[] got;
		q.offer(S);
		int curNum = 1;
		int max = -1;
		while (curNum <= N)
		{
			int size = q.size();
			got = new boolean[1001];
			for (int i = 0; i < size; i++)
			{
				int tmp = q.poll();
				if (curNum == N)
				{
					if (tmp - V[curNum] >= 0) max = max > tmp - V[curNum] ? max : tmp - V[curNum];
					if (tmp + V[curNum] <= M) max = max > tmp + V[curNum] ? max : tmp + V[curNum];
				}
				if (tmp - V[curNum] >= 0 && !got[tmp - V[curNum]]) {
					q.offer(tmp - V[curNum]);
					got[tmp - V[curNum]] = true;
				}
				if (tmp + V[curNum] <= M && !got[tmp + V[curNum]]) {
					q.offer(tmp + V[curNum]);
					got[tmp + V[curNum]] = true;
				}
			}
			if (q.size() == 0)
			{
				System.out.println(-1);
				return;
			}
			curNum++;
		}
		System.out.println(max);
		br.close();
	}
}