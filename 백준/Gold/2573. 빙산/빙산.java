import java.io.*;
import java.util.*;
public class Main {
    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[][] map = new int[R][C];
        int[][] toMinus = new int[R][C]; // 올해동안 녹을 빙산 크기 최대 4이므로 확인을 위해 5를 더해주고 뺄때는 5로 나눈 나머지를 빼줌!
        Queue<Integer> r1000c = new LinkedList<>();
        for (int r = 0; r < R; r++)
        {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++)
            {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        // 1. 그래프 전체 탐색 : 원래 배열 0이 아니고 방문하지 않은 곳(toMinus가 0인 곳)을 만나면 bfs 시작 // bfs 시작하는 점 count! => 2 이상 되면 그 때의 년수를 출력! 0되면 0출력!
        // 2. bfs로 더 이상 저런 곳이 없을 때 까지 toMinus 메모제이션처리
        // 3. 그래프 전체 탐색 : map[r][c] -= toMinus[r][c];
        int year = 0;
        while (true)
        {
            int iceBergCnt = 0; // 빙산 갯수 => 그래프 탐색후에 2 이상이거나 0이면 전체 while문 탈출
            for (int r = 1; r < R; r++)
            {
                for (int c = 1; c < C; c++)
                {
                    if (map[r][c] != 0 && toMinus[r][c] == 0)
                    {
                        iceBergCnt++;

                        r1000c.offer(1000 * r + c); // bfs 위한 값 =====> (1000 * r) + c 로 설정 // 이렇게하면 나눈 몫과 나머지로 행열 위치 나타낼 수 있음! R과 C가 최대 300까지 이므로!!
                        while (!r1000c.isEmpty())
                        {
                            int tmp = r1000c.poll();
                            int tr = tmp / 1000;
                            int tc = tmp % 1000;
                            int zeroFaceCnt = 0;
                            toMinus[tr][tc] = 5;
                            for (int i = 0; i < 4; i++) {
                                int nr = tr + dr[i];
                                int nc = tc + dc[i];
                                if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue; // 범위 벗어남
                                if (map[nr][nc] == 0) zeroFaceCnt++; // 주변 바다 갯수
                                else if (toMinus[nr][nc] == 0)
                                {
                                    toMinus[nr][nc] = 5;
                                    r1000c.offer(nr * 1000 + nc); // 이어진 빙산(map[nr][nc] != 0)이 방문하지 않은 빙산이라면(toMinus[nr][nc] == 0) 큐에 넣어 줌!!
                                }
                            }
                            toMinus[tr][tc] += zeroFaceCnt;
                        }
                    }
                }
            }
            if (iceBergCnt >= 2) {
//                System.out.println("쪼개졌다!!");
                ans.append(year);
                break;
            }
            else if (iceBergCnt == 0) {
//                System.out.println("다 녹았다!!");
                ans.append(0);
                break;
            }
            for (int r = 1; r < R; r++) {
                for (int c = 1; c < C; c++) {
                    if (toMinus[r][c] >= 5)
                    {
                        map[r][c] = Math.max(0, map[r][c] - (toMinus[r][c] % 5));
                        toMinus[r][c] = 0;
                    }
                }
            }
            // n년째 과정 확인용!
//            System.out.println("["+year+"==>"+(year+1)+"년째 상황]");
//            for (int i = 0; i < R; i++) {
//                System.out.println(Arrays.toString(map[i]));
//            }
            year++;
        }
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}

/*
10 10
10 10 10 10 10 10 10 10 10 10
10 10 10 10 10 10 10 10 10 10
10 10 10 10 10 10 10 10 10 10
10 10 10 10 10 10 10 10 10 10
10 10 10 10 10 10 10 10 10 10
10 10 10 10 10 10 10 10 10 10
10 10 10 10 10 10 10 10 10 10
10 10 10 10 10 10 10 10 10 10
10 10 10 10 10 10 10 10 10 10
10 10 10 10 10 10 10 10 10 0
=> 0

10 10
10 10 10 10 0 10 10 10 10 10
10 10 10 10 0 10 10 10 10 10
10 10 10 10 0 10 10 10 10 10
10 10 10 10 0 10 10 10 10 10
10 10 10 10 0 10 10 10 10 10
10 10 10 10 0 10 10 10 10 10
10 10 10 10 0 10 10 10 10 10
10 10 10 10 0 10 10 10 10 10
10 10 10 10 0 10 10 10 10 10
10 10 10 10 10 10 10 10 10 0

 */