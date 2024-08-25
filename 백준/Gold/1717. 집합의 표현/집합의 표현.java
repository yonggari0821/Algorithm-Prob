import java.io.*;
import java.util.*;

/*
백준 1707

 */

public class Main {
    StringBuilder sb = new StringBuilder();
    static int n, m;

    static int[] parent;
    static void union(int a, int b)
    {
        int aRoot = find(a);
        int bRoot = find(b);
        parent[bRoot] = aRoot;
    }

    static int find(int a)
    {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    static String yesOrNo(boolean flag)
    {
        if (flag) return "YES";
        return "NO";
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n+1];
        for (int i = 0; i <= n; i++) parent[i] = i;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            boolean unionOrFind = Integer.parseInt(st.nextToken()) == 0;
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (unionOrFind) union(a, b);
            else sb.append(yesOrNo(find(a) == find(b))).append('\n');
        }
        System.out.println(sb.toString());
    }
}

/*
[1]
7 8
0 1 3
1 1 7
0 7 6
1 7 1
0 3 7
0 4 2
0 1 1
1 1 1

NO
NO
YES
 */