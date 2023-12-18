import java.io.*;
import java.util.*;

/*
[자료구조 7785]_회사에 있는 사람_안상준
출입 기록 n이 주어지고 (2이상 1000000이하)
출입 기록들이 주어짐
각 출입 기록은 한 사람의 이름과 enter 또는 leave가 주어짐
단, 동명이인은 없으며 대소문자가 다르다면 다른 이름임

해쉬셋을 사용해서
enter의 경우 해쉬셋에 추가
leave의 경우 해쉬셋에서 제거


 */



public class Main {



	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		Map<String, Boolean> map = new HashMap();
		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			String inAndOut = st.nextToken();
			if (inAndOut.equals("enter")) map.put(name, true);
			else map.remove(name);
		}
		ArrayList<String> list = new ArrayList<>(map.keySet());
		Collections.sort(list, Collections.reverseOrder());
		for (int i = 0; i < list.size(); i++) ans.append(list.get(i)).append('\n');
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}