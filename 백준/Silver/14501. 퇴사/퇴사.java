import java.io.*;
import java.util.*;

public class Main {
    static int N, max = 0;
    static int[] time;
    static int[] pay;

    static void doConsult(int cur, int paySum)
    {
        if (cur == N)
        {
            max = max > paySum ? max : paySum;
            return ;
        }
        if (cur + time[cur] <= N) doConsult(cur + time[cur], paySum + pay[cur]);
        doConsult(cur + 1, paySum);
    }
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        time = new int[N];
        pay = new int[N];
        for (int n = 0; n < N; n++)
        {
            st = new StringTokenizer(br.readLine());
            time[n] = Integer.parseInt(st.nextToken());
            pay[n] = Integer.parseInt(st.nextToken());
        }
        doConsult(0, 0);
        ans.append(max);
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}