import java.io.*;
import java.util.*;

/*
[문자열 2607]_비슷한 단어_안상준
처음으로 주어지는 문자열과 "같은 구성 OR 비슷한 단어"의 갯수를 출력  (모든 글자가 영어 대문자)

1. 같은 구성
1-1) 두 개의 단어가 같은 종류의 문자로 이루어짐
1-2) 같은 문자는 같은 갯수 만큼 있음

2. 비슷함
2-2) 한 문자를 빼거나, 더하거나, 하나의 문자를 다른 문자로 바꾸어 서로 같은 구성이 됨 => 같은 문자열 아님!

총 단어 100개 이하
각 ㅈ단어는 10개 이하의 문자

첫 번째 문자열의 각 문자의 갯수를 크기 26짜리 배열을 만들어서 저장해두고
그 다음부터 나오는 문자열들의 문자별로 해당 자리의 값이 0 이상이면 하나씩 빼가면서 그 갯수를 세서
또 케이스를 3가지로 나눠서

1) 기준 문자열(첫번째 문자열)의 길이보다 1 큰 경우
2) 같은 경우
3) 1 작은 경우

각각에 맞는 처리해서 풀기!

반례
2
AB
CA
답: 1

2
A
B
답: 1
 */



public class Main {
	static int[] original = new int[26];
	// A ~ Z 갯수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		int cnt = 0;
		String tmp;
		int[] tmpArr;
		int N = Integer.parseInt(br.readLine());
		String standard = br.readLine();
		int sLen = standard.length();
		for (int j = 0; j < standard.length(); j++) original[(int)(standard.charAt(j) - 'A')]++;
		for (int i = 0; i < N - 1; i++)
		{
			tmp = br.readLine();
			if (tmp.length() > sLen + 1 || tmp.length() < sLen - 1) continue;
			tmpArr = new int[26];
			int tmpSameCnt = 0;
			for (int j = 0; j < 26; j++) tmpArr[j] = original[j];
			for (int j = 0; j < tmp.length(); j++) {
				if (tmpArr[(int)(tmp.charAt(j) - 'A')] > 0) {
					tmpArr[(int)(tmp.charAt(j) - 'A')]--;
					tmpSameCnt++;
				}
			}
			// 여기도 케이스를 나눠야 함
			// tmp.length == sLen + 1 => tmpSameCnt가 1개 적어야 맞음 => 더 적으면 다른 글자가 더 많은 것!
			if (tmp.length() == sLen + 1) {
				if (tmpSameCnt == sLen) cnt++;
			}
			// tmp.length == sLen => tmpSameCnt가 1개 적거나 같아야 함
			else if (tmp.length() == sLen) {
				if (tmpSameCnt == sLen - 1 || tmpSameCnt == sLen) cnt++;
			}
			// tmp.length == sLen - 1 => tmpSameCnt가 sLen - 1이어야 함 (무조건 문자 1개 추가해야 하므로)
			else {
				if (tmpSameCnt == sLen - 1) cnt++;
			}
		}
		ans.append(cnt);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}