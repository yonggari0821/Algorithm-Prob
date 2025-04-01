import java.util.*;

class Solution {
    public int solution(int N, int number) {
        int answer = 0;
    
        if (N == number) answer = 1;
        else {
            List<Set<Integer>> dp = new ArrayList<>();
            for (int i = 0; i <= 8; i++) dp.add(new HashSet<>());
            
            dp.get(1).add(N);
            
            for (int i = 2; i <= 8; i++) {
                int cur = N;
                for (int j = 1; j < i; j++) {
                    cur = 10 * cur + N;
                }
                dp.get(i).add(cur);
                
                // i번째까지의 연산값 == j번째까지의 연산값과 k번째까지의 연산값을 연산한 값
                for (int j = 1; j < i; j++) {
                    int k = i - j;
                    for (int num1 : dp.get(j)) {
                        for (int num2 : dp.get(k)) {
                            dp.get(i).add(num1 + num2);
                            dp.get(i).add(num1 - num2);
                            dp.get(i).add(num1 * num2);
                            if (num2 != 0) {
                                dp.get(i).add(num1 / num2);
                            }
                        }
                    }
                }
                if (dp.get(i).contains(number)) {
                    answer = i;
                    break;
                }
            }
        }
        return answer == 0 ? -1 : answer;
    }
}