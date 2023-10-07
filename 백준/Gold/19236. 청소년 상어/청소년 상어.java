import java.io.*;
import java.util.*;

/*
풀이
4x4로 맵 고정
x == r / y == c
한 칸에 물고기 1개씩 각자의 번호를 1~16으로 보유 => 이 번호 순서대로 이동
0,0에서 상어 시작 with 0, 0에 있던 물고기와 같은 방향으로
물고기 이동은 1칸씩 자기가 가리키고 있던 방향으로 가능
이동할 수 있는 칸 : 다른 물고기가 있는 칸(그 물고기와 위치를 서로 바꿈 - 물고기 클래스 만들어서 리스트나 배열에 넣어두고 관리) 또는 빈칸
이동할 수 없는 칸 : 상어가 있거나 범위를 벗어난  => 45도씩 반시계 방향으로 회전 (i + 1) % 8
물고기 이동 끝나면 상어 이동
방향에 있는 아무 칸이나 갈 수 있고, 이동하는 경로에 있는 물고기는 먹지 않으며, 방향에 물고기가 한 마리도 없으면 탈출
 */

class Fish {
	int r;
	int c;
	int d;

	public Fish(int r, int c, int d) {
		this.r = r;
		this.c = c;
		this.d = d;
	}
}

public class Main {
	// 물고기 방향 ↑, ↖, ←, ↙, ↓, ↘, →, ↗
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
	static int eatenFishesNumSumMax = 0;

	static void func(int[][] map, int eatenFishesNumSum, Fish[] fishes, int sr, int sc, int sd)
	{
		// 물고기 이동
		for (int fishNum = 1; fishNum <= 16; fishNum++)
		{
			// 먹힌 물고기는 pass
			if (fishes[fishNum].r == -1) continue;
			// 안 먹힌 물고기는 현재 방향으로 1칸 가보기
			Fish cur = fishes[fishNum];
			int r = cur.r;
			int c = cur.c;
			int d = cur.d;
			int nr = r + dr[d];
			int nc = c + dc[d];
			// 갈 수 없는 곳이라면 갈 수 있는 곳을 만날 때 까지 방향 전환
			// 빈 칸 0 // 상어 있는 칸 -1
			if (nr < 0 || nc < 0 || nr >= 4 || nc >= 4 || map[nr][nc] == -1)
			{
				while (nr < 0 || nc < 0 || nr >= 4 || nc >= 4 || map[nr][nc] == -1)
				{
					d = (d + 1) % 8;
					nr = r + dr[d];
					nc = c + dc[d];
				}
			}
			// 빈 칸이면 그냥 이동
			if (map[nr][nc] == 0)
			{
				map[nr][nc] = map[r][c];
				map[r][c] = 0;
				fishes[fishNum].r = nr;
				fishes[fishNum].c = nc;
				fishes[fishNum].d = d;
			}
			else // 다른 물고기가 있는 칸이면 다른 물고기와 자리 교체
			{
				int exchangeFishNum = map[nr][nc]; // 바뀌어지는 물고기 번호
				Fish exchangeFish = fishes[exchangeFishNum]; // 바뀌어지는 물고기
				// 현재 물고기의 위치를 바꿀 물고기의 위치로 바꿔주고
				fishes[fishNum].r = exchangeFish.r; // 여기에 nr 해도 됨!
				fishes[fishNum].c = exchangeFish.c; // 여기에 nc 해도 됨!
				fishes[fishNum].d = d; // 방향은 자기 방향 유지
				// 바꿀 물고기의 위치를 현재 물고기의 위치로 바꿔줌
				fishes[exchangeFishNum].r = r;
				fishes[exchangeFishNum].c = c;
				// map에도 반영
				map[nr][nc] = map[r][c];
				map[r][c] = exchangeFishNum;
			}
		}
		// 상어 이동 // 상어 방향 이동 경로 중에 물고기 있으면 먹고 재귀 아니면 그냥 pass
		for (int i = 1; i < 4; i++)
		{
			int nsr = sr + (i * dr[sd]);
			int nsc = sc + (i * dc[sd]);
//			System.out.println("( " + sr + ", " + sc + " ) 방향 = " + sd);
			if (nsr < 0 || nsc < 0 || nsr >= 4 || nsc >=4 || map[nsr][nsc] == 0) continue;
			else
			{
				int scapeGoatNum = map[nsr][nsc];
				int nsd = fishes[scapeGoatNum].d;
				Fish[] nextFishes = new Fish[17];
				int[][] nextMap = new int[4][4];
				for (int fishNum = 1; fishNum <= 16; fishNum++)
				{
					nextFishes[fishNum] = new Fish(fishes[fishNum].r, fishes[fishNum].c, fishes[fishNum].d);
				}
				nextFishes[scapeGoatNum] = new Fish(-1, -1, -1);
				for (int r = 0; r < 4; r++) {
					for (int c = 0; c < 4; c++) {
						nextMap[r][c] = map[r][c];
					}
				}
				nextMap[nsr][nsc] = -1;
				nextMap[sr][sc] = 0;
				// 확인용 주석
//				for (int r = 0; r < 4; r++) System.out.println(Arrays.toString(nextMap[r]));
				func(nextMap, eatenFishesNumSum + scapeGoatNum, nextFishes, nsr, nsc, nsd);
			}
		}
		eatenFishesNumSumMax = Math.max(eatenFishesNumSumMax, eatenFishesNumSum);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		Fish[] fishes = new Fish[17];
		int[][] map = new int[4][4];
		for (int i = 1; i <= 16; i++) fishes[i] = new Fish(0, 0, 0);
		for (int row = 0; row < 4; row++)
		{
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < 4; col++)
			{
				int a = Integer.parseInt(st.nextToken()); // 물고기 번호
				int b = Integer.parseInt(st.nextToken()) - 1; // 물고기 방향
				// 물고기 배열에서 물고기 위치와 방향 설정
				fishes[a].r = row;
				fishes[a].c = col;
				fishes[a].d = b;
				// 현재 맵에 물고기 위치 시키기
				map[row][col] = a;
			}
		}
		// map 확인용 주석
//		for (int r = 0; r < 4; r++) System.out.println(Arrays.toString(map[r]));
		int shark_R = 0;
		int shark_C = 0;
		int sharK_D;
		// 상어 위치 시키면서 0, 0 에 있는 물고기 먹히고 시작
		fishes[map[shark_R][shark_C]].r = -1;
		fishes[map[shark_R][shark_C]].c = -1;
		sharK_D = fishes[map[shark_R][shark_C]].d;
		int eatenFishesNumSum = map[shark_R][shark_C];
		map[shark_R][shark_C] = -1; // 상어 위치는 -1로 표시
		// map 확인용 주석
//		for (int r = 0; r < 4; r++) System.out.println(Arrays.toString(map[r]));
		// 함수 하나 만들어서 물고기 이동 -> 상어 이동(물고기 위치 중에 골라서) -> 재귀
		// 탈출 조건 상어 방향에 더 이상 물고기가 없는 경우
		func(map, eatenFishesNumSum, fishes, shark_R, shark_R, sharK_D);
		ans.append(eatenFishesNumSumMax);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}