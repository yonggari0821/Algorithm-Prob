package 시각;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int TimeNum = 0;

        int i = 0;
        while (i <= N)
        {
            int j = 0;
            while (j < 60)
            {
                int k = 0;
                while (k < 60)
                {
                    if (K < 10)
                    {
                        if (i % 10 == K || i / 10 == K || j % 10 == K || j / 10 == K || k % 10 == K || k / 10 == K)
                            TimeNum++;
                    }
                    else
                    {
                        if (i == K || j == K || k == K)
                            TimeNum++;
                    }
                    k++;
                }
                j++;
            }
            i++;
        }
        System.out.println(TimeNum);
    }
}
