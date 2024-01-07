import java.io.*;
import java.util.*;

/*
[DFS 2644]_촌수 계산_안상준


 */

class Chon {
	int n;
	int th;

	public Chon(int n, int th) {
		this.n = n;
		this.th = th;
	}
}


public class Main {
	static int n;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		for (int i = 0; i <= n; i++) list.add(new ArrayList<Integer>());
		int m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++)
		{
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list.get(p).add(c);
			list.get(c).add(p);
		}

		Queue<Chon> q = new LinkedList<>();
		boolean[] checked = new boolean[n + 1];
		boolean flag = false;
		q.offer(new Chon(A, 0));
		checked[A] = true;
		while (!q.isEmpty())
		{
			Chon tmp = q.poll();
			if (tmp.n == B) {
				System.out.println(tmp.th);
				flag = true;
				break;
			}
			for (int i = 0; i < list.get(tmp.n).size(); i++)
			{
				int ttmp = list.get(tmp.n).get(i);
				if (checked[ttmp]) continue;
				checked[ttmp] = true;
				q.offer(new Chon(ttmp, tmp.th + 1));
			}
		}
		if (!flag) System.out.println(-1);
		br.close();
	}
}