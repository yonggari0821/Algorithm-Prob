import java.util.*;

/*
(((1 - 3) + 5) - 8) = -5
((1 - (3 + 5)) - 8) = -15
(1 - ((3 + 5) - 8)) = 1
(1 - (3 + (5 - 8))) = 1
((1 - 3) + (5 - 8)) = -5

연산 기호를 기준으로 앞 뒤를 연산 하기

백준 수식트리랑 비슷함

+ 연산의 경우 상관 없지만
- 연산의 경우
    왼쪽은 최대 / 오른쪽은 최소가 되어야 함

더 자세하게는
(a + b) -> (max + max)
(a - b) -> (max - min)
-(a + b) -> -(min + min)
-(a - b) -> -(min - max)
가 되어야 함
*/

class Solution {
    
    static int getVal (int left, boolean isPlus, int right) {
        if (isPlus) return left + right;
        return left - right;
    }
    
    public int solution(String arr[]) {
        int answer = -1;
        
        int numCnt = arr.length / 2 + 1;
        int symCnt = arr.length / 2;
        
        int[] nums = new int[numCnt + 1];
        boolean[] isPlus = new boolean[symCnt + 1];
        
        // 우선 숫자와 기호 분리해두기
        for (int i = 0; i < arr.length; i++) {
            // 기호
            if ((i & 1) == 1) {
                isPlus[(i + 1) / 2] = arr[i].equals("+") ? true : false;
            }
            // 숫자
            else {
                nums[i / 2 + 1] = Integer.parseInt(arr[i]);
            }
        }
        
        /*
            이제 전체 구간을 나눠서
            a 부터 b 까지
            b + 1 부터 c까지
            로 구분했을 때
            연산자에 따라서
            a~b와 b+1~c의 값을 최대값에서 가져올 지 최소 값에서 가져올 지 구분해서 로직을 설계할 것!
        */
        
        int[][] maxDP = new int[numCnt + 1][numCnt + 1];
        int[][] minDP = new int[numCnt + 1][numCnt + 1];
        for (int i = 1; i <= numCnt; i++) {
            Arrays.fill(maxDP[i], Integer.MIN_VALUE);
            Arrays.fill(minDP[i], Integer.MAX_VALUE);
        }
        
        // 구간이 딱 하나인 경우는 무조건 자기 자신!
        for (int i = 1; i <= numCnt; i++) {
            minDP[i][i] = nums[i];
            maxDP[i][i] = nums[i];
        }
        
        // 구간이 여러 개인 경우 연산자를 고려해서 짜기
        for (int len = 2; len <= numCnt; len++) {
            for (int left = 1; left <= numCnt - len + 1; left++) {
                int right = left + len - 1;
                for (int mid = left; mid < right; mid++) {
                    int valMPM = getVal(maxDP[left][mid], isPlus[mid], maxDP[mid + 1][right]);
                    int valMSm = getVal(maxDP[left][mid], isPlus[mid], minDP[mid + 1][right]);
                    int valmPm = getVal(minDP[left][mid], isPlus[mid], minDP[mid + 1][right]);
                    int valmSM = getVal(minDP[left][mid], isPlus[mid], maxDP[mid + 1][right]);
                    
                    int min 
                        = Math.min(valMPM, Math.min(valMSm, Math.min(valmPm, valmSM)));
                    int max 
                        = Math.max(valMPM, Math.max(valMSm, Math.max(valmPm, valmSM)));
                    
                    maxDP[left][right] = Math.max(maxDP[left][right], max);
                    minDP[left][right] = Math.min(minDP[left][right], min);
                }
            }
        }
        
        answer = maxDP[1][numCnt];
        return answer;
    }
}