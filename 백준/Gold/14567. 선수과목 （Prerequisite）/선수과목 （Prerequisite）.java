import java.io.*;
import java.util.*;

/*
[위상정렬 14567]_선수과목_안상준

1. 한 학기에 들을 수 있는 과목 수에는 제한 X
2. 모든 과목은 항상 매학기 개설

선수과목 => 대상과목
DAG 구현후에 위상정렬해가면서 선수과목 수가 0이 된 애들의 학기를 기록해나가고 출력하면 되는 문제!

 */

public class Main {
    static int N, M;

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
    	StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 과목 수 // 최대 1000
        M = Integer.parseInt(st.nextToken()); // 선수 과목 조건 수 // 최대 500000
        int[] preClassNeeded = new int[N + 1];
        int[] semesterNeeded = new int[N + 1];
        ArrayList<ArrayList<Integer>> topologicalGraph = new ArrayList<>();
        for (int i = 0; i <= N; i++) topologicalGraph.add(new ArrayList<Integer>());
        for (int i = 0; i < M; i++)
        {
            st = new StringTokenizer(br.readLine());
            int pre = Integer.parseInt(st.nextToken());
            int aft = Integer.parseInt(st.nextToken());
            topologicalGraph.get(pre).add(aft);
            preClassNeeded[aft]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) if (preClassNeeded[i] == 0) q.offer(i);
        int time = 1;
        while (true)
        {
            int size = q.size();
            if (size == 0) break;
            for (int i = 0; i < size; i++)
            {
                int curClass = q.poll();
                semesterNeeded[curClass] = time;
                for (int j = 0; j < topologicalGraph.get(curClass).size(); j++)
                {
                    preClassNeeded[topologicalGraph.get(curClass).get(j)]--;
                    if (preClassNeeded[topologicalGraph.get(curClass).get(j)] == 0) q.offer(topologicalGraph.get(curClass).get(j));
                }
            }
            time++;
        }
        for (int i = 1; i <= N; i++) ans.append(semesterNeeded[i]).append(" ");
        System.out.println(ans.toString());
    	br.close();
    }
}