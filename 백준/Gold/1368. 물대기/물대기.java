import java.io.*;
import java.util.*;

/*
백준 1368 물대기

어떤 망을 연결하는 최소 비용을 구한다 => MST
크루스칼 또는 프림 알고리즘 사용 가능

여러 개의 논에 물들의 연결 망을
1) 직접 우물을 파던지
2) 다른 논의 물을 끌어오던지해서
최소 비용으로 연결하는 문제이므로 MST!
 */


class Node {
    int num;
    int cost;

    public Node(int num, int cost) {
        this.num = num;
        this.cost = cost;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 1 ~ 300

        int[][] getWaterFromOtherWell = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) getWaterFromOtherWell[i][i] = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= N; c++) {
                if (r == c) st.nextToken();
                else getWaterFromOtherWell[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int min = Integer.MAX_VALUE;
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost - o2.cost;
            }
        });
        
        boolean[] visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) pq.offer(new Node(i, getWaterFromOtherWell[i][i]));
        
        int tmp = 0;
        int visitedWellNum = 0;
        
        while(!pq.isEmpty())
        {
            Node cur = pq.poll();
            if (visited[cur.num]) continue;
            visited[cur.num] = true;
            visitedWellNum++;
            tmp += cur.cost;
            if (visitedWellNum == N) break;
            for (int j = 1; j <= N; j++) {
                if (j == cur.num) continue;
                if (!visited[j]) pq.offer(new Node(j, Math.min(getWaterFromOtherWell[j][j], getWaterFromOtherWell[cur.num][j])));
            }
        }
        
        min = Math.min(min, tmp);
        System.out.println(min);
    }
}

/*
[1]
4
5
4
4
3
0 2 2 2
2 0 3 3
2 3 0 4
2 3 4 0

9

4
5
7
4
6
0 2 6 2
2 0 3 3
6 3 0 4
2 3 4 0

10
 */