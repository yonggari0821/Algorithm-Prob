import java.io.*;
import java.util.*;
public class Main {
    static StringBuilder ans = new StringBuilder();
    static int A, B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        int nums[] = new int[B + 1];
        int DP[] = new int[B+1];
        int inputNum = 1;
        int curNum = inputNum;
        for (int i = 1; i <= B; i++) {
            if (curNum == 0)
            {
                inputNum++;
                curNum = inputNum;
            }
            nums[i] = inputNum;
            curNum--;
            DP[i] = DP[i-1] + nums[i];
        }
        ans.append(DP[B]-DP[A-1]);
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}