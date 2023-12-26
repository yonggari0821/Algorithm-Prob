import java.io.*;
import java.util.*;

/*
[ 10026]_적록색약_안상준

N x N에 R G B 중 색칠되어있음
같은 색상 상하좌우 인접 == 같은 구역 (색약이라 차이 못 느끼는 것도 같은 구역)

일반인과 적녹색약인 사람인 사람이 보는 구역의 수를 구할 것

 */

class Paint {
	byte r;
	byte c;

	public Paint(byte r, byte c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	static byte[] dr = {-1, 1, 0, 0};
	static byte[] dc = {0, 0, -1, 1};
	static int N;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		byte[][] grid = new byte[N][N];
		for (int i = 0; i < N; i++)
		{
			String tmp = br.readLine();
			for (int j = 0; j < tmp.length(); j++)
			{
				if (tmp.charAt(j) == 'R') grid[i][j] = 1;
				else if (tmp.charAt(j) == 'G') grid[i][j] = 3;
				else grid[i][j] = 2;
			}
		}
		// 일반인
		Queue<Paint> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		int area = 0;
		for (byte r = 0; r < N; r++)
		{
			for (byte c = 0; c < N; c++)
			{
				if (!visited[r][c])
				{
					q.offer(new Paint(r, c));
					visited[r][c] = true;
					while (!q.isEmpty())
					{
						Paint tmp = q.poll();
						byte tr = tmp.r;
						byte tc = tmp.c;
						for (byte i = 0; i < 4; i++)
						{
							byte nr = (byte) (tr + dr[i]); // byte 끼리의 합 == int형 => 왜? c 기반 언어들에서 언어 자체의 기본형이 int라 int형 보다 범위가 적은 자료형들의 연산값도 int로 나옴
							byte nc = (byte) (tc + dc[i]);
							if (nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc]) continue;
							if (grid[tr][tc] == grid[nr][nc]) {
								visited[nr][nc] = true;
								q.offer(new Paint(nr, nc));
							}
						}
					}
					area++;
				}
			}
		}
		ans.append(area).append(" ");
		
		// 적녹 색약
		q = new LinkedList<>();
		visited = new boolean[N][N];
		area = 0;
		for (byte r = 0; r < N; r++)
		{
			for (byte c = 0; c < N; c++)
			{
				if (!visited[r][c])
				{
					q.offer(new Paint(r, c));
					visited[r][c] = true;
					while (!q.isEmpty())
					{
						Paint tmp = q.poll();
						byte tr = tmp.r;
						byte tc = tmp.c;
						for (byte i = 0; i < 4; i++)
						{
							byte nr = (byte) (tr + dr[i]); // byte 끼리의 합 == int형 => 왜? c 기반 언어들에서 언어 자체의 기본형이 int라 int형 보다 범위가 적은 자료형들의 연산값도 int로 나옴
							byte nc = (byte) (tc + dc[i]);
							if (nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc]) continue;
							if ((grid[tr][tc] & 1) == (grid[nr][nc] & 1)) {
								visited[nr][nc] = true;
								q.offer(new Paint(nr, nc));
							}
						}
					}
					area++;
				}
			}
		}
		ans.append(area);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}