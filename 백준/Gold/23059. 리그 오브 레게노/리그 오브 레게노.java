import java.io.*;
import java.util.*;

/*
백준 23059 리그 오브 레게노


 */

public class Main {
    static Queue<Integer> sortQueue(Queue<Integer> queue, Comparator<Integer> comparator) {
        List<Integer> sortedList = new LinkedList<>(queue);
        Collections.sort(sortedList, comparator);

        Queue<Integer> sortedQueue = new LinkedList<>(sortedList);
        return sortedQueue;
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        String[] numToName = new String[2* N + 1];
        int[] inflows = new int[2 * N + 1];
        int cnt = 1;
        Map<String, Integer> nameToInt = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();
        Comparator<Integer> c = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return numToName[o1].compareTo(numToName[o2]);
            }
        };
        List<List<Integer>> adjNodes = new ArrayList<>();
        adjNodes.add(new ArrayList<>()); // idx 맞추기용 빈 리스트

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            String name1 = st.nextToken();
            String name2 = st.nextToken();
            if (!nameToInt.containsKey(name1))
            {
                nameToInt.put(name1, cnt);
                numToName[cnt] = name1;
                adjNodes.add(new ArrayList<Integer>());
                cnt++;
            }
            if (!nameToInt.containsKey(name2))
            {
                nameToInt.put(name2, cnt);
                numToName[cnt] = name2;
                adjNodes.add(new ArrayList<Integer>());
                cnt++;
            }
            inflows[nameToInt.get(name2)]++;
            adjNodes.get(nameToInt.get(name1)).add(nameToInt.get(name2));
            adjNodes.get(nameToInt.get(name2)).add(nameToInt.get(name1));
        }

        for (int i = 1; i < cnt; i++) if (inflows[i] == 0) q.offer(i);

        int check = cnt;
        while(!q.isEmpty())
        {
            q = sortQueue(q, c);
            int curQueueSize = q.size();
            for (int i = 0; i < curQueueSize; i++) {
                int from = q.poll();
                sb.append(numToName[from]).append('\n');
                check--;
                for (int j = 0; j < adjNodes.get(from).size(); j++) {
                    int to = adjNodes.get(from).get(j);
                    inflows[to]--;
                    if (inflows[to] == 0) q.offer(to);
                }
            }
        }

        if (check == 1) System.out.println(sb.toString());
        else System.out.println(-1);
    }
}

/*
[1]

 */