import java.io.*;
import java.util.*;

/*

가장 인접한 두 공유기의 거리가 최대가 되어야 함
일단 공유기들의 위치를 받아두고
1부터 공유기 (최댓값 - 최솟값) / (공유기 갯수 - 1)꺼자 이분탐색
이 때, 가능한 최대 거리를 구해야 하므로
안되는 거리 중 최솟값 - 1 하면 됨!

 */


public class Main {

	static int N, C;
	static StringBuilder sb = new StringBuilder();
	static int[] coordinateOfRouters;


	static int min = 1;
	static int max;

	static int binarySearch(int left, int right)
	{
		while (left <= right)
		{
			int mid = left + (right - left) / 2;
			if (cntIfPutByDist(mid) >= C)
			{
				left = mid + 1;
			}
			else
			{
				right = mid - 1;
			}
		}
		return left - 1;
	}

	static int cntIfPutByDist(int dist)
	{
		int cnt = 1;
		int prev = coordinateOfRouters[0];

		for (int i = 1; i < N; i++)
		{
			if (coordinateOfRouters[i] - prev >= dist)
			{
				cnt++;
				prev = coordinateOfRouters[i];
			}
		}
		return cnt;
	}


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		coordinateOfRouters = new int[N];
		for (int i = 0; i < N; i++) coordinateOfRouters[i] = Integer.parseInt(br.readLine());
		Arrays.sort(coordinateOfRouters);
		max = coordinateOfRouters[N - 1] - coordinateOfRouters[0];
		sb.append(binarySearch(min, max));
		System.out.println(sb.toString());
	}
}