import java.io.*;
import java.util.*;

/*
[이분 탐색 1654]_랜선 자르기_안상준

K개의 랜선을 잘라서
모두 길이가 같은 N개의 랜선을 만들어야 함

이미 자른 랜선은 붙힐 수 없고,
정수 길이 만큼씩 자르며,
자를 때 손실되는 길이는 없다.

또한 N개 이상을 만드는 것도 N개를 만드는 것에 포함

=> 즉, N개 이상만들 수 있는 최대 랜선 길이를 구해라

문제에서 K개의 랜선으로 N개의 랜선을 만들 수 없는 경우는 없다고 가정했기 때문에
적어도 길이를 1로 자르면 무조건 만들 수는 있음
+
잘라볼 수 있는 가장 최대의 길이는
등장하는 랜선들의 길이 중 가장 작은 랜선의 길이임!

이 두개의 매개변수로 잘랐을 때의 랜선 갯수를 이분 탐색하여
N개 이상이 되는 최대 길이를 찾는 문제!

이분탐색의 종류
상한 / 하한
상한은 특정 값을 초과하는 첫 위치를 반환
하한은 특정 값 이상인 첫 위치를 반환




 */


public class Main {
	static int N, K;
	static long max = 1;
	static long[] lengths;
	static long binarySearch(long left, long right) // 매개변수 초기값 1 & max
	{
		// 이분탐색 포인트는 갯수가 N보다 크거나 같다면 최대한 길이를 늘려보자는 것! => 중복 값이 있을 때 초과하는 첫 값을 찾아서 -1 => 상한 방식
		while (left <= right)
		{
			long mid = left + (right - left) / 2; // 현재 잘라볼 값
			int cnt = 0;

			for (int i = 0; i < K; i++) cnt += (lengths[i] / mid); // 잘랐을 때 유의미한 애들만 더하기 위해 몫으로 설정!
			// 아직 N개만큼 못 만들면 => 크기 더 줄여봐야 함
			if (cnt < N) right = mid - 1; // 갯수가 부족 => 길이 줄이기
			else left = mid + 1; // 갯수가 같거나 남음 => 길이 "최대한" 늘리기
		}
		return left - 1;
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken()); // 이미 가지고 있는 랜선의 개수 // 1 ~ 10000
		N = Integer.parseInt(st.nextToken()); // 필요한 랜선의 개수 // 1 ~ 1000000
		lengths = new long[K];
		for (int i = 0; i < K; i++)
		{
			lengths[i] = Integer.parseInt(br.readLine());
			max = max > lengths[i] ? max : lengths[i];
		}
		ans.append(binarySearch(1, max));
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}