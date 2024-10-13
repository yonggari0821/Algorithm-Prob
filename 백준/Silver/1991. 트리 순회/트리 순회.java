import java.io.*;
import java.util.*;

/*

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
    static int N, M;
    static Node[] tree;

    static void preTraverse(Node node, StringBuilder sbInFunc) {
        if (node != null)
        {
            sbInFunc.append(node.cur);
            preTraverse(node.left, sbInFunc);
            preTraverse(node.right, sbInFunc);
        }
    }

    static void midTraverse(Node node, StringBuilder sbInFunc) {
        if (node != null)
        {
            midTraverse(node.left, sbInFunc);
            sbInFunc.append(node.cur);
            midTraverse(node.right, sbInFunc);
        }
    }

    static void postTraverse(Node node, StringBuilder sbInFunc) {
        if (node != null)
        {
            postTraverse(node.left, sbInFunc);
            postTraverse(node.right, sbInFunc);
            sbInFunc.append(node.cur);
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new Node[N + 1];
        for (int i = 0; i < N; i++) tree[i + 1] = new Node((char) ('A' + i), null, null);
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char cur = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            if (left != '.') tree[cur - 'A' + 1].left = tree[left - 'A' + 1];
            if (right != '.') tree[cur - 'A' + 1].right = tree[right - 'A' + 1];
        }
        preTraverse(tree[1], sb);
        sb.append('\n');
        midTraverse(tree[1], sb);
        sb.append('\n');
        postTraverse(tree[1], sb);
        System.out.println(sb.toString());
    }
}

/*
[1]

 */