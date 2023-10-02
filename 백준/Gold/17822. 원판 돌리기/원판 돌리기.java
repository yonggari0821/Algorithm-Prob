import java.io.*;
import java.util.*;

public class Main {
	static int N, M, T;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 원판 갯수
		M = Integer.parseInt(st.nextToken()); // 원판 1개에 적히는 숫자의 갯수
		T = Integer.parseInt(st.nextToken()); // 원판 회전 수
		int[][] circularBoard = new int[N + 1][M]; // 원판 배열 // 1차원 - 원판 번호 // 2차원 - 원판 내 숫자들
		int[][] turnBoard = new int[T][3]; // 원판 돌리는 연산 배열

		// 원판 입력값 받기
		for (int n = 1; n <= N; n++)
		{
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) circularBoard[n][m] = Integer.parseInt(st.nextToken());
		}

		// 회전 연산 입력값 받기
		for (int t = 0; t < T; t++)
		{
			st = new StringTokenizer(br.readLine());
			turnBoard[t][0] = Integer.parseInt(st.nextToken()); // 돌리려는 원판 최대공약수
			turnBoard[t][1] = Integer.parseInt(st.nextToken()); // 방향 // 0이면 시계 / 1이면 반시계
			turnBoard[t][2] = Integer.parseInt(st.nextToken()); // 회전 칸 수 // 어차피 M 미만이라 부가적인 나머지 연산 불필요!
			// turnBoard[t][2] %= M; // 이 연산이 불필요하다는 말
		}

		// 임시 배열 for 회전 // swap 함수에서 tmp 자리를 대신하는 배열이라고 생각하면 됨!
		int[] tmp = new int[M];
		boolean[][] toRemove; // 제거 대상 수 표시용

		// 회전 연산 순서에 따라서
		for (int t = 0; t < T; t++)
		{
			toRemove = new boolean[N + 1][M]; // 제거 대상 회전 연산마다 리뉴얼
			int k = turnBoard[t][2]; // 회전 칸 수
			// 원판 돌리고
			if (turnBoard[t][1] == 0) // 시계 방향인 경우
			{
				for (int turningBoardNum = turnBoard[t][0]; turningBoardNum <= N; turningBoardNum += turnBoard[t][0])
				{
					for (int i = 0; i < M; i++) tmp[(i + k) % M] = circularBoard[turningBoardNum][i];
					for (int i = 0; i < M; i++) circularBoard[turningBoardNum][i] = tmp[i];
				}
			}
			else // 반시계 방향인 경우
			{
				for (int turningBoardNum = turnBoard[t][0]; turningBoardNum <= N; turningBoardNum += turnBoard[t][0])
				{
					for (int i = 0; i < M; i++) tmp[(M + i - k) % M] = circularBoard[turningBoardNum][i];
					for (int i = 0; i < M; i++) circularBoard[turningBoardNum][i] = tmp[i];
				}
			}
			// 원판 돌린 후 상태 확인용 주석
//			for (int n = 1; n <= N; n++) System.out.println(Arrays.toString(circularBoard[n]));


			// 다시 돌린 원판들 기준으로 인접하면서 같은 수가 1개라도 있으면 해당 수들을 모두 지우고

			/*
			[틀린 부분] 문제 해석에서 틀림!!
			 */

			// 누구 맘대로 다시 돌린 원판들을 기준으로 함? => 전체 원판을 기준으로 해야함!
			int adjCnt = 0; // 인접하면서 같은 수
			for (int turningBoardNum = 1; turningBoardNum <= N; turningBoardNum ++)
			{
				// 인접한 다른 원판에서 같은 위치 & 같은 번호인 경우 제거 // 제거 == Integer.MIN_VALUE로 만들기
				for (int i = 0; i < M; i++)
				{
					if (circularBoard[turningBoardNum][i] != Integer.MIN_VALUE) // 제거되지 않은 수에 대해서만 진행
					{
						int tNum = circularBoard[turningBoardNum][i]; // 현재 수

						// 안쪽 원판의 같은 위치의 수와 비교해서 제거 대상 표시 // turningBoardNum이 어차피 2 이상이므로 범위 고려 안해줘도 됨!
						if (circularBoard[turningBoardNum - 1][i] == tNum)
						{
							toRemove[turningBoardNum - 1][i] = true;
							toRemove[turningBoardNum][i] = true;
							adjCnt++;
						}

						// 바깥쪽 원판의 같은 위치의 수와 비교해서 제거 대상 표시 // 얘는 범위 체크 필수!
						if (turningBoardNum < N && circularBoard[turningBoardNum + 1][i] == tNum)
						{
							toRemove[turningBoardNum + 1][i] = true;
							toRemove[turningBoardNum][i] = true;
							adjCnt++;
						}
					}
				}

				// 같은 원판 내에서 인접한 수와 같은 번호인 경우 제거
				for (int m = 0; m < M; m++)
				{
					if (circularBoard[turningBoardNum][m] != Integer.MIN_VALUE) // 마찬가지로 이미 제거되지 않는 대상들에 대해서만 진행
					{
						// 현재 수보다 1칸 왼쪽에 있는 수와의 비교를 통해 제거 대상화
						if (circularBoard[turningBoardNum][(M - 1 + m) % M] == circularBoard[turningBoardNum][m]) {
							toRemove[turningBoardNum][(M - 1 + m) % M] = true;
							toRemove[turningBoardNum][m] = true;
							adjCnt++;
						}
						// 현재 수보다 1칸 오른쪽에 있는 수와의 비교를 통해 제거 대상화
						if (circularBoard[turningBoardNum][(m + 1) % M] == circularBoard[turningBoardNum][m]) {
							toRemove[turningBoardNum][(m + 1) % M] = true;
							toRemove[turningBoardNum][m] = true;
							adjCnt++;
						}
					}
				}
			}

			if (adjCnt != 0) // 인접하는 수가 1개라도 있다면
			{
				// 제거 대상 찾아서 제거
				for (int n = 1; n <= N; n++)
				{
					for (int m = 0; m < M; m++)
					{
						if (toRemove[n][m])
							circularBoard[n][m] = Integer.MIN_VALUE;
					}
				}
			}
			else // 없으면 다시 전체 원판 돌면서 갯수세고 합 구해서 평균 구하고 평균보다 큰 수는 -1 / 작은 수는 +1 해주기
			{
				int sum = 0;
				int numCnt = 0;
				for (int n = 1; n <= N; n++)
				{
					for (int m = 0; m < M; m++)
					{
						if (circularBoard[n][m] != Integer.MIN_VALUE) // 제거 되지 않은 대상만 고르기
						{
							sum += circularBoard[n][m];
							numCnt++;
						}
					}
				}
				if (numCnt != 0) // 0으로 나누면 안되니깐 예외처리 해주기!
				{
					double average = (double)sum / (double)numCnt;
					for (int n = 1; n <= N; n++)
					{
						for (int m = 0; m < M; m++)
						{
							if (circularBoard[n][m] != Integer.MIN_VALUE)
							{
								if ((double)circularBoard[n][m] > average) circularBoard[n][m]--;
								else if ((double)circularBoard[n][m] < average) circularBoard[n][m]++;
							}
						}
					}
				}
			}
		}
		int res = 0;
		for (int n = 1; n <= N; n++)
		{
			for (int m = 0; m < M; m++)
			{
				if (circularBoard[n][m] != Integer.MIN_VALUE) res += circularBoard[n][m];
			}
		}
		// 최종 원판 상태 확인용 주석
//		for (int n = 1; n <= N; n++) System.out.println(Arrays.toString(circularBoard[n]));
		ans.append(res);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}