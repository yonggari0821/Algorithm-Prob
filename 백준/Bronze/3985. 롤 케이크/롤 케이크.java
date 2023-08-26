import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        int L = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        int maxExpectedAudience = 1;
        int maxExpected = 0;
        int maxReceivedAudience = 1;
        int maxReceived = 0;
        int[] cake = new int[L + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int expected = K - P + 1;
            if (maxExpected < expected)
            {
                maxExpected = expected;
                maxExpectedAudience = i;
            }
            int received = 0;
            for (int j = P; j <= K; j++) {
                if (cake[j] == 0) {
                    cake[j] = i;
                    received++;
                }
            }
            if (maxReceived < received)
            {
                maxReceived = received;
                maxReceivedAudience = i;
            }
        }
        ans.append(maxExpectedAudience).append('\n');
        ans.append(maxReceivedAudience);
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}