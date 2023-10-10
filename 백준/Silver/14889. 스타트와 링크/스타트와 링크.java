import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int N, min; // 사람 수 // 최소값
	public static int[][] stats; // 시너지 점수
	public static int[] start; // 팀 스타트
	public static int[] link; // 팀 링크

	public static void DFS (int last, int pick)
	{
		if (pick == N / 2) // 탈출조건 : 반 고르기 => 반 만 고르면 나머지는 자동으로 다른 팀!
		{
			int li = 0; // link 팀 인덱스
			for (int a = 1; a <= N; a++)
			{
				boolean isin = false;
				for (int b = 0; b < N / 2; b++) // 스타트 팀의 번호들 중에 있는 지 검사
				{
					if (start[b] == a)
					{
						isin = true;
						break;
					}
				}
				// 스타트팀 명단에 없으면 => link 팀으로
				if (isin == false)
				{
					link[li] = a;
					li++;
				}
			}
			// 두 팀 각각의 시너지 점수
			int ss = 0;
			int ls = 0;
			for (int c = 0; c < N / 2; c++)
			{
				for (int d = c + 1; d < N / 2; d++)
				{
					int tmpcs = start[c];
					int tmpds = start[d];
					int tmpcl = link[c];
					int tmpdl = link[d];
					ss += (stats[tmpcs][tmpds] + stats[tmpds][tmpcs]);
					ls += (stats[tmpcl][tmpdl] + stats[tmpdl][tmpcl]);
				}
			}
			// 시너지 점수의 차를 구해서 최소값 리뉴얼
			int tmpval = Math.abs(ss - ls);
			min = min < tmpval ? min : tmpval;
			return ;
		}
		// 진행조건
		for (int i = last + 1; i <= N; i++) // 마지막에 고른 놈 다시 안 고르도록 그 다음부터 골라야 함!
		{
			start[pick] = i;
			DFS(i, pick + 1);
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		stats = new int[N+1][N+1]; // 선수 번호는 1번부터이므로 1칸씩 늘려서 선수번호 == idx 될 수 있도록 함!
		min = Integer.MAX_VALUE; // 최소값의 default는 MAX_VALUE로!!
		start = new int[N];
		link = new int[N];
		// 시너지 점수 받아두고
		for (int i = 1; i <= N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++)
			{
				stats[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 재귀로 팀 짜기 및 최소 격차 구하기
		DFS(0,0);
		// 구한 최소값 출력 => 무조건 1개는 나오므로 따로 예외처리 필요 X
		System.out.println(min);
	}
}