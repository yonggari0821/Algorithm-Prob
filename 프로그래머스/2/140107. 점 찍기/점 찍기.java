import java.util.*;

/*
    원점에서 
    x축 방향으로 a*k(a = 0, 1, 2, 3 ...), 
    y축 방향으로 b*k(b = 0, 1, 2, 3 ...)
    만큼 떨어진 위치에 점 찍기
    
    이 때, 그 거리가 d를 초과시에는 점 찍지 않음
*/

class Solution {
    public long solution(int k, int d) {
        if (k > d) return 1;
        
        long answer = 0;
        
        for (int i = 0; i <= d; i += k) {
            long target = (long)d * (long)d - (long)i * (long)i;
            long tmp = (long) Math.sqrt(target);
            // System.out.println("현재 i는 " + i + ", 그리고 최대 가능한 짝은 0부터 센 " + target + "의 제곱근인 " + tmp + "까지 " + k + "로 나눠지는 몫의 수 " + (tmp / k + 1) + "와 같음!");
            if (tmp >= 0) answer += ((tmp / k) + 1 );
        }
        
        return answer;
    }
}