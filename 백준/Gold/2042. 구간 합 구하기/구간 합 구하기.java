import java.io.*;
import java.util.*;

/*
백준 2042 구간 합 구하기
펜윅트리 또는 세그먼트 트리를 통해 풀이 가능
 */

class FenwickTree {
    long[] tree; // 트리 배열

    public FenwickTree(int n) {
        this.tree = new long[n + 1];
    } // 원래 배열의 크기보다 1개 늘려야 n번째 수를 n번 index에 바로 넣을 수 있음

    public void init(long[] nums) {
        if (this.tree == null) this.tree = new long[nums.length + 1];

        for (int i = 0; i < nums.length; i++) {
            add(i + 1, nums[i]);
        }
    }

    // 더하기 함수
    /*
    idx와 val를 받아서 idx 번째에 val만큼을 더하는데, 이 때 lowbit를 활용해서 tree 끝까지 idx번째의 수가 포함되는 모든 자리에 더해줘야 함
     */
    public void add(int idx, long val) {
        for (int i = idx; i < tree.length; i += lowbit(i)) tree[i] += val;
    }

    // 쿼리 함수 (구간합 구하기 함수)
    /*
    idx를 받아서 해당 idx까지의 값을 구하는 함수
    더하기 함수와 마찬가지로 lowbit를 활용함 
    
    보통 구간합을 구하기 위해서는 (A부터 B까지의 구간합을 구해야 한다고 가정)
    query(B) - query(A-1)의 방식으로 구한다. => 아래 sectionQuery에 구현해 둠!
     */
    public long query(int idx) {
        long sum = 0;
        for (int i = idx; i > 0; i -= lowbit(i)) sum += tree[i];
        return sum;
    }

    public long sectionQuery(int left, int right) {
        long sum = query(right);
        return sum -= query(left - 1);
    }

    // 최하위 비트를 구하는 함수
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
            N = Integer.parseInt(st.nextToken()); // 수의 갯수 1~100만
            M = Integer.parseInt(st.nextToken()); // 수 변경 횟수 1~10000
            K = Integer.parseInt(st.nextToken()); // 구간합 구하는 갯수 1~10000

            long[] nums = new long[N]; // 원래 수 배열 // 입력으로 주어지는 모든 수는 -263보다 크거나 같고, 263-1보다 작거나 같은 정수이다. 라고 했으므로 long으로 선언
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
                    tree.add(b, c - nums[b - 1]); // 펜윅트리의 b번째에, 바꾸려는 값에서 원래 배열의 b번째(nums[b-1]) 값을 뺀 값을 더해준다는 개념
                    nums[b - 1] = c; // 원래 배열에도 바꿔줘야 다음 연산때 이용!
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