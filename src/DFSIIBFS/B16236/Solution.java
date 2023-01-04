package B16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class fish{
    private int r;
    private int c;
    public fish (int r, int c)
    {
        this.r = r;
        this.c = c;
    }
    public int getR() {return this.r;}
    public int getC() {return this.c;}
}

public class Solution {

    public static int N, sr, sc, ss, minr, minc, minlen, exp, cnt;
    // sr = 상어 위치 행 // sc = 상어 위치 열 // ss = 상어 사이즈 // minr = // min c = // minlne = // exp = 물고기 먹은 수 // cnt =
    public static int[][] sea;
    public static int[][] len;
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};

    public static void findfish(int r, int c) // 상어의 현재 위치를 매개변수로 받아옴 (처음에는 처음 위치)
    {
        Queue<fish> fishes = new LinkedList<>();
        fishes.add(new fish(r, c));

        while (!fishes.isEmpty())
        {
            fish tmp = fishes.poll();
            int tr = tmp.getR();
            int tc = tmp.getC();

            for (int i = 0; i < 4; i++)
            {
                int nr = tr + dr[i];
                int nc = tc + dc[i];

                // 여기서 pass 된다 = 새로 Queue에 추가 되지도 않는다 = bfs의 가지 중 하나가 끝난다 = 이전 위치로 회귀해서 다른 방향을 살핀다 till 모두 끝날 때까지
                if (nr < 0 || nc < 0 || nr >= N || nc >= N ) continue; // grid 벗어나면 pass
                if (sea[nr][nc] > ss) continue; // 상어 현재 사이즈 보다 크면 지나갈 수 없으므로 pass
                if (len[nr][nc] != 0) continue; // 이미 이동한 거리라면 pass

                len[nr][nc] = len[tr][tc] + 1; // 이동거리 + 1 by bfs

                // 먹을 수 있는 물고기(우선 대상 후보)를 만난다면 // 그냥 빈 공간이면 그냥 bfs만!
                if (sea[nr][nc] != 0 && sea[nr][nc] < ss)
                {
                    // from 현재 상어 위치 to 여태까지 발견한 먹을 수 있는 물고기 자리
                    // 의 최소거리보다 더 짧다면
                    // 즉, 제 1 우선 순위인 최소거리에서 우선 순위를 갖는다면 => 무조건 우선 대상 변경
                    if (minlen > len[nr][nc])
                    {
                        minlen = len[nr][nc]; // 최소 이동거리 리셋 // 즉 우선 대상이 된 물고기까지의 거리
                        minr = nr; // 최소 이동 거리일 때의 행 // 즉 우선 대상이 된 물고기 위치의 행
                        minc = nc; // 최소 이동 거리일 때의 열 // 즉 우선 대상이 된 물고기 위치의 열
                    }
                    // 만약 최소거리와 거리가 동일한 다른 자리의 물고기라면
                    // 즉, 제 1 우선순위에서 동일한 우선 순위를 갖는다면
                    else if (minlen == len[nr][nc])
                    {
                        // 둘 중에 더 위에 있는 즉, 더 낮은 행값을 갖는 걸 우선 대상화 할건데 이 때도 같은 행에 있다면
                        if (minr == nr)
                        {
                            // 둘 중에 더 왼쪽에 있는 즉, 더 낮은 열값을 갖는 물고기를 우선 대상화.
                            if (minc > nc) // 이 경우에는 새로운 위치의 물고기가 우선 대상화 됨
                            {
                                minr = nr;
                                minc = nc;
                            }
                            // else => 기존 물고기가 우선대상 유지
                        }
                        // 최소거리와 거리가 같으면서, 기존의 우선 대상보다 더 높은 행에 위치한다면 새로운 위치의 물고기를 우선 대상화
                        else if (minr > nr)
                        {
                            minr = nr;
                            minc = nc;
                        }
                        // 둘 다 안걸린다 => 현재 우선대상과 같은 거리에 있지만 더 낮은 행에 있다 => 우선순위에서 밀리므로 기존 우선대상 유지
                    }
                }
                fishes.add(new fish(nr, nc)); // bfs를 이어 나가기 위한 큐에의 추가
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        sea = new int[N][N]; // grid
        ss = 2; // 상어 사이즈 초기값 2
        cnt = 0;
        exp = 0; // 물고기 먹은 수 초기값 당연히 0

        for (int r = 0; r < N; r++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++)
            {
                sea[r][c] = Integer.parseInt(st.nextToken());
                if (sea[r][c] == 9)
                {
                    sr = r; // 상어 처음(앞으로는 현재) 위치 행
                    sc = c; // 상어 처음(앞으로는 현재) 위치 열
                    sea[r][c] = 0; // 0으로해서 빈칸처럼 인식하게
                }
            }
        }

        while (true)
        {
            len = new int[N][N];
            minr = Integer.MAX_VALUE;
            minc = Integer.MAX_VALUE;
            minlen = Integer.MAX_VALUE;

            findfish(sr,sc);

            if (minr != Integer.MAX_VALUE && minc != Integer.MAX_VALUE)
            {
                exp++;
                sea[minr][minc] = 0;
                sr = minr;
                sc = minc;
                cnt += len[minr][minc];

                if (exp == ss)
                {
                    ss++;
                    exp = 0;
                }
            }
            else break;
        }
        System.out.println(cnt);
    }
}
