import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {

    static int N, M, K, sum;
    static int[][] DP;
    static StringBuilder ans;

    public static void makePascalTriangle()
    {
        // <n>C<k> = <n-1>C<k-1> + <n-1>C<k>;
        DP[0][0] = 1;
        for (int n = 1; n <= sum; n++)
        {
            for (int k = 0; k <= n; k++)
            {
                if (k == 0 || k == n)
                    DP[n][k] = 1;
                else
                {
                    DP[n][k] = DP[n-1][k-1] + DP[n-1][k];
                    if (DP[n][k] >= 1000000000)
                        DP[n][k] = 1000000000;
                }
            }
        }
    }

    public static int makePascalTriangleByDFS(int n, int r) // <n>C<r> 로 생각!
    {
        if (n == r || r == 0) return 1;
        else if (DP[n][r] != 0) return DP[n][r];
        else return DP[n][r] = makePascalTriangleByDFS(n - 1, r - 1) + makePascalTriangleByDFS(n - 1, r);
        // 재귀로 하면 필요한 부분만 만들어가면서 값을 구함
        // => 시간적으로 이점이 있을 수 있음!! (아무래도 함수를 여러 번 호출하니깐 꼭 그렇지는 않음!!)
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        sum = N + M;
        DP = new int[sum + 1][sum + 1];
        makePascalTriangle();

//        파스칼 트리 출력법
//        for (int i = 0; i <= sum; i++)
//        {
//            System.out.println("Start of " + i + "------");
//            for (int j = 0; j <= i; j++)
//            {
//                System.out.printf("/%d/", DP[i][j]);
//            }
//            System.out.println("\nEnd of " + i + "------");
//        }

        // <sum>C<M> = 총 문자열의 수이자 앞으로 K와 비교해서 놓을 문자를 결정하는 기준!!
        if (DP[sum][M] < K) // 총 문자열의 수와 K를 비교해서 K가 크면 K번째 문자열을 고를 수 없음!!
        {
            System.out.println(-1);
            return;
        }

        // 이 아래로는 고를 수 있는 경우! => 특정 문자열이 출력 되어야 함!
        // M 개만큼의 z를 sum개 만큼의 길이를 가진 문자열에 배치한다고 생각
        // 즉 sum = 문자열의 길이 // M = 붙혀야할 z의 갯수
        // ex 1) aaaaa => sum = 5 / M = 0;
        // ex 2) zzzzz => sum = 5 / M = 5;
        ans = new StringBuilder();
        while(sum - M >= 1 && M >= 1) // N 또는 M 이 0이 될 때 까지 (while문 안에서 무조건 1개씩 빼기 때문에 조건 범위는 1 이상)
        {
            sum -= 1; // 경계 설정 위해 1개 빼고 시작
            if (DP[sum][M] >= K) // 경계가 K를 포함함 // a 붙히기
            {
                ans.append('a');
            }
            else // K가 경계를 벗어남 // z 붙히고, 경계만큼 K에서 빼주고, z를 붛혔으니깐 z 갯수 - 1; 
            {
                ans.append('z');
                K -= DP[sum][M];
                M -= 1;
            }
        }

        if (sum - M == 0) // 아직 안 붙힌 z의 갯수 == 남은 자리 수인 경우 => 모두 z로 배치
        {
            while (M > 0)
            {
                ans.append('z');
                M -= 1;
            }
        }
        else // z를 다 골라서 while문을 빠져나온 경우 => 나머지 부분에 a 배치
        {
            while (sum > 0)
            {
                ans.append('a');
                sum -= 1;
            }
        }
        System.out.println(ans.toString());
    }
}