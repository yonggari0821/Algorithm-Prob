import java.io.*;
import java.util.*;

/*
[다익스트라 10282]_해킹_안상준

의존하는 컴퓨터가 감염되면 같이 감염됨
a b s
a 컴퓨터가 b 컴퓨터에 의존해서
b 컴퓨터가 감염되면 s초후에 a 컴퓨터도 감염된다는 얘기 => 처음에 양방향인 줄 알았는데 문제 예시를 다시 천천히 보니 일방향적인 것!
한 컴퓨터가 감염되면 마지막 컴퓨터가 감염되기까지
1) 총 감염되는 컴퓨터 수
2) 마지막 컴퓨터가 감염되기까지 걸리는 시간
을 공백으로 구분 지어 출력




 */

class Comp {
	int num;
	int time;

	public Comp(int num, int time) {
		this.num = num;
		this.time = time;
	}
}


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t = 1; t <= T; t++)
		{
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 컴퓨터 개수
			int d = Integer.parseInt(st.nextToken()); // 의존성 개수
			int c = Integer.parseInt(st.nextToken()); // 초기에 해킹당해있는 컴퓨터 번호
			boolean[] infectedOrNot = new boolean[n + 1];
			ArrayList<ArrayList<Comp>> graph = new ArrayList<>();
			for (int i = 0; i <= n; i++) graph.add(new ArrayList<Comp>());
			for (int i = 0; i < d; i++)
			{
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				graph.get(b).add(new Comp(a, s));
			}
			int infectedCompNum = 0;
			int infectionEndsTime = 0;
			Queue<Comp> pq = new PriorityQueue<>(new Comparator<Comp>() {
				@Override
				public int compare(Comp o1, Comp o2) {
					return o1.time - o2.time;
				}
			});
			pq.offer(new Comp(c, 0));
			while (!pq.isEmpty())
			{
				Comp cur = pq.poll();
				if (infectedOrNot[cur.num]) continue;
				infectedOrNot[cur.num] = true;
				infectedCompNum++;
				infectionEndsTime = infectionEndsTime > cur.time ? infectionEndsTime : cur.time;
//				System.out.println("[ " + cur.num + " ] " +infectedCompNum + " " + infectionEndsTime);
				for (int i = 0; i < graph.get(cur.num).size(); i++)
				{
					Comp next = graph.get(cur.num).get(i);
					if (infectedOrNot[next.num]) continue;
					pq.offer(new Comp(next.num, cur.time + next.time));
				}
			}
			ans.append(infectedCompNum + " " + infectionEndsTime).append('\n');
		}
		System.out.println(ans.toString());
		br.close();
	}
}