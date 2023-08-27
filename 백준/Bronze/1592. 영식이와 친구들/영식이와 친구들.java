import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 둘러 앉는 사람 수
        int M = Integer.parseInt(st.nextToken()); // 한 사람이 최대 받을 수 있는 공 횟
        int L = Integer.parseInt(st.nextToken()); // 공 던지는 거
        int[] tillM = new int[N + 1];
        int cur = 1;
        int throwCnt = 0;
        while (tillM[cur] < M)
        {
            if (++tillM[cur] >= M) break;
//            System.out.printf(cur + " => ");
            if (((tillM[cur]) & 1) == 1) cur = (cur + L - 1) % N + 1;
            else cur = ((cur - L - 1) + N) % N + 1;
//            System.out.println(cur);
            throwCnt++;
        }
        ans.append(throwCnt);
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}