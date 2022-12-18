package 거리두기확인하기;

import java.util.Arrays;

public class Solution {
    public static int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        char[][] grid = new char[5][5];
        for (int i = 0; i < 5; i++)
        {
            answer[i] = 1;
            for (int j = 0; j < 5; j++)
            {
                for (int k = 0; k < 5; k++)
                {
                    grid[j][k] = places[i][j].charAt(k);
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        System.out.println(Arrays.toString(solution(places)));
    }
}