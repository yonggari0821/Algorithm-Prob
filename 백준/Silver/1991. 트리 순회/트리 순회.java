import java.io.*;
import java.util.*;

/*
백준 1991 트리 순회

<풀이>

 */

class Node {
    char cur;
    Node left;
    Node right;

    public Node(char cur, Node left, Node right) {
        this.cur = cur;
        this.left = left;
        this.right = right;
    }
}

public class Main {
    static void preTraverse(Node node) {
        if (node != null) {
            sb.append(node.cur);
            preTraverse(node.left);
            preTraverse(node.right);
        }
    }
    static void midTraverse(Node node) {
        if (node != null) {
            midTraverse(node.left);
            sb.append(node.cur);
            midTraverse(node.right);
        }
    }
    static void postTraverse(Node node) {
        if (node != null) {
            postTraverse(node.left);
            postTraverse(node.right);
            sb.append(node.cur);
        }
    }

    static StringBuilder sb = new StringBuilder();

    public static void main(String[]args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node((char) ('A' + i), null, null);
//            System.out.println("nodes[" + i + "] = " + (char) ('A' + 1 - i));
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char cur = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
//            System.out.println(cur + " / " + left + " / " + right);
            if (left != '.') nodes[cur - 'A'].left = nodes[left - 'A'];
            if (right != '.') nodes[cur - 'A'].right = nodes[right - 'A'];
        }
        preTraverse(nodes[0]);
        sb.append('\n');
        midTraverse(nodes[0]);
        sb.append('\n');
        postTraverse(nodes[0]);
        System.out.println(sb.toString());
    }
}

