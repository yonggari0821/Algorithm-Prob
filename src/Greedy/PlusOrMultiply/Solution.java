package Greedy.PlusOrMultiply;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), "0123456789", true);
        int result = 0;
        while (result == 0)
            result += Integer.parseInt(st.nextToken());

        while (st.hasMoreTokens())
        {
            int n = Integer.parseInt(st.nextToken());
            if (n <= 1)
                result += n;
            else
                result *= n;
        }
        System.out.println(result);
    }
}
