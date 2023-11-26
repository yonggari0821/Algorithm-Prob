import java.io.*;
import java.util.*;

/*
[구현 20058]_마법사 상어와 파이어스톰_안상준

1) L[i]로 큰 for문 돌리면서 L값 정하기
2) 정한 L 값을 가지고 구획 나누고 구획 별로 90도 회전하기
3) 2칸 이하로 얼음과 접한 칸 -1
4) 최종적으로 최댓값과 최대로 이어진 갯수 구하기

 */

public class Main {
	static int N, Q, len;
	static int[][] grid;
	static int[] dr = {-1, 1, 0 , 0};
	static int[] dc = {0 , 0, -1, 1};


	// 함수 설명
	/*
	tr tc가 시작점
	l은 2의 l승만큼 크기의 격자라는 뜻
	90도 돌리려면 l/2만큼씩을 돌리면되는데
	돌리는 대상은
	(tr, tc) 부터 (tr + l/2 - 1, tc + l/2 - 1)까지임!
	 */
	static void func2 (int tr, int tc, int l)
	{
		int[][] tmpGrid = new int[l][l];
		for (int r = tr; r < tr + l; r++)
		{
			for (int c = tc; c < tc + l; c++) tmpGrid[c - tc][l-1-r + tr] = grid[r][c];
			// 0, 0 => 0, 3
			// 0, 1 => 1, 3
			// 0, 2 => 2, 3
			// 0, 3 => 3, 3
		}
		for (int r = 0; r < l; r++)
		{
			for (int c = 0; c < l; c++) grid[r + tr][c + tc] = tmpGrid[r][c];
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 2 ~ 6
		Q = Integer.parseInt(st.nextToken()); // max 1000
		len = (int) Math.pow(2, N);
		grid = new int[len][len];
		boolean[][] toMinus;
		for (int r = 0; r < len; r++)
		{
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < len; c++) grid[r][c] = Integer.parseInt(st.nextToken());
		}
		int[] L = new int[Q + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= Q; i++) L[i] = Integer.parseInt(st.nextToken());

		// 1)
		for (int i = 1; i <= Q; i++)
		{
			int l = L[i];
			// 2) 90도 돌리기
			if (l != 0) {
				for (int tr = 0; tr < len; tr += Math.pow(2, l)) {
					for (int tc = 0; tc < len; tc += Math.pow(2, l)) func2(tr, tc, (int) Math.pow(2, l));
				}
			}
			// 3) 2칸 이하로 얼음과 접한 칸 -1
			toMinus = new boolean[len][len];
			for (int r = 0; r < len; r++)
			{
				for (int c = 0; c < len; c++)
				{
					if (grid[r][c] == 0) continue;
					int near = 0;
					for (int j = 0; j < 4; j++)
					{
						int nr = r + dr[j];
						int nc = c + dc[j];
						if (nr < 0 || nc < 0 || nr >= len || nc >= len || grid[nr][nc] == 0) continue;
						near++;
					}
					if (near < 3) toMinus[r][c] = true;
				}
			}
			for (int r = 0; r < len; r++)
			{
				for (int c = 0; c < len; c++)
				{
					if (toMinus[r][c]) grid[r][c]--;
				}
			}
		}

		int sum = 0;
		int max = 0;

		// 4) 남은 얼음의 총 갯수 + 가장 큰 덩어리의 얼음 갯수
		boolean[][] visited = new boolean[len][len];
		Queue<Integer> q = new LinkedList<>();
		for (int r = 0; r < len; r++)
		{
			for (int c = 0; c < len; c++)
			{
				if (grid[r][c] != 0 && !visited[r][c])
				{
					visited[r][c] = true;
					q.offer(r * 1000 + c);
					int mass = 0;
					while (!q.isEmpty())
					{
						int tmp = q.poll();
						int tr = tmp / 1000;
						int tc = tmp % 1000;
						mass++;
						sum += grid[tr][tc];
						for (int i = 0; i < 4; i++)
						{
							int nr = tr + dr[i];
							int nc = tc + dc[i];
							if (nr < 0 || nc < 0 || nr >= len || nc >= len) continue;
							if (grid[nr][nc] != 0 && !visited[nr][nc]) {
								visited[nr][nc] = true;
								q.offer(nr * 1000 + nc);
							}
						}
					}
					max = max > mass ? max : mass;
				}
			}
		}
		/*
		for (int i = 0; i < len; i++)
		{
			System.out.println(Arrays.toString(grid[i]));
		}
		*/
		ans.append(sum).append('\n').append(max);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}