import java.io.*;
import java.util.*;

/*
백준 14476 최대 공약수 하나 빼기

정수의 개수 N의 범위가 (4 ≤ N ≤ 1,000,000)

*/

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int exceptNum, gcdNum = -1, N;
    static int[] LtoR;
    static int[] RtoL;
    static int[] nums;
    static int[] gcdExcept;

    static int gcd(int a, int b) {
        while (b > 0)
        {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    public static void main(String[]args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        LtoR = new int[N];
        RtoL = new int[N];
        nums = new int[N];
        gcdExcept = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());

        LtoR[0] = nums[0];
        RtoL[N - 1] = nums[N - 1];

        for (int i = 1; i < N; i++) LtoR[i] = gcd(LtoR[i - 1], nums[i]);
        for (int i = N - 2; i >= 0; i--) RtoL[i] = gcd(RtoL[i + 1], nums[i]);

        for (int i = 0; i < N; i++) {
            if (i == 0) gcdExcept[i] = RtoL[i + 1];
            else if (i == N - 1) gcdExcept[i] = LtoR[i - 1];
            else gcdExcept[i] = gcd( LtoR[i - 1], RtoL[i + 1] );

            if (nums[i] % gcdExcept[i] != 0 && gcdNum < gcdExcept[i]) {
                gcdNum = gcdExcept[i];
                exceptNum = nums[i];
            }
            else gcdExcept[i] = -1;
        }

//        System.out.println("nums");
//        System.out.println(Arrays.toString(nums));
//        System.out.println("LtoR");
//        System.out.println(Arrays.toString(LtoR));
//        System.out.println("RtoL");
//        System.out.println(Arrays.toString(RtoL));
//        System.out.println("gcdExcept");
//        System.out.println(Arrays.toString(gcdExcept));

        if (gcdNum == -1) System.out.println(gcdNum);
        else System.out.println(gcdNum + " " + exceptNum);
    }
}