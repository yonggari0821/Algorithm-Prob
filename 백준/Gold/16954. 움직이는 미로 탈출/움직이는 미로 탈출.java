import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

/*

8x8 체스판
캐릭터가 맨 오른쪽 위칸 이동 가능한지 아닌 지 구하기 (도착 가능 1 / 불가능 0)

벽이 움직임
1초마다 모든 벽이 아래에 있는 행으로 한 칸 내려감
가장 아래라면 벽이 사라짐

캐릭터는 8방향 한칸 이동 또는 현재 위치 유지
당연히 빈칸만 이동

캐릭터가 먼저 이동 후 벽이 이동
만약 벽이 캐릭터가 있는 칸으로 이동하면 더 이상 캐릭터는 이동할 수 없음!

dfs/bfs 중 어느 것을 사용해야할까?
아니다 단순 반복문으로도 가능할 것 같다.
8x8이니까 벽은 아무리 많이 내려가봐야 8번이고
이 안에 캐릭터를 만날 수 밖에 없는 상황을 제외하곤 전부 가능하므로
8번의 턴을 미리 배열화해서 저장해두면 될 거 같음.
 */


class N {
    int r;
    int c;

    public N(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {

    static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0, 0};
    static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1, 0};

    static final char empty = '.';
    static final char wall = '#';

    static int[][][] chessBoard = new int[8][8][8];

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 체스판 입력 받기
        // 입력 받을 때는 위에서부터 아래로 입력받음
        for (int r = 0; r < 8; r++) {
            String str = br.readLine();
//            System.out.println(str);
            st = new StringTokenizer(str);
            for (int c = 0; c < 8; c++) {
                char tmp = str.charAt(c);
                chessBoard[0][r][c] = (tmp == empty) ? 0 : 4; // .(빈 공간)이면 0 / #(벽)이면 4
            }
        }

        // 8턴 동안의 체스판을 미리 만들어두기
        // 이때는 아래부터 전 턴의 위의 값을 받아와야 됨
        for (int t = 1; t < 8; t++) {
            for (int r = 7; r >= 0; r--) {
                for (int c = 0; c < 8; c++) {
                    if (r == 0) chessBoard[t][r][c] = 0;
                    else chessBoard[t][r][c] = chessBoard[t - 1][r - 1][c];
                }
            }
        }

        // 0t부터 7t까지 확인용
//        for (int t = 0; t < 8; t++) {
//            System.out.println("t[" + t + "] start--------------------------------");
//            for (int r = 0; r < 8; r++) {
//                System.out.println(Arrays.toString(chessBoard[t][r]));
//            }
//            System.out.println("t[" + t + "] end--------------------------------");
//        }

        // 큐 만들어서 넣어주기
        Queue<N> queue = new LinkedList<>();
        queue.offer(new N(7, 0)); // 시작점 위치는 왼쪽 아래이므로 7,0 !!! // 0, 0 아님 주의!
        for (int t = 0; t < 8; t++) {
            int size = queue.size();
            if (size == 0) {
                System.out.println(0);
                break ;
            }
//            System.out.println("t[" + t + "] size: " + size + "");
            while(size > 0) {
                N cur = queue.poll();
                for (int i = 0; i < 9; i++) {
                    int nr = cur.r + dr[i];
                    int nc = cur.c + dc[i];
                    if (nr < 0 || nc < 0 || nr >= 8 || nc >= 8) continue;
                    if (chessBoard[t][nr][nc] == 4) continue;
                    if (t < 7 && chessBoard[t + 1][nr][nc] == 4) continue;
//                    System.out.println("t[" + t + "] (" + nr + ", " + nc + ")" );
                    queue.offer(new N(nr, nc));
                }
                size--;
            }
            if (t == 7) System.out.println(1);
        }
    }
}

/*

......#.
....##..
...##...
..##.#..
.##.....
##.....
#.......
........

 */
