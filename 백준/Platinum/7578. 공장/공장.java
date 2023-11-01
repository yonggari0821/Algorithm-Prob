/*
import java.io.*;
import java.util.*;

/*
7578번 풀이
N개의 서로 다른 식별 번호를 가진 기계들
서로 다른 줄에 같은 식별번호를 가진 기계들 간 케이블 연결
이 때, 서로 '교차'하는 케이블의 갯수
=> '교차 판단을 어떻게 할 것인가?'

=> 도저히 모르겠어서 아래 주소의 블로그 풀이를 참고했습니다.
https://steady-coding.tistory.com/137

첫 번째 배열의 값과 같은 두 번째 배열의 값에 1을 표시하고
그 뒤로 1이 표시된 것이 있는 지를 확인해서 해당 갯수 만큼(교차된 것이므로) 더해줌

 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static long[] tree; // 세그먼트 트리

	// left부터 right 중에 이미 방문한 적이 있는 갯수
	public static long sum(int start, int end, int node, int left, int right) {
		// 구하려는 범위와 무관한 범위
		if (end < left || right < start) return 0;

		// 전체가 다 포함된 경우
		if (left <= start && end <= right) return tree[node];

		// 일부만 포함된 경우
		int mid = (start + end) / 2;
		return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
	}

	public static void update(int start, int end, int node, int idx, int dif) {
		// 무관한 범위는 아무련 변화 없이 바로 리턴
		if (idx < start || idx > end) return;
		// 여기서 부턴 유관하다는 의미이므로 값 반영
		tree[node] += dif;
		// 리프노드까지 쭉 자식으로 내려가면서 반영
		if (start != end) {
			int mid = (start + end) / 2;
			update(start, mid, node * 2, idx, dif);
			update(mid + 1, end, node * 2 + 1, idx, dif);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] A = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) A[i] = Integer.parseInt(st.nextToken());

		Map<Integer, Integer> B = new HashMap<>(); // key - 식별번호, value - 인덱스
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int val = Integer.parseInt(st.nextToken()); // 받은 값을 key로 해당 값의 인덱스를 해쉬맵에 저장
			B.put(val, i);
		}

		tree = new long[N * 4]; // 이건 몰랐는데 그냥 넉넉하게 * 4를 해버리면 모든 경우를 포함해버릴 수 있네요.
		long ans = 0; // 교차하는 케이블 갯수 => 반드시 N의 범위가 최대 50만이므로 long형으로 선언해야 함!

		for (int i = 1; i <= N; i++) {
			int valA = A[i]; // A 배열에서의 위치
			int valB = B.get(valA); // A[i]와 같은 식별번호가 있는 인덱스를 찾음. // B 배열에서의 위치

			// valB보다 큰 인덱스 중에 이미 방문한 적 있는 인덱스의 개수를 구함.
			ans += sum(1, N, 1, valB + 1, N);

			// valB를 방문했다는 의미로 1을 더해 줌.
			// 동시에 valB이 포함된 구간합은 모두 1씩 더해질 것임. => 구간 update!
			update(1, N, 1, valB, 1);
		}

		bw.write(ans + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}