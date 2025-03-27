import java.util.*;
class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        
        int[] plusMinus = new int[sequence.length];
        int[] minusPlus = new int[sequence.length];
        long[] dpPM = new long[sequence.length];
        long[] dpMP = new long[sequence.length];
        
        plusMinus[0] = sequence[0];
        minusPlus[0] = sequence[0] * -1;
        
        dpPM[0] = plusMinus[0];
        dpMP[0] = minusPlus[0];
        answer = Math.max(plusMinus[0], minusPlus[0]);
        
        int tmp = -1;
        for (int i = 1; i < sequence.length; i++) {
            plusMinus[i] = sequence[i] * tmp;
            tmp *= -1;
            minusPlus[i] = sequence[i] * tmp;
            
            dpPM[i] = Math.max(dpPM[i - 1] + plusMinus[i], plusMinus[i]);
            dpMP[i] = Math.max(dpMP[i - 1] + minusPlus[i], minusPlus[i]);
            
            long max = Math.max(dpMP[i], dpPM[i]);
            answer = Math.max(answer, max);
        }
        
        return answer;
    }
}