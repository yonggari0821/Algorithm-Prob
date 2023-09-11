import java.io.*;
import java.util.*;

public class Main {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < N; c++) map[r][c] = str.charAt(c) - '0';
		}
		Queue<Integer> queue = new LinkedList<>();
		PriorityQueue<Integer> complexSize = new PriorityQueue<>();
		int complexNum = 1;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == 1)
				{
					++complexNum;
					map[r][c] = complexNum;
					int cnt = 0;
					queue.offer(r * 100 + c);
					while (!queue.isEmpty())
					{
						int tHouse = queue.poll();
						int tr = tHouse / 100;
						int tc = tHouse % 100;
						for (int i = 0; i < 4; i++) {
							int nr = tr + dr[i];
							int nc = tc + dc[i];
							if (nr < 0 || nc < 0 || nr >= N || nc >= N || map[nr][nc] != 1) continue;
							else {
								map[nr][nc] = complexNum;
								queue.offer(nr * 100 + nc);
							}
						}
						cnt++;
					}
					complexSize.offer(cnt);
				}
			}
		}
		int size = complexSize.size();
		ans.append(size).append('\n');
		for (int i = 0; i < size; i++) ans.append(complexSize.poll()).append('\n');
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}