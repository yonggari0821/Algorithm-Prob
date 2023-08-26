import java.io.*;
import java.util.StringTokenizer;
public class Main {
    static StringBuilder ans = new StringBuilder();
    static int C, max = 0;
    static int[] testCase, DP, visible;
    static int gcd(int a, int b)
    {
        while (b != 0)
        {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        C = Integer.parseInt(br.readLine());
        testCase = new int[C + 1];
        for (int c = 1; c <= C; c++)
        {
            testCase[c] = Integer.parseInt(br.readLine());
            max = max >= testCase[c] ? max : testCase[c];
        }
        visible = new int[max + 1];
        DP = new int[max + 1];
        visible[1] = 3;
        for (int i = 2; i <= max; i++)
        {
            for (int j = 1; j < i; j++)
                if (gcd(j, i) == 1) visible[i] += 2;
        }

        for (int c = 1; c <= max; c++)
            DP[c] = DP[c-1] + visible[c];

        for (int c = 1; c <= C; c++)
            ans.append(DP[testCase[c]]).append('\n');

        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}