import java.util.*;

/*
    이동 경로 전부 노드로 만들어서 큐에 넣을 것인가?
    1,1 -> 100, 100 => 최대 198턴 가능
    0,0 -> 99, 99로두고 r위치에는 100곱하고 c를 더해서 숫자로 매턴 map 만들기?
    
    0초부터 세야하고
    도착할때까지 세야 함
*/


class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        Map<Integer, Integer>[] turnsAndMoves = new HashMap[20001];
        for (int i = 0; i <= 20000; i++) {
            turnsAndMoves[i] = new HashMap<Integer, Integer>();
        }

        int max = 0;
        for (int i = 0; i < routes.length; i++) {
            int turn = 0;
            int fr = points[routes[i][0] - 1][0] - 1;
            int fc = points[routes[i][0] - 1][1] - 1;

            // 첫 출발점 기록
            turnsAndMoves[turn].put(fr * 100 + fc, turnsAndMoves[turn].getOrDefault(fr * 100 + fc, 0) + 1);
            turn++;

            for (int n = 1; n < routes[i].length; n++) {
                int tr = points[routes[i][n] - 1][0] - 1;
                int tc = points[routes[i][n] - 1][1] - 1;

                // r좌표 이동 (시작점은 이미 기록했으니, fr+1 또는 fr-1부터 시작)
                if (fr < tr) {
                    for (int m = fr + 1; m <= tr; m++) {
                        turnsAndMoves[turn].put(m * 100 + fc, turnsAndMoves[turn].getOrDefault(m * 100 + fc, 0) + 1 );
                        turn++;
                    }
                } else if (fr > tr) {
                    for (int m = fr - 1; m >= tr; m--) {
                        turnsAndMoves[turn].put(m * 100 + fc, turnsAndMoves[turn].getOrDefault(m * 100 + fc, 0) + 1 );
                        turn++;
                    }
                }
                fr = tr;

                // c좌표 이동 (fc+1 또는 fc-1부터)
                if (fc < tc) {
                    for (int m = fc + 1; m <= tc; m++) {
                        turnsAndMoves[turn].put(fr * 100 + m, turnsAndMoves[turn].getOrDefault(fr * 100 + m, 0) + 1 );
                        turn++;
                    }
                } else if (fc > tc) {
                    for (int m = fc - 1; m >= tc; m--) {
                        turnsAndMoves[turn].put(fr * 100 + m, turnsAndMoves[turn].getOrDefault(fr * 100 + m, 0) + 1 );
                        turn++;
                    }
                }
                fc = tc;

                max = Math.max(max, turn);
            }
        }

        for (int i = 0; i <= max; i++) {
            for (int value : turnsAndMoves[i].values()) {
                if (value >= 2) answer++;
            }
        }

        return answer;
    }
}
