import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        // 완호의 근무 태도 및 동료 평가 점수와 그 합
        int pa = scores[0][0];
        int pb = scores[0][1];
        int wanhoSum = pa + pb;
        
        // 근무태도 내림차순, 같으면 동료평가 오름차순 정렬
        Arrays.sort(scores, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return b[0] - a[0];
        });
        
        int maxPeer = 0;
        int curRank = 1;
        
        for (int[] score : scores) {
            int w = score[0];
            int p = score[1];
            
            if (p >= maxPeer) {
                // 인센티브 받을 수 있는 사원
                if (w + p > wanhoSum) {
                    curRank++;
                }
                maxPeer = p;
            } else {
                // 인센티브 제외된 사원
                if (w == pa && p == pb) {
                    return -1; // 완호가 인센티브 제외
                }
            }
        }
        
        return curRank;
    }
}
