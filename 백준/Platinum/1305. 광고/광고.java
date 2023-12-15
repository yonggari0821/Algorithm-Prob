import java.io.*;

/*
[KMP 1305]_광고_안상준
광고판의 길이 > 광고 문구의 길이인 경우 광고 문구의 앞에부터 다시 광고문구가 등장함을 가정
특정 시점의 광고판에 나온 문자열을 기준으로 해당 문자열을 구현할 수 있는
최소한의 광고 문구 길이를 구하는 문제






 */




public class Main {

	static int[] subString;
	static void KMPsubString (String str)
	{
		subString = new int[str.length()]; // 찾으려는 문자열의 길이
		int i = 0; // 앞 부분
		int j = 1; // 뒷 부분
		while ( j < str.length() )
		{
			// 앞 부분이 0이 아니면서 앞 부분과 뒷 부분의 글자가 같지 않다면 앞 부분은 그 전으로 돌아가야 함! (즉, 계속 맞지 않으면 0까지 돌아감!)
			while ( i > 0 && str.charAt(i) != str.charAt(j) ) i = subString[i - 1];
			// 0 까지 갔는데도 앞 부분과 뒷 부분의 글자가 다른 경우 => 뒷 부분 한 칸 옮겨서 다시 보기
			if ( i == 0 && str.charAt(i) != str.charAt(j) ) j++;
			// 만약에 앞 부분 글자 == 뒷 부분 글자라면
			else if ( str.charAt(i) == str.charAt(j) )
			{
				subString[j] = i + 1; // 뒷 부분의 앞 부분과 일치하는 수는 앞 부분의 인덱스 + 1로 판단 가능! (인덱스는 0부터니깐!)
				// 이 때, 앞 부분과 뒷 부분 둘 다 1칸씩 옮겨줘야 제대로 셀 수 있음!
				i++;
				j++;
			}
		}
	}



	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		int L = Integer.parseInt(br.readLine());
		String str = br.readLine();
		KMPsubString(str);
		ans.append(L - subString[L - 1]);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}