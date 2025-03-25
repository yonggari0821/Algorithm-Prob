import java.io.*;
import java.util.*;

// 좌표
class Node {
    int r, c;
    Node(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

class Solution {
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static int len;
    static Queue<Node> q = new LinkedList<>();
    static ArrayList<ArrayList<Node>> EmptyArealist = new ArrayList<>();
    static Queue<int[][]> puzzleList = new LinkedList<>();

    // 좌표 정렬용 Comparator
    static Comparator<Node> com = (o1, o2) -> {
        if (o1.r != o2.r) return o1.r - o2.r;
        return o1.c - o2.c;
    };

    static int answer = 0;

    // 전체 로직
    public int solution(int[][] game_board, int[][] table) {
        len = game_board.length;
        getEmptyAreas(game_board); // 빈 공간 추출
        getPuzzles(table); // 퍼즐 추출
        getMatchPiecesNum(); // 빈 공간과 퍼즐을 맞춰보면서 답 구해놓기
        return answer;
    }

    // 범위 체크용!
    public static boolean check(int nr, int nc) {
        return 0 <= nr && 0 <= nc && nr < len && nc < len;
    }

    public static void getMatchPiecesNum() {
        for (ArrayList<Node> curEmptyArea : EmptyArealist) {
            int puzzleNum = puzzleList.size();
            // 사이즈 미리 구해서 사이즈까지만 살펴봐야 함!
            for (int i = 0; i < puzzleNum; i++) {
                int[][] puzzle = puzzleList.poll();
                if (checkIfMatches(curEmptyArea, puzzle)) break;
                puzzleList.add(puzzle); // 매칭 안된 건 다시 넣어줘야 함!
            }
        }
    }

    public static boolean checkIfMatches(ArrayList<Node> cur, int[][] puzzle) {
        int count = 0;
        int pl = puzzle.length;

        // 총 4개의 각도 버전을 검토
        while (count < 4) {
            puzzle = rotate(puzzle, count);
            boolean first = true;

            for (int r = 0; r < pl; r++) {
                for (int c = 0; c < pl; c++) {
                    // 빈 공간 패스
                    if (puzzle[r][c] == 0) continue;

                    ArrayList<Node> temp = new ArrayList<>();
                    boolean[][] visited = new boolean[pl][pl];
                    visited[r][c] = true;
                    q.add(new Node(r, c));

                    while (!q.isEmpty()) {
                        Node out = q.poll();
                        for (int k = 0; k < 4; k++) {
                            int nr = dr[k] + out.r;
                            int nc = dc[k] + out.c;
                            if (0 > nr || 0 > nc || nr >= pl || nc >= pl) continue; // 범위 벗어나면 pass
                            if (visited[nr][nc] || puzzle[nr][nc] == 0) continue; // 이미 방문했거나 빈공간이면 pass
                            visited[nr][nc] = true;
                            temp.add(new Node(nr, nc));
                            q.add(new Node(nr, nc));
                        }
                    }

                    // 애초에 사이즈가 다른 경우 바로 return
                    if (temp.size() != cur.size()) return false;
                    // 사이즈가 같다면 위에서 정의한 상단 좌측 우선순위로 정렬
                    temp.sort(com);

                    boolean answer_check = true;
                    for (int k = 0; k < temp.size(); k++) {
                        Node curEmptySpot = cur.get(k); // 빈 공간 한 조각
                        Node curPuzzlePiece = temp.get(k); // 퍼즐 한 조각
                        // 틀리면 바로 false
                        if (curEmptySpot.r != curPuzzlePiece.r - r || curEmptySpot.c != curPuzzlePiece.c - c) {
                            answer_check = false;
                            break;
                        }
                    }
                    // 전부 통과 시에만 사이즈 더해주기! 이 때, +1을 해줘야 하는 이유는
                    if (answer_check) {
                        answer += cur.size() + 1;
                        return true;
                    }
                    first = false;
                    break;
                }
                if (!first) break;
            }
            count++;
        }
        return false;
    }

    // 회전용 (시계 방향: 새로운 행값 == 이전 열값 // 새로운 열값 == 이전 행값을 최대 길이에서 빼고 -1)
    // ex)
    /*

    [값 배열]
    1 0 1 0    \   0 0 0 1
    0 1 0 0  ===\  0 0 1 0
    0 0 1 0  ===/  0 1 0 1
    0 0 0 1    /   1 0 0 0

    [좌표 배열]
    [이해를 위해서 이전 좌표값 기준으로 우측에 표현]
    (0,0) (0,1) (0,2) (0,3)     \    [(3, 0) => (0, 4-3-1=0)] [(2, 0) => (0, 4-2-1=1)] [(1, 0) => (0, 4-1-1=2)] [(0, 0) => (0, 4-0-1=3)]
    (1,0) (1,1) (1,2) (1,3)   ===\   [(3, 1) => (1, 4-3-1=0)] [(2, 1) => (1, 4-2-1=1)] [(1, 1) => (1, 4-1-1=2)] [(0, 1) => (1, 4-0-1=3)]
    (2,0) (2,1) (2,2) (2,3)   ===/   [(3, 2) => (2, 4-3-1=0)] [(2, 2) => (2, 4-2-1=1)] [(1, 2) => (2, 4-1-1=2)] [(0, 2) => (2, 4-0-1=3)]
    (3,0) (3,1) (3,2) (3,3)     /    [(3, 3) => (3, 4-3-1=0)] [(2, 3) => (3, 4-2-1=1)] [(1, 3) => (3, 4-1-1=2)] [(0, 3) => (3, 4-0-1=3)]

     */


    // 회전용 함수 자세한 내용은 위 주석 참고!
    public static int[][] rotate(int[][] puzzle, int count) {
        if (count == 0) return puzzle;
        int m = puzzle.length;
        int[][] rotated = new int[m][m];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < m; c++) {
                rotated[c][m - 1 - r] = puzzle[r][c];
            }
        }
        return rotated;
    }

