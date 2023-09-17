import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			tree.add(new ArrayList<Integer>());
		}
		int[] visited = new int[N + 1];
		for (int i = 1; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int nodeA = Integer.parseInt(st.nextToken());
			int nodeB = Integer.parseInt(st.nextToken());
			tree.get(nodeA).add(nodeB);
			tree.get(nodeB).add(nodeA);
		}
		Queue<Integer> q = new LinkedList<>();
		q.offer(1);
		visited[1] = -1;
		while (!q.isEmpty())
		{
			int tmp = q.poll();
			for (int i = 0; i < tree.get(tmp).size(); i++) {
				int tmp2 = tree.get(tmp).get(i);
				if (visited[tmp2] == 0)
				{
					visited[tmp2] = tmp;
					q.offer(tmp2);
				}
			}
		}
		for (int i = 2; i <= N; i++) {
			ans.append(visited[i]).append('\n');
		}
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}