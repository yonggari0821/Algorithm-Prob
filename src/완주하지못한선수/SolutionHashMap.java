package 완주하지못한선수;

import java.util.HashMap;

public class SolutionHashMap {
    public static String solution(String[] participant, String[] completion) {
        String answer = "";

        HashMap winner = new HashMap(completion.length); // completion.length일테니까

        for (int i = 0; i < completion.length; i++)
        {
            if (winner.containsKey(completion[i]))
                winner.put(completion[i], (int)winner.get(completion[i]) + 1);
            else
                winner.put(completion[i], 1);
        }
        // 만약 기존에 completion의 요소를 포함하고 있었다면 value++;
        // 아니라면 value = 1;

        for (int i = 0; i < participant.length; i++)
        {
            if (winner.containsKey(participant[i]) && (int)winner.get(participant[i]) >= 0)
                winner.put(participant[i], (int)winner.get(participant[i]) - 1);
            else if (!(winner.containsKey(participant[i])))
            {
                System.out.println("The loser is " + participant[i]);
                return (participant[i]);
            }
        }

        for (int i = 0; i < participant.length; i++)
        {
            if ((int)winner.get(participant[i]) == -1)
                answer = participant[i];
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] participant = {"leo", "kiki", "eden"};
        String[] completion = {"eden", "kiki"};
        System.out.println(solution(participant, completion));
    }
}