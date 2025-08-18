import java.util.*;

/*
    +-(10의 n승) 형태의 정수 버튼이 있고 누르면 현재 층 + 해당 층으로 이동
    이동마다 마법의 돌 1개 사용
    0층(가장 아래층)으로 가기 위해 최소로 사용할 마법의 돌 갯수 구하기
    (단, 더한 값이 0층보다 낮은 경우 이동 불가!!)
    
    현재 층 = storey <= 10억
    
<95 - 6>
    
    95
    100 + 5
    0 + 1
    
<495 - 10>
    
    495
    500 + 5
    0 + 5

<465 - 13>

    465
    470 + 5
    500 + 3
    0 + 5
    
<184 - 8>
    
    180 + 4
    200 + 2
    0 + 2
    
*/      

class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while (storey > 0) {
            // 현재 자리 숫자
            int digit = storey % 10;
            
            // 올림이 더 싸게 먹히는 경우
            if (digit > 5) {
                answer += (10 - digit); 
                storey += 10 - digit;
            }
            
            // 내림이 더 싸게 먹히는 경우
            else if (digit < 5) {
                answer += digit;        
                storey -= digit;
            }
            
            // digit == 5일 때
            else {                    
                // 다음 자리수가 5 이상이면 올림이 더 싼 경우
                int nextDigit = (storey / 10) % 10;
                if (nextDigit >= 5) {
                    answer += 5;
                    storey += 5;
                } else {
                    answer += 5;
                    storey -= 5;
                }
            }
            
            storey /= 10; // 다음 자리로 이동
        }
        
        return answer;
    }
}
