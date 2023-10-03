import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
		for (int i = 3; i <= N; i++)
		{
			if (i % 3 == 0) dp[i] = i / 3;
			if (i % 5 == 0) dp[i] = i / 5;
		}
		for (int i = 3; i <= N; i++)
		{
			if (dp[i - 3] != 0) dp[i] = dp[i - 3] + 1;
		}
		for (int i = 5; i <= N; i++)
		{
			if (dp[i - 5] != 0) dp[i] = dp[i - 5] + 1;
		}
		if (dp[N] == 0) ans.append(-1);
		else ans.append(dp[N]);
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}