import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		int computerNum = Integer.parseInt(br.readLine());
		int vertexNum = Integer.parseInt(br.readLine());
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= computerNum; i++) {
			graph.add(new ArrayList<Integer>());
		}
		boolean[] checked = new boolean[computerNum + 1];
		for (int i = 0; i < vertexNum; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			graph.get(s).add(e);
			graph.get(e).add(s);
		}
		int cnt = 0;
		Queue<Integer> q = new LinkedList<>();
		q.offer(1);
		checked[1] = true;
		while (!q.isEmpty())
		{
			int tmp = q.poll();
//			System.out.println("num = " + tmp);
			for (int i = 0; i < graph.get(tmp).size(); i++) {
				int tmp2 = graph.get(tmp).get(i);
				if (checked[tmp2]) continue;
				checked[tmp2] = true;
				q.offer(tmp2);
				cnt++;
			}
		}
		ans.append(cnt);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}