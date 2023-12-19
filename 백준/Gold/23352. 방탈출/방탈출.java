import java.io.*;
import java.util.*;

/*
[그래프 탐색 & 브루트포스 23352]_방탈출_안상준
int N, M (각각 구역의 세로, 가로 크기이며 최대 50까지)
그리고 N x M 크기의 구역에 1칸마다 방이 있고 해당 방의 정보가 0~9의 숫자로 주어짐!
<조건>
0인 경우 갈 수 없으며,
방 간의 이동은 항상 최단 경로로 이루어져야 하고
그 중에서 가장 긴 경로의 시작 및 끝방의 번호합이 곧 비밀번호 (0, 2 ~ 18) (단, 구할 수 없으면 0)
+ 시작 방과 끝 방은 동일할 수 있음 == 0이 아닌 방이 하나라도 있으면 무조건 0은 아님!

각 방으로 이동 시에 0이 아닌 지를 체크해서 구현해야 함
& 최단 거리이면서 그 중에서 최장 거리를 찾아야 되고 그 때의 시작점 및 끝점을 알아야 함

 */

class Room {
	int val;
	int r;
	int c;
	int move;

	public Room(int val, int r, int c, int move) {
		this.val = val;
		this.r = r;
		this.c = c;
		this.move = move;
	}
}


public class Main {
	static int N, M;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] area = new int[N][M];
		boolean[][] visited;
		Queue<Room> q = new LinkedList<>();
		for (int r = 0; r < N; r++)
		{
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) area[r][c] = Integer.parseInt(st.nextToken());
		}
		int maxLen = -1;
		int password = 0;
		for (int r = 0; r < N; r++)
		{
			for (int c = 0; c < M; c++)
			{
				if (area[r][c] != 0)
				{
					int start = area[r][c];
					visited = new boolean[N][M];
					q.offer(new Room(area[r][c], r, c, 0));
					visited[r][c] = true;
					while (!q.isEmpty())
					{
						Room cur = q.poll();
						int cr = cur.r;
						int cc = cur.c;
						int cVal =  cur.val;
						int cMove = cur.move;
						if (maxLen <= cMove) {
							if (maxLen == cMove) password = Math.max(password, start + cVal);
							else {
								password = start + cVal; // 더 크다면
								maxLen = cMove;
							}
						}
						for (int i = 0; i < 4; i++)
						{
							int nr = cr + dr[i];
							int nc = cc + dc[i];
							if (nr < 0 || nc < 0 || nr >= N || nc >= M || area[nr][nc] == 0 || visited[nr][nc]) continue;
							else {
								visited[nr][nc] = true;
								q.offer(new Room(area[nr][nc], nr, nc, cMove + 1));
							}
						}
					}
				}
			}
		}
		ans.append(password);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}