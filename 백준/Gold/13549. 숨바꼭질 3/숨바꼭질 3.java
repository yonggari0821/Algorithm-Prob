import java.io.*;
import java.util.*;

class Point
{
    int num;
    int time;

    public Point(int num, int time) {
        this.num = num;
        this.time = time;
    }
}

public class Main
{
    static int N, K, min = Integer.MAX_VALUE;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 수빈이
        K = Integer.parseInt(st.nextToken()); // 동생
        // 수빈이가 순간이동 할 수 있는 건 오직 * 2로만 가능! 즉 동행이 더 뒤에 있거나 같은 위치라면 무조건 한 칸씩 걸어가야 하므로 두 사람의 위치의 차를 바로 반환하면 됨!
        if (N >= K) {
            System.out.println(N - K);
            return ;
        }
        // 그렇지 않으면 즉 동생이 더 뒤에 있으면
        visited = new boolean[100001];
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(N, 0));
        while (!q.isEmpty())
        {
            Point tmp = q.poll();
            visited[tmp.num] = true;
            if (tmp.num == K) min = Math.min(min, tmp.time);

            if (tmp.num * 2 <= 100000 && !visited[tmp.num * 2]) q.offer(new Point(tmp.num * 2, tmp.time));
            if (tmp.num + 1 <= 100000 && !visited[tmp.num + 1]) q.offer(new Point(tmp.num + 1, tmp.time + 1));
            if (tmp.num - 1 >= 0 && !visited[tmp.num - 1]) q.offer(new Point(tmp.num - 1, tmp.time + 1));
        }
        ans.append(min);
        bw.write(ans.toString());
        br.close();
        bw.close();
    }
}