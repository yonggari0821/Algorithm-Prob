import java.util.*;
import java.util.stream.IntStream;
import java.io.*;
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		long answer = 0;
		int divNum = 1_000_000_007;
		for (int i = 0; i < N; i++) {
			answer += arr[i]*customPow(2,i,divNum);
			answer -= arr[i]*customPow(2, N-i-1,divNum);
			answer %= divNum;
		}
		System.out.println(answer);
	}
	
	public static long customPow(int base, int x, int divNum) {
		if(x == 0) return 1L;
		long half = customPow(base, x/2, divNum);
		if(x%2 == 0) return half*half % divNum;
		return half*half*base % divNum;
	}
	
	
}