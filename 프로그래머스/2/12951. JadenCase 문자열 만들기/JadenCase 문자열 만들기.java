import java.util.*;

class Solution {
    public String solution(String s) {
        StringBuilder ans = new StringBuilder();
        boolean afterBlank = true;
        char val;
        for (int i = 0; i < s.length(); i++) {
            val = s.charAt(i);
            
            // 첫 글자인 경우
            if (afterBlank) {
                // 소문자인 경우만 바꿔줌
                if (val >= 97) {
                    val -= 32;
                }
                // 공백문자 여부에 따라 afterBlank 토글
                afterBlank = val == ' ' ? true : false;
            } 
            // 나머지의 경우
            else {
                // 공백 문자인 경우 afterblank 토글
                if (val == ' ') {
                    afterBlank = true;
                } 
                // 문자인 경우
                else {
                    // 대문자인 경우만 소문자로
                    if (val >= 65 && val < 97) {
                        val += 32;
                    }
                    afterBlank = false;
                }
            }
            ans.append(val);
        }
        return ans.toString();
    }
}