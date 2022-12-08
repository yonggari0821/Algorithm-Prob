package 최댓값과최솟값;

import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution {
    public static String solution(String s) {
        String answer = "";

        StringTokenizer numArr = new StringTokenizer(s," ", false);
        StringBuilder answerstr = new StringBuilder();
        TreeSet numTree = new TreeSet<>();
        while(numArr.hasMoreTokens())
            numTree.add(Integer.parseInt(numArr.nextToken().toString()));
        System.out.println(numTree);
        answerstr.append(numTree.first());
        answerstr.append(" ");
        answerstr.append(numTree.last());
        System.out.println(answerstr);

        answer = answerstr.toString();
        return answer;
    }

    public static void main(String[] args) {
        String s = "-1 -2 -3 -4";
        System.out.println(solution(s));
    }
}