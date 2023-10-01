import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, K, L, curDirection = 0, hr = 1, hc = 1, tr = 1, tc = 1;
	static boolean[][] board;
	static int[][] snake;
	static int[][] direction;

	// 우 하 좌 상
	public static int[] dr = {0, 1, 0 , -1};
	public static int[] dc = {1, 0, -1, 0};

	static void turnDir (int turningDir)
	{
		if (turningDir == 'D') // 시계 방향
			curDirection = (curDirection + 1) % 4; // 0 -> 1 // 1 -> 2 // 2 -> 3 // 3 -> 4 -> 0
		else // == 'L' // 반시계 방향
		{
			if (curDirection > 0) curDirection -= 1; // 1 -> 0 // 2 -> 1 // 3 -> 2
			else curDirection = 3; // 0 -> 3
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()); // 보드 한 변의 길이
		K = Integer.parseInt(br.readLine()); // 사과의 갯수
		board = new boolean[N + 1][N + 1]; // 보드 배열
		snake = new int[N + 1][N + 1]; // 뱀 몸뚱아리 배열
		for (int r = 0; r <= N; r++)
		{
			for (int c = 0; c <= N; c++) snake[r][c] = -1; // 0,1,2,3으로 각각 해당 칸의 뱀의 부분이 바라보고 있는 방향을 설정할 것이므로 초기값은 -1
		}

		for (int k = 0; k < K; k++)
		{
			st = new StringTokenizer(br.readLine());
			int ar = Integer.parseInt(st.nextToken()); // 사과 위치 행
			int ac = Integer.parseInt(st.nextToken()); // 사과 위치 열
			board[ar][ac] = true; // 사과 놓기
		}

		// 방향 전환 관련 입력값들
		L = Integer.parseInt(br.readLine()); // 방향 전환 횟수
		direction = new int[L][2]; // 전환하는 방향 저장용 배열 // [방향전환 idx][0 = 시간 // 1 = 전환 방향]
		for (int l = 0; l < L; l++)
		{
			st = new StringTokenizer(br.readLine());
			direction[l][0] = Integer.parseInt(st.nextToken()); // 게임 경과 시간(초)
			direction[l][1] = st.nextToken().charAt(0); // 방향 L - 왼쪽 // D - 오른쪽 // 대문자 알파벳은 char인데 이는 int로 자동변환되어서 들어가므로 상관 없음!
		}

		// 뱀 움직임 시작
		snake[1][1] = curDirection; // 우측을 바라보게 설정
		int time = 0;
		int turnIdx = 0;
		while (true)
		{
			time++;
			int nr = hr + dr[curDirection];
			int nc = hc + dc[curDirection];
			// 게임 오버
			if (nr <= 0 || nc <= 0 || nr > N || nc > N || snake[nr][nc] != -1) break;
			hr = nr;
			hc = nc;
			snake[hr][hc] = curDirection;
			if (board[hr][hc]) board[hr][hc] = false;
			// 사과가 없는 경우 => 꼬리 한 칸 줄여줘야 함!
			else
			{
				int ntr = tr + dr[snake[tr][tc]];
				int ntc = tc + dc[snake[tr][tc]];
				snake[tr][tc] = -1;
				tr = ntr;
				tc = ntc;
 			}
			if (turnIdx < L && direction[turnIdx][0] == time)
			{
				turnDir(direction[turnIdx][1]);
				turnIdx++;
				snake[hr][hc] = curDirection;
			}
		}
		System.out.println(time);
		br.close();
	}
}