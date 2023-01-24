package SamsungAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class NC implements Comparable<NC>
{
    int num;
    int cnt;

    NC(int num, int cnt)
    {
        this.num = num;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(NC o) {
        if (this.cnt == o.cnt) // 등장 횟수가 같으면
            return (this.num - o.num); // 숫자 크기로 오름차순
        else // 다르면
            return (this.cnt - o.cnt); // 등장 횟수로 오름차순
    }
}

public class B17140 {

    static int r, c, k;
    static int[][] DD = new int[101][101]; // 행과 열은 1~100 범위로 가능하므로!
    static int rlen = 3; // 행 길이 초기값
    static int clen = 3; // 열 길이 초기값

    static void Rcal(int key) // R 연산 => key 값으로 현재 행 길이까지의 행들을 받아옴
    {
        PriorityQueue<NC> pq = new PriorityQueue<>(); // 숫자와 그 등장 횟수를 갖는 클래스를 담는 우선순위 큐
        Map<Integer, Integer> nc = new HashMap<>(); // 키 = 숫자 // 값 = 등장 횟수
        for (int i = 1; i <= clen; i++)
        {
            if (DD[key][i] != 0) // 0이 아닌 수가 등장하면
                nc.put(DD[key][i], nc.getOrDefault(DD[key][i], 0) + 1); // 해당 수를 키 값으로 넣고, 값에 등장횟수를 넣음 by getOrDefault 메서드
            // 처음 등장 => 디폴트 값이 0을 뱉고 거기에 + 1이니 1 // 또 등장 1을 뱉고 거기에 + 1이니 2 // ...
        }
        // 이 과정 이후에 모든 등장하는 수는 키 값으로 그리고 그 각각의 등장횟수가 값으로 nc Map에 배정

        for (Map.Entry<Integer, Integer> e : nc.entrySet()) // Entry는 Map에서 키와 값을 둘 다 for문을 통해서 쓸 때 좋음!
            pq.add(new NC(e.getKey(), e.getValue())); // nc Map에서 엔트리를 따와서 그 키와 값을 NC 클래스의 num과 cnt로 하여 우선순위 큐에 배정
        // NC 클래스 내에 해당 클래스 간 비교에 쓰이는 comparable을 구현해놨기 때문에
        // 그 우선순위에 따라서 큐에 배치 => 아래에 poll 하는 부분에서도 이 순서대로 빠짐

        int t = 1; // 1번 열부터
        while(!pq.isEmpty())
        {
            NC tmp = pq.poll();
            DD[key][t] = tmp.num; // 등장한 수
            DD[key][t+1] = tmp.cnt; // 등장 횟수
            t+=2;
        }

        clen = clen > t ? clen : t;
        // 현재 열의 길이와 t의 값을 비교해서 더 큰쪽으로 열의 길이를 지정

        while (t <= 99) // t가 더 작다면 끝까지 0으로 배정 // t가 더 커도 기존보다 더 작아졌을 수 있기 때문에 0으로 배정
        {
            DD[key][t] = 0;
            DD[key][t+1] = 0;
            t+=2;
        }
    }

    static void Ccal(int key) // R 연산과 행열값만 다르고 마찬가지
    {
        PriorityQueue<NC> pq = new PriorityQueue<>();
        Map<Integer, Integer> nc = new HashMap<>();
        for (int i = 1; i <= rlen; i++)
        {
            if (DD[i][key] != 0)
                nc.put(DD[i][key], nc.getOrDefault(DD[i][key], 0) + 1);
        }

        for (Map.Entry<Integer, Integer> e : nc.entrySet())
            pq.add(new NC(e.getKey(), e.getValue()));

        int t = 1;
        while(!pq.isEmpty())
        {
            NC tmp = pq.poll();
            DD[t][key] = tmp.num;
            DD[t+1][key] = tmp.cnt;
            t+=2;
        }

        rlen = rlen > t ? rlen : t;

        while (t <= 99)
        {
            DD[t][key] = 0;
            DD[t+1][key] = 0;
            t+=2;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= 3; i++)
        {
            StringTokenizer sta = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++)
                DD[i][j] = Integer.parseInt(sta.nextToken());
        }

        for (int time = 0; time <= 100; time++)
        {
            if (DD[r][c] == k)
            {
                System.out.println(time);
                return ;
            }

            if (rlen >= clen)
            {
                int trlen = rlen;
                for (int i = 1; i <= trlen; i++)
                    Rcal(i);
            }
            else
            {
                int tclen = clen;
                for (int i = 1; i <= tclen; i++)
                    Ccal(i);
            }
        }
        System.out.println(-1);
        return ;
    }
}
