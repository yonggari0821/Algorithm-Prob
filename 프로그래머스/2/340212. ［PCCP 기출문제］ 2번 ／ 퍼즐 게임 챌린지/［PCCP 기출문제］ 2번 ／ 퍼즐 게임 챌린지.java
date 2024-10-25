class Solution {
    public long solution(int[] diffs, int[] times, long limit) {
        long answer = 0;
        int len = diffs.length;
        int max = 0;
        
        for (int i = 1; i < len; i++) {
            max = max < diffs[i] ? diffs[i] : max;
        }
        
        // 이분탐색 탐색 범위
        long l = 1;
        long r = max;
        
        while (l < r) {
            long m = (l + r) / 2;
            int i = 0;
            long time = 0;
            while (i < len) {
                if (m >= diffs[i]) {
                    time += times[i];
                }
                else {
                    // (diffs[i] - m) : 틀리는 횟수
                    if (i == 0) {
                        time += ( (diffs[i] - m) * (times[i]) );
                    }
                    else {
                        time += ( (diffs[i] - m) * (times[i] + times[i - 1]) );
                    }
                    time += times[i];
                }
                i++;
            }
            if (time <= limit) r = m;
            else l = m + 1;
        }
    
        answer = l;
        return answer;
    }
}