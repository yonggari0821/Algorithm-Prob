package B15927;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String palin = new String(br.readLine());
        int len = palin.length();
        int ans = -1;
        int end = len - 1;

        if (palin.charAt(0) != palin.charAt(end))
            ans = len;
        else
        {
            for (int i = 0; i <= end; i++)
            {
                if (palin.charAt(0) != palin.charAt(i))
                {
                    ans = len - 1;
                }
            }
            for (int i = 0; i <= end/2; i++)
            {
                if (palin.charAt(i) != palin.charAt(end - i))
                {
                    ans = len;
                }
            }
        }


        System.out.println(ans);
    }
}
