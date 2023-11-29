import java.io.*;
import java.util.*;

class Node {
	int r;
	int c;
	int leftF;

	public Node(int r, int c, int leftF) {
		this.r = r;
		this.c = c;
		this.leftF = leftF;
	}
}

public class Main {
	static String isReachable (boolean flag)
	{
		if (flag) return "잘했어!!";
		return "인성 문제있어??";
	}

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken()); // 세로 길이
			int W = Integer.parseInt(st.nextToken()); // 가로 길이
			int O = Integer.parseInt(st.nextToken()); // 장애물의 갯수
			int F = Integer.parseInt(st.nextToken()); // 초기 힘
			int R1 = Integer.parseInt(st.nextToken()) - 1; // 출발 X
			int C1 = Integer.parseInt(st.nextToken()) - 1; // 출발 Y
			int R2 = Integer.parseInt(st.nextToken()) - 1; // 도착 X
			int C2 = Integer.parseInt(st.nextToken()) - 1; // 도착 Y
			int[][] grid = new int[H][W];
			boolean[][] visited = new boolean[H][W];
			int[] or = new int[O]; // 장애물 X 좌표들
			int[] oc = new int[O]; // 장애물 Y 좌표들
			boolean flag = false;
			for (int o = 0; o < O; o++)
			{
				st = new StringTokenizer(br.readLine());
				or[o] = Integer.parseInt(st.nextToken()) - 1;
				oc[o] = Integer.parseInt(st.nextToken()) - 1;
				grid[ or[o] ][ oc[o] ] = Integer.parseInt(st.nextToken());
			}
//			Queue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
//				@Override
//				public int compare(Node o1, Node o2) {
//					return o2.leftF - o1.leftF;
//				}
//			});
			Queue<Node> pq = new LinkedList<>();
			pq.offer(new Node(R1, C1, F));
			visited[R1][C1] = true;
			loop: while (!pq.isEmpty())
			{
				Node tmp = pq.poll();
				int tr = tmp.r;
				int tc = tmp.c;
				int tf = tmp.leftF;
//				System.out.println("[ " +  tr + ", " + tc + " ] tf = " + tf );
				if (tr == R2 && tc == C2) {
					flag = true;
					break;
				}
				if (tf == 0) continue;
				for (int i = 0; i < 4; i++)
				{
					int nr = tr + dr[i];
					int nc = tc + dc[i];
					if (nr < 0 || nc < 0 || nr >= H || nc >= W || (grid[nr][nc] - grid[tr][tc]) > tf || visited[nr][nc] ) continue;
					if (nr == R2 && nc == C2) {
						flag = true;
						break loop;
					}
					visited[nr][nc] = true;
					pq.offer(new Node(nr, nc, tf - 1));
				}
			}
			ans.append(isReachable(flag)).append('\n');
		}
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}