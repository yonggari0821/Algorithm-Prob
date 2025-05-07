import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static ArrayList<Integer>[] workers;
    static int[] compliments;
    /*

    n과 m은 최대 10만임
    따라서 칭찬을 받을 때 마다 모든 값에 더해주면
    10만 제곱 ==> 시간 복잡도 폭망

    따라서 시간 복잡도를 줄일 수 있는 모종의 방법이 필요함. => 누적합

     */

    static void func(int num) {
        for (int i = 0; i < workers[num].size(); i++) {
            int next = workers[num].get(i);
            compliments[next] += compliments[num];
            func(next);
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 사원 수
        m = Integer.parseInt(st.nextToken()); // 칭찬 수
        st = new StringTokenizer(br.readLine());
        workers = new ArrayList[n + 1];
        compliments = new int[n + 1];
        for (int i = 1; i <= n; i++) workers[i] = new ArrayList<>();
        st.nextToken();
        for (int i = 2; i <= n; i++) {
            int p = Integer.parseInt(st.nextToken());
            workers[p].add(i);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int who = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            compliments[who] += val;
        }

        func(1);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) sb.append(compliments[i]).append(' ');

        System.out.println(sb.toString());
    }
}