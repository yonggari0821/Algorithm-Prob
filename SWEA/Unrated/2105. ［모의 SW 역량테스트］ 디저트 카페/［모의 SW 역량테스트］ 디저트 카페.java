import java.io.*;
import java.util.StringTokenizer;

public class Solution {
	static int max;
	static int N;
	static int[] dr = {0, 1, 1, -1, -1};
	static int[] dc = {0, -1, 1, 1, -1};
	static int[][] grid;
	static void func (int sr, int sc, int r, int c, int dir, boolean[] usedNum, int sum)
	{
		if (usedNum[grid[sr][sc]] && r == sr && c == sc) // usedNum[grid[r][c]] (처음이 아니면서) r == sr && c == sc (다시 처음으로 돌아왔음) ==> 유효한 사각형
		{
			max = max > sum ? max : sum;
			return ;
		}
		if (usedNum[grid[r][c]]) {
			return ;
		}
		usedNum[grid[r][c]] = true;
		int nr = r + dr[dir];
		int nc = c + dc[dir];
		if (nr >= 0 && nc >= 0 && nr < N && nc < N) func(sr, sc, nr, nc, dir, usedNum, sum + 1);
		if (dir != 4) {
			int n2r = r + dr[dir + 1];
			int n2c = c + dc[dir + 1];
			if (n2r >= 0 && n2c >= 0 && n2r < N && n2c < N) func(sr, sc, n2r, n2c, dir + 1, usedNum, sum + 1);
		}
		usedNum[grid[r][c]] = false;
	}
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++)
		{
		    ans.append("#" + t + " ");
			max = -1;
			N = Integer.parseInt(br.readLine());
			grid = new int[N][N];
			for (int r = 0; r < N; r++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) grid[r][c] = Integer.parseInt(st.nextToken());
			}
			for (int r = 0; r < N; r++)
			{
				for (int c = 0; c < N; c++) {
					func(r, c, r, c, 1, new boolean[101], 0);
				}
			}
			ans.append(max);
			ans.append('\n');
		}

    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}