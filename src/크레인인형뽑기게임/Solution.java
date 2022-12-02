package 크레인인형뽑기게임;

import java.util.ArrayList;

public class Solution {
    public static int solution(int[][] board, int[] moves) {
        int answer = 0;
        ArrayList<Integer> basket = new ArrayList<>();
        // 스택 자료구조형인 ArrayList 형태로 basket 만들기

        for (int i = 0; i < moves.length; i++)
        {
            int j = 0;
            while(j < board.length && board[j][moves[i] - 1] == 0)
                j++;
            // (moves[i] - 1) == 몇 열 // 해당열의 0이 아닌 곳까지 내리기(j++) // 다만 board를 벗어나지 않게 선행조건 필수!
            if (j < board.length)
            {
                if (basket.size() > 0 && basket.get(basket.size() - 1) == board[j][moves[i] - 1])
                {
                    answer += 2; // 한 번에 2개씩 묶여서 바로바로 터지므로 답은 항상 짝수개!!
                    basket.remove(basket.size() - 1); // 기존의 맨위의 인형 터뜨리기
                }
                else
                    basket.add(board[j][moves[i] - 1]); // 바구니에 새로운 인형 넣기
                board[j][moves[i] - 1] = 0; // 넣었으면 기존에 쌓인곳은 0으로 만들기!!
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
        int[] moves = {1,5,3,5,1,2,1,4};
        System.out.println(solution(board, moves));
        // 답: 4
    }
}