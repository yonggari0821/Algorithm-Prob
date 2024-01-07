import java.io.*;
import java.util.*;

/*
[이분탐색 2512]_예산_안상준

예산 요청들에 대해서
모두 감당 가능하면 모두 지급
안되면 상한선을 정해서 그 이상의 것들은 상한선까지만 지급
단 최대한의 예산을 배정할 것!!

 */

public class Main {
	static int N, budgetLimit;
	static int[] requests;

	static int findUpperLimit()
	{
		int left = 0;
		int right = budgetLimit;
		while (left <= right) {
			int mid = (left + right) / 2;
			int sum = 0;
			for (int i = 0; i < N; i++) sum += Math.min(mid, requests[i]);
			// 오바됨 => mid 줄이기
			if (sum > budgetLimit) right = mid - 1;
			// 오바는 안됨 => 최대한 늘릴 수 있을 때까지 늘려보기!
			else left = mid + 1;
		}
		return left - 1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		requests = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int sum = 0; // 최대 범위 10억 고려하면 int로 해도 무방!
		int max = 0;
		for (int i = 0; i < N; i++) {
			requests[i] = Integer.parseInt(st.nextToken());
			sum += requests[i];
			max = max > requests[i] ? max : requests[i];
		}
		budgetLimit = Integer.parseInt(br.readLine());
		if (budgetLimit >= sum) System.out.println(max);
		else
		{
			int ul = findUpperLimit();
//			int res = 0;
//			for (int i = 0; i < N; i++) res += Math.min(ul, requests[i]);
			System.out.println(ul);
		}
		br.close();
	}
}