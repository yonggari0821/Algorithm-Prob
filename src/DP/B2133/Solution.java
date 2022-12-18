package B2133IDPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int[] dp;

    public static int get_tile(int n) {
        dp[0] = 1; // 다음 짝수가 될 때마다 새롭게 생겨나는 * 2 부분을 위해서는 dp[0] = 1 설정이 필수!!
        dp[1] = 0;
        dp[2] = 3;
        if (n <= 2 || dp[n] != 0) return dp[n];
        // n <= 2 조건이 있어야 Array밖까지 나가는 일이 없음!
        // n = 1 일때 dp[-1]을 보지 않기 위함!!

        int result = 3 * get_tile(n - 2); // 홀수는 자동으로 0! 이후에도 더해지는 일 없음!

        for (int i = 3; i <= n; i++)
        {
            if (i % 2 == 0)
                result += 2 * get_tile(n - i); // 직전 짝수 항 외의 짝수항들에는 x 2!
        }

        dp[n] = result;
        return dp[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if (n <= 1)
        {
            System.out.println(0);
        }
        else {
            dp = new int[n + 1];

            System.out.println(get_tile(n));
        }
    }
}
