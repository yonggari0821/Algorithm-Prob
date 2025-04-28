import java.util.*;
/*
    1개 남기고 풍선 전부 터뜨리기
    인접한 두 풍선 중 1개 터뜨리기
    빈공간 발생 시 밀착
    번호가 더 작은 풍선은 최대 1번만 터뜨릴 수 있음 => 작을 수록 살아남기 유리하다는 느낌 받기
    
    어떤 풍선은 최후까지 남을수도,
    어떤 풍선은 무슨 수를 쓰더라도 마지막까지 남기는 것이 불가능 할 수도 있음!
    
    a의 길이(숫자 갯수)는 100만 이하
    a의 숫자 범위는 -10억 ~ 10억
    모든 수는 서로 다름
    
    [살아남기 위한 조건]
    1. 왼쪽/오른쪽부터 보면서 최소값을 저장해두는 배열을 만들고
    2. 두 배열을 참고해서 자기 왼쪽/오른쪽의 최소값과 비교해봤을 때, 자기보다 작은 값이 1개뿐이어야 함.
*/
class Solution {
    public int solution(int[] a) {
        int answer = 0;
        int len = a.length;
        int[] fromL = new int[len];
        int[] fromR = new int[len];
        
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            min = Math.min(min, a[i]);
            fromL[i] = min;
        }
        min = Integer.MAX_VALUE;
        for (int i = len - 1; i >= 0; i--) {
            min = Math.min(min, a[i]);
            fromR[i] = min;
        }
        
        // 탐색
        int lessThanMe;
        for (int i = 0; i < len; i++) {
            lessThanMe = 0;
            if (i > 0) {
                if (a[i] > fromL[i - 1]) lessThanMe++;
            }
            
            if (i < len - 1) {
                if (a[i] > fromR[i + 1]) lessThanMe++;
            }
            
            if (lessThanMe < 2) answer++;
        }

        return answer;
    }
}