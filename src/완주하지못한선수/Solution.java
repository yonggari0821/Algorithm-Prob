package 완주하지못한선수;

import java.util.Arrays;

public class Solution {
    public static String solution(String[] participant, String[] completion) {
        String answer = "";

        Arrays.sort(participant);
        Arrays.sort(completion);

        int i = 0;
        while(i < completion.length)
        {
            if (!completion[i].equals(participant[i]))
                break;
            i++;
        }

        answer = participant[i].toString();

        return answer;
    }

    public static void main(String[] args) {
        String[] participant = {"stanko", "mislav", "mislav","ana"};
        String[] completion = {"stanko", "mislav", "ana"};
        System.out.println(solution(participant, completion));
    }
}