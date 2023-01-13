package SamsungAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B20055 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] Durability = new int[2*N];
        int[] Robot = new int[N];
        StringTokenizer sta = new StringTokenizer(br.readLine());
        for (int d = 0; d < Durability.length; d++)
            Durability[d] =  Integer.parseInt(sta.nextToken());
        int step = 1;
        while (true)
        {
            // 벨트 회전
            int f = Durability[2*N-1];
            for (int d = 2*N - 1; d > 0; d--)
                Durability[d] = Durability[d-1];
            Durability[0] = f;
            // 로봇 회전
            for (int dd = N - 1; dd > 0; dd--)
                Robot[dd] = Robot[dd-1];
            Robot[N-1] = 0;
            Robot[0] = 0;


            // 로봇 전진
            for (int i = N - 2; i >= 0; i--)
            {
                if (Robot[i] > 0 && Robot[i + 1] == 0 && Durability[i+1] > 0)
                {
                    Robot[i + 1] = Robot[i];
                    Robot[i] = 0;
                    Durability[i+1]--;
                }
            }
            Robot[N-1] = 0;
            // 로봇 올리기
            if (Durability[0] > 0)
            {
                Robot[0] = step;
                Durability[0]--;
            }
            // 내구도 테스트 및 반복 여부 결정
            int tmp = 0;
            for (int d = 0; d < 2*N; d++)
            {
                if (Durability[d] == 0)
                    tmp++;
            }
            if (tmp >= K)
                break;
            step++;
        }
        System.out.println(step);
    }
}
