import java.util.*;

/*
    보유 병사 n명
    연속되는 적의 공격을 순서대로 막아야 함
    보유 병사 < 적 수 = 게임 종료
    "무적권" 병사 소모 없이 한 라운드 공격 막음
    최대 k번 사용
    최대 몇 라운드까지 막을 수 있는가?
    
    무적권은 무적권 적의 수가 많은 곳에 쓸 수록 유리하지만 또 그게 뒤에 몰려 있으면 의미가 없음
    그냥 매 라운드에 쓸 지 말지로 dfs 해볼까? => 최대 2의 50만승 => 안될 거 같은데
    
    그럼 어떻게 풀지?
    enemy 길이를 이분탐색하면서 해당 라운드까지 막을 수 있는 지 여부를 판단해서 해볼까?
    => 어떻게 판단하는데? => 어떤 라운드까지 갔을 때 언제 무적권을 쓸지를 어떻게 판단하는데?
    => 해당 라운드까지 라운들 별 적의 수를 내림차순으로 정렬해주는 우선순위 큐 이용?
    => 그럼 우선순위 큐가 100만개 필요한데? => 그리고 앞에서 작더라도 무조건 써야하는 경우도 있을 거 같은데 이 경우 판단이 불가할 거 같다
    
    => 다른 방법 뭐가 있을까?
    => 결국 매 상황별로 무적권을 써야할 지 안써도 될지 판단하면 될 거 같은데
    => 이게 또 그리디 처럼 무조건 미루고 뒤에 쓸 수도 없는 게 앞에 큰 수의 적이 오는 경우에는 앞에 쓰는게 좋단 말이지
    => 모르겠다....
    => 일단 2의 50만승 되는 지 해볼까?
    => 역시 안됨
    
    => 그럼 앞에 생각했던 이분탐색 풀이로 해보자
    
*/

import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int left = 0;
        int right = enemy.length + 1;

        while (left < right) {
            int mid = (left + right) / 2;
            if (canDefend(mid, n, k, enemy)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left - 1;
    }


    private boolean canDefend(int rounds, int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int soldiers = n;
        for (int i = 0; i < rounds; i++) {
            pq.offer(enemy[i]);
        }
        for (int i = 0; i < k && !pq.isEmpty(); i++) {
            pq.poll(); // k번 큰 숫자 제거 (무적권 사용)
        }
        while (!pq.isEmpty()) {
            soldiers -= pq.poll();
            if (soldiers < 0) return false;
        }
        return true;
    }
}
