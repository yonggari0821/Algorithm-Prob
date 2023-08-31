import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> toDo = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        int[][] homework = new int[N][2];
        int max = 0;
        for (int i = 0; i < N; i++)
        {
            st = new StringTokenizer(br.readLine());
            homework[i][0] = (Integer.parseInt(st.nextToken())); // days left <d>
            homework[i][1] = (Integer.parseInt(st.nextToken())); // point <w>
            max = max > homework[i][0] ? max : homework[i][0];
        }
        ArrayList<Integer>[] homeworkList = new ArrayList[max + 1];
        for (int i = 1; i <= max; i++) homeworkList[i] = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) homeworkList[homework[i][0]].add(homework[i][1]);
        for (int i = 1; i <= max; i++) {
            if (homeworkList[i] != null) {
                int hwli = 0;
                while (hwli < homeworkList[i].size())
                {
                    if (toDo.isEmpty() || toDo.size() < i) toDo.offer(homeworkList[i].get(hwli++));
                    else
                    {
                        if (hwli < homeworkList[i].size() && toDo.peek() < homeworkList[i].get(hwli)) {
                            while (hwli < homeworkList[i].size() && toDo.peek() < homeworkList[i].get(hwli)) {
                                toDo.poll();
                                toDo.offer(homeworkList[i].get(hwli++));
                            }
                        }
                        else hwli++;
                    }
                }
            }
        }
        int sum = 0;
        while (!toDo.isEmpty()) sum += toDo.poll();
        ans.append(sum);
        bw.write(ans.toString());
        br.close();
        bw.close();
    }
}