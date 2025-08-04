import java.util.*;

/*
    xox
    oxo
    xox
    
    규칙을 지킨 경우
    1. o와 x의 갯수가 1차이가 나거나 같아야 함(2개 이상 차이나면 이상)
    2. 빙고(3개 연속인 줄)가 1개 이하(2개 이상인 경우 이상)
    3. o가 선공이므로 o가 존재한 후 x가 존재할 수 있음(o가 0인 경우는 따로 예외 체크!)
    
    
    오답노트
    어떤 빙고인지에 따라 o/x갯수를 체크해야함!
    o 빙고 => 선공 빙고이므로 o의 갯수 == x의 갯수 + 1
    x 빙고 => 후공 빙고이므로 \o의 갯수 == x의 갯수
    이어야만 정상적인 것
    
    그 외에는 전부 비정상
*/

// class Solution {
    
//     private static boolean cntOX(String[] board) {
//         int o = 0;
//         int x = 0;
//         for (int r = 0; r < board.length; r++) {
//             for (int c = 0; c < board[0].length(); c++) {
//                 if (board[r].charAt(c) == '.') continue;
//                 else if (board[r].charAt(c) == 'O') o++;
//                 else x++;
//             }
//         }
//         if (x > o) return false;
//         return o - x <= 1;
//     }
    
//     private static boolean cntBingo(String[] board) {
//         int bingo = 0;
//         int[] tr = new int[3];
//         int[] tc = new int[3];
//         int rightDown = 0;
//         int rightUp = 0;
//         for (int r = 0; r < board.length; r++) {
//             for (int c = 0; c < board[0].length(); c++) {
//                 if (board[r].charAt(c) == '.') break;
//                 else if (board[r].charAt(c) == 'O') {
//                     tr[r]++;
//                     tc[c]++;
//                     if (r == c) rightDown++;
//                     if (r + c == 2) rightUp++;
//                 }
//                 else {
//                     tr[r]--;
//                     tc[c]--;
//                     if (r == c) rightDown--;
//                     if (r + c == 2) rightUp--;
//                 }
//             }
//         }
        
//         for (int x = 0; x < board.length; x++) {
//             if (Math.abs(tr[x]) == 3) bingo++;
//             if (Math.abs(tc[x]) == 3) bingo++;
//         }
        
//         if (Math.abs(rightDown) == 3) bingo++;
//         if (Math.abs(rightUp) == 3) bingo++;
        
//         return bingo <= 1;
//     }
    
//     public int solution(String[] board) {
//         return cntOX(board) && cntBingo(board) ? 1 : 0;
//     }
// }

class Solution {
    public int solution(String[] board) {
        int o = 0, x = 0;
        
        // 돌 개수 세기
        for (String row : board) {
            for (char c : row.toCharArray()) {
                if (c == 'O') o++;
                if (c == 'X') x++;
            }
        }
        
        // 불가능한 경우 선행 필터링
        if (x > o) return 0;
        if (o > x + 1) return 0;

        // 빙고 검사 함수
        boolean oWin = bingo(board, 'O');
        boolean xWin = bingo(board, 'X');

        // 둘 다 승리 (불가)
        if (oWin && xWin) return 0;
        // O 승리인데 돌 개수 맞는지
        if (oWin && o != x + 1) return 0;
        // X 승리인데 돌 개수 맞는지
        if (xWin && o != x) return 0;

        return 1;
    }

    // 해당 문자가 빙고인지 판정
    private boolean bingo(String[] b, char p) {
        for (int i = 0; i < 3; i++) {
            if (b[i].charAt(0) == p && b[i].charAt(1) == p && b[i].charAt(2) == p)
                return true;
            if (b[0].charAt(i) == p && b[1].charAt(i) == p && b[2].charAt(i) == p)
                return true;
        }
        // 대각선 ↘
        if (b[0].charAt(0) == p && b[1].charAt(1) == p && b[2].charAt(2) == p) return true;
        // 대각선 ↗
        if (b[2].charAt(0) == p && b[1].charAt(1) == p && b[0].charAt(2) == p) return true;
        
        return false;
    }
}
