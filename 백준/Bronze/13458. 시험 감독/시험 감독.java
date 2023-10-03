import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        long min = N;
        for (int i = 0; i < N; i++) A[i] -= B;
        for (int i = 0; i < N; i++)
        {
            if (A[i] > 0)
            {
                if (A[i] % C == 0) min += (A[i] / C);
                else min += ((A[i] / C) + 1);
            }
        }
        ans.append(min);
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}