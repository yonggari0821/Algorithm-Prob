import java.io.*;
import java.util.*;

public class Main {
	static int point = 0; // 점수
	static int blockCnt = 0; // 남아있는 블럭 갯수
	static boolean[][] monoMinoDomino = new boolean[10][10]; // 맵
	static void checkBlueLine(int xl, int xr) // 파란색 영역 확인
	{
		// 1칸 확인해야 될 때 => xl은 5이거나 5 초과 // 4 이하는 불가능!
		if (xl == xr) {
			if (xl == 5) // 연한 파랑 영역에 쌓인 경우 => 가장 오른쪽 열 삭제하고 한 칸씩 댕기기 => 그냥 바로 댕기면 됨
			{
				for (int r = 0; r < 4; r++)
				{
					for (int c = 9; c > 3; c--) monoMinoDomino[r][c] = monoMinoDomino[r][c - 1];
				}
			}
			else // 그냥 파랑 영역에 쌓인 경우 => 해당 라인 확인해서 꽉찼으면 지우기
			{
				int cnt = 0;
				for (int r = 0; r < 4; r++)
				{
					if (monoMinoDomino[r][xl]) cnt++;
				}
				if (cnt == 4) removeBlueLine(xl, true);
			}
		}
		else // 두 칸 확인해야 될 때
		{
			if (xl == 4) // 둘 다 연한 파랑 영역에 걸린 경우
			{
				// 2칸씩 당기기
				for (int r = 0; r < 4; r++)
				{
					for (int c = 9; c > 3; c--) monoMinoDomino[r][c] = monoMinoDomino[r][c - 2];
				}
			}
			else if (xl == 5) // 한 칸만 연한 파랑 영역에 걸린 경우
			{
				// 행이나 열이 타일로 가득찬 경우와 연한 칸에 블록이 있는 경우가 동시에 발생할 수 있다.
				// 이 경우에는 행이나 열이 타일로 가득 찬 경우가 없을 때까지 점수를 획득하는 과정이 모두 진행된 후,
				// 연한 칸에 블록이 있는 경우를 처리해야 한다.
				int cnt = 0;
				for (int r = 0; r < 4; r++)
				{
					if (monoMinoDomino[r][xr]) cnt++;
				}
				if (cnt == 4) // 오른쪽 칸 점수 처리 및 그 칸 기준 1칸 땡기기
				{
					removeBlueLine(xr, true);
					// 땡긴 칸은 계속해서 봐줘야 함
					for (int r = 0; r < 4; r++)
					{
						if (monoMinoDomino[r][xr])
						{
							int xx = xr;
							while (xx + 1 < 10 && !monoMinoDomino[r][xx + 1])
							{
								monoMinoDomino[r][xx + 1] = true;
								monoMinoDomino[r][xx] = false;
								xx++;
							}
							checkBlueLine(xx, xx);
						}
					}
				}
				else // 모든 칸 1칸씩 땡기기
				{
					// 한 칸씩 땡기기
					for (int r = 0; r < 4; r++)
					{
						for (int c = 9; c > 3; c--) monoMinoDomino[r][c] = monoMinoDomino[r][c - 1];
					}
				}
			}
			else // 모두 진한 파랑 영역인 경우 => 왼쪽부터 검사해서 밀어주고
			{
				int cnt = 0;
				for (int r = 0; r < 4; r++)
				{
					if (monoMinoDomino[r][xl]) cnt++;
				}
				if (cnt == 4) removeBlueLine(xl, true);
				// 오른쪽도 검사해서 밀어줌
				// 오른쪽부터 밀면 예외 발생 가능!
				cnt = 0;
				for (int r = 0; r < 4; r++)
				{
					if (monoMinoDomino[r][xr]) cnt++;
				}
				if (cnt == 4) removeBlueLine(xr, true);
			}
		}
	}
	static void removeBlueLine(int x, boolean plus)
	{
		// 점수 올려주고
		if (plus) point++;
		// 한 칸 씩 땡겨주기
		for (int r = 0; r < 4; r++)
		{
			for (int col = x; col > 3; col--) monoMinoDomino[r][col] = monoMinoDomino[r][col - 1];
		}
	}
	static void checkGreenLine(int yu, int yd) // 초록색 영역 확인
	{
		if (yu == yd) // 한 칸만 확인하면 될 때
		{
			if (yu == 5) // 연한 초록 영역일 경우
			{
				// 한 칸씩 땡겨주기
				for (int c = 0; c < 4; c++)
				{
					for (int r = 9; r > 3; r--) monoMinoDomino[r][c] = monoMinoDomino[r - 1][c];
				}
			}
			else // 진한 초록 영역일 경우
			{
				int cnt = 0;
				for (int c = 0; c < 4; c++)
				{
					if (monoMinoDomino[yu][c]) cnt++;
				}
				if (cnt == 4) removeGreenLine(yu, true);
			}
		}
		else // 두 칸 확인해야 하는 경우
		{
			if (yu == 4)
			{
				// 2칸씩 땡기기
				for (int c = 0; c < 4; c++)
				{
					for (int r = 9; r > 3; r--)
						monoMinoDomino[r][c] = monoMinoDomino[r - 2][c];
				}
			}
			else if (yu == 5) // 한 칸만 연한 영역인 경우 => 나머지 한 칸의 진한영역이 점수화 될 수 있는 경우와 아닌 경우 나눠서 생각
			{
				int cnt = 0;
				for (int c = 0; c < 4; c++)
				{
					if (monoMinoDomino[yd][c]) cnt++;
				}
				if (cnt == 4) // 아럐쪽 칸 점수 처리 및 그 칸 기준 1칸 땡기기
				{
					removeGreenLine(yd, true);
					// 땡긴 칸은 계속해서 봐줘야 함
					for (int c = 0; c < 4; c++)
					{
						if (monoMinoDomino[yd][c])
						{
							int yy = yd;
							while (yy + 1 < 10 && !monoMinoDomino[yy + 1][c])
							{
								monoMinoDomino[yy + 1][c] = true;
								monoMinoDomino[yy][c] = false;
								yy++;
							}
							checkBlueLine(yy, yy);
						}
					}
				}
				else // 모든 칸 1칸씩 땡기기
				{
					// 한 칸씩 땡기기
					for (int c = 0; c < 4; c++)
					{
						for (int r = 9; r > 3; r--) monoMinoDomino[r][c] = monoMinoDomino[r - 1][c];
					}
				}
			}
			else // 두 칸 모두 진한 초록 영역인 경우
			{
				// 위에서부터 체크 후 점수화
				int cnt = 0;
				for (int c = 0; c < 4; c++)
				{
					if (monoMinoDomino[yu][c]) cnt++;
				}
				if (cnt == 4) removeGreenLine(yu, true);
				// 아래도 같은 로직 수행
				cnt = 0;
				for (int c = 0; c < 4; c++)
				{
					if (monoMinoDomino[yd][c]) cnt++;
				}
				if (cnt == 4) removeGreenLine(yd, true);
			}
		}
	}
	static void removeGreenLine(int y, boolean plus)
	{
		// 점수 올려주고
		if (plus) point++;
		// 한 칸 씩 땡겨주기
		for (int i = 0; i < 4; i++)
		{
			for (int row = y; row > 3; row--) monoMinoDomino[row][i] = monoMinoDomino[row - 1][i];
		}
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());

