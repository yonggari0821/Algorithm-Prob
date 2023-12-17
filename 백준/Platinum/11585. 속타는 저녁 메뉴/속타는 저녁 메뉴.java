import java.io.*;
import java.util.*;

/*
[KMP 11585]_속타는 저녁 메뉴_안상준
총 N칸의 원형 룰렛
각 칸에는 알파벳 대문자가 1개씩
12시부터 시계방향으로 읽어서 저녁 메뉴를 결정

첫 번째로 주어지는 문자열은 오늘 저녁으로 고기를 선택하게 되는 문구가 12시부터 시계방향으로 한 글자씩 N개가 주어짐
그 다음으로는 원형룰렛의 모양이 12시부터 시계방향으로 한 글자씩 N개가 주어짐
둘 다 최소 1글자에서 최대 100만 글자

즉 아래의 문자열에서 원형으로 회전시켜(전체적인 순서는 유지하되 어디부터 시작할지만 바뀌는 것)
위의 문자열을 만들 수 있는 확률을 구하는 것

위의 문자열과 아래 문자열은 결국 같은 원형 룰렛이기 때문에 무조건 1개 이상은 있을 것이고,
따라서 기약분수의 형태로 답을 출력하면 됨!

풀이의 핵심은
아래 문자열에서 마지막 글자를 빼고 한 번 더 붙혀서 임시 문자열을 만들고
해당 위의 문자열과 임시 문자열을 가지고 KMP 알고리즘으로
위의 문자열이 임시 문자열에 몇 번 들어가는 지 찾으면 됨!

 */



public class Main {
	static int[] subString;

	static void KMPsubString(String str)
	{
		subString = new int[str.length()];
		int i = 0;
		int j = 1;
		while ( j < str.length() )
		{
			while ( i != 0 && str.charAt(i) != str.charAt(j) ) i = subString[i - 1];
			if ( i == 0 && str.charAt(i) != str.charAt(j)) j++;
			else if ( str.charAt(i) == str.charAt(j) ) {
				subString[j] = i + 1;
				i++;
				j++;
			}
		}
	}

	static int KMPsearch(String str1, String str2)
	{
		int i = 0;
		int j = 0;
		int cnt = 0;
		while ( i < str1.length() && j < str2.length() )
		{
			if ( str1.charAt(i) == str2.charAt(j) )
			{
				while ( i < str1.length() && j < str2.length()
						&& str1.charAt(i) == str2.charAt(j) )
				{
					i++;
					j++;
				}
				if ( j == str2.length() ) cnt++;
				if (j != 0)	j = subString[j - 1];
			}
			else
			{
				i++;
				if (j != 0)	{
					j = subString[j - 1];
					i--;
				}
			}
		}
		return cnt;
	}

	static int gcd (int a, int b)
	{
		while (b != 0)
		{
			int tmp = a % b;
			a = b;
			b = tmp;
		}
		return a;
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		String strMEAT = br.readLine().replaceAll("\\s", "");
		String str = br.readLine().replaceAll("\\s", "");
		KMPsubString(strMEAT);
		StringBuilder tmp = new StringBuilder(str);
		tmp.append(str);
		tmp.deleteCharAt(tmp.length() - 1);
		int numerator = KMPsearch(tmp.toString(), strMEAT);
		int denominator = str.length();
		int theGCD = gcd(denominator, numerator);
		ans.append(numerator/theGCD).append("/").append(denominator/theGCD);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}