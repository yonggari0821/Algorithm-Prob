import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        int[][] EWSN = new int[5][2];
        int[][] info = new int[6][2];
        int[] minRC = new int[2];
        int melonPerCell = Integer.parseInt(br.readLine());
        for (int i = 0; i < 6; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());
            info[i][0] = dir; // 방향
            info[i][1] = len; // 길이
            if (EWSN[dir][0] == 0) EWSN[dir][0] = len; // 처음 등장하는 방향 & 길이 저장
            else EWSN[dir][1] = len; // 2번째로 등장하는 방향 & 길이 저장
        }
        int bigger = 1, smaller = 1; // 큰 밭, 작은 밭
        if (EWSN[2][1] == 0 && EWSN[3][1] == 0) // 남서가 1개씩 => 동북 중 작은 밭 구성하는 길이 구하기
        {
            bigger = EWSN[2][0] * EWSN[3][0];
            for (int i = 0; i < 6; i++) {
                if (info[i][0] == 2) smaller *= info[(i + 3) % 6][1];
                if (info[i][0] == 3) smaller *= info[(i + 3) % 6][1];
            }
        }
        else if (EWSN[2][1] == 0 && EWSN[4][1] == 0)
        {
            bigger = EWSN[2][0] * EWSN[4][0];
            for (int i = 0; i < 6; i++) {
                if (info[i][0] == 2) smaller *= info[(i + 3) % 6][1];
                if (info[i][0] == 4) smaller *= info[(i + 3) % 6][1];
            }
        }
        else if (EWSN[1][1] == 0 && EWSN[3][1] == 0)
        {
            bigger = EWSN[1][0] * EWSN[3][0];
            for (int i = 0; i < 6; i++) {
                if (info[i][0] == 1) smaller *= info[(i + 3) % 6][1];
                if (info[i][0] == 3) smaller *= info[(i + 3) % 6][1];
            }
        }
        else if (EWSN[1][1] == 0 && EWSN[4][1] == 0)
        {
            bigger = EWSN[1][0] * EWSN[4][0];
            for (int i = 0; i < 6; i++) {
                if (info[i][0] == 1) smaller *= info[(i + 3) % 6][1];
                if (info[i][0] == 4) smaller *= info[(i + 3) % 6][1];
            }
        }
        ans.append(melonPerCell * ( bigger - smaller ));
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}