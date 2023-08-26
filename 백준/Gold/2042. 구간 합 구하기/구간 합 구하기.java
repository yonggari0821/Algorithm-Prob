import java.io.*;
import java.util.StringTokenizer;
public class Main {
    static int N, M, K, S = 1;
    static StringBuilder ans = new StringBuilder();
    static long[] nums;
    static long[] tree;

    // [Top-Down]
    static long TDinit(int start, int end, int node) // (1, S, 1)
    {
        if (start > N) return 0;
        if (start != end) // 내부 노드
        {
            int mid = start + (end - start) / 2;
            tree[node] = TDinit(start, mid, node * 2) + TDinit(mid + 1, end, node * 2 + 1);
        }
        else // 리프 노드
            tree[node] = nums[node - S + 1];
        return tree[node];
    }

    static void TDupdate(int start, int end, int node, int target, long diff)
    {
        if (end < target || target < start) return; // 무관한 범위
        else // 유관한 범위
        {
            tree[node] += diff; // 값 반영
            if (start != end) // 내부 노드
            {
                int mid = start + (end - start) / 2;
                TDupdate(start, mid, node * 2, target, diff);
                TDupdate(mid + 1, end, node * 2 + 1, target, diff);
            }
            // else == 리프 노드 ==> 리프 노드 전 마지막 내부노드에서 이미 값 변경 수행해줄 것이므로 따로 로직 필요 없음!
        }
    }

    static long TDquery (int start, int end, int node, int queryStart, int queryEnd)
    {
        if (end < queryStart || queryEnd < start)  return 0; // 무관한 범위
        else if (queryStart <= start && end <= queryEnd) return tree[node]; // 완전히 포함
        else // 일부만 포함
        {
            int mid = start + (end - start) / 2;
            long leftValue = TDquery(start, mid, node * 2, queryStart, queryEnd);
            long rightValue = TDquery(mid + 1, end, node * 2 + 1, queryStart, queryEnd);
            return leftValue + rightValue;
        }
    }

    // [Bottom-Up]
    static void BUinit()
    {
        for (int i = 1; i <= N; i++) tree[S + i - 1] = nums[i];
        for (int i = S - 1; i > 0; i--) tree[i] = tree[i*2] + tree[i*2 + 1];
    }

    static void BUupdate(int target, long goal)
    {
        int node = S + target - 1;
        tree[node] = goal;
        node /= 2;
        while(node > 0)
        {
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
            node /= 2;
        }
    }

    static long BUquery (int queryStart, int queryEnd)
    {
        long sum = 0;
        int start = queryStart + S - 1;
        int end = queryEnd + S - 1;
        while (start <= end)
        {
            if (start % 2 == 1)
                sum += tree[start++];
            if (end % 2 == 0)
                sum += tree[end++];
            start /= 2;
            end /= 2;
            // else로 하면 start만 가지고 판단하므로 X
        }
        return sum;
    }
    public static void main(String[] args) throws IOException {
        // 구간합 + 값 변경 잦음 => 인덱스 트리 활용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 전체 수 갯수
        M = Integer.parseInt(st.nextToken()); // 값 변경 횟수
        K = Integer.parseInt(st.nextToken()); // 쿼리 갯수

        while (S < N) S *= 2; // 인덱스 트리 사이즈 조정

        nums = new long[N+1];
        tree = new long[2*S];

        for (int n = 1; n <= N; n++)
            nums[n] = Long.parseLong(br.readLine());

        TDinit(1, S, 1);
        for (int mk = 1; mk <= M + K; mk++)
        {
            st = new StringTokenizer(br.readLine());
            int updateOrQuery = Integer.parseInt(st.nextToken());
            int numOrderToUpdate = Integer.parseInt(st.nextToken());
            long updateTo = Long.parseLong(st.nextToken());

            if (updateOrQuery == 1)
            {
                long diff = updateTo - tree[numOrderToUpdate + S - 1];
                TDupdate(1, S, 1, numOrderToUpdate, diff);
            }
            else if (updateOrQuery == 2)
                ans.append(TDquery(1, S, 1, numOrderToUpdate, (int) updateTo)).append('\n');
        }

        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}