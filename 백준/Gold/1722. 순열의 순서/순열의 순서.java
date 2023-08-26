import java.io.*;
import java.util.*;
public class Main {
    static StringBuilder ans = new StringBuilder();
    static int N, K;
//    static long getFactorial(int n)
//    {
//        if (n <= 1) return 1;
//        long ans = 1;
//        while (n > 1) ans *= n--;
//        return ans;
//    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        boolean[] usedNum = new boolean[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        long[] factorialGrid = new long[N]; // 팩토리얼 값들 저장 해놓을 배열
        factorialGrid[0]= 1;
        for (int i = 1; i < N; i++) factorialGrid[i] = factorialGrid[i-1] * i;
        if (K==1) // order 번째 순열 출력
        {
            long order = Long.parseLong(st.nextToken());
            for (int i = 1; i <= N; i++) // 최종 출력할 순열에서의 인덱스
            {
                for (int j = 1; j <= N; j++) // 최종 출력할 순열에 들어갈 값
                {
                    if (usedNum[j]) continue;
                    if (factorialGrid[N-i] < order) order -= factorialGrid[N-i];
                    else
                    {
                        if (i == N) ans.append(j);
                        else ans.append(j).append(" ");
                        usedNum[j] = true;
                        break;
                    }
                }
            }
        }
        else // 순열 입력받아 순서 출력
        {
            int[] nums = new int[N + 1];
            long tmp = 1; // 0 번째 수는 없으므로 못해도 1 <= 이 tmp도 int로 하면 안됨!!
            for (int i = 1; i <= N; i++) nums[i] = Integer.parseInt(st.nextToken());
            for (int i = 1; i <= N; i++) // 수 갯수
            {
                for (int j = 1; j < nums[i]; j++) // nums[i] = 주어진 순열에서 i번째의 수
                {
                    if (usedNum[j]) continue;//  nums[i]보다 작은 수라도 이미 사용된 수인 경우는 세서는 안됨!
                    tmp += factorialGrid[N-i]; // nums[i] - 1 - 사용된 자기보다 작은 수 만큼, 해당 자리 보다 아래 들어갈 수 있는 가짓 수 팩토리얼을 최종 값에 더해줌
                }
                usedNum[nums[i]] = true; // 사용했음 처리 => 후에 걸러져야 하기 때문!!
            }
            ans.append(tmp);
        }
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}