    // 퍼즐 추출
    public static void getPuzzles(int[][] table) {
        boolean[][] visited = new boolean[len][len];
        
        for (int r = 0; r < len; r++) {
            for (int c = 0; c < len; c++) {
                if (visited[r][c] || table[r][c] == 0) continue;
                visited[r][c] = true;
                q.add(new Node(r, c));

                // 최대 최소 행/열값을 함께 구해놓고
                int maxR = r, maxC = c, minR = r, minC = c;
                while (!q.isEmpty()) {
                    Node cur = q.poll();
                    for (int k = 0; k < 4; k++) {
                        int nr = cur.r + dr[k];
                        int nc = cur.c + dc[k];
                        if (!check(nr, nc) || visited[nr][nc] || table[nr][nc] == 0) continue;
                        visited[nr][nc] = true;
                        q.add(new Node(nr, nc));
                        maxR = Math.max(maxR, nr);
                        maxC = Math.max(maxC, nc);
                        minR = Math.min(minR, nr);
                        minC = Math.min(minC, nc);
                    }
                }

                // 퍼즐 틀을 만들어서 틀 자체를 돌리는 방식으로!
                // 퍼즐틀의 0,0은 minR과 minC가 가져가게 해서 퍼즐의 최상단 좌측 퍼즐이 들어가게 함
                int max = Math.max(maxR - minR, maxC - minC) + 1;
                int[][] puzzleGrid = new int[max][max];
                for (int r1 = minR; r1 <= maxR; r1++) {
                    for (int c1 = minC; c1 <= maxC; c1++) {
                        puzzleGrid[r1 - minR][c1 - minC] = table[r1][c1];
                    }
                }
                // 퍼즐 틀 넣기
                puzzleList.add(puzzleGrid);
            }
        }
    }

    // 빈공간 추출
    public static void getEmptyAreas(int[][] gb) {
        boolean[][] visited = new boolean[len][len];
        for (int r = 0; r < len; r++) {
            for (int c = 0; c < len; c++) {
                if (visited[r][c] || gb[r][c] == 1) continue;
                
                // 임시 리스트 => 최종적으로 EmptyAreaList에 각 좌표들의 합인 덩어리 형태로 넣을 것임!
                ArrayList<Node> temp = new ArrayList<>();
                visited[r][c] = true;
                q.add(new Node(r, c));

                while (!q.isEmpty()) {
                    Node cur = q.poll();
                    for (int k = 0; k < 4; k++) {
                        int nr = cur.r + dr[k];
                        int nc = cur.c + dc[k];
                        if (!check(nr, nc) || visited[nr][nc] || gb[nr][nc] == 1) continue;
                        visited[nr][nc] = true;
                        temp.add(new Node(nr - r, nc - c));
                        q.add(new Node(nr, nc));
                    }
                }
                temp.sort(com);
                EmptyArealist.add(temp);
            }
        }
    }
}