import java.util.*;
import java.io.*;

public class Main {

    public static void main (String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] weightsAndValues = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            weightsAndValues[i][0] = Integer.parseInt(st.nextToken()); // w
            weightsAndValues[i][1] = Integer.parseInt(st.nextToken()); // v
        }

        int[][] dp = new int[N][K + 1];
        // 첫 번째 물건 세팅 (무게에 값 넣기 == weightsAndValues[0][0]에 weightsAndValues[0][1]넣기)
        for (int i = weightsAndValues[0][0]; i <= K; i++) dp[0][i] = weightsAndValues[0][1];
        // 나머지 물건들은 그 전 물건 넣은 것과 비교해서 넣기
        for (int i = 1; i < N; i++) {
            // 현재 물건을 넣었을 때 넘칠수도 있으므로 범위 미리 세팅!
            int weightLimit = Math.min(weightsAndValues[i][0], K + 1);
            // 현재 물건 넣기 전까지는 그 전 무게로 세팅
            for (int weight = 0; weight < weightLimit; weight++) dp[i][weight] = dp[i - 1][weight];
            // 물건 넣을 경우 dp[i][weight] 설정
            // 1) 현재 물건 넣는 경우 - 현재 물건 무게 만큼 뺀 부분의 무게의 값에다가 현재 물건 값 더하기
            // 2) 현재 물건 넣지 않는 경우 - 그냥 그대로 가져오기
            for (int weight = weightsAndValues[i][0]; weight < K + 1; weight++) {
                dp[i][weight] = Math.max(
                    // 1)
                     dp[i - 1][weight - weightsAndValues[i][0]] + weightsAndValues[i][1]
                    ,
                    // 2)
                     dp[i - 1][weight]
                );
            }
        }
        System.out.println(dp[N - 1][K]);
    }
}

/*
        Queue<Node> pq = new PriorityQueue<>(
                new Comparator<Node>() {
                    @Override
                    public int compare(Node o1, Node o2) {
                        return o1.v - o2.v;
                    }
                }
        );
 */