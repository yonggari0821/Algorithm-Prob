import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder ans = new StringBuilder();
    static int N, M, limit = 1000000000;
    static long totalGCD = 1;
    static boolean overNine = false;
    static int getGCD(int a, int b)
    {
        while (b != 0)
        {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        int[] Nnumbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++)
            Nnumbers[n]= Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        int[] Mnumbers = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int m = 0; m < M; m++)
            Mnumbers[m] = Integer.parseInt(st.nextToken());
        for (int n = 0; n < N; n++)
        {
            int ntmp = Nnumbers[n];
            if (ntmp == 1) continue;
            for (int m = 0; m < M; m++)
            {
                ntmp = Nnumbers[n]; // 여기서 꼭 재정의 해줘야 함!!
                int mtmp = Mnumbers[m];
                if (ntmp != 1 && mtmp != 1)
                {
                    int gcd = getGCD(ntmp, mtmp);
                    if (gcd != 1)
                    {
                        totalGCD *= gcd;
                        // System.out.println(ntmp + "/" + mtmp + "/" +totalGCD);
                        if (totalGCD >= limit)
                        {
                            overNine = true;
                            totalGCD = totalGCD % limit;
                        }
                        Nnumbers[n] /= gcd;
                        Mnumbers[m] /= gcd;
                    }
                }
            }
        }
        ans.append(totalGCD);
        if (overNine)
        {
            int gap = 9 - ans.length();
            for (int i = 1; i <= gap; i++)
                ans.insert(0, 0);
        }
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}