import java.util.*;

class Solution {
    static int maxWin = 0;
    
    static int[] answer;
    static int[] pickedNums;
    static int[] opponent;
    static boolean[] isPicked;
    
    static int diceNum;
    static final int diceFaceNum = 6;
    
    static ArrayList<Integer> myList;
    static ArrayList<Integer> opList;
    
    // 주사위 눈 합계 구하기
    static void getTotal (int[][] dice, int[] my, int depth, int cur, boolean isMine) {
        if (depth == diceNum / 2) {
            if(isMine) myList.add(cur);
            else opList.add(cur);
            return;
        }
        for (int i = 0; i < diceFaceNum; i++) {
            getTotal(dice, my, depth + 1, cur + dice[my[depth]][i], isMine);
        }
    }
    
    // 시간초과 방지용 이분탐색으로 승리 수 구하기
    static int getWins() {
        int res = 0;
        for (int mine : myList) {
            // mine보다 작은 상대방 값의 개수 구하기
            int l = 0;
            int r = opList.size();

            while (l < r) {
                int mid = l + (r - l) / 2;
                if (opList.get(mid) < mine) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            res += l;  // mine보다 작은 값의 개수
        }
        return res;
    }

    
    // 대결하기 == 주사위 눈 수 구해놓고 승리 횟수 구하기
    static int versus (int[][] dice, int[] my, int[] op) {
        myList = new ArrayList<>();
        opList = new ArrayList<>();
        getTotal(dice, pickedNums, 0, 0, true);
        getTotal(dice, opponent, 0, 0, false);
        Collections.sort(myList);
        Collections.sort(opList);
        return getWins();
    }
    
    // 
    static void func(int[][] dice, int picked, int last) {
        // diceNum은 항상 2의 배수라고 했으므로 홀수일 때 어떡할 지 걱정은 ㄴㄴ
        if (picked == diceNum / 2) {
            int idx = 0;
            opponent = new int[diceNum / 2];
            for (int i = 0; i < diceNum; i++) {
                if (!isPicked[i]) opponent[idx++] = i;
            }
            
            // 승수 구하기
            int tmpWins = versus(dice, pickedNums, opponent);
            
            // 최댓값 리뉴얼 및 답 리뉴얼
            if (tmpWins > maxWin) {
                maxWin = tmpWins;
                for (int a = 0; a < diceNum / 2; a++) {
                    answer[a] = pickedNums[a] + 1;
                }
            }
            return ;
        }
        
        for (int i = last; i < diceNum; i++) {
            if (isPicked[i]) continue;
            isPicked[i] = true;
            pickedNums[picked] = i;
            func(dice, picked + 1, i + 1);
            isPicked[i] = false;
        }
    }
    
    
    public int[] solution(int[][] dice) {
        diceNum = dice.length;
        answer = new int[diceNum / 2];
        pickedNums = new int[diceNum / 2];
        isPicked = new boolean[diceNum];
        
        // 모든 경우의 수를 살펴서 가장 높은 경우일 때를 저장하고 반환
        func(dice, 0, 0);
        
        return answer;
    }
}