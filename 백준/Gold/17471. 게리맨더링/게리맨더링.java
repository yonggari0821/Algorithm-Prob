import java.io.*;
import java.util.*;

public class Main {

	static int N, min;

	static int[] ppl, ward;
	static int[][] graph;
	static boolean[] visited;

	static void func (int pickedTown)
	{
		if (pickedTown == N + 1)
		{
			int area1 = 0;
			int area2 = 0;
			for (int i = 1; i <= N; i++) {
				if (ward[i] == 1) area1 += ppl[i];
				else area2 += ppl[i];
			}

			visited = new boolean[N + 1];
			int connectedNum = 0;
			for (int i = 1; i <= N; i++) {
				if (!visited[i]) {
					BFS(i, ward[i]);
					connectedNum++;
				}
			}

			if (connectedNum == 2) {
				int gap = Math.abs(area1 - area2);
				min = min < gap ? min : gap;
			}
			
			return ;
		}
		ward[pickedTown] = 1;
		func(pickedTown + 1);
		ward[pickedTown] = 2;
		func(pickedTown + 1);
	}

	static void BFS(int idx, int wardNum)
	{
		Queue<Integer> q = new LinkedList<>();
		visited[idx] = true;
		q.offer(idx);

		while (!q.isEmpty())
		{
			int cur = q.poll();
			for (int i = 1; i <= N; i++)
			{
				if (graph[cur][i] == 1)
				{
					if (ward[i] == wardNum && !visited[i])
					{
						visited[i] = true;
						q.offer(i);
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		min = Integer.MAX_VALUE;
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		ppl = new int[N + 1]; // 각 지역별 인구 수 저장용 배열
		graph = new int[N + 1][N + 1]; // 각 지역별 연결 관계 저장용 배열 (1이면 서로 연결된 것!)
		// 입력 받기
		for (int i = 1; i <= N; i++) ppl[i] = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int connectedNodeNum = Integer.parseInt(st.nextToken());
			for (int j = 0; j < connectedNodeNum; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				graph[i][tmp] = 1;
				graph[tmp][i] = 1;
			}
		}
		ward = new int[N + 1]; // 각 지역별 속한 선거구 표시용 배열
		func(1);
		if (min == Integer.MAX_VALUE) min = -1;
		ans.append(min);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}