import java.io.*;
import java.util.*;

/*
백준 17501 수식트리

<풀이>
기본적으로 후위순회의 개념을 적용
이 때, 수들의 자리를 바꿀 수 있으므로 기존대로 배정하고 바꾸기 보다
수들을 따로 빼서 정렬해두고
실제로 순회를 진행하면서 하나씩 배정하는 게 좋음

중요한 문제는
"어떻게 배정할 것인가?"
인데,

- 인 경우와
+ 인 경우로 나눠놓고 보면
- 인 경우에는 왼쪽은 크게 오른쪽은 작게 되어야 하고
+ 인 경우에는 어느쪽이든 커야 함.
따라서, 루트노드부터 현재 노드까지의 거쳐간 - 연산자의 갯수에 따라서 오른쪽 자식의 값을 다르게 배정해야 함.

cf) Node class에 따로 연산자 항목을 추가하는 대신에 문제 조건을 참고해 + / - 를 정수 최대 / 최소값으로 갈음함.
cf) 수를 배정하기 전 피연산자를 표현하기 위해 마찬가지로 정수 최소값 + 1 로 대신함.
*/

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
