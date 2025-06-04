import java.util.*;
import java.util.stream.*;

/*
    1시간동안 최소한의 친구들과 함께 n미터 둘레의 외벽 점검
    0 == n
    취약지점들 간 거리
    
    [풀이]
    사실 시계방향 반시계방향은 전혀 상관이 없음
    
*/
class Solution {
    
    private int[] 친구들이동거리;
    private int 외벽총둘레;
    private int 최소친구수 = Integer.MAX_VALUE;
    
    // 순서 뒤집기
    private void reverse(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int tmp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = tmp;
        }
    }
    
    private void countFriends(List<Integer> list, int found) {
        // 리스트가 비었다 == 모든 취약점들을 다 살펴봤다
        if (list.isEmpty()) {
            if (최소친구수 > found) 최소친구수 = found;
            return;
        }
        
        // 가지치기
        // 전자 : 모든 취약점 이미 점검한 경우
        // 후자 : 모든 취약점을 점검할 수 없는 경우
        if (found == 친구들이동거리.length || found == 최소친구수) return;
        
        // 취약지점들 하나씩 꺼내서 시작점으로 잡고 친구 배치해나가면서 depth 늘려가면서 재귀
        for (int i = 0; i < list.size(); i++) {
            // 체크 시작 할 취약지점
            int start = list.get(i);
            int end;
            
            // 임시 리스트
            List<Integer> tmp = new ArrayList<>(list);
            // 도착점 == 현재 취약점부터 배치하는 친구의 이동거리만큼 이동한 곳
            end = start + 친구들이동거리[found];
            
            for (int 취약점: list) {
                // 도착점이 외벽총둘레보다 작음 == 친구 이동 범위 내의 취약점들 제거하고 다음 depth 가기
                if (end < 외벽총둘레) {
                    if (취약점 >= start && 취약점 <= end) tmp.remove(tmp.indexOf(취약점));
                }
                // 도착점이 외벽총 둘레 이상임 == 친구 이동 범위 내의 취약점들 제거하되, 외벽총둘레 넘지 않게 제거하고 다음 depth가기
                else {
                    if (취약점 >= start || 취약점 <= end % 외벽총둘레) tmp.remove(tmp.indexOf(취약점));
                }
            }
            // 다음 depth로 슛~!
            countFriends(tmp, found + 1);
        }
    }
    
    public int solution(int n, int[] weak, int[] dist) {
        친구들이동거리 = dist;
        외벽총둘레 = n;
        // 친구들 이동거리는 왜 갑자기 뒤집어요? => 더 먼 거리를 이동할 수 있는 친구부터 우선적으로 투입해야 하므로!
        reverse(친구들이동거리);
        
        List<Integer> 취약지점들 = Arrays.stream(weak).boxed().collect(Collectors.toList());
        
        countFriends(취약지점들, 0);
        
        return 최소친구수 != Integer.MAX_VALUE ? 최소친구수 : -1;
    }
}