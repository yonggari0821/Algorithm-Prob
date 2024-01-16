import java.io.*;
import java.util.*;

/*
[2258]_정육점_안상준

더 싼 고기 덩어리는 얼마든지 덤으로 받을 수 있음
원하는 무게 이상 사는 건(덤 포함해서) 무관함!

우선순위 큐를 이용해서
가격을 기준으로 오름차순 순서대로 뽑아가면서
가능한 무게를 더해가면서 최소 가격을 찾기!

 */

class Meat
{
    int weight;
    int cost;

    public Meat(int weight, int cost) {
        this.weight = weight;
        this.cost = cost;
    }
}

public class Main {
    static int N, M;

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder ans = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 고기 덩어리 갯수
        M = Integer.parseInt(st.nextToken()); // 은혜가 원하는 고기의 양
        Queue<Meat> pq = new PriorityQueue<>( new Comparator<Meat>()
            {
                @Override
                public int compare(Meat o1, Meat o2) {
                    if (o1.cost == o2.cost) return o2.weight - o1.weight;
                    return o1.cost - o2.cost;
                }
            }
        );
        for (int i = 0; i < N; i++)
        {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken()); // 고기 덩어리 무게
            int c = Integer.parseInt(st.nextToken()); // 고기 덩어리 가격
            pq.offer(new Meat(w, c));
        }

        int nw = 0;
        int nc = 0;
        int nm = 0;
        int res = Integer.MAX_VALUE;
        boolean flag = false;
        while(!pq.isEmpty())
        {
            Meat tmp = pq.poll();
            nw += tmp.weight;
            if (nm == tmp.cost) nc += nm;
            else nc = nm = tmp.cost;
            if (nw >= M) {
                flag = true;
                res = Math.min(res, nc);
            }
        }
        if (!flag) ans.append(-1);
        else ans.append(res);
        System.out.println(ans.toString());
    	br.close();
    }
}

/*
3 2
1 1
1 1
2 3

답 2

10 14
2 3
2 4
2 5
3 1
1 3
7 9
7 3
8 4
10 3
3 10

답 4
 */