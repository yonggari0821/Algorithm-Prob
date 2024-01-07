import java.io.*;
import java.util.*;

/*
[브루트포스 3085]_사탕 게임_안상준

N * N 보드에 총 4가지 색깔의 사탕
C - 빨강 / P - 파랑 / Z - 초록 / Y - 노랑
이 주어지고
한 번만 인접한 서로 다른 색상의 사탕을 교환해서
가장 같은 색상 연속으로 많이 이어져있는 행 또는 열의 사탕을 먹을 때 최댓값 구하기

일단 최대 50 x 50이므로
인접한 == 상하좌우로 생각
4의 50승 == 2의 100승
모두 고려하면 바로 타임아웃!

생각해보면 교환은 쌍방의 개념이므로
현재 고른 칸 기준으로 우측과 아래측만 고려하면 됨!
또 행과 열 모두 마지막 행과 열은 고려할 필요 자체가 없음!

 */

public class Main {
	static int N, max = 1;

	static int candyColors(char color)
	{
		if (color == 'C') return 0;
		if (color == 'P') return 1;
		if (color == 'Z') return 2;
		else return 3;
	}
	static int[][] grid;

	static void check()
	{
		int cur;
		int cnt;
		for (int r = 0; r < N; r++)
		{
			cur = 0;
			cnt = 1;
			while (cur < N - 1)
			{
				if (grid[r][cur] == grid[r][cur + 1]) cnt++;
				else cnt = 1;
				max = max > cnt ? max : cnt;
				cur++;
			}
		}
		for (int c = 0; c < N; c++)
		{
			cur = 0;
			cnt = 1;
			while (cur < N - 1)
			{
				if (grid[cur][c] == grid[cur + 1][c]) cnt++;
				else cnt = 1;
				max = max > cnt ? max : cnt;
				cur++;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		grid = new int[N][N];
		for (int r = 0; r < N; r++)
		{
			String str = br.readLine();
			for (int c = 0; c < N; c++) grid[r][c] = ( 1 << ( candyColors(str.charAt(c)) ) );
			// 빨강 1 / 파랑 2 / 초록 4 / 노랑 8으로 저장
		}

		for (int r = 0; r < N; r++)
		{
			for (int c = 0; c < N; c++)
			{
				// 우측
				if ( c < N - 1 && (grid[r][c] & grid[r][c + 1]) == 0)
				{
					int tmp = grid[r][c];
					grid[r][c] = grid[r][c + 1];
					grid[r][c + 1] = tmp;
//					System.out.println("[before]");
//					for (int i = 0; i < N; i++) System.out.println(Arrays.toString(grid[i]));
					check();
//					System.out.println("[after]");
//					for (int i = 0; i < N; i++) System.out.println(Arrays.toString(grid[i]));
					tmp = grid[r][c];
					grid[r][c] = grid[r][c + 1];
					grid[r][c + 1] = tmp;
				}
				// 아래측
				if ( r < N - 1 && (grid[r][c] & grid[r + 1][c]) == 0)
				{
					int tmp = grid[r][c];
					grid[r][c] = grid[r + 1][c];
					grid[r + 1][c] = tmp;
//					System.out.println("[before]");
//					for (int i = 0; i < N; i++) System.out.println(Arrays.toString(grid[i]));
					check();
//					System.out.println("[after]");
//					for (int i = 0; i < N; i++) System.out.println(Arrays.toString(grid[i]));
					tmp = grid[r][c];
					grid[r][c] = grid[r + 1][c];
					grid[r + 1][c] = tmp;
				}
			}
		}
		ans.append(max);
		System.out.println(ans.toString());
		br.close();
	}
}