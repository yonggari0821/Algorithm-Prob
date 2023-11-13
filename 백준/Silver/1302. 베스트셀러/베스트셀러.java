import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Map<String, Integer> map = new HashMap<>(); // key로 단어 // value로 등장 횟수를 저장하는 해쉬 맵
		for (int i = 0; i < N; i++)
		{
			String tmp = br.readLine();
			map.put(tmp, map.getOrDefault(tmp, 0) + 1); // 등장횟수 저장 시 getOrDefault 사용
		}
		
		Iterator< Map.Entry<String, Integer> > it = map.entrySet().iterator(); // Map 내의 Entry들을 순회할 Iterator 정의
		// 우선순위 큐를 이용한 힙정렬로 우선순위 여러 개 고려해서 정답 추출
		PriorityQueue< Map.Entry<String, Integer> > pq = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				if (o1.getValue() == o2.getValue()) // 등장횟수가 같다면
				{
					int len1 = o1.getKey().length();
					int len2 = o2.getKey().length();
					for (int i = 0; i < Math.min(len1, len2); i++) // 둘 중에 더 작은 길이까지만 탐색
					{
						if (o1.getKey().charAt(i) == o2.getKey().charAt(i)) continue; // 같은 글자면 다음 글자
						else return o1.getKey().charAt(i) - o2.getKey().charAt(i); // 다른 글자면 오름차순으로 리턴
					}
					return len1 - len2;
				}
				return o2.getValue() - o1.getValue(); // 등장횟수가 다르면 등장횟수가 더 많은 쪽이 나와야 하므로 내림차순
			}
		});
		while (it.hasNext()) pq.offer(it.next());
		ans.append(pq.poll().getKey());
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}