import java.util.*;
/*
    최대 10 x 10
    하나의 열 또는 하나의 행을 뒤집거나 놔두거나
    2번 뒤집는 순간 비효율적이기 때문에 무조건 뒤집거나 놔두거나
    
*/
class Solution {
    
    private static int rowLen, colLen;
    private static int[][] original;
    private static int[][] last;
    private static int[][] cur;
    private static int min = Integer.MAX_VALUE;
    
    private void flipAndCheck(int row, int col) {
        
        for (int r = 0; r < rowLen; r++) {
            for (int c = 0; c < colLen; c++) {
                cur[r][c] = original[r][c];
            }
        }
        
        int tmp = 0;

        for (int r = 0; r < rowLen; r++) {
            if ((row & (1 << r)) != 0) {
                tmp++;
                for (int c = 0; c < colLen; c++) {
                    cur[r][c] = 1 - cur[r][c];
                }
            }
        }

        for (int c = 0; c < colLen; c++) {
            if ((col & (1 << c)) != 0) {
                tmp++;
                for (int r = 0; r < rowLen; r++) {
                    cur[r][c] = 1 - cur[r][c];
                }
            }
        }
        
        for (int r = 0; r < rowLen; r++){
            for (int c = 0; c < colLen; c++) {
                if (cur[r][c] != last[r][c]) return ;
            }
        }
        
        min = Math.min(min, tmp);
        
    }
    
    public int solution(int[][] beginning, int[][] target) {
        // 초기화
        rowLen = beginning.length;
        colLen = beginning[0].length;
        cur = new int[rowLen][colLen];
        original = new int[rowLen][colLen];
        last = new int[rowLen][colLen];
        for (int r = 0; r < rowLen; r++) {
            for (int c = 0; c < colLen; c++) {
                last[r][c] = target[r][c];
                original[r][c] = beginning[r][c];
                cur[r][c] = beginning[r][c];
            }
        }
        
        // 모든 행과 열에 대해서 뒤집거나(1) 놔두거나(0) 비트 연산활용
        for (int r = 0; r < (1 << rowLen); r++) {
            for (int c = 0; c < (1 << colLen); c++) {
                flipAndCheck(r, c);
            }
        }
        
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}