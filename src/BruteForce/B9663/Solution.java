import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

    public static int cnt = 0;

    public static int check(int[] put, int col, int row)
    {
        for (int i = 0; i < col; i++)
        {
            if(row == put[i])
                return 0;
            if (Math.abs(col - i) ==  Math.abs(row - put[i]))
                return 0;
        }
        return 1;
    }

    public static void N_Queen(int N, int[] put, int picked)
    {
        if (picked == N)
        {
            //System.out.println(Arrays.toString(put));
            cnt++;
        }
        else
        {
            for (int i = 0; i < N; i++)
            {
                if (check(put, picked, i) == 1)
                {
                    put[picked] = i;
                    N_Queen(N, put, picked + 1);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] put = new int[N];

        N_Queen(N, put, 0);

        System.out.println(cnt);
    }
}
