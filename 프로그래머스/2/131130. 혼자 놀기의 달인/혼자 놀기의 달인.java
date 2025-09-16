import java.util.*;

/*
    cards
        index + 1: 상자 번호
        val: 상자 안 숫자 카드 번호
    nums
        cards 배열의 별도 복사본
    dfs
        index를 받아서 이미 연 상자인지 nums 배열 활용해서 체크 후 탐색 진행
*/

class Solution {
    private static int[] cards;
    private static int[] nums;
    private static int max = 0;
    private static void dfs(int cur, int av, int bv, boolean a, boolean b) {
        // System.out.println("DFS 호출: cur=" + cur + ", av=" + av + ", bv=" + bv + 
                      // ", a=" + a + ", b=" + b);
        // System.out.println("현재 nums 배열: " + Arrays.toString(nums));
        // 아직 안 열어본 상자라면
        if (nums[cur] != 0) {
            // System.out.println("상자 " + (cur+1) + " 열기");
            int og = nums[cur];
            if (!a) {
                av++;
                nums[cur] = 0;
                dfs(og - 1,av,bv, true, b);
                nums[cur] = og;
            }
            else if (a && !b) {
                av++;
                nums[cur] = 0;
                dfs(og - 1,av,bv,a, b);
                nums[cur] = og;
            }
            else if (a && b) {
                bv++;
                nums[cur] = 0;
                dfs(og - 1,av,bv,a, b);
                nums[cur] = og;
            }
        }
        // 이미 열어본 상자라면
        else {
             // System.out.println("이미 열린 상자 " + (cur+1) + " 도착");
            // 아직 탐색한 게 없을 때,
            if (!a) return;
            // A 탐색 중이었을 때, -> A 탐색 마치고 B 탐색 시작
            else if (a & !b) {
                // System.out.println("A 그룹 완료! 현재 A 그룹 갯수 = " + av + " B 그룹 찾기 시작");
                // System.out.println("현재 nums: " + Arrays.toString(nums));
                for (int i = 0; i < cards.length; i++) {
                    if (nums[i] != 0) {
                        dfs(nums[i] - 1,av,bv, a, true);
                    }
                }
            }
            // B 탐색 중이었을 때, -> 탐색 마치고 최댓값 계산
            else if (a && b) {
                max = Math.max(max, av * bv);
            }
        }
    }
    
    public int solution(int[] cards) {
        this.cards = cards;
        nums = new int[cards.length];
        for (int i = 0; i < cards.length; i++) nums[i] = cards[i];
        for (int i = 0; i < cards.length; i++) dfs(i, 0, 0, false, false);
            
        return max;
    }
}