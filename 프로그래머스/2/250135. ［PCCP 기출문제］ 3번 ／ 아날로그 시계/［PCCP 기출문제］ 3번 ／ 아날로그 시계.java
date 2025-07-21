import java.util.*;
class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = -1;
        
        int start = toSecond(h1,m1,s1);
        int end = toSecond(h2,m2,s2);
        
        answer = cal(end) - cal(start) + (alarm(start)? 1:0); 
    
        return answer;
    }
    
    static int cal(int time){
        int sm = time * 59 / (60 * 60);
        int sh = time * 719 / (60 * 60 * 12);
        int a = 43200 <= time ? 2 : 1;
                                      
        return sm + sh - a;
    }
    
    static int toSecond(int h,int m,int s){
        int res = h*3600 + m*60 + s;
        return res;
    }
    
    static boolean alarm(int time){
        return time * 59 % 3600 == 0 || time * 719 % 43200 == 0;
    }
}