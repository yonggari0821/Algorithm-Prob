package B11726IDPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    static int[] dp;

    public static int nx2Tile(int num) {
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        if(dp[num] != 0) return dp[num];
        for (int i = 3; i <= num; i++)
            dp[i] = (nx2Tile(i - 1) % 10007 + nx2Tile(i - 2) % 10007) % 10007;
        return dp[num];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if (n <= 0)
        {
            System.out.println("Can't Make Any pattern!");
            return ;
        } else if (n == 1) {
            System.out.println(1);
            return ;
        }
        else {
            dp = new int[n + 1];
            System.out.println(nx2Tile(n));
        }
        br.close();
    }
}
