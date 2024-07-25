import java.io.*;
import java.util.*;

class Node {
    int cur;
    Node left;
    Node right;

    public Node(int cur, Node left, Node right) {
        this.cur = cur;
        this.left = left;
        this.right = right;
    }
}

public class Main {
    static int[] numArr;
    static int front, rear;

    static int postTraverse(Node node, int minusCnt) {
        if (node.cur == Integer.MIN_VALUE + 1) {
            return (minusCnt % 2 == 0) ? numArr[rear--] : numArr[front++];
        }

        int lv = postTraverse(node.left, minusCnt);
        int rv = postTraverse(node.right, node.cur == Integer.MIN_VALUE ? minusCnt + 1 : minusCnt);

        // Apply operation
        if (node.cur == Integer.MAX_VALUE) {
            return lv + rv;
        } else {
            return lv - rv;
        }
    }

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        Node[] numTree = new Node[2 * N];
        numArr = new int[N];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            numArr[i - 1] = Integer.parseInt(st.nextToken());
            numTree[i] = new Node(Integer.MIN_VALUE + 1, null, null);
        }
        Arrays.sort(numArr);
        front = 0;
        rear = N - 1;

        int[][] tmp = new int[N - 1][2];
        for (int i = N + 1; i < 2 * N; i++) {
            st = new StringTokenizer(br.readLine());
            boolean isPlus = st.nextToken().charAt(0) == '+';
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            numTree[i] = new Node(isPlus ? Integer.MAX_VALUE : Integer.MIN_VALUE, null, null);
            tmp[i - N - 1][0] = left;
            tmp[i - N - 1][1] = right;
        }
        for (int i = 0; i < N - 1; i++) {
            numTree[i + N + 1].left = numTree[tmp[i][0]];
            numTree[i + N + 1].right = numTree[tmp[i][1]];
        }

        int res = postTraverse(numTree[2 * N - 1], 0);
        System.out.println(res);
    }
}

/*
<반례>

5
15
20
25
40
50
+ 1 2
- 3 8
- 4 5
- 6 7

80
 */
