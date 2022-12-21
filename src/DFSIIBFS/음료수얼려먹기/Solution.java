package 음료수얼려먹기;

import java.util.Scanner;
public class Solution {


    public static int N, M;
    public static int[][] graph = new int[1001][1001];
    public static boolean DFS(int x, int y)
    {
        if (x < 0 || y < 0 || x >= N || y >= M)
            return false;
        if (graph[x][y] == 0)
        {
            graph[x][y] = 1;
            DFS(x-1, y);
            DFS(x, y-1);
            DFS(x+1, y);
            DFS(x, y+1);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < N; i++)
        {
            String str = sc.nextLine();
            for (int j = 0; j < M; j++)
            {
                graph[i][j] = str.charAt(j) - '0';
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < M; j++)
            {
                if (DFS(i, j))
                    cnt += 1;
            }
        }
        System.out.println(cnt);
    }
}
