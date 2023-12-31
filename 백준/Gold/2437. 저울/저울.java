import java.io.*;
import java.util.*;

/*
[2437]_저울_안상준
추의 갯수와 각 추의 무게가 주어졌을 때
해당 추들로 잴 수 없는 최소 값(단, 자연수) 구하기

최대 1000개의 추
최대 100만의 무게를 가진 추

1000개를 가지고 만들 수 있는 모든 무게의 조합
1000C1 + 1000C2 + 1000C3 + ... + 1000C1000 = 2의 1000제곱 == 2의 10제곱의 10제곱의 10제곱
2의 10제곱 == 1024 (대략 10의 3제곱으로 잡아도 10의 3제곱의 10제곱의 10제곱 == 10의 300제곱 => 계산 불가능!)

다른 방법이 필요

일단 가장 크기가 작은 추보다 더 작은 값은 못 재므로 가장 크기가 작은 추를 알아 놓고 해당 추의 무게가 1보다 크다면 답은 무조건 1!

+ 양팔 저울의 한쪽에만 올리는 것이기 때문에 한 쪽에 올린 값에서 나머지를 빼는 것은 불가능 => 무조건 더해가서 어떤 무게를 달성해야 함
예를 들어
7
3 1 6 2 7 30 1
의 경우 정렬하면
1 1 2 3 6 7 30

이 때 1부터 그 다음으로 값을 더해갈 건데
더하기 전에 여태까지의 누적합보다 더할 값이 크다면 중간에 만들 수 없는 수가 생김
1 => 다음 수인 1 만들 수 있음 => 더함
1 + 1 = 2 => 다음 수인 2 만들 수 있음 => 더함
1 + 1 + 2 = 4 => 다음 수인 3 만들 수 있음 => 더함
1 + 1 + 2 + 3 = 7 => 다음 수인 6 만들 수 있음 => 더함
1 + 1 + 2 + 3 + 6 = 13 => 다음 수인 7 만들 수 있음 => 더함
1 + 1 + 2 + 3 + 6 + 7 = 20 => 다음 수인 30 만들 수 없음
=> 20까진 만들 수 있으므로 그 다음 수인 21이 답!


 */


public class Main {
	static int minWeight = 1000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		int N = Integer.parseInt(br.readLine()); // 최대 1000
		int[] weights = new int[N]; // 1 ~ 100만
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
		{
			weights[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(weights);
		int curTotal = weights[0];
		if (curTotal > 1) ans.append(1);
		else {
			for (int i = 0; i < weights.length; i++) {
				if (curTotal < weights[i]) break;
				else curTotal += weights[i];
			}
			ans.append(curTotal);
		}
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}