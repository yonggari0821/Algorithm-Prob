import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < N; i++)
		{
			String tmp = br.readLine();
			map.put(tmp, map.getOrDefault(tmp, 0) + 1);
		}
		Iterator< Map.Entry<String, Integer> > it = map.entrySet().iterator();
		PriorityQueue< Map.Entry<String, Integer> > pq = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				if (o1.getValue() == o2.getValue())
				{
					int len1 = o1.getKey().length();
					int len2 = o2.getKey().length();
					for (int i = 0; i < Math.min(len1, len2); i++)
					{
						if (o1.getKey().charAt(i) == o2.getKey().charAt(i)) continue;
						else return o1.getKey().charAt(i) - o2.getKey().charAt(i);
					}
					return len1 - len2;
				}
				return o2.getValue() - o1.getValue();
			}
		});
		while (it.hasNext()) pq.offer(it.next());
		ans.append(pq.poll().getKey());
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}