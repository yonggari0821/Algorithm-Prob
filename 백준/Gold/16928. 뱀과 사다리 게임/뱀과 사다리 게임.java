import java.io.*;
import java.util.*;

/*

1~6 주사위 눈
10 x 10 형태의 판
1부터 100까지 수 적혀있음!

*/

class Cur {
    int num;
    int cnt;

    public Cur(int num, int cnt) {
        this.num = num;
        this.cnt = cnt;
    }
}

public class Main {

    static int n, m;
    static int[] ladderOrSnake = new int[101];
    static boolean[] visited = new boolean[101];

    static {
        for (int i = 0; i <= 100; i++) ladderOrSnake[i] = i;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n + m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            ladderOrSnake[from] = to;
        }

        // 내림차순
        PriorityQueue<Cur> pq = new PriorityQueue<>(new Comparator<Cur>(){
            @Override
            public int compare(Cur o1, Cur o2) {
                if (o1.cnt == o2.cnt) return o2.num - o1.num;
                return o1.cnt - o2.cnt;
            }
        });

        visited[1] = true;
        pq.offer(new Cur(1, 0));
        while(!pq.isEmpty()) {
            Cur cur = pq.poll();
            visited[cur.num] = true;
            if (cur.num == 100) {
                System.out.println(cur.cnt);
                break;
            }
            for (int i = 6; i > 0; i--) {
                if (cur.num + i > 100) continue;
                if (visited[cur.num + i]) continue;
                pq.offer(new Cur(ladderOrSnake[ cur.num + i ], cur.cnt + 1) );
            }
        }
    }
}