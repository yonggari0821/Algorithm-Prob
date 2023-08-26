import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder ans = new StringBuilder();
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        int[] zero = new int[N + 1];
        int[] leftAndRight = new int[N + 1];
        int[] total = new int[N + 1];
        zero[1] = 1;
        leftAndRight[1] = 2;
        total[1] = 3;
        for (int i = 2; i <= N; i++)
        {
            zero[i] = total[i - 1];
            leftAndRight[i] = zero[i-1] * 2 + leftAndRight[i-1];
            total[i] = zero[i] + leftAndRight[i];
            total[i] %= 9901;
        }
        ans.append(total[N]);
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}