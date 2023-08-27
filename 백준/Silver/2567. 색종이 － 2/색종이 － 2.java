import java.io.*;
import java.util.*;
public class Main {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[][] paintingPaper = new int[100][100];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            for (int pr = r - 1; pr < r + 10 - 1; pr++) {
                for (int pc = c - 1; pc < c + 10 - 1; pc++) {
                    paintingPaper[pr][pc] = 1;
                }
            }
        }
        int round = 0;
        for (int r = 0; r < 100; r++) {
            for (int c = 0; c < 100; c++) {
                if (paintingPaper[r][c] == 1)
                {
                    int tmp = 0;
                    for (int i = 0; i < 4; i++)
                    {
                        int nr = r + dr[i];
                        int nc = c + dc[i];
                        if (nr < 0 || nc < 0 || nr >= 100 || nc >= 100) tmp++;
                        else {
                            if (paintingPaper[nr][nc] == 1) continue;
                            tmp++;
                        }
                    }
                    round += tmp;
                }
            }
        }


        ans.append(round);
        bw.write(ans.toString());
        br.close();
        bw.close();
    }
}
