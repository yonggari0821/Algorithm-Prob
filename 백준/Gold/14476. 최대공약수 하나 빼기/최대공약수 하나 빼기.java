import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// gcd(A, B, C) == gcd(gcd(A, B) , C)
public class Main {
    static int N;
    static int[] nums;
    static int[] LtoR;
    static int[] RtoL;
    static int[] findMax;

    static int gcd(int o1, int o2)
    {
        // gcd 정의
        // gcd (o1, o2) == gcd (o2, o1 % o2)
        while (o2 != 0)
        {
            int tmp = o1 % o2;
            o1 = o2;
            o2 = tmp;
        }
        return o1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        LtoR = new int[N];
        RtoL = new int[N];
        findMax = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++)
            nums[n] = Integer.parseInt(st.nextToken());

        LtoR[0] = nums[0];
        for (int i = 1; i < N; i++)
            LtoR[i] = gcd(LtoR[i - 1], nums[i]);

        RtoL[N-1] = nums[N-1];
        for (int i = N-2; i >= 0; i--)
            RtoL[i] = gcd(RtoL[i + 1], nums[i]);

        for (int i = 0; i < N; i++)
        {
            if (1 <= i && i <= N - 2)
                findMax[i] = gcd(LtoR[i-1], RtoL[i+1]);
            else
            {
                if (i == 0)
                    findMax[i] = RtoL[i + 1];
                else // i == N - 1
                    findMax[i] = LtoR[i - 1];
            }
        }

        int max = -1;
        int maxindex = -1;
        for (int i = 0; i < N; i++)
        {
            if (nums[i] % findMax[i] == 0)
                findMax[i] = -1;
            else
            {
                if (max < findMax[i])
                {
                    max = findMax[i];
                    maxindex = i;
                }
            }
        }
        if (max != -1)
            System.out.println(findMax[maxindex] + " " + nums[maxindex]);
        else
            System.out.println(-1);
    }
}