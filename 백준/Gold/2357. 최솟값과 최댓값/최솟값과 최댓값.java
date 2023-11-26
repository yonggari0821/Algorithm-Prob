import java.io.*;
import java.rmi.activation.ActivationGroup_Stub;
import java.util.*;

public class Main {
	static StringBuilder ans = new StringBuilder();
	static int[] nums;
	static int[] segmentMaxTree;
	static int[] segmentMinTree;

	static void BUInit(int S)
	{
		for (int i = S; i < Math.min(segmentMaxTree.length, nums.length + S); i++)
		{
			segmentMaxTree[i] = nums[i - S];
			segmentMinTree[i] = nums[i - S];
		}
		for (int i = S - 1; i > 0; i--)
		{
			segmentMaxTree[i] = Math.max(segmentMaxTree[i * 2], segmentMaxTree[i * 2 + 1]);
			segmentMinTree[i] = Math.min(segmentMinTree[i * 2], segmentMinTree[i * 2 + 1]);
		}
	}

	static void BUQuery(int queryLeft, int queryRight, int S)
	{
		int max = 0, min = 1000000001;
		int left = S + queryLeft - 1;
		int right = S + queryRight - 1;
		while (left <= right)
		{
			if ((left & 1) != 0) {
				min = Math.min(min, segmentMinTree[left]);
				max = Math.max(max, segmentMaxTree[left++]);
			}
			left /= 2;
			if ( (right & 1) == 0) {
				min = Math.min(min, segmentMinTree[right]);
				max = Math.max(max, segmentMaxTree[right--]);
			}
			right /= 2;
		}
		ans.append(min).append(" ").append(max).append('\n');
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int S = 1;
		while (S < N) S *= 2;
		nums = new int[N];
		segmentMaxTree = new int[2 * S];
		segmentMinTree = new int[2 * S];
		Arrays.fill(segmentMinTree, 1000000001);
		for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(br.readLine());
		int[] a = new int[M];
		int[] b = new int[M];
		for (int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine());
			a[i] = Integer.parseInt(st.nextToken());
			b[i] = Integer.parseInt(st.nextToken());
		}
		BUInit(S);
		for (int i = 0; i < M; i++) BUQuery(a[i], b[i], S);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}
