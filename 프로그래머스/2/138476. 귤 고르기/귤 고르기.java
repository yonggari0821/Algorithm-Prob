import java.util.*;

/*
    가장 적게 등장하는 숫자들 제거하면 됨
*/

class Tangerine {
    int size;
    int count;
    
    Tangerine(int size, int count) {
        this.size = size;
        this.count = count;
    }
    
}

class Solution {
    private static Map<Integer, Integer> map = new HashMap<>();
    
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        for (int i = 0; i < tangerine.length; i++) {
            map.put(tangerine[i], map.getOrDefault(tangerine[i], 0) + 1);
        }
        
        PriorityQueue<Tangerine> pq = new PriorityQueue<>(new Comparator<Tangerine>(){
            @Override
            public int compare(Tangerine a, Tangerine b) {
                return b.count - a.count;
            }
        });
        
        for (int i : map.keySet()) {
            pq.offer(new Tangerine(i, map.get(i)));
        }
        
        while (k > 0 && !pq.isEmpty()) {
            // System.out.println(pq.peek().size + " sized tangerine with count " + pq.peek().count);
            k -= pq.poll().count;
            answer++;
        }
        
        return answer;
    }
}