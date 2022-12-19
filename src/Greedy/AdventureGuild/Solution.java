package Greedy.AdventureGuild;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int result = 0;

        int[] SepByFear = new int[N+1];
        while (st.hasMoreTokens())
        {
            int n = Integer.parseInt(st.nextToken());
            SepByFear[n]++;
        }

        for (int i = 1; i < N; i++)
        {
            result += SepByFear[i] / i;
            SepByFear[i + 1] += SepByFear[i] % i;
        }

        if (SepByFear[N] == N)
            result = 1;

        System.out.println(result);
    }
}
