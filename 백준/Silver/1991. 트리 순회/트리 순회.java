import java.io.*;
import java.util.StringTokenizer;

class node
{
    char me;
    char left;
    char right;

    public node(char m, char l, char r)
    {
        this.me = m;
        this.left = l;
        this.right = r;
    }
}
public class Main {
    static int N;
    static node[] tree;
    static StringBuilder ans;
    static void preorder(node now)
    {
        ans.append(now.me);
        if (now.left != '.')
            preorder(tree[now.left-65]);
        if (now.right != '.')
            preorder(tree[now.right-65]);
    }
    static void midorder(node now)
    {
        if (now.left != '.')
            midorder(tree[now.left-65]);
        ans.append(now.me);
        if (now.right != '.')
            midorder(tree[now.right-65]);
    }
    static void postorder(node now)
    {
        if (now.left != '.')
            postorder(tree[now.left-65]);
        if (now.right != '.')
            postorder(tree[now.right-65]);
        ans.append(now.me);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ans = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        tree = new node[N];
        for (int n = 0; n < N; n++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char tm = st.nextToken().charAt(0);
            char tl = st.nextToken().charAt(0);
            char tr = st.nextToken().charAt(0);
            node tmp = new node(tm, tl, tr);
            tree[tm-65] = tmp;
        }

        preorder(tree[0]);
        ans.append('\n');
        midorder(tree[0]);
        ans.append('\n');
        postorder(tree[0]);

        bw.write(ans.toString());

        br.close();
        bw.flush();
        bw.close();
    }
}