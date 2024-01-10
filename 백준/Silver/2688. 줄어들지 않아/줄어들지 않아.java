import java.io.*;
import java.util.*;

/*
[DP 2688]_줄어들지 않아_안상준

n = 1 일때 : 0~9 => 10
n = 2 일때 : 0 0~9 앞에 올 수 있음 / 1 1~9 앞에 올 수 있음 => 10 + 9 + ... + 1 = 55
n = 3 일때 : 0 맨 앞자리가 뭐든 앞에 올 수 있음 / 1 맨 앞자리가 1이상인 애들 앞에 올 수 있음 => 55 + 45 + 36 + ... + 1 = 220
.
.
.
n = k 일때 : n = k - 1 일때의 맨 앞자리를 고려해서 계산

 */


public class Main {

	static long[][] theNum = new long[65][10];
	static {
		for (int i = 0; i < 10; i++) theNum[1][i] = 1;
		for (int i = 2; i <= 64; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				for (int k = j; k < 10; k++) theNum[i][j] += theNum[i - 1][k];
			}
		}
	}


    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder ans = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++)
		{
			int N = Integer.parseInt(br.readLine());
			long tmp = 0;
			for (int i = 0; i < 10; i++) tmp += theNum[N][i];
			ans.append(tmp).append('\n');
		}
		System.out.println(ans.toString());
    	br.close();
    }
}