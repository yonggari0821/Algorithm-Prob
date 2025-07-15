import java.util.*;

/*
    30 * 29 * 28 * 27 * 26 = 17100720 <= Integer.MAX_VALUE;
    
    
*/

class Solution {
    
    private static void func(int last, int depth, Set<Integer> set) {
        if (depth == 5) {
            can(set);
            return;
        }
        
        for (int i = last + 1; i <= nn; i++) {
            set.add(i);
            func(i, depth + 1, set);
            set.remove(i);
        }
    }
    
    private static void can(Set<Integer> set) {
        List<Integer> sortedList = new ArrayList<>(set);
        Collections.sort(sortedList);
        
        for (int j = 0; j < qq.length; j++) {
            int cnt = 0;
            int idx = 0;
            for (int i : sortedList) {
                while (idx < 5 && qq[j][idx] < i) idx++;
                if (idx < 5 && qq[j][idx] == i) cnt++;
            }
            if (cnt != ansans[j]) return;
        }
        answer++;
    }
    
    private static int answer = 0;
    private static int nn;
    private static int[][] qq;
    private static int[] ansans;
    
    public int solution(int n, int[][] q, int[] ans) {
        nn = n;
        qq = q;
        ansans = ans;
        
        func(0, 0, new HashSet<>());
        
        return answer;
    }
}