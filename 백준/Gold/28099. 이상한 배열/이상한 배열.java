import java.io.*;
import java.util.*;

/*
[ 28099]_이상한 배열_안상준

길이가 N인 배열 A이
1) Ai = Aj를 만족하는 정수 1 <= i,j <= N과
2) i < k < j를 만족하는 정수 k에 대해,
3) 항상 Ak <= Ai를 만족할 경우
A를 "이상한 배열"이라고 하기로 함

테스트 케이스 최대 20만개
배열의 길이인 N은 최대 20만개

이상한 배열이면 Yes // 아니면 No를 출력할 것

<풀이>
먼저 조건 1) 이 의미하는 건 "배열 내에서 값이 같은 원소가 있다면"이고
해당 원소들 사이에 있는 원소들은 그 같은 값의 원소보다 작거나 같아야 한다는 뜻!

예를 들면
2 1 1 1 2 => 이상한 배열
2 1 3 1 2 => 이상하지 않은 배열

1) 같은 숫자가 있음 == 해당 숫자가 2번 이상 등장함 // 모든 수들의 범위는 최소 1, 최대 20만
=> 따라서 수의 범위인 1 ~ 20만을 원래 배열로 하는 최댓값 세그먼트 트리를 만들고
2) Map을 사용해서 이미 등장한 적이 있으면 그때의 인덱스와 현재 인덱스 사이의 구간 최댓값 <= 현재 값인지 체크 (그리고 다시 현재 인덱스 집어넣어야 함!)
=> 한 개라도 아니면 이상하지 않은 배열
=> 모두 괜찮으면 이상한 배열
3) 등장한 적이 없으면 그냥 Map에 새로 넣기만 하면 됨

 */


public class Main {

	static int[] nums;
	static int[] segmentMaxTree;
	static int N, S;

	static int TdInit(int left, int right, int node) // 초기 값 left = 0, right = 원래 배열의 끝 값, node = 1(세그먼트 트리 구조 상 0은 빼고 생각!)
	{
		if (left > N) return 0; // 관련 없는 곳은 관련 없는 값을 return

		// 여기 부턴 관련이 조금이라도 있는 구간
		if ( left != right ) // 만약 내부 노드라면
		{
			int mid = (left + right) / 2;  // 중앙값을 구해서
			segmentMaxTree[node] = Math.max(TdInit(left, mid, node * 2), TdInit(mid + 1, right, node * 2 + 1)); // 자기 왼쪽 자식과 오른쪽 자식의 연산 값을 리턴
		}
		else segmentMaxTree[node] = nums[left]; // 리프 노드라면 바로 그 값을 리턴 // 이 때, 헷갈리는 것은 node와 left // node는 현재 실질적으로는 세그먼트 트리 배열의 절반 사이즈를 left에 더한 값임 // left는 말 그대로 현재 node가 참조하고 있는 범위의 왼쪽 경계 값임!
		return segmentMaxTree[node];
	}

	static int TdQuery(int left, int right, int node, int queryLeft, int queryRight)
	{
		if (right < queryLeft || queryRight < left) return 0; // 관련 없는 구간은 관련 없는 값! // 최댓값 트리일 때는 제일 작은 값 // 최소값 트리일 때는 제일 큰 값 // 구간 합 트리일 때는 0 // 구간 곱 트리일 때는 1 등등!
		else if (queryLeft <= left && right <= queryRight) return segmentMaxTree[node];// 완전히 포함되는 경우 바로 그 값을 가져다 씀
		else // 부분만 포함되는 경우 => 필요한 부분만 가져다 써야 함 => 계속해서 자식까지 내려가면 결국 온전히 담기거나 아예 관련 없는 부분이 나오므로 자식으로 일단 넘기자!
		{
			int mid = (left + right) / 2;
			return Math.max( TdQuery(left, mid, node * 2, queryLeft, queryRight), TdQuery(mid + 1, right, node * 2 + 1, queryLeft, queryRight) );
		}
	}

	static boolean isOdd;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		Map<Integer, Integer> map;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++)
		{
			S = 1;
			isOdd = true;
			map = new HashMap<>();
			N = Integer.parseInt(br.readLine());
			nums = new int[N];
			while (S < N) S *= 2;
			segmentMaxTree = new int[2 * S];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
			TdInit(0, N - 1, 1);
			for (int i = 0; i < N; i++)
			{
				int isExist = map.getOrDefault(nums[i], -1);
				if (isExist != -1)
				{
					if (TdQuery(0, N - 1, 1, isExist, i) > nums[i]) {
						isOdd = false;
						break;
					}
				}
				map.put(nums[i], i);
			}
			if (isOdd) ans.append("Yes");
			else ans.append("No");
			ans.append('\n');
		}
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}