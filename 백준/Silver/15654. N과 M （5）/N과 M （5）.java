import java.io.*;
import java.util.*;

public class Main
{
	static int[] nums;
	static int[] used;
	static StringBuilder ans;
	static int N, M;
	
	static void func (int picked)
	{
		if (picked == M + 1)
		{
			int[] tmpNumArr = new int[N + 1];
			for (int i = 0; i < N; i++)
			{
				if (used[i] != 0) tmpNumArr[used[i]] = nums[i];
			}
			for (int i = 1; i <= N; i++)
			{
				if (tmpNumArr[i] != 0) ans.append(tmpNumArr[i]).append(" ");
			}
			ans.append('\n');
			return ;
		}
		for (int i = 0; i < N; i++)
		{
			if (used[i] != 0) continue;
			used[i] = picked;
			func(picked + 1);
			used[i] = 0;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		ans = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[N];
		used = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(nums);
		func(1);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}