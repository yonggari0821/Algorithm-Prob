import java.util.*;

class Solution {

    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];
        int[] divisorCounts = new int[e + 1]; // 1~e까지 각 수의 약수 개수 저장

        // 약수 개수 전처리
        // i는 약수, i의 배수마다 약수 개수 +1
        for (int i = 1; i <= e; i++) {
            for (int j = 1; j <= e / i; j++) {
                divisorCounts[i * j]++;
            }
        }

        // 각 구간에서 약수 개수가 가장 많은 수를 기록
        // maxNum[i] = i~e 중 약수 개수가 가장 많은(여러 개면 가장 작은) 수
        int[] maxNum = new int[e + 1];
        maxNum[e] = e;
        for (int i = e - 1; i >= 1; i--) {
            // 현재 i와 i+1~e 중 최적값 비교
            maxNum[i] = (divisorCounts[i] >= divisorCounts[maxNum[i + 1]]) ? i : maxNum[i + 1];
        }

        // 각 질의에 대해 빠르게 정답 조회
        for (int i = 0; i < answer.length; i++) {
            answer[i] = maxNum[starts[i]];
        }

        return answer;
    }
}
