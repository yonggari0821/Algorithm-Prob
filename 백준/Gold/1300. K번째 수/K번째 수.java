import java.io.*;
import java.util.*;
/*

[풀이]
<N = 3 / K = 7>

1 2 3
2 4 6
3 6 9

1 2 3 2 4 6  3  6 9
1 2 2 3 3 4 '6' 6 9

N의 범위는 1 ~ 100000
100000 x 100000 = 100억
당연히 배열 생성 불가
정렬도 당연히 불가

1 2 3 4  5  6  7
2 4 6 8  10 12 14
3 6 9 12 15 18 21
4 8
5 10
6 12
7 14

1 1개 1x1
2 2개 1x2 2x1
3 2개 1x3 3x1
4 3개 1x4 2x2 4x1
.
.
.
n 은 n의 약수들 갯수만큼 존재!
그런데
1 2 2 3 2 4 2 4 3 4 ... => 딱히 공식이 안보임
하나하나 봐야될 거 같은데
1) 각 숫자별 등장 횟수 배열
2) 배열 1)의 누적합 배열
=> 둘 다 어차피 갯수의 합이므로 int로 선언해도 무방!

K를 넣었을 때 1부터
1) 배열 채우고
2) 배열 채우고
2) 배열의 값이 K보다 같거나 커지면 해당 숫자가 답

근데 1) 배열의 크기를 어떻게 정할 건데? => 모르겠음

K는 Math.min(10의 9승, N의 2승)보다 작거나 같음

*/

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		long left = 1;
		long right = K;
		while (left <= right)
		{
			long mid = left + (right - left) / 2;
			long cnt = 0;
			for (int i = 1; i <= N; i++)
			{
				long numsOfsmallerNumsFromRowI = mid / i;
				if (numsOfsmallerNumsFromRowI == 0) continue;
				cnt += Math.min(numsOfsmallerNumsFromRowI, N);
			}
			if (cnt >= K) right = mid - 1;
			else left = mid + 1;
		}
		ans.append(left);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}