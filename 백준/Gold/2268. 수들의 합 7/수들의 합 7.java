import java.io.*;
import java.util.*;

/*
[세그먼트 트리 2268]_수들의 합 7_안상준
N개 만큼 주어지는 수들의 배열 A[N]을 대상으로
M개 만큼 주어지는 두 연산을 수행해서 Sum 연산의 경우 결과 값을 출력하는 문제

1) Sum 연산
Sum(a, b)
=> A[a] ~ A[b]의 구간 합 구하기

2) Modify 연산
Modify(a, b)
=> A[a] = b로 바꾸기

N의 범위 1~100만
M의 범위 1~100만
구간 합을 상당히 여러 번 구해야 하고
해당 구간의 값들이 상당히 여러 번 바뀌어야 하므로
=> Segment Tree로 풀이!

 */


public class Main {

	static int N, M, S = 1;
	static long[] nums;
	static long[] segmentSumTree;

//	static long TDInit(int left, int right, int node) {
//		if (left >= N) return 0; // 원래 수들의 배열 이상의 트리 구조를 만드는 필요에 의해 만들어진 공간
//
//		int mid = (left + right) / 2;
//
//		// 내부노드 left != right // 리프노드 left == right
//		if (left != right) // 내부 노드의 경우 자기 두 자식의 구간 값들의 연산 값을 모두 포함한 값
//			segmentSumTree[node] = TDInit(left, mid, node * 2) + TDInit(mid + 1, right, node * 2 + 1);
//		else // 리프 노드의 경우 원래 배열의 값을 인덱스만 맞춰서 그대로 가져다 쓰기
//			return segmentSumTree[node] = nums[node - S];
//		return segmentSumTree[node];
//	}

	static void TDUpdate(int left, int right, int node, int target, long diff)
	{
		// 연관 없음
		if (right < target || left > target) return ;
		// 연관 있음
		else
		{
			segmentSumTree[node] += diff;
			if (left != right) // 내부 노드면 자기 자식들에도 반영해주기!
			{
				int mid = (left + right) / 2;
				// 왼쪽 자식
				TDUpdate(left, mid, node * 2, target, diff);
				// 오른쪽 자식
				TDUpdate(mid + 1, right, node * 2 + 1, target, diff);
			}
			// 리프 노드면 아무것도 안해도 됨! 어차피 리프 노드 전 마지막 내부노드에서 해당 리프노드에도 값을 반영하도록 뿌려놨기 때문에!
		}
	}

	static long TDQuery(int left, int right, int node, int queryLeft, int queryRight)
	{
		// 전체 미포함
		if (right < queryLeft|| queryRight < left) return 0;
		// 전체 포함
		else if (queryLeft <= left && right <= queryRight) return segmentSumTree[node];
		// 부분 포함
		else {
			int mid = (left + right) / 2;
			long leftVal = TDQuery(left, mid, node * 2, queryLeft, queryRight);
			long rightVal = TDQuery(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
			return leftVal + rightVal;
		}
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
//		nums = new long[N];
		while (S < N) S *= 2;
		segmentSumTree = new long[2 * S];
		for (int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int sumOrModify = Integer.parseInt(st.nextToken());
			if ((sumOrModify & 1) == 0) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int left = Math.min(a, b);
				int right = Math.max(a, b);
				ans.append(TDQuery(1, S,1, left, right)).append('\n');
			}
			else
			{
				int target = Integer.parseInt(st.nextToken());
//				System.out.println("target = " + target);
				long diff = Integer.parseInt(st.nextToken()) - segmentSumTree[S + target - 1];
//				System.out.println("diff = " + diff);
				TDUpdate(1, S, 1, target, diff);
			}
//			System.out.println(Arrays.toString(segmentSumTree));
		}
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}