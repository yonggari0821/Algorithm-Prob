import java.io.*;
import java.util.*;

/*
1306번 풀이

홍준이의 현재 위치의 인덱스를 cur이라고 할 때,
cur-M+1 부터 cur+M-1 까지 중 최대 빛의 세기 중 가장 센(높은) 빛의 세기만을 본다.

구간합을 총 N - M + 1 만큼 구해야 하는데
N과 M의 범위를 생각해보았을 때
구간의 최대 값을 모든 경우에 탐색으로 구하면 당연히 시간초과가 발생

따라서 세그먼트 트리르 활용해서 구간의 최대값을 상수시간으로 구할 수 있도록 해야 함!

세그먼트 트리 구현부터!



 */

public class Main {
	static int N, M;

	// Bottom Up 방식의 세그먼트 트리 구현
	/*
	골자는 세그먼트 트리 사이즈를 먼저 구한 후에 세그먼트 트리 뼈대부터 만들고
	리프 노드 시작점 size부터 원래 배열의 값들을 배정 후에
	2개씩 잡아서 부모노드로 올라가기!
	 */
	static void BUinit(int[] segmentTree, int[] nums)
	{
		// 리프 노드부터 채우기
		int size = segmentTree.length / 2;
		for (int i = 0; i < nums.length; i++) segmentTree[i + size] = nums[i];
		// 구간 최대 값으로 채워야 되니깐 Math.max 이용!
		for (int i = size - 1; i > 0; i--) segmentTree[i] = Math.max(segmentTree[i * 2], segmentTree[i * 2 + 1]);
	}

	// Bottom Up 방식의 구간 최대 값 쿼리 구현
	/*
	만들어놓은 세그먼트 트리에서 구간 값을 구할 때,
	queryLeft와 queryRight의 값을 이용해서
	리프노드부터 탐색을 시작 => left와 right를 queryLeft, queryRight + size
	left와 right를 경계 값으로 생각했을 때 경계가
	1) 두 자식 중 둘 다를 포함하는 경우 (짝수 인덱스) => 두 자식 모두 포함하니깐 그냥 부모의 값으로 대신 가능!
	2) 두 자식 중 오른쪽/왼쪽 자식만 포함하는 경우 (홀수 인덱스) => 해당 자식만 포함할 것!
	 */
	static int BUQuery(int queryLeft, int queryRight, int[] segmentTree)
	{
		int maxVal = 0;
		int size = segmentTree.length / 2;
		int left = queryLeft + size - 1; // n번째 => 실제로는 n-1번째 이므로!
		int right = queryRight + size - 1;
		while (left <= right)
		{
			if ((left & 1) != 0) maxVal = Math.max(maxVal, segmentTree[left++]); // 오른쪽 자식만 포함하는 경우 해당 값 고려해주고
			left /= 2; // 부모로 이동 // 두 자식 모두 포함하는 경우 바로 부모로 이동하게 됨!
			if ((right & 1) == 0) maxVal = Math.max(maxVal, segmentTree[right--]); // 왼쪽 자식만 포함하는 경우 해당 값 고려해주기
			right /= 2;
		}
		return maxVal;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 홍준 코스 길이 // 최대 100만
		M = Integer.parseInt(st.nextToken()); // 홍준 시야 범위 // 최대 100만
		int[] nums = new int[N]; //
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
		// 세그먼트 트리 구축 // Bottom Up 방식 사용
		int size = 1;
		while (size < N) size *= 2;
		int[] segmentTree = new int[size * 2];
		BUinit(segmentTree, nums);
		for (int m = M; m <= N - M + 1; m++) ans.append(BUQuery(m - M + 1, m + M - 1, segmentTree)).append(" ");
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}