import java.io.*;
import java.util.*;

/*
15657번 풀이
N 개의 수 중 M개를 고를 때
같은 수를 여러 번 골라도 됨 + 비 내림차순이어야 함

4 2
9 8 7 1

1 1
1 7
1 8
1 9
7 7
7 8
7 9
8 8
8 9
9 9

현재 뽑으려는 수를 고르려면 전의 뽑은 수를 알고 그것보단 같거나 뒤의 수를 뽑아야 함

 */

public class Main {

	static int N, M;
	static int[] nums;
	static StringBuilder ans;
	static void func (int picked, int last, int[] pickedNums)
	{
		if (picked == M)
		{
			for (int i = 0; i < N; i++)
			{
				if (pickedNums[i] > 0)
				{
					int cnt = pickedNums[i];
					while (cnt-- > 0) ans.append(nums[i]).append(" ");
				}
			}
			ans.append('\n');
			return ;
		}
		for (int i = last; i < N; i++)
		{
			pickedNums[i]++;
			func(picked + 1, i, pickedNums);
			pickedNums[i]--;
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
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(nums); // 오름차순 정렬
		func(0, 0, new int[N]);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}

/*
4
98 1 1 1
10 200 10 1

 */