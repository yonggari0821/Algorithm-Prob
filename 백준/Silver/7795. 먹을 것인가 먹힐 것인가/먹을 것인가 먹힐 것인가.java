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
			int ai = N - 1; // 뒤에서부터 ai를 기준으로
			int bi = M - 1; // bi도 뒤에서 부터
			int cnt = 0;
			// ai를 기준으로 현재 bi보다 큰 상태면 하나씩 빼가면서 현재 bi인덱스 + 1 만큼 갯수로 더해주고
			while (ai >= 0 && bi >= 0)
			{
				while (ai >= 0 && A[ai] > B[bi]) {
					cnt+=(bi + 1);
					ai--;
				}
				// 작아지면 bi를 줄여보기!
				while (ai >= 0 && bi >= 0 && A[ai] <= B[bi]) bi--;
			}
			ans.append(cnt).append('\n');
		}
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}