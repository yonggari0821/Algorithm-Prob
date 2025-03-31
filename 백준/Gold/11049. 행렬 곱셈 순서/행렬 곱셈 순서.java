import java.io.*;
import java.util.*;

public class Main {

    static int n, m;

    public static void main(String args[]) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 행렬 갯수
        int[][] matrixRC = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            matrixRC[i][0] = Integer.parseInt(st.nextToken()); // 행 길이
            matrixRC[i][1] = Integer.parseInt(st.nextToken()); // 열 길이
        }
        // dp[i][j]: i번재 행렬부터 j번째 행렬까지 곱하는데 필요한 최소 연산 횟수
        int[][] dp = new int[n][n];

        // 구간 길이: 1부터 n-1까지
        for (int len = 1; len < n; len++) {
            // s는 시작 인덱스 // e는 끝 인덱스
            for (int s = 0; s < n - len; s++) {
                int e = s + len; //
                // 같은 행렬이면 연산횟수 0
                if (s == e) dp[s][e] = 0;
                // 인접한 두 행렬의 곱셈 연산 횟수는 바로 곱해서 넣기
                else if (e == s + 1) dp[s][e] = matrixRC[s][0] * matrixRC[s][1] * matrixRC[e][1];
                // 3개 이상인 경우부터는 여러 가지 경우를 살펴서 최소값을 저장해가기
                else {
                    int min = Integer.MAX_VALUE; // 최소값
                    // k는 분할 지점임
                    /*
                        전체 범위를 분할 지점을 기준으로 왼쪽과 오른쪽으로 나눠서
                        왼쪽 부분행렬 연산 + 오른쪽 부분행렬 연산 + 두 결과 행렬의 곱셈 연산
                    */
                    for (int k = s; k < e; k++) {
                        min = Math.min( min, dp[s][k] + dp[k+1][e] + matrixRC[s][0] * matrixRC[k][1] * matrixRC[e][1] );
                        dp[s][e] = min;
                    }
                }
            }

        }

        System.out.println(dp[0][n - 1]);
    }
}