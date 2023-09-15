import java.io.*;
import java.util.*;

class Node
{
	int color;
	int no;

	public Node(int color, int no) {
		this.color = color;
		this.no = no;
	}
}

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
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			ArrayList<ArrayList<Node>> graph = new ArrayList<>();
			for (int i = 0; i <= V; i++) {
				graph.add(new ArrayList<Node>());
			}
			int[] vertexColors = new int[V + 1];
			for (int e = 0; e < E; e++) {
				st = new StringTokenizer(br.readLine());
				int e1 = Integer.parseInt(st.nextToken());
				int e2 = Integer.parseInt(st.nextToken());
				graph.get(e1).add(new Node(0, e2));
				graph.get(e2).add(new Node(0, e1));
			}

			loop :
			for (int i = 1; i <= V; i++) {
				if (vertexColors[i] == 0) {
					vertexColors[i] = 1;
					Queue<Node> q = new LinkedList<>();
					q.offer(new Node(1, i));

					while (!q.isEmpty()) {
						Node tmpS = q.poll();
						for (int v = 0; v < graph.get(tmpS.no).size(); v++) {
							Node tmpE = graph.get(tmpS.no).get(v);
							if (vertexColors[tmpE.no] == 0) {
								vertexColors[tmpE.no] = tmpS.color == 1 ? 2 : 1;
								tmpE.color = tmpS.color == 1 ? 2 : 1;
								q.offer(tmpE);
							} else {
								if (vertexColors[tmpE.no] == vertexColors[tmpS.no]) {
									flag = true;
									break;
								}
							}
						}
						if (flag) break loop;
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