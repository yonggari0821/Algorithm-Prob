import java.io.*;
import java.util.*;
public class Main {
    static StringBuilder ans = new StringBuilder();
    static int N, M, cnt = 0;
    static int[] missions = new int[6];

    static void getPoint(int day, int lastM, int point)
    {
        if (day == N)
        {
            if (point >= M) cnt++;
            return ;
        }
        for (int i = 0; i < 6; i++)
        {
           if (i % 3 == lastM % 3) getPoint(day + 1, i, point + missions[i] / 2);
           else getPoint(day + 1, i, point + missions[i]);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int r = 0; r < 2; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < 3; c++) {
                missions[3*r+c] = Integer.parseInt(st.nextToken());
            }
        }
        getPoint(0, -1,0);
        ans.append(cnt);
        bw.write(ans.toString());
        br.close();
        bw.close();
    }
}