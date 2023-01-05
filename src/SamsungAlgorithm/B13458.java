package SamsungAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B13458 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] testplace = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            testplace[i] = Integer.parseInt(st.nextToken());

        StringTokenizer sta = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(sta.nextToken());
        int C = Integer.parseInt(sta.nextToken());

        long cnt;
        cnt = 0;

        for (int i = 0; i < N; i++)
        {
            testplace[i] = testplace[i] - B;
            cnt++;
            if (testplace[i] >= C)
            {
                if (testplace[i] % C == 0)
                    cnt += testplace[i] / C;
                else
                    cnt += (testplace[i] / C) + 1;
            }
            else if (testplace[i] > 0)
            {
                cnt += 1;
            }
        }

        System.out.println(cnt);
    }
}
