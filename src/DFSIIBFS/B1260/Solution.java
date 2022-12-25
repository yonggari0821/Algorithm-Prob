package B1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    public static boolean[] Dvisited;
    public static boolean[] Bvisited;
    public static int[][] connected;
    public static ArrayList<ArrayList<Integer>> numgraph = new ArrayList<>();
    public static Queue<Integer> forBFS = new LinkedList<>();

    public static void DFS(int n)
    {
        Dvisited[n] = true;
        System.out.printf("%d ", n);
        for (int i = 0; i < numgraph.get(n).size(); i++)
        {
            int a = numgraph.get(n).get(i);
            if (!Dvisited[a]) DFS(a);
        }
    }

    public static void BFS(int n)
    {
        forBFS.offer(n);
        Bvisited[n] = true;
        while (!forBFS.isEmpty())
        {
            int a = forBFS.poll();
            System.out.printf("%d ", a);

            for (int i = 0; i < numgraph.get(a).size(); i++)
            {
                int b = numgraph.get(a).get(i);
                if (!Bvisited[b])
                {
                    forBFS.offer(b);
                    Bvisited[b] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        Dvisited = new boolean[N + 1];
        Bvisited = new boolean[N + 1];
        connected = new int[N + 1][N + 1];

        for (int i = 0; i < N + 1; i++)
            numgraph.add(new ArrayList<Integer>());

        int m = 0;
        while (m < M)
        {
            StringTokenizer stp = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(stp.nextToken());
            int to = Integer.parseInt(stp.nextToken());

            connected[from][to] += 1;
            connected[to][from] += 1;
            m++;
        }

        for (int i = 0; i < N + 1; i++)
        {
            for (int j = 0; j < N + 1; j++)
            {
                if (connected[i][j] > 0)
                    numgraph.get(i).add(j);
            }
        }

        DFS(V);
        System.out.println("");
        BFS(V);
    }

}