import java.util.*;

/*

1:1 (2:2 / 3:3 / 4:4) 모두 포함
2:3
2:4
3:4

총 4가지 경우 뿐!

*/
class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        int len = weights.length;
        
        Map<Integer, Long> two = new HashMap<>();
        Map<Integer, Long> three = new HashMap<>();
        Map<Integer, Long> four = new HashMap<>();
        
        for (int i = 0; i < len; i++) {
            int v = weights[i];
            two.put(v * 2, two.getOrDefault(v * 2, (long)0) + 1);
            three.put(v * 3, three.getOrDefault(v * 3, (long)0) + 1);
            four.put(v * 4, four.getOrDefault(v * 4, (long)0) + 1);
        }
        
        // 1:1 & 2:3 & 2:4 체크
        
        // 1:1의 경우 cnt 1 => 0 / 2 => 1 / 3 => 3 / 4 => 6 ... n => n(n-1) / 2
        // 2:3이나 2:4의 경우 two의 cnt * three(four)의 cnt
        for (Map.Entry<Integer, Long> entry2 : two.entrySet()) {
            int val2 = entry2.getKey();
            long cnt2 = entry2.getValue();
            answer += (cnt2 * (cnt2 - 1) / 2);
            for (Map.Entry<Integer, Long> entry3 : three.entrySet()) {
                int val3 = entry3.getKey();
                long cnt3 = entry3.getValue();
                if (val2 == val3) {
                    answer += (cnt2 * cnt3);
                }
            }
            for (Map.Entry<Integer, Long> entry4 : four.entrySet()) {
                int val4 = entry4.getKey();
                long cnt4 = entry4.getValue();
                if (val2 == val4) {
                    answer += (cnt2 * cnt4);
                }
            }
        }
        
        // 3:4의 경우
        for (Map.Entry<Integer, Long> entry3 : three.entrySet()) {
            int val3 = entry3.getKey();
            long cnt3 = entry3.getValue();
            for (Map.Entry<Integer, Long> entry4 : four.entrySet()) {
                int val4 = entry4.getKey();
                long cnt4 = entry4.getValue();
                if (val3 == val4) {
                    answer += (cnt3 * cnt4);
                }
            }
        }
        
        return answer;
    }
}