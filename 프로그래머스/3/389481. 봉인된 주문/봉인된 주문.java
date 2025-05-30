import java.util.*;

class Solution {
    
    static final int ALPHA = 26;
    
    // 문자열 => 숫자
    /*
        a => 1
        aa => 27
        ab => 28
         
    */
    static long getLongVal(String s) {
        int len = s.length();
        long res = 0;
        
        for (int i = 0; i < len; i++) {
            res += (s.charAt(i) - 'a' + 1) * Math.pow(ALPHA, (len - 1 - i));
        }
        
        return res;
    }
    
    static String getStringVal(long n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append( (char) ((n - 1) % ALPHA + (int)'a') );
            n = (n - 1) / ALPHA;
        }
        return sb.reverse().toString();
    }
    
    public String solution(long n, String[] bans) {
        int len = bans.length;
        long[] nums = new long[len];
        for (int i = 0; i < len; i++) {
            nums[i] = getLongVal(bans[i]);
        }
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) if (nums[i] <= n) n++;
        return getStringVal(n);
    }
}