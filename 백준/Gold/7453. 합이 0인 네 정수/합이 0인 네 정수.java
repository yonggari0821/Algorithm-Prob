import java.io.*;
import java.util.*;

/*

가장 인접한 두 공유기의 거리가 최대가 되어야 함
일단 공유기들의 위치를 받아두고
1부터 공유기 (최댓값 - 최솟값) / (공유기 갯수 - 1)꺼자 이분탐색
이 때, 가능한 최대 거리를 구해야 하므로
안되는 거리 중 최솟값 - 1 하면 됨!

 */


public class Main {

	static int n;
	static long res = 0;
	static int[] A, B, C, D;
	static int[] AB;
	static int[] CD;

	static void bs(int left, int right)
	{
		while (left < n * n & right >= 0)
		{
			int plus = AB[left] + CD[right];
			if (plus == 0)
			{
				long abcnt = 1;
				long cdcnt = 1;
				while( (left < (n * n - 1) ) && ( AB[left] == AB[left + 1] ) )
				{
					left++;
					abcnt++;
				}
				while( (right > 0) && (CD[right] == CD[right - 1]) )
				{
					right--;
					cdcnt++;
				}
				res += (abcnt * cdcnt);
			}
			if (plus < 0) left++;
			else right--;
		}
	}


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		A = new int[n];
		B = new int[n];
		C = new int[n];
		D = new int[n];
		AB = new int[n * n];
		CD = new int[AB.length];
		for (int i = 0; i < n; i++)
		{
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				AB[i * n + j] = A[i] + B[j];
				CD[i * n + j] = C[i] + D[j];
			}
		}
		Arrays.sort(AB);
		Arrays.sort(CD);
		bs(0, n * n - 1);
		System.out.println(res);
	}
}