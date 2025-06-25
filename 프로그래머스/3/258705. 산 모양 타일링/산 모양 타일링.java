import java.util.*;
/*
    dp문제
    2n + 1 크기 배열 만들기
    홀수 번째 => tops 배열 살펴서 0인지, 1인지에 따라서 경우 나눠서 하기
    0 포함 짝수 번째 => 삼각형 놓을지, ▲▼ 모양의 사다리꼴 놓을 지 결정
    
    10007로 나눈 나머지를 구하라고 한 이유는 overflow 방지 위함
    10000제곱이 약 1억이므로 10007의 제곱도 21억을 넘지는 않음!
    (A * B) % m = ((A % m) * (B % m)) % m
    위 공식 활용하는 것!
    
*/
class Solution {
    private static int[] dp;
    
    public int solution(int n, int[] tops) {
        // 초기화
        dp = new int[2 * n + 1]; // n은 최소 1이므로 최소 3 크기의 dp 배열이 만들어짐
        dp[0] = 1;
        dp[1] = tops[0] == 0 ? 2 : 3;
        dp[2] = tops[0] == 0 ? 3 : 4;
        
        for (int i = 3; i <= 2 * n; i++) {
            // 홀수 idx
            if ((i & 1) != 0) {
                if (tops[i / 2] == 0) {
                    dp[i] = dp[i - 1] + dp[i - 2];
                }
                else {
                    dp[i] = dp[i - 1] * 2 + dp[i - 2];
                }
            }
            // 짝수 idx
            else {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            dp[i] = dp[i] % 10007;
        }
        return dp[2 * n];
    }
}