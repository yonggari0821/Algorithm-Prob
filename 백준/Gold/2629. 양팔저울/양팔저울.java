import java.io.*;
import java.util.*;

/*
[풀이]

추 갯수 최대 30개고
그걸로 가능한 무게 조합은 3의 30제곱 == 205 8911 3209 4649 (약 205조)
(해당 무게추를 사용하지 않는 경우 + 해당 무게 추를 구슬과 [같은(-) / 다른(+)] 편에 놓는 경우)
당연히 모든 경우의 수를 살펴봐서는 안 됨!

모든 경우의 수를 살펴보지 않고 어떻게 알맞은 무게 조합만 고려할 것인가?

배낭문제 방식으로 경우의 수를 줄여야 함
작은 추부터 고려하되, 고려하는 순서를 인덱스로 가지고
해당 인덱스까지 해당 무게가 이미 체크 되어 있으면 바로 리턴(뒤로 가도 같은 경우이므로)


일단,
구슬의 무게는 40000 이하고 최대 7개
추의 무게는 500이하고 최대 30개 => 최소 -15000부터 최대 15000
확인하려는 구슬의 무게가 15000 초과시 어차피 잴 수 없음!




 */

public class Main {
	static char YesOrNo(boolean measurable)
	{
		if (measurable) return 'Y';
		return 'N';
	}

	static boolean[][] possibleWeights;
	static int[] balanceWeights;
	static int[] marvels;
	static void putBalanceWeight(int idx, int weight)
	{
		if (possibleWeights[idx][weight]) return;
		possibleWeights[idx][weight] = true;
		if (idx == balanceWeightNum) return;

		putBalanceWeight(idx + 1, weight - balanceWeights[idx + 1]);
		putBalanceWeight(idx + 1, weight);
		putBalanceWeight(idx + 1, weight + balanceWeights[idx + 1]);
	}
	static int balanceWeightNum, marvelNum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		balanceWeightNum = Integer.parseInt(br.readLine()); // 추 갯수 // 최대 30개
		balanceWeights = new int[balanceWeightNum + 1]; // 추 무게들 (오름차순이고 최대 500이며 동일한 값도 나올 수 있음!)
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int bw = 1; bw <= balanceWeightNum; bw++) balanceWeights[bw] = Integer.parseInt(st.nextToken());

		marvelNum = Integer.parseInt(br.readLine()); // 구슬 갯수 // 최대 7개
		marvels = new int[marvelNum]; // 구슬 무게들 // 최대 40000
		st = new StringTokenizer(br.readLine());
		for (int mw = 0; mw < marvelNum; mw++) marvels[mw] = Integer.parseInt(st.nextToken());

		possibleWeights = new boolean[balanceWeightNum + 1][30001];
		putBalanceWeight(0, 15000);

		for (int i = 0; i < marvelNum; i++) {
			if (marvels[i] > 15000) ans.append('N');
			else ans.append(YesOrNo(possibleWeights[balanceWeightNum][marvels[i] + 15000]));
			if (i != marvelNum - 1) ans.append(" ");
		}
		br.close();
		System.out.println(ans.toString());
	}
}

/*
3
20 35 50
1
5
Y

3
1 1 100
1
50
N

30
500 500 500 500 500 500 500 500 500 500 500 500 500 500 500 500 500 500 500 500 500 500 500 500 500 500 500 500 500 500
1
15000
Y
 */