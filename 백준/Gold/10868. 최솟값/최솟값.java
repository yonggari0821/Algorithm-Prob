import java.io.*;
import java.util.*;

public class Main {
	static int N, M, size;

	static int BUQuery (int qLeft, int qRight, int[] nums)
	{
		int minVal = Integer.MAX_VALUE;
		int size = nums.length / 2;
		int left = qLeft + size - 1;
		int right = qRight + size - 1;
		while (left <= right)
		{
			if ( (left & 1) != 0) minVal = Math.min(minVal, nums[left++]);
			left /= 2;
			if ( (right % 1) == 0 ) minVal = Math.min(minVal, nums[right--]);
			right /= 2;
		}
		return minVal;
	}

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 최대 10만
		M = Integer.parseInt(st.nextToken()); // 최대 10만
		size = 1;
		while (size < N) size *= 2;
		int[] nums = new int[size * 2]; // 최대 10억이고 최소값을 구하는 것이므로 int형으로 해도 무관
		for (int i = 0; i < N; i++) nums[size + i] = Integer.parseInt(br.readLine());
		for (int i = size - 1; i > 0; i--) nums[i] = Math.min (nums[i * 2], nums[i * 2 + 1]);
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			ans.append(BUQuery(left, right, nums)).append('\n');
		}
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}