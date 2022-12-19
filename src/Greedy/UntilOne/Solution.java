package Greedy.UntilOne;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int result = 0;
        while (true)
        {
            int tmp = (N / K) * K;
            result += N - tmp;
            N = tmp;
            if (N < K)
                break;
            result++;
            N /= K;
        }

        result += (N - 1);

        System.out.println(result);
    }
}
