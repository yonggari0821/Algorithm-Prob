import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] ropes = new int[N];
        for (int i = 0; i < N; i++) ropes[i] = Integer.parseInt(br.readLine());
        Arrays.sort(ropes);
        int max = ropes[N - 1];
        for (int i = 2; i <= N; i++)
        {
            if (max <= ropes[N - i] * i) max = ropes[N - i] * i;
        }
        ans.append(max);
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}