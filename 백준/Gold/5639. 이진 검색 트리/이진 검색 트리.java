import java.io.*;
import java.util.StringTokenizer;

class node5639
{
    int value;
    node5639 left, right;

    node5639 (int v)
    {
        this.value = v;
    }

    void insert (int num)
    {
        if (this.value > num)
        {
            if (this.left == null) this.left = new node5639(num);
            else this.left.insert(num);
        }
        else
        {
            if (this.right == null) this.right = new node5639(num);
            else this.right.insert(num);
        }
    }
}
public class Main {
    static StringBuilder ans = new StringBuilder();
    static void postorder(node5639 n)
    {
        if (n.left != null) postorder(n.left);
        if (n.right != null) postorder(n.right);
        ans.append(n.value).append('\n');
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int rootValue = Integer.parseInt(br.readLine());
        node5639 root = new node5639(rootValue);
        String str;

        while(true)
        {
            str = br.readLine();
            if (str == null || str.equals("")) break;
            else
            {
                int tmp = Integer.parseInt(str);
                root.insert(tmp);
            }
        }

        postorder(root);

        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}