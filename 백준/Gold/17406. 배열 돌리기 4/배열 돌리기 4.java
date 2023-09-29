import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K, minVal = Integer.MAX_VALUE; // K는 6이하
	static int[][] grid;
	static int[][] rotation;
	static int[] rotationOrder;
	static void doRotate(int[][] tgrid, int r, int c, int s)
	{
		for (int dist = 1; dist <= s; dist++)
		{
			int ur = r - dist;
			int dr = r + dist;
			int lc = c - dist;
			int rc = c + dist;
			int luv = tgrid[ur][lc];
			int ruv = tgrid[ur][rc];
			int ldv = tgrid[dr][lc];
			int rdv = tgrid[dr][rc];
			// 위쪽 가로줄 밀기 // ur고정 // rc부터 lc + 1까지 --
			for (int i = rc; i > lc + 1; i--) tgrid[ur][i] = tgrid[ur][i - 1];
			tgrid[ur][lc + 1] = luv;
			// 오른쪽 세로줄 밀기 // rc 고정 // dr부터 ur + 1까지 --
			for (int i = dr; i > ur + 1; i--) tgrid[i][rc] = tgrid[i - 1][rc];
			tgrid[ur + 1][rc] = ruv;
			// 아래 가로줄 밀기 // dr 고정 // lc부터 rc - 1까지 ++
			for (int i = lc; i < rc - 1; i++) tgrid[dr][i] = tgrid[dr][i + 1];
			tgrid[dr][rc - 1] = rdv;
			// 왼쪽 세로줄 밀기 // lc 고정 // ur부터 dr - 1까지 ++
			for (int i = ur; i < dr - 1; i++) tgrid[i][lc] = tgrid[i + 1][lc];
			tgrid[dr - 1][lc] = ldv;
		}
	}

	static void decideRotationOrder(int idx, int used)
	{
		if (idx == K)
		{
//			System.out.println(Arrays.toString(rotationOrder));
			int[][] tmp = new int[N][M];
			for (int r = 0; r < N; r++)
			{
				for (int c = 0; c < M; c++) tmp[r][c] = grid[r][c];
			}
			for (int k = 0; k < K; k++)
			{
				doRotate(tmp, rotation[rotationOrder[k]][0], rotation[rotationOrder[k]][1], rotation[rotationOrder[k]][2]);
			}
			int tval = Integer.MAX_VALUE;
			for (int r = 0; r < N; r++)
			{
				int trval = 0;
				for (int c = 0; c < M; c++) {
//					System.out.print(tmp[r][c] + " ");
					trval += tmp[r][c];
				}
//				System.out.println("");
				tval = tval < trval ? tval : trval;
			}
			minVal = minVal < tval ? minVal : tval;
		}
		for (int k = 0; k < K; k++)
		{
			if ((used & (1 << k)) != 0) continue;
			rotationOrder[idx] = k;
			decideRotationOrder(idx + 1, (used | (1 << k)));
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		grid = new int[N][M];
		rotation = new int[K][3];
		rotationOrder = new int[K];
		for (int r = 0; r < N; r++)
		{
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) grid[r][c] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < K; i++)
		{
			st = new StringTokenizer(br.readLine());
			rotation[i][0] = Integer.parseInt(st.nextToken()) - 1; // r
			rotation[i][1] = Integer.parseInt(st.nextToken()) - 1; // c
			rotation[i][2] = Integer.parseInt(st.nextToken()); // s
		}
		decideRotationOrder(0, 0);
		ans.append(minVal);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}