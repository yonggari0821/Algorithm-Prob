import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        // 카드 갯수
        int n = cards.length;
        // 타겟 숫자
        int target = n + 1;
        // 구분 idx
        int idx = n / 3;
        
        // 초기 손패
        Set<Integer> hand = new HashSet<>();
        for (int i = 0; i < idx; i++) hand.add(cards[i]);
        
        // 후보 카드들(초기 손패 제외 카드들)
        Set<Integer> candidates = new HashSet<>();
        
        // 라운드
        int round = 1;
        
        
        while (idx < n) {
            // 매 라운드마다 2장씩 뽑기
            candidates.add(cards[idx]);
            candidates.add(cards[idx + 1]);
            idx += 2;
            
            boolean canPlay = false;
            
            // 1순위: 손패끼리 조합 (비용 0)
            Integer cardToRemove1 = null, cardToRemove2 = null;
            for (int card : hand) {
                int pair = target - card;
                if (hand.contains(pair) && card < pair) { // 중복 방지
                    cardToRemove1 = card;
                    cardToRemove2 = pair;
                    canPlay = true;
                    break;
                }
            }
            if (canPlay) {
                hand.remove(cardToRemove1);
                hand.remove(cardToRemove2);
            }
            
            // 2순위: 손패 + 후보카드 조합 (비용 1)
            if (!canPlay && coin >= 1) {
                Integer handCard = null, candidateCard = null;
                for (int card : hand) {
                    int pair = target - card;
                    if (candidates.contains(pair)) {
                        handCard = card;
                        candidateCard = pair;
                        canPlay = true;
                        break;
                    }
                }
                if (canPlay) {
                    hand.remove(handCard);
                    candidates.remove(candidateCard);
                    coin--;
                }
            }
            
            // 3순위: 후보카드끼리 조합 (비용 2)
            if (!canPlay && coin >= 2) {
                Integer candidate1 = null, candidate2 = null;
                for (int card : candidates) {
                    int pair = target - card;
                    if (candidates.contains(pair) && card < pair) { // 중복 방지
                        candidate1 = card;
                        candidate2 = pair;
                        canPlay = true;
                        break;
                    }
                }
                if (canPlay) {
                    candidates.remove(candidate1);
                    candidates.remove(candidate2);
                    coin -= 2;
                }
            }
            
            // 조합을 만들 수 없으면 게임 종료
            if (!canPlay) {
                break;
            }
            
            round++;
        }
        
        return round;
    }
}
