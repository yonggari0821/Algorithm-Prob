package 행렬테두리회전하기;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class Solution {

    public int[] solution(int rows, int columns, int[][] queries) {

        int[][] grid = new int[rows][columns]; // 그리드 만들어두기
        int[][] Refgrid = new int[rows][columns]; // 참조용 그리드 만들어두기
        TreeSet<Integer> forMin = new TreeSet<>(); // 테두리 값들 모아서 Min값 빼낼 TreeSet
        ArrayList<Integer> forAnswer = new ArrayList<>(); // Min 값들 모아서 answer로 넘겨줄 ArrayList

        // 그리드 초기화
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                grid[i][j] = columns * i + j + 1;
            }
        }

        // 참조용 그리드 초기화
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                Refgrid[i][j] = columns * i + j + 1;
            }
        }


        for (int i = 0; i < queries.length; i++)
        {
            // 쿼리에서 값 가져와서 시작점과 끝점의 x, y 좌표 변수 생성
            int startrow = queries[i][0] - 1;
            int startcol = queries[i][1] - 1;
            int endrow = queries[i][2] - 1;
            int endcol = queries[i][3] - 1;

            // 회전하는 모양대로 참조용 그리드에서 값을 가져와서 원래 그리드 값에 배정
            for (int j = startcol + 1; j <= endcol; j++)
                grid[startrow][j] = Refgrid[startrow][j-1];
            for (int j = startrow + 1; j <= endrow; j++)
                grid[j][endcol] = Refgrid[j - 1][endcol];
            for (int j = endcol - 1; j >= startcol; j--)
                grid[endrow][j] = Refgrid[endrow][j + 1];
            for (int j = endrow - 1; j >= startrow; j--)
                grid[j][startcol] = Refgrid[j + 1][startcol];

            // 바뀐 그리드 한 번 출력
            /*
            for (int j = 0; j < rows; j++)
            {
                for (int k = 0; k < columns; k++)
                {
                    System.out.printf("[%d]", grid[j][k]);
                }
                System.out.println("");
            }
            */

            // 테두리 값들 TreeSet에 추가(자동으로 오름차순화)
            for (int k = startrow; k <= endrow; k++)
            {
                forMin.add(grid[k][startcol]);
                forMin.add(grid[k][endcol]);
            }
            for (int k = startcol; k <= endcol; k++)
            {
                forMin.add(grid[startrow][k]);
                forMin.add(grid[endrow][k]);
            }

            forAnswer.add(forMin.first()); // 그 중에 최소값을 forAnswer리스트에 추가
            forMin.clear(); // 테두리 값들 넣었던 TreeSet을 다음 query를 위해 초기화

            for (int a = 0; a < rows; a++)
            {
                for (int b = 0; b < columns; b++)
                {
                    Refgrid[a][b] = grid[a][b];
                }
            }
        }

        int[] answer = new int[forAnswer.size()]; // answer 배열에 forAnswer 리스트에 담아두었던 값들 배정
        for (int i = 0; i < forAnswer.size(); i++)
            answer[i] = forAnswer.get(i);

        return answer;
    }

    public static void main(String[] args) {
        int rows = 6;
        int column = 6;
        int[][] queries = {{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}};
        Solution sol = new Solution();

        System.out.println(Arrays.toString(sol.solution(rows, column, queries)));
    }
}
