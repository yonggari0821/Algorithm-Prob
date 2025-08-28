import java.util.*;

/*
    롤케이크 공평하게 나눠먹기
    잘린 조각들의 크기와 올려진 토핑 갯수에 상관 없이 "동일한 가짓 수의 토핑"이 올라가는 것이 공평함의 척도
    최대 100만
    
    1) 그냥 mid를 0으로 두고 0 ~ mid / mid + 1 ~ 나눠서 하면
    mid 가짓 수 총 100만 x 그 때마다 매번 for문으로 세어야 하므로 시간초과
    
    2) LtoR RtoL 2개의 Set와 2개의 dp 배열을 활용하여 100만 x 2만큼 순회하면서 저장해두고
    mid 가짓 수로 찾기
*/

class Solution {
    
    private static Set<Integer> LtoRset = new HashSet<>();
    private static Set<Integer> RtoLset = new HashSet<>();
    private static int[] LtoR;
    private static int[] RtoL;
    
    public int solution(int[] topping) {
        int answer = 0;
        
        int len = topping.length;
        LtoR = new int[len];
        RtoL = new int[len];
        
        for (int i = 0; i < len; i++) {
            LtoRset.add(topping[i]);
            RtoLset.add(topping[len - 1 - i]);
            LtoR[i] = LtoRset.size();
            RtoL[len - 1 - i] = RtoLset.size();
        }
        
        for (int m = 0; m < len - 1; m++) {
            // System.out.println("[m = " + m + "]");
            // System.out.println("l = " + LtoR[m] + " / r = " + RtoL[m + 1]);
            if (LtoR[m] == RtoL[m + 1]) answer++;
        }
        
        return answer;
    }
}