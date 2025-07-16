import java.util.*;

/*
    막대 그래프 => 진출 차수가 0인 점
    8자 그래프 => 진입차수 2 이상이면서 진출 차수도 2인 점
    도넛 그래프 => 나머지
*/

class Solution {
    private static HashMap<Integer, Integer> fmap = new HashMap<>();
    private static HashMap<Integer, Integer> tmap = new HashMap<>();
    private static int maxVal = 0;
    private static int created = -1;
    private static int total = -1;
    
    
    public int[] solution(int[][] edges) {
        // answer[0]: 생성 정점 번호
        // answer[1]: 도넛
        // answer[2]: 막대
        // answer[3]: 8자
        int[] answer = new int[4];
        
        for (int i = 0; i < edges.length; i++) {
            int f = edges[i][0];
            int t = edges[i][1];
            fmap.put(f, fmap.getOrDefault(f, 0) + 1); // 진출차수 표시
            tmap.put(t, tmap.getOrDefault(t, 0) + 1); // 진입차수 표시
            
            maxVal = Math.max(maxVal, Math.max(f, t));
        }
        
        for (int i = 1; i <= maxVal; i++) {
            // 있는 번호면
            if (fmap.containsKey(i)) {
                // 출발점 & 진출/진입차수
                int cur = i;
                int howManyOut = fmap.get(cur); // 진출
                int howManyIn = tmap.getOrDefault(cur, 0); // 진입
                
                // 생성점인 경우 == 진출차수가 2 이상 & 진입차수 = 0
                if (howManyOut >= 2 && howManyIn == 0){
                    answer[0] = created = cur;
                    total = howManyOut;
                    fmap.remove(cur);
                    break;
                }
            }
        }
        
        int bar = 0;
        int eight = 0;
        int donut = 0;
        
        for (int i = 1; i <= maxVal; i++) {
            if (i == created) continue;
            
            if (!fmap.containsKey(i) && !tmap.containsKey(i)) {
                continue; // fmap, tmap 둘 다 없다면 그래프에 없는 번호이므로 건너뛴다.
            }
            
            // 출발점 & 진출/진입차수
            int cur = i;
            int howManyOut = fmap.getOrDefault(cur, 0); // 진출
            int howManyIn = tmap.getOrDefault(cur, 0); // 진입


            // 막대 => 진출이 0인 점
            if (howManyOut == 0) {
                bar++;
            }
            // 8자 => 진입 진출 모두 2인 점
            if (howManyIn >= 2 && howManyOut == 2) {
                eight++;
            }
        }
        
        answer[2] = bar;
        answer[3] = eight;
        // 도넛 => 나머지
        answer[1] = total - bar - eight;
        
        return answer;
    }
}
