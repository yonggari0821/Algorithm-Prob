import java.io.*;
import java.util.*;

public class Main {
	static int R, C;
	static int eR, eC;
	static int[][] map;
	static int[][] wayNum;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int findWays(int cur)
	{
		if (cur == eR * 1000 + eC)
		{
			return 1;
		}
		int tr = cur / 1000;
		int tc = cur % 1000;
		int tval = map[tr][tc];
		// wayNum[tr][tc] == -1 >>> 아직 살펴보지 않은 곳
		if (wayNum[tr][tc] == -1) {
			wayNum[tr][tc] = 0; // 해당 위치 까지의 경로를 0으로 초기화 하고
			for (int i = 0; i < 4; i++) {
				int nr = tr + dr[i];
				int nc = tc + dc[i];
				if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
				// 주변에서 더 작은 값이 있는 곳까지의 끝점에서부터의 경로 갯수를 현재 경로 갯수에 더해준 다음 리턴
				if (tval > map[nr][nc]) {
					wayNum[tr][tc] += findWays(nr * 1000 + nc);
				}
			}
		}
		return wayNum[tr][tc];
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		eR = R - 1;
		eC = C - 1;
		map = new int[R][C];
		wayNum = new int[R][C];
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				wayNum[r][c] = -1;
			}
		}
		ans.append(findWays(0));
//		for (int i = 0; i < R; i++) {
//			System.out.println(Arrays.toString(wayNum[i]));
//		}
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}

/*
5 5
50 49 48 47 46
41 42 43 44 45
40 39 38 37 36
31 32 33 34 35
30 29 28 27 26
190
 */