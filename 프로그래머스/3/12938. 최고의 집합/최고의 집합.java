import java.util.*;

/*

원소의 갯수가 n
원소의 합이 s
원소의 곱이 최대
안되면 -1

*/
class Solution {
    public int[] solution(int n, int s) {
        int[] answer = {};
        
        if (s % n == 0) {
            answer = new int[n];
            Arrays.fill(answer, s/n);
        }
        else {
            if ( s < n ) {
                answer = new int[]{-1};
            }
            else {
                answer = new int[n];
                int left = s % n;
                for (int i = 0; i < n; i++) {
                    if (left > 0) {
                        answer[i] = 1;
                        left--;
                    }    
                    answer[i] += s/n;
                }
                Arrays.sort(answer);
            }
        }
        return answer;
    }
}