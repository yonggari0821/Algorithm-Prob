package 성격유형검사;

public class Solution {
    public static String solution(String[] survey, int[] choices) {
        String answer = "";
        StringBuilder sb = new StringBuilder(answer);
        int[] Indicator = new int[8];

        char[] CharOfKakao = {'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'};
        int PNum = choices.length;

        for (int i = 0; i < PNum; i++)
        {
            if (choices[i] >= 5) {
                for (int j = 0; j < 8; j++)
                {
                    if (survey[i].charAt(1) == CharOfKakao[j])
                        Indicator[j] += (choices[i] - 4);
                }
            }
            else if (choices[i] <= 3)
            {
                for (int k = 0; k < 8; k++)
                {
                    if (survey[i].charAt(0) == CharOfKakao[k])
                        Indicator[k] += (4 - choices[i]);
                }
            }
        }

        for (int a = 0; a < 4; a++)
        {
            if (Indicator[2 * a] < Indicator[(2 * a) + 1])
                sb.append(CharOfKakao[2 * a + 1]);
            else
                sb.append(CharOfKakao[2 * a]);
        }

        answer = sb.toString();
        return answer;
        }

    public static void main(String[] args) {
        String[] survey = {"AN", "CF", "MJ", "RT", "NA"};
        int[] choice = {5, 3, 2, 7, 5};

        System.out.println(solution(survey, choice));
        // 답 TCMA
    }
}