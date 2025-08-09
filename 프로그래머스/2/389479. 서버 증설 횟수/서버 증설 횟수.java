import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        Queue<int[]> queue = new LinkedList<>();
        int plus = 0;

        for (int i = 0; i < players.length; i++) {
            // 만료 서버 내리기
            while (!queue.isEmpty() && queue.peek()[0] == i) {
                plus -= queue.poll()[1];
            }
            int need = players[i] / m;
            int toPlus = need - plus;
            if (toPlus > 0) {
                plus += toPlus;
                answer += toPlus;
                queue.add(new int[]{i + k, toPlus});
            }
        }

        return answer;
    }
}