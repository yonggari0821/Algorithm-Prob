import java.io.*;
import java.util.*;

/*
28099번 풀이
1) 각 숫자

 */


public class Main {
	static String yesOrNo (boolean isOdd)
	{
		if (isOdd) return "Yes";
		return "No";
	}

	static void query (int queryLeft, int queryRight, int[] tree)
	{
		int val = tree[queryLeft];
		int target = tree[queryRight];
		while (queryLeft <= queryRight)
		{
			if ( (queryLeft & 1) != 0 ) val = Math.max(tree[queryLeft++], val);
			queryLeft /= 2;
			if ( (queryRight & 1) == 0 ) val = Math.max(tree[queryRight--], val);
			queryRight /= 2;
		}
		if (val > target) isOdd = false;
	}

	static boolean isOdd;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++)
		{
			isOdd = true;
			int size = 1;
			Map<Integer, Integer> startMap = new HashMap<>();
			Map<Integer, Integer> endMap = new HashMap<>();
			int N = Integer.parseInt(br.readLine()); // 최대 20만
			while (size < N) size *= 2;
			int[] segmentTree = new int[size * 2];
			st = new StringTokenizer(br.readLine());
			for (int i = size; i < size + N; i++)
			{
				segmentTree[i] = Integer.parseInt(st.nextToken());
				if (!startMap.containsKey(segmentTree[i])) startMap.put(segmentTree[i], i);
				else endMap.put(segmentTree[i], i); // 여러 개면 자동적으로 더 뒤의 것으로 덮어써지므로 상관 없음!
			}
			// 세그먼트 트리는 최댓값 트리로 구성
			for (int i = size - 1; i > 0; i--) segmentTree[i] = Math.max(segmentTree[2 * i], segmentTree[2 * i + 1]);
			// endMap에 들어있는 수들을 대상으로 startMap에서 같은 수의 value 값과 함께 세그먼트 트리 탐방
			Iterator<Map.Entry<Integer, Integer>> iterator = endMap.entrySet().iterator();
			while (iterator.hasNext())
			{
				Map.Entry<Integer, Integer> keyAndValue = iterator.next();
				int num = keyAndValue.getKey();
				int endIndex = keyAndValue.getValue();
				query(startMap.get(num), endIndex, segmentTree);
				if (!isOdd) break;
			}
			ans.append(yesOrNo(isOdd));
			ans.append('\n');
		}
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}