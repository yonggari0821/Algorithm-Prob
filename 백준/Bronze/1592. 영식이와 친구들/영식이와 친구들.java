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
            if (++tillM[cur] >= M) break; // 선행 연산자로 먼저 받고 최대로 받는 횟수와 비교해서 루프 탈출
//            System.out.printf(cur + " => ");
            if (((tillM[cur]) & 1) == 1) cur = (cur + L - 1) % N + 1; // 홀수 번 받음 => L만큼 오른쪽으로 // 딱 N일 때 0이 되어버리는 걸 방지하기 위해서 -1을 먼저 해주고 나머지 구한 후에 +1
            else cur = ((cur - L - 1) + N) % N + 1; // 짝수 번 받음 => L만큼 왼쪽으로 // 딱 0일때 그대로 0이 되어버리는 걸 방지하기 위해서 -1을 먼저 해주고 나머지를 구한 후에 +1
//            System.out.println(cur);
            throwCnt++; // 던진 횟수 + 1
        }
        ans.append(throwCnt);
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}