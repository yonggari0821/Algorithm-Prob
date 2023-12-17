import java.io.*;
import java.util.Arrays;

/*
[KMP 4354]_문자열 제곱_안상준
문자열 a 와 b가 주어지고 a * b이면 해당 문자열을 이어 붙이는 것을 말함
이런 이어 붙힘을 곱셈으로 치면 하나의 문자열을 계속해서 a * a * a * .... 이런식으로 이어 붙히는 것을 문자열의 제곱으로 정의해볼 수 있다.
a의 (n + 1) 제곱인 a ^ (n + 1)을 a 곱하기 a의 n제곱으로 생각해 볼 수 있음!

이 때, 주어지는 문자열이 s 라면 s = a ^ n을 만족하는 가장 큰 n을 찾아야 함

입력은 10개 이하 테스트 케이스
각 테스트 케이스별로 문자열의 길이는 1 ~ 100만

문제 풀이의 핵심은
어떤 문자열에서 앞 부분과 뒷 부분이 똑같고 더 쪼갤 수 없는 최소한의 문자열을 찾아 해당 문자열이 몇 번 반복되는 지를 찾는 것!

KMP 알고리즘에서 부분 문자열의 연속성 찾는 함수에서 (KMPsubString)

일단 문자열을 반을 나눴을 때 그 이후에 0이 있으면 무조건 a = s.length, n = 1이 되고

0이 없어도
abcdefabcdefa
[0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7]
와 같이 a = s.length이 될 수 있기 때문에



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
		while ( true ) {
			String str = br.readLine();
			if (str.equals(".")) break;
			KMPsubString(str);
//			System.out.println(Arrays.toString(subString));
			if (str.length() == 1) ans.append(1).append('\n');
			else {
				int tmp = str.length() - subString[str.length() - 1];
				if (str.length() % tmp == 0) ans.append(str.length() / tmp).append('\n');
				else ans.append(1).append('\n');
			}
		}
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}