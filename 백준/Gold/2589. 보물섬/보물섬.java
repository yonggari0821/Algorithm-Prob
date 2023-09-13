import java.io.*;
import java.util.*;

class Node
{
	int r;
	int c;
	int d;

	public Node(int r, int c, int d) {
		this.r = r;
		this.c = c;
		this.d = d;
	}
}

public class Main {
	static int R, C;
	static int minButMostFar = 0;
	static boolean[][] map; // true == 'L' // false == 'W'
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new boolean[R][C];
		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) map[r][c] = (str.charAt(c) == 'L');
		}
		Queue<Node> q = new LinkedList<>();
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c])
				{
					boolean[][] visited = new boolean[R][C];
					visited[r][c] = true;
					q.offer(new Node(r, c, 0));
					while(!q.isEmpty())
					{
						Node tmp = q.poll();
						int tr = tmp.r;
						int tc = tmp.c;
						int td = tmp.d;
						int cnt = 0;
						for (int i = 0; i < 4; i++) {
							int nr = tr + dr[i];
							int nc = tc + dc[i];
							if (nr < 0 || nc < 0 || nr >= R || nc >= C || visited[nr][nc] || !map[nr][nc]) continue;
							visited[nr][nc] = true;
							q.offer(new Node(nr, nc, td + 1));
							cnt++;
						}
						if (cnt == 0) {
							minButMostFar = (minButMostFar > td) ? minButMostFar : td;
						}
					}
				}
			}
		}
		ans.append(minButMostFar);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}

/*
5 7
WLLWWWL
LLLWLLL
LWLWLWW
LWLWLLL
WLLWLWW
8
 */