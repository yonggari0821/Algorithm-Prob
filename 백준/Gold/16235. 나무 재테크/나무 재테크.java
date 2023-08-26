import java.io.*;
import java.util.*;
public class Main {
    static StringBuilder ans = new StringBuilder();
    static int N, M, K;
    static int[][] winterMineral;
    static int[][] ground;
    static int[] apr = {-1, -1, -1, 0, 0, 1, 1, 1}; // approximate row
    static int[] apc = {-1, 0, 1, -1, 1, -1, 0, 1}; // approximate column
    static void spring(Deque<Integer> trees, Queue<Integer> deadTrees, Queue<Integer> parentTrees) {
        int treeCnt = trees.size();
        for (int i = 0; i < treeCnt; i++)
        {
            int tree = trees.poll();
            int to = tree / 10000;
            int tw = tree % 10000;
            int tr = tw / 100;
            int tc = tw % 100;
            if (ground[tr][tc] < to) deadTrees.offer(tree); // 자기 위치 땅의 양분이 부족 => 죽어버림
            else
            {
                ground[tr][tc] -= to; // 자신의 나이만큼 양분을 먹고
                tree += 10000; // 나이 1살 먹기
                trees.offer(tree);
                if ((tree / 10000) >= 5 && (tree / 10000) % 5 == 0) parentTrees.offer(tree);
            }
        }
    };
    static void summer(Queue<Integer> deadTrees) {
        while(!deadTrees.isEmpty())
        {
            int deadTree = deadTrees.poll();
            int dto = deadTree / 10000;
            dto /= 2;
            int dtw = deadTree % 10000;
            int dtr = dtw / 100;
            int dtc = dtw % 100;
            ground[dtr][dtc] += (dto);
        }
    };
    static void fall(Deque<Integer> trees, Queue<Integer> parentTrees) {
        while(!parentTrees.isEmpty())
        {
            int parentTree = parentTrees.poll();
            int pto = parentTree / 10000;
            int ptw = parentTree % 10000;
            int ptr = ptw / 100;
            int ptc = ptw % 100;
            for (int i = 0; i < 8; i++)
            {
                int nr = ptr + apr[i];
                int nc = ptc + apc[i];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                trees.offerFirst(10000 + (nr * 100) + nc);
            }
        }
    };
    static void winter() {
        for (int r = 0; r < N; r++)
        {
            for (int c = 0; c < N; c++)
            {
                ground[r][c] += winterMineral[r][c];
            }
        }
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 땅 한 변의 길이 1 ~ 10
        M = Integer.parseInt(st.nextToken()); // 처음 심는 나무 갯수 1 ~ 100
        K = Integer.parseInt(st.nextToken()); // 지나갈 년 수
        winterMineral = new int[N][N]; // 겨울에 각 땅에 추가할 양분 양
        ground = new int[N][N]; // 땅 배열
        // 겨울에 추가하는 양분 양 입력 받아 놓기
        for (int r = 0; r < N; r++)
        {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++)
            {
                winterMineral[r][c] = Integer.parseInt(st.nextToken());
                ground[r][c] = 5;
            }
        }
        Deque<Integer> trees = new LinkedList<>(); // 나무들
        Queue<Integer> deadTrees = new LinkedList<>(); // 봄에 죽은 나무들
        Queue<Integer> parentTrees = new LinkedList<>(); // 가을에 번식할 나무들
        int[] firstTrees = new int[M];
        for (int treeNum = 0; treeNum < M; treeNum++)
        {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int o = Integer.parseInt(st.nextToken());
            firstTrees[treeNum] = (o * 10000) + (r * 100) + c;
            // 나이 * 10000 => 10000 ~ 10000000
            // 행 * 100 => 100 ~ 1000
            // 열 => 1 ~ 10
        }
        Arrays.sort(firstTrees); // 처음에 크기 (나이 * 10000 이므로 나이 순으로 정렬됨!) 정렬해두면 그 다음부턴 안해도 됨!
//        System.out.println(Arrays.toString(firstTrees));
        for (int treeNum = 0; treeNum < M; treeNum++) trees.offer(firstTrees[treeNum]); // 큐에 넣기 by 나이순!
        for (int years = 0; years < K; years++)
        {
//            System.out.println("year = " + years);
//            for (int i = 0; i < N; i++) System.out.println(Arrays.toString(ground[i]));
            spring(trees, deadTrees, parentTrees);
            summer(deadTrees);
            fall(trees, parentTrees);
            winter();
        }
//        for (int i = 0; i < N; i++) System.out.println(Arrays.toString(ground[i]));
        ans.append(trees.size());
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}