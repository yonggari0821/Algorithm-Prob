package SamsungAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B13458 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 총 고사실 수
        int[] testplace = new int[N]; // 고사실 배열

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            testplace[i] = Integer.parseInt(st.nextToken()); // 고사실 마다 응시인원 수 받기

        StringTokenizer sta = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(sta.nextToken()); // 총 감독관이 한 시험장에서 감시할 수 있는 응시자 수
        int C = Integer.parseInt(sta.nextToken()); // 부 감독관이 한 시험장에서 감시할 수 있는 응시자 수

        // 총 cnt는 int가 아니라 long으로 되어야 함
        // => 극단적으로 B = C = 1이고, 각 응시장마다 100만명씩 100만개의 시험장이 있다고 가정
        // cnt => 는 100만 x 100만 => 1조 > 2147483647(= Integer.MAX_VALUE)
        long cnt;
        cnt = 0;

        for (int i = 0; i < N; i++) // 고사장별로
        {
            testplace[i] = testplace[i] - B; // 일단 총 감독관은 1명이 무조건 있어야하므로 총 감독관 몫 만큼은 빼주기
            cnt++; // 총 감독관 세기
            if (testplace[i] >= C) // 남은 응시인원이 부감독관이 한 번에 감시가능한 수보다 같거나 많다면
            {
                if (testplace[i] % C == 0) // 부감독관 감시 가능 수로 나누어 떨어지면 그 몫 만큼 부 감독관 필요
                    cnt += testplace[i] / C;
                else // 나누어 떨어지지 않으면 몫 + 1 만큼 부 감독관 필요
                    cnt += (testplace[i] / C) + 1;
            }
            else if (testplace[i] > 0) // 부감독관 감시 가능 수 보다는 작지만 0보다는 크면 여전히 부감독관 1명은 필요
            {
                cnt += 1;
            }
            // 그 밖 == 0명 남음 == 안 남음
        }

        System.out.println(cnt);
    }
}
