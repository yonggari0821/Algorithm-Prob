import java.io.*;
import java.util.*;

/*
28099번 풀이
1) 각 숫자가 처음 등장하면 startMap에 두 번 이상 등장하면 endMap에 등장하는 index를 저장해두면서
   동시에 segmentTree를 구성 (원본 배열 없이 한 번에 함)
2) segmentTree의 경우 체크 하려는 구간에 더 큰 값이 없는 지 체크해야하므로 최대값 트리로 만들어야 함!
3) endMap에 있는 숫자들을 기준으로 startMap에서 시작 index를 꺼내서 두 범위에 더 큰 값이 있는 지 체크해서
   더 큰 값이 하나라도 있으면 isOdd를 false로 만들고 출력

 */


public class Main {
	// 출력용
	static String yesOrNo (boolean isOdd)
	{
		if (isOdd) return "Yes";
		return "No";
	}

	// 쿼리 : 구간에 target보다 더 큰 값이 있는 지 체크하고 있으면 isOdd 를 false로 만드는 함수
	static void query (int queryLeft, int queryRight, int[] tree)
	{
		int val = tree[queryLeft];
		int target = tree[queryRight];
		while (queryLeft <= queryRight)
		{
			if ( (queryLeft & 1) != 0 ) val = Math.max(tree[queryLeft++], val);
			queryLeft /= 2;
			if ( (queryRight & 1) == 0 ) val = Math.max(tree[queryRight--], val);
			queryRight /= 2;
		}
		if (val > target) isOdd = false;
	}

	static boolean isOdd;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++)
		{
			isOdd = true;
			int size = 1;
			Map<Integer, Integer> startMap = new HashMap<>();
			Map<Integer, Integer> endMap = new HashMap<>();
			int N = Integer.parseInt(br.readLine()); // 최대 20만
			while (size < N) size *= 2;
			int[] segmentTree = new int[size * 2];
			st = new StringTokenizer(br.readLine());
			for (int i = size; i < size + N; i++)
			{
				segmentTree[i] = Integer.parseInt(st.nextToken());
				if (!startMap.containsKey(segmentTree[i])) startMap.put(segmentTree[i], i); // 등장한 적 없으면 시작맵에 인덱스와 값 저장
				else endMap.put(segmentTree[i], i); // 등장한 적이 있다면 끝맵에 저장 // 여러 개면 자동적으로 더 뒤의 것으로 덮어써지므로 상관 없음!
			}
			// 세그먼트 트리는 최댓값 트리로 구성
			for (int i = size - 1; i > 0; i--) segmentTree[i] = Math.max(segmentTree[2 * i], segmentTree[2 * i + 1]);
			
			
			/*
			[Map에 있는 key와 value 탐색하는 법]
			Map에 있는 key와 value의 쌍을 Entry라고 함
			----------------------------------------
			Entry == <key , value>
			----------------------------------------
			즉, Map은 이 Entry들의 Set라고 할 수도 있음
			이 때, Map에 있는 이 Entry 들을 탐색할 수 있는 방법이 있는데, 바로 Iterator라는 클래스를 이용하는 것이다.
			
			사용방법은 Iterator를 선언하는 것부터 시작한다.
			
			Iterator <Map.Entry<>> iterator = 맵_이름.entrySet().iterator();
			이 때, Map.Entry<>의 제네릭에는 받아올 Map의 제네릭을 똑같이 넣어주면 된다.
			
			그러면 이제 이 iterator라는 객체는 hasNext()와 next()라는 메서드를 가진 탐색용 객체가 된다.
			hasNext()의 경우 Queue의 isEmpty()와 같은 함수로 Map에 탐색할 다음 Entry가 있는 지를 true/false로 반환해주는 함수이고,
			Next()의 경우 다음 Entry를 반환하는 함수이다.
			
			따라서 우리는 엔트리를 iterator.next()로 받아와서 사용하면 되고, 그 방법은 아래와 같다.
			while (iterator.hasNext()) {
				// 엔트리 받아오기
				Map.Entry<> entry = iterator.next();
				// 받아온 엔트리에서 key 가져오기 
				key의_자료형 keyName = entry.getKey();
				// 받아온 엔트리에서 value 가져오기
				value_자료형 valueName = entry.getValue();
			}
			
			 */
			
			// endMap에 들어있는 수들을 대상으로 startMap에서 같은 수의 value 값과 함께 세그먼트 트리 탐방
			Iterator<Map.Entry<Integer, Integer>> iterator = endMap.entrySet().iterator();
			while (iterator.hasNext())
			{
				Map.Entry<Integer, Integer> keyAndValue = iterator.next();
				int num = keyAndValue.getKey();
				int endIndex = keyAndValue.getValue();
				query(startMap.get(num), endIndex, segmentTree);
				if (!isOdd) break;
			}
			ans.append(yesOrNo(isOdd));
			ans.append('\n');
		}
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}