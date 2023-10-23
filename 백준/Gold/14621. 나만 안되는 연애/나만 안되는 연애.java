import java.io.*;
import java.util.*;
/*
[풀이]
최소 스패닝 트리를 구하되, 중간에 사심 경로가 아니면 제외 하기!

 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 학교 수 // 최대 1000
		int M = Integer.parseInt(st.nextToken()); // 도로 수 // 최대 10000

		boolean[] isBoyDominant = new boolean[N + 1];
		boolean[] visited = new boolean[N + 1];
		ArrayList<ArrayList<Integer>> selfishRoute = new ArrayList<>();
		selfishRoute.add(new ArrayList<>());
		st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) {
			isBoyDominant[n] = st.nextToken().charAt(0) == 'M' ? true : false;
			selfishRoute.add(new ArrayList<Integer>());
		}

		for (int m = 0; m < M; m++)
		{
			st = new StringTokenizer(br.readLine());
			int schoolA = Integer.parseInt(st.nextToken());
			int schoolB = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			selfishRoute.get(schoolA).add(schoolB * 10000 + dist);
			selfishRoute.get(schoolB).add(schoolA * 10000 + dist);
		}

		Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return ((o1 % 10000) - (o2 % 10000));
			}
		});
		int distSum = 0;
		int connectionCnt = 0;
		queue.offer(10000);
		while (!queue.isEmpty())
		{
			int tmp = queue.poll();
			int schoolNum = tmp / 10000;
			int schoolDist = tmp % 10000;
			if (visited[schoolNum]) continue;
			visited[schoolNum] = true;
			distSum += schoolDist;
			connectionCnt++;
			if (isBoyDominant[schoolNum])
			{
				for (int i = 0; i < selfishRoute.get(schoolNum).size(); i++)
				{
					int nextSchool = selfishRoute.get(schoolNum).get(i);
					int nsNum = nextSchool / 10000;
					if (visited[nsNum] || isBoyDominant[nsNum]) continue;
					queue.offer(nextSchool);
				}
			}
			else
			{
				for (int i = 0; i < selfishRoute.get(schoolNum).size(); i++)
				{
					int nextSchool = selfishRoute.get(schoolNum).get(i);
					int nsNum = nextSchool / 10000;
					if (visited[nsNum] || !isBoyDominant[nsNum]) continue;
					queue.offer(nextSchool);
				}
			}
		}
		if (connectionCnt == N) ans.append(distSum);
		else ans.append(-1);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}