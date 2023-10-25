import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++)
		{
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // A의 수
			int M = Integer.parseInt(st.nextToken()); // B의 수
			int[] A = new int[N];
			int[] B = new int[M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) B[i] = Integer.parseInt(st.nextToken());
			Arrays.sort(A);
			Arrays.sort(B);
			int ai = N - 1;
			int bi = M - 1;
			int cnt = 0;
			while (ai >= 0 && bi >= 0)
			{
				while (ai >= 0 && A[ai] > B[bi]) {
					cnt+=(bi + 1);
					ai--;
				}
				while (ai >= 0 && bi >= 0 && A[ai] <= B[bi]) bi--;
			}
			ans.append(cnt).append('\n');
		}
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}