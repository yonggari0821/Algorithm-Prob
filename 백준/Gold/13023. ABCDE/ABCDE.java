import java.io.*;
import java.util.*;

public class Main {

    static int n, m;

    static ArrayList<Integer>[] friends;

    static boolean[] checked;

    static int ans;

    // 연결관계 표시 후
    // 재귀적으로 확인하기!

    static void func(int cur, int depth) {
        if (depth == 4) {
            ans = 1;
            return ;
        }
        for (int i = 0; i < friends[cur].size(); i++) {
            int next = friends[cur].get(i);
            if (!checked[next]) {
                checked[next] = true;
                func(next, depth + 1);
                checked[next] = false;
                if (ans == 1) return;
            }
        }
    }



    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ans = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 사람 수
        m = Integer.parseInt(st.nextToken()); // 친구 관계 수

        friends = new ArrayList[n];
        for (int i = 0; i < n; i++) friends[i] = new ArrayList<>();

        for (int f = 0; f < m; f++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friends[a].add(b);
            friends[b].add(a);
        }

        for (int i = 0; i < n; i++) {
            if (ans == 1) break;
            checked = new boolean[n];
            checked[i] = true;
            func(i, 0);
        }
        System.out.println(ans);
    }
}