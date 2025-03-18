class Solution {
    public int solution(int n) {
        int answer = 0;
        
        for (int left = 1; left <= n / 2; left++) {
            int l = left;
            int toPlus = l + 1;
            while (l < n) {
                l += toPlus;
                toPlus++;
            }
            if (l == n) answer++;
        }
        return answer + 1;
    }
}