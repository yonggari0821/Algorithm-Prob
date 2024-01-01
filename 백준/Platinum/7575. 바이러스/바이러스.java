import java.io.*;
import java.util.*;

public class Main {
	static int[][] programs; // 전체 프로그램 배열
	static int N, K;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 프로그램 갯수
		K = Integer.parseInt(st.nextToken()); // 바이러스 가능성 기준 길이
		programs = new int[N][];
		for(int i = 0; i < N; i++)
		{
			int m = Integer.parseInt(br.readLine()); // 프로그램 길이
			programs[i] = new int[m];
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) programs[i][j]=Integer.parseInt(st.nextToken()); // 프로그램 내용 받기
		}

		// 첫 번째 프로그램의 길이가 K인 부분 연속 집합 중 1개라도 다른 모든 프로그램에 공통된다면 YES
		// 한 개도 안되면 NO
		for(int i = 0; i <= programs[0].length - K; i++)
		{
			if( kmp(i) )
			{
				System.out.println("YES");
				return;
			}
		}
		System.out.println("NO");
	}


	static boolean kmp(int start)
	{
		int[] pattern=new int[K]; // 부분 연속 집합
		int[] reversePattern=new int[K]; // 부분 연속 집합의 거꾸로 버전
		for( int i = start; i < start + K; i++) //
		{
			pattern[i - start] = programs[0][i];
			reversePattern[(K - 1) - (i - start)] = pattern[i - start];
		}

		// kmp 과정에서 필요한 판단용 배열 만들기
		int[] pi = makePi(pattern);
		int[] rpi = makePi(reversePattern);

		// i 번째 프로그램, 현재 부분 연속 집합, 현재 부분 연속 집합의 kmp 판단용 배열 가지고 kmpTest
		for(int i = 1; i< N; i++)
		{
			boolean result = kmpTest(i, pattern, pi); // 정방향 검사
			// 정방향에서 이미 true 면 역방향 볼 필요 없으니 최적화
			if(!result)
			{
				result = kmpTest(i, reversePattern, rpi); // 역방향 검사
				if(!result) return false; // 둘 다 없다면 false;
			}
		}
		// 만약 모든 프로그램에 대해서 kmpTest를 통과한 경우
		return true;
	}

	// kmp 판단용 배열 만드는 함수
	static int[] makePi(int[] pattern)
	{
		int[] pi = new int[K];
		for(int i = 1, j = 0; i < K; i++)
		{
			while(j > 0 && pattern[i] != pattern[j]) j = pi[j - 1];
			if(pattern[i] == pattern[j]) pi[i] = ++j;
		}
		return pi;
	}

	static boolean kmpTest(int i, int[] pattern, int[] pi)
	{
		int[] src = programs[i];
		for(int idx = 0, j = 0; idx < src.length; idx++){
			while(j > 0 && src[idx] != pattern[j]) j = pi[j - 1];
			if(src[idx] == pattern[j])
			{
				if(j == K - 1) return true;
				else ++j;
			}
		}
		return false;
	}
}