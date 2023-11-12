import java.io.*;
import java.util.*;


/*
[17951 풀이]
0점부터 20 x 10의 5승까지의 값 중 구간합 배열에서
값들을 탐색해서 그 값 이상이 될 때마다 카운트해서


 */
public class Main {

	static int N, K;

	static int func (int[] nums)
	{
		int min = 0;
		int max = 2000000;
		while (min <= max)
		{
			int mid = (min + max) / 2;
			int cnt = 0;
			int val = 0;
			for (int i = 0; i < nums.length; i++)
			{
				val += nums[i];
				if (val >= mid)
				{
					cnt++;
					val = 0;
				}
			}
			if ( cnt >= K ) min = mid + 1;
			else max = mid - 1;
		}
		return max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int[] nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
		ans.append(func(nums));
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}