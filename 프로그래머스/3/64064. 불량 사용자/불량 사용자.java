import java.util.*;

class Solution {
    
    private static boolean isDefective(String original, String opponent) {
        if (original.length() != opponent.length()) return false;
        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) == '*') continue;
            if (original.charAt(i) != opponent.charAt(i)) return false;
        }
        return true;
    }
    
    private void backtrack(List<Integer> candidates, int index, int used, Set<Integer> result) {
        if (index == candidates.size()) {
            result.add(used);
            return;
        }
        
        int candidate = candidates.get(index);
        
        for (int i = 0; i < 8; i++) {
            if ((candidate & (1 << i)) != 0 && (used & (1 << i)) == 0) {
                backtrack(candidates, index + 1, used | (1 << i), result);
            }
        }
    }
    
    public int solution(String[] user_id, String[] banned_id) {
        // 각 banned_id에 매칭되는 user_id들의 비트마스크를 저장
        List<Integer> candidates = new ArrayList<>();
        
        for (int i = 0; i < banned_id.length; i++) {
            String cur = banned_id[i];
            int tmp = 0;
            for (int j = 0; j < user_id.length; j++) {
                if (isDefective(cur, user_id[j])) {
                    tmp |= (1 << j);
                }
            }
            if (tmp != 0) {
                candidates.add(tmp);
            }
        }
        
        if (candidates.size() == 0) return 0;
        
        // 백트래킹으로 가능한 조합 찾기
        Set<Integer> result = new HashSet<>();
        backtrack(candidates, 0, 0, result);
        
        return result.size();
    }
    
}
