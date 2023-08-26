import java.io.*;
import java.util.*;
public class Main {
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        int N = Integer.parseInt(br.readLine()); // 한 변의 길이
        int numToFind = Integer.parseInt(br.readLine()); // 찾을 숫자
        int rowToFind = 0, colToFind = 0;
        int[][] snailGrid = new int[N][N];
        int tr = 0, tc = 0, num = N * N, dir = 0;
        while (true)
        {
            snailGrid[tr][tc] = num;
            if (num == numToFind) // 찾는 숫자면 그 때의 행/열 값 따로 빼두기
            {
                rowToFind = tr + 1;
                colToFind = tc + 1;
            }
            if (num == 1) break;
            int nr = tr + dr[dir]; // 새로 살펴볼 행
            int nc = tc + dc[dir]; // 새로 살펴볼 열
            // 어차피 앞에서 num == 1 일 때, break 처리해줘서 딱히 문제 없음!
            while (nr < 0 || nc < 0 || nr >= N || nc >= N || snailGrid[nr][nc] != 0)
            {
                dir = (dir + 1) % 4;
                nr = tr + dr[dir];
                nc = tc + dc[dir];
            }
            tr = nr;
            tc = nc;
            num--;
        }
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                ans.append(snailGrid[r][c]).append(" ");
            }
            ans.append('\n');
        }
        ans.append(rowToFind + " " + colToFind);
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}