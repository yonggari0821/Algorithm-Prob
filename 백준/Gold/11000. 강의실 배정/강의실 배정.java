import java.io.*;
import java.util.*;

public class Main {
	static int roomCnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		roomCnt = 0;
		int N = Integer.parseInt(br.readLine()); // 최대 20만
		int[][] lectures = new int[N][2]; // 시작 시간과 끝 시간은 최대 10억 => 배열로 표시하는 방법 불가능!
		PriorityQueue<Integer> startTimes = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		PriorityQueue<Integer> endTimes = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});

		for (int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			startTimes.offer(Integer.parseInt(st.nextToken())); // start
			endTimes.offer(Integer.parseInt(st.nextToken())); // end
		}

		int maxEnd = endTimes.poll();
		while (!startTimes.isEmpty())
		{
			int curStart = startTimes.poll();
			if (curStart >= maxEnd)
			{
				maxEnd = endTimes.poll();
			}
			else roomCnt++;
		}
		ans.append(roomCnt);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}