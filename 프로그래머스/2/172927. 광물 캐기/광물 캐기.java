import java.util.*;

class Solution {
    
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        
        // 사용할 수 있는 곡괭이의 총 개수
        int totalPicks = picks[0] + picks[1] + picks[2];
        
        // 캘 수 있는 광물 그룹의 수 (+4 / 5로 묶음)
        int groups = Math.min((minerals.length + 4) / 5, totalPicks);
        
        // 각 그룹별 피로도 계산을 위한 배열
        int[][] fatigueByGroup = new int[groups][3]; // [그룹][곡괭이 타입(0:다이아, 1:철, 2:돌)]
        
        // 각 그룹별로 다이아/철/돌 곡괭이를 사용했을 때의 피로도 계산
        for (int i = 0; i < groups; i++) {
            for (int j = 0; j < 5 && i * 5 + j < minerals.length; j++) {
                String mineral = minerals[i * 5 + j];
                
                // 다이아 곡괭이 사용 시 피로도
                fatigueByGroup[i][0] += 1;
                
                // 철 곡괭이 사용 시 피로도
                if (mineral.equals("diamond")) {
                    fatigueByGroup[i][1] += 5;
                } else {
                    fatigueByGroup[i][1] += 1;
                }
                
                // 돌 곡괭이 사용 시 피로도
                if (mineral.equals("diamond")) {
                    fatigueByGroup[i][2] += 25;
                } else if (mineral.equals("iron")) {
                    fatigueByGroup[i][2] += 5;
                } else {
                    fatigueByGroup[i][2] += 1;
                }
            }
        }
        
        // 돌 곡괭이 사용 시 피로도를 기준으로 그룹을 내림차순 정렬
        // 가장 피로도가 높은 그룹에 좋은 곡괭이를 사용하기 위함
        Arrays.sort(fatigueByGroup, (a, b) -> b[2] - a[2]);
        
        // 각 그룹에 사용할 곡괭이 할당
        for (int i = 0; i < groups; i++) {
            if (picks[0] > 0) {  // 다이아 곡괭이 사용
                answer += fatigueByGroup[i][0];
                picks[0]--;
            } else if (picks[1] > 0) {  // 철 곡괭이 사용
                answer += fatigueByGroup[i][1];
                picks[1]--;
            } else if (picks[2] > 0) {  // 돌 곡괭이 사용
                answer += fatigueByGroup[i][2];
                picks[2]--;
            }
        }
        
        return answer;
    }
}
