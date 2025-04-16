import java.util.*;

/*
    발판은 밟고 이동 하면 사라짐
    상하좌우 인접한 칸으로 이동 못하면 패배
    밟고 있던 칸이 사라져도 패배
    
    항상 A가 먼저 시작함
    
    이기는 플레이어는 항상 이기는 쪽으로
    지는 플레이어는 항상 오래 버티는 쪽으로
    
    움직이기 전에 항상 이기는 쪽과 항상 오래 버티는 쪽을 알 수 있는가? 
    => 안될 거 같음
    
    그렇지만 일단 움직여 보면,
    끝나는 상황을 기준으로
    
    짝수번인 경우 A의 패배 / B의 승리
    홀수번인 경우 B의 패배 / A의 승리
    
    가 되는 걸 알 수 있음
    ex) 총 3회 이동이라고 치면
    A -> B -> A 
        -> B 움직여야 하는데 끝임 == B의 패배
    

*/

class Node {
    int r;
    int c;
    Node (int r, int c) {
        this.r = r;
        this.c = c;
    }
}

class Solution {
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, 1, -1, 0};
    static int[][] gameBoard;
    
    static int rowSize;
    static int colSize;
    
    static int playGame(Node a, Node b) {
        int r = a.r;
        int c = a.c;
        int res = 0; // 판정 결과 (Base Case)
        if (gameBoard[r][c] == 0) return res;
        
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            
            if (nr < 0 || nc < 0 || nr >= rowSize || nc >= colSize 
                || gameBoard[nr][nc] == 0) continue;
            
            // 이동 가능한 경우 먼저 이전 발판 사라지기
            gameBoard[r][c] = 0;
            
            // 상대 턴으로 넘어가서 값 받아오기 // 턴+1해서 받기
            int val = playGame(b, new Node(nr, nc)) + 1;
            
            // 발판 아직 밟기 전으로 돌려놓기
            gameBoard[r][c] = 1;
            
            /*
                여기서 움직인 횟수(val)가 
                    홀수라면 현재 전자 player(a)가 이긴 거
                    짝수라면 현재 후자 player(b)가 이긴 거
                
                이 때, res 값에 따라 어떻게 행동해야 할지가 달라지는데
                기본 전제는
                이길 수 있다면 최대한 빨리 이기기 위해 최소 이동을 지향하고
                질 수 밖에 없다면 최대한 느리게 지기 위해 최대 이동을 지향한다는 것이다.
                
                따라서 다음의 3가지 경우로 나뉠 수 있음
                1) 지금까지 졌지만 이길 수 있는 경우를 발견한 경우
                    => 이기는 쪽으로 이동
                2) 지금까지 졌고, 이번에도 진 경우
                    => 최대한 이동하기
                3) 지금까지 이겼기 때문에 이번에는 무조건 이기는 쪽으로 이동할 경우
                    => 최소한 이동하기
            */
            // 1)
            // 지금까지 졌지만(res가 짝수)
            // 이번엔 이긴 경우(val이 홀수)
            // 이기는 케이스로 전환
            if ( (res & 1) == 0 && (val & 1) == 1 ) res = val;
            
            // 2)
            // 지금까지 졌고(res가 짝수)
            // 이번에도 지는 경우(val도 짝수)
            // 최대한 많이 움직이기
            else if ( (res & 1) == 0 && (val & 1) == 0 ) res = Math.max(res, val);
            
            // 3)
            // 지금까지 이겼으니(res가 홀수)
            // 이번에도 이기는 쪽으로(val도 홀수)
            else if ( (res & 1) == 1 && (val & 1) == 1 ) res = Math.min(res, val);
            
            // 4)
            // 지금까지 이겼으니 이번에는 지는 쪽은 없음 => 무조건 최선의 선택이 아니기 때문에!
        }
        
        return res;
    }
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        int answer = -1;
        
        rowSize = board.length;
        colSize = board[0].length;
        gameBoard = new int[rowSize][colSize];
        for (int r = 0; r < rowSize; r++) gameBoard[r] = board[r].clone();
        
        Node A = new Node(aloc[0], aloc[1]);
        Node B = new Node(bloc[0], bloc[1]);
        
        answer = playGame(A, B);
        return answer;
    }
}