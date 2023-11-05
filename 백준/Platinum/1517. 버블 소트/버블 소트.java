import java.io.*;
import java.util.*;

/*
[1517번 풀이]

최대 50만개인 N개의 수열에 대해서 버블소트 수행 시 swap이 총 몇 번 일어나는 지 구하기
버블소트가 일어나는 횟수는 총 횟수는 넉넉 잡으면 50만의 제곱이므로 2500억 => Long형으로 만들기

시행착오 1)
왼쪽 자식과 오른쪽 자식 그리고 부모를 확인해서 더 큰 쪽을 부모에 배정하면서
1) 오른쪽 자식과 부모가 같음 ==> swap X
2) 왼쪽 자식과 부모가 같음 ==> swap O
=> Bottom - Up 방식으로 세그먼트 트리를 구현해 나가면서 풀려고 했던 시도

    5
   5 5
  3 5 5
 3 3 5 5   (2)
1 3 2 5 4

1 2 3 5 4
1 2 3 4 5

=> 2

    5
   5 5
  3 5 5    (1)
 3 2 5 5   (3)
3 2 1 5 4

2 3 1 5 4
2 1 3 5 4
2 1 3 4 5
1 2 3 4 5

=> 4

	   9
	  9 9
	 9 9 9
    9 9 9 9       (1)
   9 9 9 9 4      (1)
  8 9 9 9 4 4     (3)
 8 6 9 9 4 4 3    (4)
8 6 4 9 1 4 2 3

6 8 4 9 1 4 2 3
6 4 8 9 1 4 2 3

6 4 8 1 9 4 2 3
6 4 8 1 4 9 2 3
6 4 8 1 4 2 9 3
6 4 8 1 4 2 3 9

4 6 8 1 4 2 3 9
4 6 1 8 4 2 3 9
4 6 1 4 8 2 3 9
4 6 1 4 2 8 3 9
4 6 1 4 2 3 8 9

4 1 6 4 2 3 8 9
4 1 4 6 2 3 8 9
4 1 4 2 6 3 8 9
4 1 4 2 3 6 8 9

1 4 4 2 3 6 8 9
1 4 2 4 3 6 8 9
1 4 2 3 4 6 8 9

1 2 4 3 4 6 8 9
1 2 3 4 4 6 8 9

=> 20 <반례 발견>

시행 착오 2)
버블 소트 원리
1번 돌때마다 가장 큰 수가 오른쪽에 쌓임

세그먼트 트리를 최소 값으로 구현하고
각 라인 별로 맨 왼쪽값을 기준으로 두고
거기서부터 해당 라인의 끝까지 가면서 다음 값과 비교해서
1) 같으면 기준 값을 해당 값으로 바꾸고
2) 작으면 그냥 넘어가고
3) 크거나 해당 줄의 끝까지 가면 해당 인덱스 전까지를 카운트 해주고 기준 값을 해당 값으로 바꿈
cf) 이 때 마지막 리프 노드의 경우

       1
      1 1
     1 1 1
    1 1 1 1
   4 1 1 1 1    ( 4 )
  4 4 1 1 1 2   ( 4 )
 6 4 4 1 1 2 2  ( 6 )
8 6 4 9 1 4 2 3 ( 2 + 4 )

	   1
	  1 1
	 1 2 2
	1 2 2 4      ( 1 )
   1 2 2 4 4     ( 1 + 1 ) 이미 틀림
  1 2 2 4 4 m    ( 1 + 1 )
 1 2 2 4 4 m m
1 3 2 5 4 m m m

=> 반례 발견

답 - 클론 코딩 : https://loosie.tistory.com/328)

1)




 */

public class Main {

	// 세그먼트 트리에서 갯수 구하는 쿼리 함수
//	static long query(int[] tree, int target, int left, int right, int queryLeft, int queryRight)
//	{
//		if (queryRight < left || right < queryLeft) return 0;
//		else if (queryLeft <= left && right <= queryRight) return tree[target];
//		else {
//			int mid = ( left + right ) / 2;
//			return query(tree, target * 2, left, mid, 	queryLeft, queryRight) + query(tree, target * 2 + 1,mid + 1, right, queryLeft, queryRight);
//		}
//	}

	static long query(int[] tree, int qLeft, int qRight)
	{
		long tmpcnt = 0;
		int left = qLeft + tree.length / 2;
		int right = qRight + tree.length / 2;
		while (left <= right)
		{
			if ( (left & 1) != 0 ) tmpcnt += tree[left++];
			left /= 2;
			if ( (right & 1) == 0) tmpcnt += tree[right--];
			right /= 2;
		}
		return tmpcnt;
	}


	// 세그먼트 트리 업데이트 함수
	static void update(int[] tree, int target)
	{
		target += tree.length / 2;
		while (target > 0)
		{
			tree[target]++;
			target /= 2;
		}
	}
	static int N, size = 1;
	static int[] segmentTree;
	static long cnt = 0;

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		Map<Integer, Integer> numValueAsKeyAndIndexAsValue = new HashMap<>();
		while (size <= N) size *= 2;
		segmentTree = new int[size * 2];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			numValueAsKeyAndIndexAsValue.put(nums[i], i); // 현재 순서가 값 // 현재 수가 키
		}
		// 오름차순 정렬
		Arrays.sort(nums);
		// 0번째부터 N번째까지 중 작은 값부터 현재 값보다 작은 값들의 갯수를 세어가면서 update
		// 해당 수보다 작은 값들의 수는
		// 해당 수의 원래 인덱스(Map에 저장해뒀던)부터 뒤에 있는 수들의 갯수를 세면 됨!
		for (int i = 0; i < N; i++)
		{
			int idx = numValueAsKeyAndIndexAsValue.get(nums[i]);
			cnt += query(segmentTree, idx + 1, N - 1);
			update(segmentTree, idx);
		}
		ans.append(cnt);
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}