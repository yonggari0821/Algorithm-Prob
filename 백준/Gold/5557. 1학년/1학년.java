import java.io.*;
import java.util.*;
public class Main {
    static StringBuilder ans = new StringBuilder();
    static int N;
    static int[] nums;
    static long[][] DP;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine()); // 숫자의 갯수 3 ~ 100
        nums = new int[N];
        DP = new long[N][21]; // N번째 자릿까지의 나온 숫자들의 갯수를 저장해두는 배열
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken()); // 0 ~ 9 사이
        DP[0][nums[0]] = 1;
        for (int i = 1; i < N - 1; i++) // N - 2 까지 봐야함! 마지막꺼(N-1)은 등식의 결과값이므로 제외!
        {
            for (int j = 0; j <= 20; j++) // 등식의 중간 값으로 0 ~ 20까지만 가능! 나머진 필요 없음!!
            {
                if (DP[i-1][j] != 0) // 그 전 단계에서 등장했던 값
                {
                    int plus = j + nums[i]; // 더한 값
                    int minus = j - nums[i]; // 뺀 값
                    if (plus <= 20) DP[i][plus] += DP[i-1][j];
                    if (minus >= 0) DP[i][minus] += DP[i-1][j];
                }
            }
        }
        ans.append(DP[N-2][nums[N-1]]);
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}