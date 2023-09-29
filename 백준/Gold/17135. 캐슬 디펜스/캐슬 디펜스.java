import java.io.*;
import java.util.*;

class Archer
{
	int col;
	Enemy enemiesToKill;

	public Archer(int col, Enemy enemiesToKill) {
		this.col = col;
		this.enemiesToKill = enemiesToKill;
	}
}

class Enemy
{
	int dist;
	int row;
	int col;

	public Enemy(int dist, int row, int col) {
		this.dist = dist;
		this.row = row;
		this.col = col;
	}
}

public class Main {
	static int N, M, D, maxEnemiesKill = 0; // 3 <= N & M <= 15 // 1 <= D <= 10
	static int[][] grid; // 맵
	static Archer[] archers = new Archer[3]; // 궁수 배치

	// 궁수배치용 함수
	static void placeThreeArchers(int placedArcherNum, int placedArcherColumn, int last)
	{
		if (placedArcherNum == 3)
		{
			int ai = 0;
			for (int i = 0; i < M; i++)
			{
				if ((placedArcherColumn & (1 << i)) != 0)
				{
					archers[ai++] = new Archer(i, null);
//					System.out.print(i + " ");
				}
			}
//			System.out.println("");
			if (ai == 3) getMaxEnemiesKill();
			return ;
		}
		for (int i = last + 1; i < M; i++)
		{
			if ((placedArcherColumn & (1 << i)) != 0) continue;
			placeThreeArchers(placedArcherNum + 1, (placedArcherColumn | (1 << i)), i);
		}
	}

	static void getMaxEnemiesKill() {
		int[][] tmpGrid = new int[N][M]; // 임시 맵
		for (int r = 0; r < N; r++) tmpGrid[r] = grid[r].clone(); // 맵 본떠 오기
		int tmpkills = 0;
		while (true)
		{
			int enemiesCnt = 0;
			int archerPicked = 0;
			for (int d = 1; d <= D; d++)
			{
				if (archerPicked == 3) break;
				for (int a = 0; a < 3; a++)
				{
					Archer cur = archers[a];
					if (cur.enemiesToKill != null) continue;
					for (int c = 1 - d; c <= d - 1; c++) // 같은 거리면 더 왼쪽에 있는 것을 고르기 위해서 c부터 설정해놓고 탐색
					{
						int r = d - Math.abs(c);
						int nr = N - r;
						int nc = cur.col + c;
						if (nr < 0 || nr >= N) continue;
						if (nc < 0 || nc >= M) continue;
						if (tmpGrid[nr][nc] == 1)
						{
							archers[a].enemiesToKill = new Enemy(Math.abs(N - r) + Math.abs(cur.col - c), nr, nc);
							archerPicked++;
							break; // 다음 궁수로
						}
					}
				}
			}
			// 죽이기 타임
			for (int a = 0; a < 3; a++)
			{
				Archer cur = archers[a];
				if (cur.enemiesToKill == null) continue;
				if (tmpGrid[cur.enemiesToKill.row][cur.enemiesToKill.col] == 1)
				{
					tmpGrid[cur.enemiesToKill.row][cur.enemiesToKill.col] = 0;
					tmpkills++;
				}
				cur.enemiesToKill = null;
			}
			// 살아남은 적을 이동하는 시간
			for (int r = N - 1; r >= 0; r--) // 아래서 부터 봐야 예외가 발생하지 않음!
			{
				for (int c = 0; c < M; c++)
				{
					if (tmpGrid[r][c] == 1)
					{
						tmpGrid[r][c] = 0;
						if (r < N - 1) {
							tmpGrid[r + 1][c] = 1;
							enemiesCnt++;
						}
					}
				}
			}
			if (enemiesCnt == 0) break;
		}
		if (maxEnemiesKill < tmpkills)
		{
//			System.out.println(tmpkills);
			maxEnemiesKill = tmpkills;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		grid = new int[N][M];
		for (int r = 0; r < N; r++)
		{
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) grid[r][c] = Integer.parseInt(st.nextToken());
		}
		placeThreeArchers(0,0, -1);
		ans.append(maxEnemiesKill);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}