package SamsungAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14891 {
    public static boolean[] pole; // 극 일치 여부 체크용 참거짓 배열 // 참 = 일치 // 거짓 = 불일치
    public static int[][] gear = new int[5][9]; // 각 번호대로 하기 위해서 톱니바퀴 수 + 1 = 5 / 톱니 수 + 1 = 9
    public static void gearCheck() // 인접한 톱니의 극 일치여부 반영
    {
        // n번과 n+1번 톱니의 3시와 9시 방향 톱니의 극 일치 여부 => pole[n]
        if (gear[1][3] == gear[2][7])
            pole[1] = true;
        if (gear[2][3] == gear[3][7])
            pole[2] = true;
        if (gear[3][3] == gear[4][7])
            pole[3] = true;
    }

    public static void reset() // 톱니의 극 일치 여부 초기화 => 안해주면 true였다가 false이면 반영 안될 수 있음! 위의 gearcheck함수의 구조와 연게에서 생각하면 이해가능!
    {
        for (int i = 1; i <= 3; i++)
            pole[i] = false;
    }
    public static int getScore(int[][] gear) // 마지막에 점수 구해서 리턴하는 함수 // 각 톱니바퀴의 12시 방향의 톱니의 극이 S인 경우만 새로운 점수 반영!
    {
        int score = 0;
        for (int i = 1; i <= 4; i++)
        {
            if (gear[i][1] == 1)
                score += Math.pow(2, i - 1); // 2의 i-1제곱 만큼 더해주는 규칙성에 따라 cf) 2의 0제곱 = 1
        }
        return score;
    }
    public static void clockwise (int[] gear) // 시계방향으로 회전
    {
        int tmp = gear[8];
        for (int i = 8; i >= 2; i--)
            gear[i] = gear[i - 1];
        gear[1] = tmp;
    }
    public static void counterwise (int[] gear) // 반시계방향으로 회전
    {
        int tmp = gear[1];
        for (int i = 1; i <= 7; i++)
            gear[i] = gear[i + 1];
        gear[8] = tmp;
    }
    public static void rotataion (int way, int which) // 입력받은 값에 따라 해당하는 톱니바퀴를 해당하는 방향으로 회전
    {
        if (way == 1)
            clockwise(gear[which]);
        else
            counterwise(gear[which]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 1; i <= 4; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine(), "01", true);
            for (int j = 1; j <= 8; j++)
                gear[i][j] = Integer.parseInt(st.nextToken()); // N극은 0 / S극은 1
            // i 1(12시) 2(1시30분) 3(3시) 4(4시 30분) 5(6시) 6(7시 30분) 7(9시) 8(10시 30분)
            // 중요한 건 1번 톱니의 3 / 2,3 번 톱니의 3 & 7 / 4번 톱니의 7
        }
        int K = Integer.parseInt(br.readLine());
        int[] gearNum = new int[K + 1]; // 회전 시작 톱니바퀴 번호
        int[] rotate = new int[K + 1]; // 회전할 방향
        pole = new boolean[4]; // 3(각 톱니간 사이 공간의 수 죽, 4 - 1) + 1(1부터 시작하기 위해)
        for (int k = 1; k <= K; k++)
        {
            StringTokenizer sta = new StringTokenizer(br.readLine());
            gearNum[k] = Integer.parseInt(sta.nextToken()); // 1,2,3,4 중 하나
            rotate[k] = Integer.parseInt(sta.nextToken()); // 1은 시계 / -1은 반시계
        }
        for (int k = 1; k <= K; k++)
        {
            reset(); // 톱니바퀴 간 극 일치 여부 초기화
            gearCheck(); // 현재 톱니바퀴 간 극 일치 여부 반영
            rotataion(rotate[k], gearNum[k]); // 일단 회전을 시작하는 톱니바퀴 회전
            int tmp = rotate[k]; // 해당 회전 방향 초기값 저장 for 오른쪽 톱니바퀴 회전
            for (int l = gearNum[k] - 1; l >= 1; l--) // 회전 시작 톱니바퀴보다 왼쪽 톱니부터 회전 시작
            {
                if (pole[l] == false) // 극이 일치 하지 않는 경우에만 회전방향 바꿔서 회전
                {
                    rotate[k] *= -1;
                    rotataion(rotate[k], l);
                }
                else // 한 번 일치하지 않고나면 해당 톱니바퀴와 그 다음것들을 어차피 회전 X
                    break;
            }
            rotate[k] = tmp; // 회전방향 다시 초기값으로 돌려주고
            for (int r = gearNum[k]; r <= 3; r++) // 오른쪽 톱니바퀴들도 회전
            {
                if (pole[r] == false)
                {
                    rotate[k] *= -1;
                    rotataion(rotate[k], r + 1);
                }
                else
                    break;
            }
        }
        System.out.println(getScore(gear)); // 점수 구해서 출력
    }
}