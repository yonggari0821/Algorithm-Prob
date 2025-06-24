import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int n = cards.length; // 카드 수
        int target = n + 1; // 타겟 넘버
        int idx = n / 3; // 손패 vs 나머지 구분용 idx
        
        // 손패
        Set<Integer> hand = new HashSet<>();
        for (int i = 0; i < idx; i++) hand.add(cards[i]);
        
        // 후보
        Set<Integer> pool = new HashSet<>();
        
        // 라운드 및 게임 지속 가능 여부
        int round = 1;
        boolean canPlay;
        
        while (idx < n) {
            // 2장 뽑아서 일단 pool에 추가
            pool.add(cards[idx]);
            pool.add(cards[idx + 1]);
            idx += 2;
            
            // 일단 게임 지속 불가능하다고 놓고
            canPlay = false;
            
            // 아래 우선순위에 따라서 조합해서 턴 넘어가기
            // 1순위: 손패끼리 조합 (비용 0)
            for (int card : new ArrayList<>(hand)) {
                if (hand.contains(target - card)) {
                    hand.remove(card);
                    hand.remove(target - card);
                    canPlay = true;
                    break;
                }
            }
            
            // 2순위: 손패 + pool 카드 조합 (비용 1 필요하므로 coin >= 1 체크!)
            if (!canPlay && coin >= 1) {
                for (int card : new ArrayList<>(hand)) {
                    if (pool.contains(target - card)) {
                        hand.remove(card);
                        pool.remove(target - card);
                        coin--;
                        canPlay = true;
                        break;
                    }
                }
            }
            
            // 3순위: pool 카드끼리 조합 (비용 2 필요하므로 coin >= 2 체크!)
            if (!canPlay && coin >= 2) {
                for (int card : new ArrayList<>(pool)) {
                    if (pool.contains(target - card)) {
                        pool.remove(card);
                        pool.remove(target - card);
                        coin -= 2;
                        canPlay = true;
                        break;
                    }
                }
            }
            
            if (!canPlay) break;
            round++;
        }
        
        return round;
    }
}
