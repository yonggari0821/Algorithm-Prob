import java.io.*;
import java.util.*;

/*
[최소 신장 트리 10423]_전기가 부족해_안상준
케이블 연결 비용과 그 케이블에 연결된 두 나라의 번호가 주어짐
각 나라에는 발전소가 있거나 없음
있는 나라는 상관 없지만
없는 나라의 경우 발전소가 있는 나라와 어떻게든 이어져야 함!

모든 나라에 전기가 공급되도록하는 최소한의 비용을 구하는 문제

최소 신장 트리 문제!
2가지 방법이 있음
1) 크루스칼 - 간선 기준
2) 프림 - 정점 기준

이 문제의 경우 연결하기 전에 살펴봐야 할 것이
1) 이미 전기가 공급되는 곳인가?
간선의 출발점과 도착점을 체크해서
한 쪽만 전기가 공급되는 경우 케이블을 설치
이미 둘 다 들어온 경우는 패스
둘 다 안 들어온 경우는 보류

=> 이렇게 하니깐 반례가 발생함!!
첫 번째 예시부터 22가 아니라 23이 나와버림
그렇다고 보류하는 장치를 빼면 pq 특성상 무한루프!

해결방법 => https://steady-coding.tistory.com/123 참고
크루스칼 알고리즘을 그대로 가져다 써서 발전소를 부모로 둘 것!

 */

class Cable {
	int from;
	int to;
	int cost;

	public Cable() {}
	public Cable(int from, int to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}
}


public class Main {
	static int N, M, K;
	static int[] powered;
	static boolean isAllPowered()
	{
		for (int i = 1; i < powered.length; i++)
		{
			if (findGenerator(i) != 0) return false;
		}
		return true;
	}

	static int findGenerator(int x)
	{
		if (powered[x] == 0) return 0;
		if (powered[x] == x) return x;
		return findGenerator(powered[x]);
	}

	static void connectElectricity(int x, int y)
	{
		if (findGenerator(x) == findGenerator(y)) return;
		if (findGenerator(x) == 0 || findGenerator(y) == 0)
		{
			if (findGenerator(x) == 0) powered[findGenerator(y)] = 0;
			else powered[findGenerator(x)] = 0;
		}
		else powered[findGenerator(y)] = findGenerator(x);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 도시 수 (1 ~ 1000)
		M = Integer.parseInt(st.nextToken()); // 케이블 수 (1 ~ 100000)
		K = Integer.parseInt(st.nextToken()); // 발전소 수 (1 ~ N)
		powered = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) powered[Integer.parseInt(st.nextToken())] = -1;
		for (int i = 1; i <= N; i++) {
			if (powered[i] == -1) {
//				System.out.println("Country " + i + " has generator!");
				powered[i] = 0;
			}
			else powered[i] = i;
		}
		Queue<Cable> pq = new PriorityQueue<>(new Comparator<Cable>() {
			@Override
			public int compare(Cable o1, Cable o2) {
				return o1.cost - o2.cost;
			}
		});

		for (int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()); // u
			int to = Integer.parseInt(st.nextToken()); // v
			int cost = Integer.parseInt(st.nextToken()); // w // 10000 이하의 양의 정수 => 활용 가능!
			pq.offer(new Cable(from, to, cost));
		}

		int total = 0;
		while(!pq.isEmpty())
		{
			if (isAllPowered()) break;
			Cable tmp = pq.poll();
			int from = tmp.from;
			int to = tmp.to;
			int cost = tmp.cost;
			// 둘 다 이미 전기가 공급된 경우 pass!
			if (findGenerator(from) == findGenerator(to)) continue;
			// 둘 중에 하나만 전기가 공급되지 않은 경우 & 둘 다 전기가 공급되지 않은 경우
			// 케이블 연결하고 가중치 계산
			else
			{
				connectElectricity(from, to);
//				System.out.println("from " + (char)(from+64) + " to " + (char)(to+64));
				total += cost;
			}
		}
		ans.append(total);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}