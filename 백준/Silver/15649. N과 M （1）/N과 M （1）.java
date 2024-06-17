import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] pickedNums;
	static boolean[] used;
	static StringBuilder ans = new StringBuilder();
	static void func (int picked)
	{
		if (picked == M + 1)
		{
			for (int i = 1; i <= M; i++)
			{
				ans.append(pickedNums[i]).append(" ");
			}
			ans.append('\n');
			return ;
		}
		for (int i = 1; i <= N; i++)
		{
			if (used[i]) continue;
			used[i] = true;
			pickedNums[picked] = i;
			func(picked + 1);
			used[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		pickedNums = new int[M + 1];
		used = new boolean[N + 1];
		func(1);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}