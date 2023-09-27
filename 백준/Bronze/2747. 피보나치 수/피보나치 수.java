import java.io.*;
import java.util.*;

public class Main {
	static int[] fiboDP;
	static int fibo(int n)
	{
		if (n == 0) return 0;
		else if (n == 1) return 1;
		else {
			if (fiboDP[n] == 0) return fiboDP[n] = fibo(n - 1) + fibo(n - 2);
			return fiboDP[n];
		}
	}
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		fiboDP = new int[n + 1];
		fiboDP[1] = 1;
		ans.append(fibo(n));
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}