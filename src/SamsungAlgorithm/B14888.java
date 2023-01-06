package SamsungAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14888 {

    public static int N, max, min; // N = 수의 갯수 // max = 최대가능값 // min = 최소가능값
    public static int[] num; // 숫자들 받는 배열
    public static int[] symbol; // 기호들 갯수대로 쭉 받아놓을 배열 for DFS

    public static int ft_plus(int a, int b) // 더하기
    {
        return a + b;
    }
    public static int ft_minus(int a, int b) // 빼기
    {
        return a - b;
    }
    public static int ft_multiple(int a, int b) // 곱하기
    {
        return a * b;
    }
    public static int ft_divide(int a, int b) // 나누기
    {
        if (a < 0 && b > 0)
        {
            a = -a;
            int tmp = a / b;
            return -tmp;
        }
        else if (a > 0 && b < 0)
        {
            b = -b;
            int tmp = a / b;
            return -tmp;
        }
        return a / b;
    }
    public static void ft_getsymbol(int plus, int minus, int multiplication, int divide) // 기호들 받기
    {
        int index = 0; // symbol 배열 인덱스
        while (plus > 0) // 플러스 갯수 만큼 1 배정
        {
            symbol[index] = 1;
            plus--;
            index++;
        }
        while (minus > 0) // 마이너스 갯수 만큼 2 배정
        {
            symbol[index] = 2;
            minus--;
            index++;
        }
        while (multiplication > 0) // 곱하기 갯수 만큼 3 배정
        {
            symbol[index] = 3;
            multiplication--;
            index++;
        }
        while (divide > 0) // 나누기 갯수 만큼 4 배정
        {
            symbol[index] = 4;
            divide--;
            index++;
        }
    }

    public static void DFS(int pick, int tmp, boolean[] picked)
    {
        if (pick == N - 1)
        {
            // System.out.println("tmp = " + tmp);
            max = max > tmp ? max : tmp; // 기존 최대가능값보다 크면 리뉴얼
            min = min < tmp ? min : tmp; // 기존 최소가능값보다 작으면 리뉴얼
            return ;
        }
        for (int i = 0; i < N - 1; i++) // i = symbol 배열 인덱스 = 기호 결정
        {
            // 만약 아직 고르지 않은 기호라면(동일한 기호라도 순서에 따라서 고르지 않은 기호가 될 수 있음!!)
            if (picked[i] == false) {
                int init = tmp; // 그 때의 기존 값을 따로 빼 두어야 함!
                // System.out.println(i);
                picked[i] = true; // 고른 걸로 처리해주고
                switch (symbol[i]) { // 해당 기호에 따라 맞는 연산 처리 후에 값에 배정
                    case 1:
                        tmp = ft_plus(tmp, num[pick + 1]);
                        break;
                    case 2:
                        tmp = ft_minus(tmp, num[pick + 1]);
                        break;
                    case 3:
                        tmp = ft_multiple(tmp, num[pick + 1]);
                        break;
                    case 4:
                        tmp = ft_divide(tmp, num[pick + 1]);
                        break;
                }
                DFS(pick + 1, tmp, picked); // 새로운 값과 고른 횟수 + 1로 DFS 실행
                tmp = init; // 끝났으면 연산 안한 걸로 되돌려 주고 다른 기호를 트라이 하기 위해 연산 전 값으로 초기화
                picked[i] = false; // 마찬가지로 고르지 않은 것으로 바꿔주기!
            }
        }
    }

    public static void main(String[] args) throws IOException {
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        num = new int[N];
        symbol = new int[N - 1];
        boolean[] picked = new boolean[N - 1];
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++)
            num[n] = Integer.parseInt(st1.nextToken());

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int plus = Integer.parseInt(st2.nextToken());
        int minus = Integer.parseInt(st2.nextToken());
        int multiplication = Integer.parseInt(st2.nextToken());
        int divide = Integer.parseInt(st2.nextToken());

        ft_getsymbol(plus, minus, multiplication, divide);
        DFS(0, num[0], picked);

        System.out.println(max + "\n" + min);
    }
}
