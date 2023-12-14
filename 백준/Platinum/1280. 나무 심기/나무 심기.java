import java.io.*;

/*
[세그먼트 트리 1280]_나무 심기_안상준

1. 각 나무는 X[i] 좌표에 심어지며,
2. 각 나무를 심는데 비용은 여태까지 심어진 모든 나무와의 거리
cf) 1번 나무를 심는 비용은 없음
cf) 모든 나무를 심는 비용의 곱을 구하는데, 이 때 당연히 2번부터 고려해야 함!

[틀린 시행착오 풀이]

경우의 수가 2가지로 나뉨
1] 현재까지 등장한 나무들의 최소 최대 범위 '밖'에 새로운 나무가 등장하는 경우
2] 현재까지 등장한 나무들의 최소 최대 범위 '안'에 새로운 나무가 등장하는 경우


[1]번 경우
===========================================
1번 나무 거리 : 0
2번 나무 거리 : 1번 나무와의 거리
3번 나무 거리 : 1번 나무와의 거리 + 2번 나무와의 거리
.
.
.
n번 나무 거리 : 1 ~ n-1번 나무와의 거리들의 함

0 1 2 3 4
5 4 3 6 7

0) 0
1) 1(-1) => 1
2) 2(-1-1) + 1(-1) = > 3
3) 1(-1-1+3) + 2(-1-1+3) + 3 => 6
4) 2(1+1) + 3(2+1) + 4(3+1) + 1 => 10

=> n) (n-1번째까지의 합) + (자신위치 - 자기-1번째위치) * 자기 번호

[2]번 경우
===========================================
0 1 2  3 4
1 8 15 1 3

0) 0
1) 7
2) 14(7+7) + 7 => 21
3) 0(14-14) + 7(-14) + 14(-14) => 21
4) 2 + 5 + 12 + 2 => 21

=> 3), 4) 의 경우 기존에 가장 큰 값과 같은 값이 나옴

=> 얘만 그런 것! 다른 경우에 꼭 안에 있다고 해서 그 전까지의 최댓값과 같은 값이 나오지 않음!

 */




/*
[참고한 풀이]
https://tttuer.tistory.com/225

결국
현재 심으려고 하는 나무의 좌표를 기준으로

왼쪽에 있는가? (더 작은가?) => 현재 좌표 * 현재 좌표보다 이전 나무 갯수 - 왼쪽 구간 합
오른쪽에 있는가? (더 큰가?) => 오른쪽 구간 합 - 현재 좌표 * 현재 좌표보다 이후 나무 갯수

를 구분해서
각 나무별로 갯수합과 구간합을 빠르게 구해서 풀어야 하는 문제!
=> 그래서 구간 갯수 & 구간 합 세그먼트 트리를 구현해야 함


*/


public class Main {

	static int N, S = 1;
	static long aaa = 1;
	static int[] trees;
	static long[] costs;
	static long[] segmentCntTree;
	static long[] segmentSumTree;


	static long BUqueryCnt(int ql, int qr)
	{
		long res = 0;
		int left = ql + S;
		int right = qr + S;
		while (left <= right)
		{
			if ( (left & 1) != 0 ) res += segmentCntTree[left++];
			if ( (right & 1) == 0 ) res += segmentCntTree[right--];
			left /= 2;
			right /= 2;
		}
		return res;
	}
	static long BUquerySum(int ql, int qr)
	{
		long res = 0;
		int left = ql + S;
		int right = qr + S;
		while (left <= right)
		{
			if ( (left & 1) != 0 ) res += segmentSumTree[left++];
			if ( (right & 1) == 0 ) res += segmentSumTree[right--];
			left /= 2;
			right /= 2;
		}
		return res;
	}

	static void BUupdateCnt(int target)
	{
		int node = target + S;
		segmentCntTree[node] += 1;
		node /= 2;
		while (node > 0)
		{
			segmentCntTree[node] = segmentCntTree[node * 2] + segmentCntTree[node * 2 + 1];
			node /= 2;
		}
	}
	static void BUupdateSum(int target)
	{
		int node = target + S;
		segmentSumTree[node] += target;
		node /= 2;
		while (node > 0)
		{
			segmentSumTree[node] = segmentSumTree[node * 2] + segmentSumTree[node * 2 + 1];
			node /= 2;
		}
	}

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
		N = Integer.parseInt(br.readLine()); // 나무 갯수 // 2 이상 20만 이하
		while (S < 200001) S *= 2; // 0 ~ 200000까지의 좌표값들을 인덱스로 해당 좌표에 나무 갯수를 저장해둬야 하므로
		trees = new int[N];
		costs = new long[N];
		segmentCntTree = new long[2 * S];
		segmentSumTree = new long[2 * S];
		for (int i = 0; i < N; i++) {
//			System.out.println("[ i = " + i + " ]");
			trees[i] = Integer.parseInt(br.readLine()); // 각 나무들의 좌표
			// 먼저 제일 처음 나무의 경우 그냥 업데이트만 해야되므로 하나로 크게 묶는다.
			if (i != 0)
			{
				long leftSum = BUqueryCnt(0, trees[i] - 1) * trees[i] - BUquerySum(0, trees[i] - 1);
				long rightSum = BUquerySum(trees[i] + 1, 200000) - BUqueryCnt(trees[i] + 1, 200000) * trees[i];
//				System.out.println("leftSum = " + leftSum);
//				System.out.println("righttSum = " + rightSum);
				costs[i] = (leftSum + rightSum) % 1000000007;
				aaa = aaa * costs[i] % 1000000007;
			}
			// 업데이트 해줘야 함!
			BUupdateCnt(trees[i]); // 원래 들어있는 값에 현재 좌표값이 자동으로 더해지고 업데이트 되게 됨!
			BUupdateSum(trees[i]); // 원래 들어있는 값에 현재 좌표값이 자동으로 더해지고 업데이트 되게 됨!
		}
		ans.append(aaa);
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}