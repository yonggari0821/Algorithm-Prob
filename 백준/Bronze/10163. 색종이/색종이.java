import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[][] grid = new int[1001][1001];
        int mr = 0;
        int mc = 0;
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            if (N == 1) ans.append(width * height);
            else
            {
                for (int j = 0; j < width; j++) {
                    for (int k = 0; k < height; k++) {
                        grid[r + k][c + j] = i;
                    }
                }
                mr = (mr < r + height - 1) ? (r + height - 1) : mr;
                mc = (mc < c + width - 1) ? (c + width - 1) : mc;
            }
        }
        int[] sum = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            for (int r = 0; r <= mr; r++) {
                for (int c = 0; c <= mc; c++) if (grid[r][c] == i) sum[i]++;
            }
        }
        for (int i = 1; i <= N; i++) ans.append(sum[i]).append('\n');
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}