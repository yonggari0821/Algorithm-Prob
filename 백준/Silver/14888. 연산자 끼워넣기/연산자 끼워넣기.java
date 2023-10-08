import java.io.*;
import java.util.*;

/*
[풀이]

N 개의 숫자로 이루어진 수열이 주어지고
그 사이에 끼워넣을 수 있는 N - 1개의 연산자가 주어질 때,
주어진 수들의 순서를 그대로 두고 중간중간 배치하는 연산자들의 조합에 따라 달라지는 값 중
가장 큰 값과 가장 작은 값을 한 줄씩 나눠서 출력하는 문제
조합 => 재귀
// 연산자 갯수가 정해져 있으므로 연산자 갯수가 0인 경우는 미리 제외할 수 있음
 */

public class Main {
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;
    static void func(int picked)
    {
        if (picked == N - 1)
        {
            int res = nums[0];
            for (int i = 0; i < N - 1; i++)
            {
                if (putOperators[i] == 0) res += nums[i + 1];
                else if (putOperators[i] == 1) res -= nums[i + 1];
                else if (putOperators[i] == 2) res *= nums[i + 1];
                else if (putOperators[i] == 3) res /= nums[i + 1]; // 수열로 나오는 수들은 1 이상 100이하 자연수이므로 0으로 나눠지는 걱정은 안해도 됨!
            }
            min = min < res ? min : res;
            max = max > res ? max : res;
            return ;
        }
        for (int i = 0; i < 4; i++)
        {
            if (operators[i] == 0) continue;
            operators[i]--;
            putOperators[picked] = i;
            func(picked + 1);
            operators[i]++;
        }
    }



    static int N;
    static int[] nums;
    static int[] operators;
    static int[] putOperators;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        putOperators = new int[N - 1];
        operators = new int[4];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
        // + - × ÷ 순서
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) operators[i] = Integer.parseInt(st.nextToken());
        func(0);
        ans.append(max).append('\n').append(min);
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}