package 문자열나누기;

import java.util.ArrayList;

public class Solution {
    public static int solution(String s) {
        int answer = 0;
        if ("".equals(s))
            return answer;
        ArrayList<String> strarr = new ArrayList<>();

        int cnt1 = 0;
        int cnt2 = 0;
        int start = 0;
        int i = 0;
        while (i < s.length())
        {
            char a = s.charAt(start);
            if (a == s.charAt(i))
                cnt1++;
            else
                cnt2++;
            if (cnt1 == cnt2) {
                strarr.add(s.substring(start, i + 1));
                start = i + 1;
                cnt1 = 0;
                cnt2 = 0;
            }
            i++;
        }
        if (cnt1 != cnt2)
            strarr.add(s.substring(start));
        System.out.println(strarr);
        answer = strarr.size();
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("banana"));
    }
}