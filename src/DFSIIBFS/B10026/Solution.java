package B10026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
    private int r;
    private int c;

    public Node (int r, int c)
    {
        this.r = r;
        this.c = c;
    }

    public int getR() {return this.r;}
    public int getC() {return this.c;}
}

public class Solution {

    public static char[][] RGBgrid;
    public static int[][] seen;
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        RGBgrid = new char[N][N];
        seen = new int[N][N];

        for (int n1 = 0; n1 < N; n1++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine(), "RGB", true);
            for (int n2 = 0; n2 < N; n2++)
                RGBgrid[n1][n2] = st.nextToken().charAt(0);
        }

        Queue<Node> RGBpoints = new LinkedList<>();

        int normal = 0;
        int Cweak = 0;

        for (int r = 0; r < N; r++)
        {
            for (int c = 0; c < N; c++)
            {
                if (seen[r][c] == 0)
                {
                    RGBpoints.offer(new Node(r, c));
                    seen[r][c] = 1;

                    while (!RGBpoints.isEmpty()) {
                        Node tmp = RGBpoints.poll();
                        int pr = tmp.getR();
                        int pc = tmp.getC();

                        for (int i = 0; i < 4; i++) {
                            int nr = pr + dr[i];
                            int nc = pc + dc[i];

                            if (nr < 0 || nc < 0 || nr >= N || nc >= N)
                                continue;
                            if (seen[nr][nc] == 0 && RGBgrid[nr][nc] == RGBgrid[pr][pc])
                            {
                                seen[nr][nc] = 1;
                                RGBpoints.offer(new Node(nr, nc));
                            }
                            if (seen[nr][nc] == 0 && RGBgrid[nr][nc] != RGBgrid[pr][pc]) {
                                if (RGBgrid[pr][pc] == 'R' && RGBgrid[nr][nc] == 'G')
                                {
                                    seen[nr][nc] = 1;
                                    RGBpoints.offer(new Node(nr, nc));
                                }
                                else if (RGBgrid[pr][pc] == 'G' && RGBgrid[nr][nc] == 'R')
                                {
                                    seen[nr][nc] = 1;
                                    RGBpoints.offer(new Node(nr, nc));
                                }
                                else
                                    continue;
                            }
                        }
                    }
                    Cweak++;
                }
            }
        }

        for (int r = 0; r < N; r++)
        {
            for (int c = 0; c < N; c++)
            {
                if (seen[r][c] == 1)
                {
                    RGBpoints.offer(new Node(r, c));
                    seen[r][c] = 2;

                    while (!RGBpoints.isEmpty())
                    {
                        Node tmp = RGBpoints.poll();
                        int pr = tmp.getR();
                        int pc = tmp.getC();

                        for (int i = 0; i < 4; i++)
                        {
                            int nr = pr + dr[i];
                            int nc = pc + dc[i];

                            if (nr < 0 || nc < 0 || nr >= N || nc >= N)
                                continue;
                            if (seen[nr][nc] == 1 && RGBgrid[nr][nc] == RGBgrid[pr][pc])
                            {
                                seen[nr][nc] = 2;
                                RGBpoints.offer(new Node(nr, nc));
                            }
                            if (RGBgrid[nr][nc] != RGBgrid[pr][pc])
                                continue;
                        }
                    }
                    normal++;
                }
            }
        }
        System.out.println(normal + " " + Cweak);
    }
}
