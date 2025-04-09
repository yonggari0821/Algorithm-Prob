import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        
        // 모든 종류의 보석 수 계산
        Set<String> set = new HashSet<>(Arrays.asList(gems));
        int gemNum = set.size();
        
        // 투 포인터 및 맵 초기화
        int si = 0, ei = 0; // 시작, 끝 포인터
        int gap = Integer.MAX_VALUE; // 최소 구간 길이
        Map<String, Integer> map = new HashMap<>();

        while (ei < gems.length) {
            // 현재 구간에 보석 추가
            map.put(gems[ei], map.getOrDefault(gems[ei], 0) + 1);
            ei++; // 끝 포인터 이동
            
            // 모든 종류의 보석이 포함되었을 때 처리
            while (map.size() == gemNum) {
                if (ei - si < gap) { // 더 짧은 구간 발견 시 갱신
                    gap = ei - si;
                    answer[0] = si + 1; // 1-based index로 변환
                    answer[1] = ei;     // 1-based index로 변환
                }
                
                // 시작 포인터 이동 및 해당 보석 제거 처리 // 무조건 1개 이상은 있으니 사전 체크는 불필요하고 사후에 0개 됐는 지 확인해서 빼주기!
                map.put(gems[si], map.get(gems[si]) - 1);
                if (map.get(gems[si]) == 0) map.remove(gems[si]);
                si++; // 시작 포인터 이동
            }
        }
        
        return answer;
    }
}
