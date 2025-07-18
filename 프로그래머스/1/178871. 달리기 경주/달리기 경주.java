import java.util.*;

class Solution {
    
    private static Map<String, Integer> map = new HashMap<>();
    private static String[] p;
    private static void swap(int a, int b) {
        if (b == 0) return;
        String strA = p[a];
        String strB = p[b];
        p[a] = strB;
        p[b] = strA;
        map.put(strA, b);
        map.put(strB, a);
    }
    
    public String[] solution(String[] players, String[] callings) {
        String[] answer = new String[players.length];
        p = new String[players.length];
        
        for (int i = 0; i < players.length; i++) {
            p[i] = players[i];
            map.put(players[i], i);
        }
        
        for (int i = 0; i < callings.length; i++) {
            int b = map.get(callings[i]);
            swap(b - 1, b);
        }
        
        for (int i = 0; i < players.length; i++) {
            answer[i] = p[i];
        }
        return answer;
    }
}