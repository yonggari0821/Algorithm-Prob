import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static StringBuilder ans = new StringBuilder();
	static void func(int[] pickedNums, boolean[] pickedOrNot, int pickOrder)
	{
		if (pickOrder == M)
		{
			for (int i = 0; i < pickedNums.length; i++)
			{
				ans.append(pickedNums[i]).append(" ");
			}
			ans.append('\n');
			return ;
		}
		for (int i = 1; i <= N; i++)
		{
			if (pickedOrNot[i]) continue;
			pickedOrNot[i] = true;
			pickedNums[pickOrder] = i;
			func(pickedNums, pickedOrNot, pickOrder + 1);
			pickedOrNot[i] = false;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[] pickedNums = new int[M];
		boolean[] pickedOrNot = new boolean[N+1];
		func(pickedNums, pickedOrNot, 0);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}