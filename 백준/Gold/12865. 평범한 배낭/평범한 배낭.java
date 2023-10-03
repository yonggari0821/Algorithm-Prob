import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 물건 갯수
        int K = Integer.parseInt(st.nextToken()); // 준서가 들 수 있는 최대 무게
        int[][] weightAndValue = new int[N][2]; // [0] - weight // [1] - value;
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            weightAndValue[n][0] = Integer.parseInt(st.nextToken());
            weightAndValue[n][1] = Integer.parseInt(st.nextToken());
        }
        /*
        [풀이]
        각 물건은 1개씩 있음
        배낭 문제를 풀 듯이 풀 되, 어떤 변수를 기준으로 배열을 만들 것인가가 중요
        dp[r][c]

        물건 수 == N (최대 100개)
        우리가 물건 별로 무게와 가치를 받아두기 때문에 => 얘는 무조건 행 크기(r)로 고정임

        그럼 열 크기(c)에 들어갈 수 있는 것은?
        1) 무게 2) 가치
        이 중 제한이 있는 것은 1) 무게 이므로 최대 버틸 수 있는 무게를 기준으로 배열 만들기
         */

        int[][] dp = new int[N][K + 1];
        // 첫 번째 물건의 무게 이상의 케이스에 첫 번째 물건의 가치를 세팅해주기
        for (int i = weightAndValue[0][0]; i <= K; i++)
        {
            dp[0][i] = weightAndValue[0][1];
        }
        // 두 번째 물건부터는
        for (int stuffNum = 1; stuffNum < N; stuffNum++)
        {
            for (int weight = 0; weight < Math.min(weightAndValue[stuffNum][0], K + 1); weight++)
            {
                dp[stuffNum][weight] = dp[stuffNum - 1][weight];
            }
            for (int weight = weightAndValue[stuffNum][0]; weight <= K; weight++)
            {
                dp[stuffNum][weight] = Math.max( dp[stuffNum - 1][weight - weightAndValue[stuffNum][0]] + weightAndValue[stuffNum][1], dp[stuffNum - 1][weight]);
            }
        }
        ans.append(dp[N - 1][K]);
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}