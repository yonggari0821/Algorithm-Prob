import java.io.*;
import java.util.*;

public  class Main {
	static String isBipartite(boolean flag)
	{
		if (flag) return "YES";
		return "NO";
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++)
		{
			boolean flag = false;
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken()); // 정점 갯수
			int E = Integer.parseInt(st.nextToken()); // 간선 갯수
			ArrayList<ArrayList<Integer>> graph = new ArrayList<>(); // 정점들의 그래프를 이 중 ArrayList형태로 기억

			// 빈 ArrayList를 정점 갯수 + 1 개만큼 전체 그래프 리스트에 추가
			// 이 부분을 하지 않으면 정점 별로 이어진 정점을 연결하는 작업 시에 리스트가 아니라 빈 공간을 참조하게 됨!
			for (int i = 0; i <= V; i++) {
				graph.add(new ArrayList<Integer>());
			}

			// 각 정점 별로 칠하는 색깔 (1 또는 2) 표시용 (0이면 아직 색칠 안한 것!)
			int[] vertexColors = new int[V + 1];
			for (int e = 0; e < E; e++) {
				st = new StringTokenizer(br.readLine());
				int e1 = Integer.parseInt(st.nextToken());
				int e2 = Integer.parseInt(st.nextToken());
				// 주어지는 간선에 따라 정점 간 연결관계 표시
				graph.get(e1).add(e2);
				graph.get(e2).add(e1);
			}
			loop :
			for (int s = 1; s <= V; s++) {
				if (vertexColors[s] == 0) // 만약 아직 칠하지 않은 정점의 경우 => start 점이 됨!
				{
					vertexColors[s] = 1; // 일단 1번 색으로 칠하고
					Queue<Integer> q = new LinkedList<>();
					q.offer(s); // 큐에 넣고 색칠용 탐색 시작
					while (!q.isEmpty())
					{
						int start = q.poll();
						for (int fs = 0; fs < graph.get(start).size(); fs++) {
							int end = graph.get(start).get(fs); // 새로 살펴 볼(도착) 정점 번호
							// 아직 색 칠 안되어있으면
							if (vertexColors[end] == 0)
							{
								// 시작 정점의 색에 따라 서로 다른 색으로 칠해주고 다시 큐에 넣어주기!
								vertexColors[end] = vertexColors[start] == 1 ? 2 : 1;
								q.offer(end);
							}
							else // 색칠 되어 있는 경우
							{
								if (vertexColors[end] == vertexColors[start]) // 같은 간선에 속한 정점의 색이 같으면 이분 그래프가 될 수 없음!!
								{
									flag = true; // 이분 그래프가 될 수 없다는 flag On!
									break loop; // 아예 나가기
								}
							}
						}
					}
				}
			}
			ans.append(isBipartite(!flag)).append('\n');
		}
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}