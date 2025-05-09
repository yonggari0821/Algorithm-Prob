import java.io.*;
import java.util.*;

///*
//
//1~6 주사위 눈
//10 x 10 형태의 판
//1부터 100까지 수 적혀있음!
//
//*/
//
//// 현재 위치 + 주사위 던진 수를 묶어서 클래스화
//class Cur {
//    int num;
//    int cnt;
//
//    public Cur(int num, int cnt) {
//        this.num = num;
//        this.cnt = cnt;
//    }
//}
//
//public class Main {
//
//    static int n, m;
//    static int[] ladderOrSnake = new int[101]; // 사다리나 뱀을 탈 경우 이동하는 위치 배열 (둘 다 아닌 경우는 자기 자신으로 초기화)
//    static boolean[] visited = new boolean[101]; // 방문 처리용 배열
//
//    // 일단 자기 자신으로 초기화
//    static {
//        for (int i = 0; i <= 100; i++) ladderOrSnake[i] = i;
//    }
//
//    public static void main(String args[]) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        n = Integer.parseInt(st.nextToken()); // 사다리 수
//        m = Integer.parseInt(st.nextToken()); // 뱀 수
//        // 사다리나 뱀 타는 경우 배열에 위치 미리 저장
//        for (int i = 0; i < n + m; i++) {
//            st = new StringTokenizer(br.readLine());
//            int from = Integer.parseInt(st.nextToken());
//            int to = Integer.parseInt(st.nextToken());
//            ladderOrSnake[from] = to;
//        }
//
//        // 내림차순
//        /*
//        일단 주사위 던진 수가 더 적을 수록 먼저 테스트 해봐야 하고
//        그 다음 같은 횟수라면 더 많은 수가 더 유리하므로 2개의 우선순위를 함께 고려!
//         */
//        PriorityQueue<Cur> pq = new PriorityQueue<>(new Comparator<Cur>(){
//            @Override
//            public int compare(Cur o1, Cur o2) {
//                if (o1.cnt == o2.cnt) return o2.num - o1.num;
//                return o1.cnt - o2.cnt;
//            }
//        });
//
//        // 첫 시작 위치 방문 처리 후 우선순위 큐에 넣기
//        visited[1] = true;
//        pq.offer(new Cur(1, 0));
//
//        // 큐가 비거나 조건 만족(100번 칸 도착)할 때 까지 아래 while문 돌리기
//        while(!pq.isEmpty()) {
//            // 하나 꺼내서
//            Cur cur = pq.poll();
//            // 방문 처리 후
//            visited[cur.num] = true;
//            // 100번 도착이면 cnt(주사위 던진 횟수) 출력 후 끝내고
//            if (cur.num == 100) {
//                System.out.println(cur.cnt);
//                break;
//            }
//            // 아니라면 1~6 주사위 던져서 역시 우선순위 큐에 넣기
//            for (int i = 6; i > 0; i--) {
//                if (cur.num + i > 100) continue; // 범위 벗어나는 경우 제외(뱀이나 사다리가 맵을 벗어나는 경우는 없으므로 마지막만 고려해주면 됨!
//                if (visited[cur.num + i]) continue; // 이미 방문한 경우 제외(이미 최소 주사위 던진 횟수로 1차적으로 걸러지기 때문에 이후에 같은 곳을 방문 하는 건 어짜피 제외!)
//                // 여까지 통과하면 우선순위 큐에 들어갈 자격이 됨!
//                pq.offer(new Cur(ladderOrSnake[ cur.num + i ], cur.cnt + 1) );
//            }
//        }
//    }
//}

public class Main {
    static int n, m, k;

    static int[] friends;
    static int[] costs;
    static int find(int num) {
        if (friends[num] == num) return num;
        return friends[num] = find(friends[num]);
    }

    static void union(int a, int b) {
        int fa = find(friends[a]);
        int fb = find(friends[b]);
        if (fa != fb) {
            if (costs[fa] >= costs[fb]) friends[fa] = find(fb);
            else friends[fb] = find(fa);
        }
    }

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        friends = new int[n + 1];
        costs = new int[n + 1];
        for (int i = 0; i <= n; i++) friends[i] = i;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) costs[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            set.add(find(i));
        }

        int answer = 0; // 최대 1억이라 그냥 int로 해도 됨!
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            int cur = it.next();
            answer += costs[cur];
        }

        if (answer <= k) System.out.println(answer);
        else System.out.println("Oh no");
    }
}