			monoMinoDomino[y][x] = true;
			if (t == 2) // 가로로 긴 경우
				monoMinoDomino[y][x + 1] = true;
			else if (t == 3) // 세로로 긴 경우
				monoMinoDomino[y + 1][x] = true;

			if (t == 1) {
				// 파란 색 쪽으로 이동
				int curX = x;
				while (curX + 1 < 10 && !monoMinoDomino[y][curX + 1])
				{
					monoMinoDomino[y][curX + 1] = monoMinoDomino[y][curX];
					monoMinoDomino[y][curX] = false;
					curX += 1;
				}
				checkBlueLine(curX, curX);
				monoMinoDomino[y][x] = true;
				// 초록 색 쪽으로 이동
				int curY = y;
				while (curY + 1 < 10 && !monoMinoDomino[curY + 1][x])
				{
					monoMinoDomino[curY + 1][x] = monoMinoDomino[curY][x];
					monoMinoDomino[curY][x] = false;
					curY += 1;
				}
				checkGreenLine(curY, curY);
			}
			else if (t == 2) // 가로로 긴 경우
			{
				// 파란 색 쪽으로 이동
				int curXR = x + 1;
				int curXL = x;
				while (curXR + 1 < 10 && !monoMinoDomino[y][curXR + 1])
				{
					monoMinoDomino[y][curXR + 1] = monoMinoDomino[y][curXR];
					monoMinoDomino[y][curXR] = monoMinoDomino[y][curXL];
					monoMinoDomino[y][curXL] = false;
					curXL = curXR;
					curXR += 1;
				}
				// 왼쪽부터 해줘야 함!
				checkBlueLine(curXL, curXR);

				// 초록 색 쪽으로 이동
				monoMinoDomino[y][x] = true;
				monoMinoDomino[y][x + 1] = true;
				curXR = x + 1;
				curXL = x;
				int curY = y;
				while (curY + 1 < 10 && !monoMinoDomino[curY + 1][curXL] && !monoMinoDomino[curY + 1][curXR])
				{
					monoMinoDomino[curY + 1][curXL] = monoMinoDomino[curY][curXL];
					monoMinoDomino[curY + 1][curXR] = monoMinoDomino[curY][curXR];
					monoMinoDomino[curY][curXL] = false;
					monoMinoDomino[curY][curXR] = false;
					curY += 1;
				}
				checkGreenLine(curY, curY);
			}
			else if (t == 3) // 세로로 긴 경우
			{
				// 파란 색 쪽으로 이동
				int curYU = y;
				int curYD = y + 1;
				int curX = x;
				while (curX + 1 < 10 && !monoMinoDomino[curYU][curX + 1] && !monoMinoDomino[curYD][curX + 1])
				{
					monoMinoDomino[curYU][curX + 1] = monoMinoDomino[curYU][curX];
					monoMinoDomino[curYD][curX + 1] = monoMinoDomino[curYD][curX];
					monoMinoDomino[curYU][curX] = false;
					monoMinoDomino[curYD][curX] = false;
					curX += 1;
				}
				checkBlueLine(curX, curX);

				// 초록 색 쪽으로 이동
				monoMinoDomino[y][x] = true;
				monoMinoDomino[y + 1][x] = true;
				curYU = y;
				curYD = y + 1;
				while (curYD + 1 < 10 && !monoMinoDomino[curYD + 1][x])
				{
					monoMinoDomino[curYD + 1][x] = monoMinoDomino[curYD][x];
					monoMinoDomino[curYD][x] = monoMinoDomino[curYU][x];
					monoMinoDomino[curYU][x] = false;
					curYU = curYD;
					curYD += 1;
				}
				// 위쪽부터 해줘야 함!
				checkGreenLine(curYU, curYD);
			}
		}
		for (int r = 0; r < 4; r++) {
			for (int c = 6; c < 10; c++) {
				if (monoMinoDomino[r][c]) blockCnt++;
			}
		}
		for (int r = 6; r < 10; r++) {
			for (int c = 0; c < 4; c++) {
				if (monoMinoDomino[r][c]) blockCnt++;
			}
		}
//		for (int r = 0; r < 10; r++)
//		{
//			for (int c = 0; c < 10; c++)
//			{
//				if(monoMinoDomino[r][c]) System.out.print(1 + " ");
//				else System.out.print(0 + " ");
//			}
//			System.out.println("");
//		}
		ans.append(point).append('\n').append(blockCnt);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}