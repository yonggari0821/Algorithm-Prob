import java.io.*;
import java.util.*;

class FenwickTree {
    long[] tree;

    public FenwickTree(int n) {
        this.tree = new long[n + 1];
    }

    public void init(long[] nums) {
        if (this.tree == null) this.tree = new long[nums.length + 1];

        for (int i = 0; i < nums.length; i++) {
            add(i + 1, nums[i]);
        }
    }

    public void add(int idx, long val) {
        for (int i = idx; i < tree.length; i += lowbit(i)) tree[i] += val;
    }

    public long query(int idx) {
        long sum = 0;
        for (int i = idx; i > 0; i -= lowbit(i)) sum += tree[i];
        return sum;
    }

    public long sectionQuery(int left, int right) {
        long sum = query(right);
        return sum -= query(left - 1);
    }

    private int lowbit(int x) {
        return x & -x;
    }
}

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N, M, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String testCase;

        while ((testCase = br.readLine()) != null && !testCase.isEmpty()) {
            StringTokenizer st = new StringTokenizer(testCase);
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            long[] nums = new long[N];
            FenwickTree tree = new FenwickTree(N);
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                nums[i] = Long.parseLong(st.nextToken());
            }

            tree.init(nums);

            for (int i = 0; i < M + K; i++) {
                st = new StringTokenizer(br.readLine());
                int addOrQuery = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                long c = Long.parseLong(st.nextToken());

                if (addOrQuery == 1) {
                    tree.add(b, c - nums[b - 1]);
                    nums[b - 1] = c;
                } else {
                    sb.append(tree.sectionQuery(b, (int) c)).append('\n');
                }
            }
        }
        System.out.print(sb.toString());
    }
}

/*
[1]
5 2 2
1
2
3
4
5
1 3 6
2 2 5
1 5 2
2 3 3

17
6

[2]
4 0 1
1
2
3
4
2 1 1

1
 */