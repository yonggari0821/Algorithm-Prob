import java.io.*;
import java.util.*;
public class Main {

    // 상하반전
    static void func1(int[][] grid) {
        int rlen = grid.length;
        int clen = grid[0].length;
        int tmp;
        for (int r = 0; r < rlen / 2; r++) {
            for (int c = 0; c < clen; c++) {
                tmp = grid[r][c];
                grid[r][c] = grid[rlen-1-r][c];
                grid[rlen-1-r][c] = tmp;
            }
        }
    }
    // 좌우반전
    static void func2(int[][] grid) {
        int rlen = grid.length;
        int clen = grid[0].length;
        int tmp;
        for (int c = 0; c < clen / 2; c++) {
            for (int r = 0; r < rlen; r++) {
                tmp = grid[r][c];
                grid[r][c] = grid[r][clen-1-c];
                grid[r][clen-1-c] = tmp;
            }
        }
    }

//    // 시계방향
//    static int[][] func3(int[][] grid) {
//        int rlen = grid.length;
//        int clen = grid[0].length;
//        int[][] res = new int[clen][rlen];
//        for (int r = 0; r < res.length; r++) {
//            for (int c = 0; c < res[0].length; c++) {
//                res[r][c] = grid[rlen-1-c][r];
//            }
//        }
//        return res;
//    }
//
//    // 반시계방향
//    static int[][] func4(int[][] grid) {
//        int rlen = grid.length;
//        int clen = grid[0].length;
//        int[][] res = new int[clen][rlen];
//        for (int r = 0; r < res.length; r++) {
//            for (int c = 0; c < res[0].length; c++) {
//                res[r][c] = grid[c][clen-1-r];
//            }
//        }
//        return res;
//    }
    // 시계방향 1 -> 2 -> 3 -> 4 -> 1
    static void func5(int[][] grid) {
        int rlen = grid.length;
        int clen = grid[0].length;
        int[][] tmp = new int[rlen][clen];
        // 1번 배열 값 빼두기
        for (int r = 0; r < rlen / 2; r++) {
            for (int c = 0; c < clen / 2; c++) {
                tmp[r][c] = grid[r][c];
            }
        }
        // 1 <- 4
        for (int r = rlen / 2; r < rlen; r++) {
            for (int c = 0; c < clen / 2; c++) {
                grid[r - rlen/2][c] = grid[r][c];
            }
        }
        // 4 <- 3
        for (int r = rlen / 2; r < rlen; r++) {
            for (int c = clen / 2; c < clen; c++) {
                grid[r][c - clen/2] = grid[r][c];
            }
        }
        // 3 <- 2
        for (int r = 0; r < rlen / 2; r++) {
            for (int c = clen / 2; c < clen; c++) {
                grid[r + rlen/2][c] = grid[r][c];
            }
        }
        // 2 <- 1 (tmp 대입)
        for (int r = 0; r < rlen / 2; r++) {
            for (int c = clen / 2; c < clen; c++) {
                grid[r][c] = tmp[r][c-clen/2];
            }
        }
    }
    // 반시계방향 1 -> 4 -> 3 -> 2 -> 1
    static void func6(int[][] grid) {
        int rlen = grid.length;
        int clen = grid[0].length;
        int[][] tmp = new int[rlen][clen];
        // 1번 배열 값 빼두기
        for (int r = 0; r < rlen / 2; r++) {
            for (int c = 0; c < clen / 2; c++) {
                tmp[r][c] = grid[r][c];
            }
        }
        // 1 <- 2
        for (int r = 0; r < rlen / 2; r++) {
            for (int c = clen / 2; c < clen; c++) {
                grid[r][c - clen/2] = grid[r][c];
            }
        }
        // 2 <- 3
        for (int r = rlen / 2; r < rlen; r++) {
            for (int c = clen / 2; c < clen; c++) {
                grid[r-rlen/2][c] = grid[r][c];
            }
        }
        // 3 <- 4
        for (int r = rlen/2; r < rlen; r++) {
            for (int c = 0; c < clen / 2; c++) {
                grid[r][c+clen/2] = grid[r][c];
            }
        }
        // 4 <- 1 (tmp 대입)
        for (int r = rlen/2; r < rlen; r++) {
            for (int c = 0; c < clen / 2; c++) {
                grid[r][c] = tmp[r-rlen/2][c];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder ans = new StringBuilder();
        int R, C, K;
        R = Integer.parseInt(st.nextToken()); // 2 ~ 100
        C = Integer.parseInt(st.nextToken()); // 1 ~ 1000
        K = Integer.parseInt(st.nextToken());
        int[][] grid = new int[R][C];
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                grid[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        int[] arrayK = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) arrayK[i] = Integer.parseInt(st.nextToken());
        // 간단 풀이
        // 1, 2 => 바로 교환 가능 // 변수 하나로 해결 가능!
        // 5, 6 => 바로 덮어쓰기 가능 // 하고자 하는 연산에 필요한 임시 1차원 배열 만들어서 진행
        // 3, 4 => grid 모양이 바뀔 수 있음 // 아예 큰 배열 만들어서 옮겨야 함!

        // 1
        for (int i = 0; i < K; i++)
        {
            if (arrayK[i] == 1) {func1(grid);}
            else if (arrayK[i] == 2) {func2(grid);}
            else if (arrayK[i] == 3) {
                int rlen = grid.length;
                int clen = grid[0].length;
                int[][] res = new int[clen][rlen];
                for (int r = 0; r < res.length; r++) {
                    for (int c = 0; c < res[0].length; c++) {
                        res[r][c] = grid[rlen-1-c][r];
                    }
                }
                grid = new int[res.length][res[0].length];
                for (int j = 0; j < res.length; j++) {
                    grid[j] = res[j].clone();
                }
            }
            else if (arrayK[i] == 4) {
                int rlen = grid.length;
                int clen = grid[0].length;
                int[][] res = new int[clen][rlen];
                for (int r = 0; r < res.length; r++) {
                    for (int c = 0; c < res[0].length; c++) {
                        res[r][c] = grid[c][clen-1-r];
                    }
                }
                grid = new int[res.length][res[0].length];
                for (int j = 0; j < res.length; j++) {
                    grid[j] = res[j].clone();
                }
            }
            else if (arrayK[i] == 5) {func5(grid);}
            else func6(grid); // 6
        }
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                ans.append(grid[r][c] + " ");
            }
            if (r < grid.length - 1) ans.append('\n');
        }
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}
