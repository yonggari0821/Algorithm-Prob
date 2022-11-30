package 신고결과받기;

import java.util.StringTokenizer;

public class Solution {
    public static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = {};
        int id_num = id_list.length;
        answer = new int[id_num];
        int[][] got_reported = new int[id_num][id_num];
        int[] reported_num = new int[id_num];

        for (int i = 0; i < report.length; i++)
        {
            StringTokenizer str = new StringTokenizer(report[i]);
            String reporter = str.nextToken();
            String reported = str.nextToken();

            for (int d = 0; d < id_num; d++)
            {
                if (reported.equals(id_list[d]))
                {
                    for (int r = 0; r < id_num; r++)
                    {
                        if (reporter.equals(id_list[r]))
                            got_reported[d][r] = 1;
                    }
                }
            }
        }

        for (int d = 0; d < id_num; d++)
        {
            for (int r = 0; r < id_num; r++)
                reported_num[d] += got_reported[d][r];
            if (reported_num[d] >= k)
            {
                for(int m = 0; m < id_num; m++)
                {
                    if (got_reported[d][m] == 1)
                        answer[m]++;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
        int k = 2;
        int i = 0;
        while (i < id_list.length)
        {
            System.out.println(solution(id_list, report, k)[i]);
            i++;
        }

    }
}