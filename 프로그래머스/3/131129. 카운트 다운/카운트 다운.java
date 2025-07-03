import java.util.*;

class Solution {
    public int[] solution(int target) {
        // 가능한 점수 세트 생성 => 중복 제거 위함
        Set<Integer> scoreSet = new HashSet<>();
        for (int i = 1; i <= 20; i++) scoreSet.add(i);         // 싱글
        for (int i = 1; i <= 20; i++) scoreSet.add(i * 2);     // 더블
        for (int i = 1; i <= 20; i++) scoreSet.add(i * 3);     // 트리플
        scoreSet.add(50);                                      // 불
        // 리스트화
        List<Integer> scoreList = new ArrayList<>(scoreSet);

        int[] dpT = new int[target + 1];   // 최소 다트 수
        int[] dpSB = new int[target + 1];  // 싱글/불 최대 개수
        Arrays.fill(dpT, Integer.MAX_VALUE); // 다트수는 최소화 해야하므로 최댓값 넣어두고
        Arrays.fill(dpSB, 0); // 싱글/불은 최대화 해야하므로 0 넣어두기
        
        dpT[0] = 0;
        dpSB[0] = 0;

        for (int i = 1; i <= target; i++) {
            // 가능한 모든 점수에 대해서 살펴봐야 함
            for (int score : scoreList) {
                if (i - score < 0) continue;
                
                // 이전 다트 수
                int prevT = dpT[i - score];
                int prevSB = dpSB[i - score];
                // 싱글이나 불 여부
                int isSB = (score == 50 || (1 <= score && score <= 20)) ? 1 : 0;

                // 더 적은 횟수로 가능하다면 싱글인지 불인지 여부만 판단해서 더해주면서 더해가기
                if (dpT[i] > prevT + 1) {
                    dpT[i] = prevT + 1;
                    dpSB[i] = prevSB + isSB;
                } 
                // 동일한 경우도 싱글이나 불 더 많이 던질 수 있다면 리뉴얼!
                else if (dpT[i] == prevT + 1) {
                    // 최소 다트 수가 같으면 싱글/불 최대화
                    if (dpSB[i] < prevSB + isSB) {
                        dpSB[i] = prevSB + isSB;
                    }
                }
            }
        }

        return new int[]{dpT[target], dpSB[target]};
    }
}
