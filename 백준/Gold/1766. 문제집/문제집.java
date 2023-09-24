import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int n = 0; n <= N; n++) graph.add(new ArrayList<Integer>());
        int[] inflow = new int[N + 1];
        for (int m = 1; m <= M; m++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph.get(from).add(to);
            inflow[to]++;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Queue<Integer> output = new LinkedList<>();
        for (int prob = 1; prob <= N; prob++) {
            if (inflow[prob] == 0) pq.offer(prob);
        }
        while (!pq.isEmpty())
        {
            int cur = pq.poll();
            output.add(cur);
            for (int i = 0; i < graph.get(cur).size(); i++) {
                if (--inflow[graph.get(cur).get(i)] == 0) pq.offer(graph.get(cur).get(i));
            }
        }
        while (!output.isEmpty())
        {
            ans.append(output.poll()).append(" ");
        }
        bw.write(ans.toString());
        br.close();
        bw.close();
    }
}