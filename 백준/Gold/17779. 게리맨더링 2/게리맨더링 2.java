import java.io.*;
import java.util.*;

public class Main {
	static int N, minGap = Integer.MAX_VALUE, total;
	static int[][] A;
	static int[][] ward;
	static int sum(int area, int x, int y, int d1, int d2)
	{
		int res = 0;
		if (area == 1)
		{
			for (int r = 1; r < y; r++)
			{
				for (int c = 1; c <= x + d1; c++)
				{
					if (r + c < x + y) res += A[r][c];
					else break;
				}
			}
		}
		else if (area == 2)
		{
			for (int c = N; c > x + d1 ; c--)
			{
				for (int r = 1; r <= y - d1 + d2; r++)
				{
					if (c - r > (x + d1) - (y - d1)) res += A[r][c];
					else break;
				}
			}
		}
		else if (area == 3)
		{
			for (int c = 1; c < x + d2; c++)
			{
				for (int r = N; r >= y; r--)
				{
					if (r - c > y - x) res += A[r][c];
					else break;
				}
			}
		}
		else if (area == 4)
		{
			for (int r = N; r > y - d1 + d2; r--)
			{
				for (int c = N; c >= x + d2 ; c--)
				{
					if (r + c > x + d2 + y + d2) res += A[r][c];
					else break;
				}
			}
		}
		return res;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		A = new int[N + 1][N + 1];
		total = 0;
		for (int r = 1; r <= N; r++)
		{
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
				total += A[r][c];
			}
		}
		int[] populationPerWard = new int[6];
		int notArea5;
		int max;
		int min;
		for (int x = 1; x <= N - 2; x++)
		{
			for (int y = 2; y <= N - 1; y++)
			{
				for (int d1 = 1; d1 <= y - 1; d1++)
				{
					for (int d2 = 1; d2 <= N - y; d2++)
					{
						if (x + d1 + d2 > N) break;
						notArea5 = 0;
						max = Integer.MIN_VALUE;
						min = Integer.MAX_VALUE;
						for (int area = 1; area <= 4; area++)
						{
							populationPerWard[area] = sum(area, x, y, d1, d2);
							max = max > populationPerWard[area] ? max : populationPerWard[area];
							min = min < populationPerWard[area] ? min : populationPerWard[area];
							notArea5 += populationPerWard[area];
						}
						populationPerWard[5] = total - notArea5;
						max = max > populationPerWard[5] ? max : populationPerWard[5];
						min = min < populationPerWard[5] ? min : populationPerWard[5];
						int gap = max - min;
						minGap = minGap < gap ? minGap : gap;
					}
				}
			}
		}
		ans.append(minGap);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}