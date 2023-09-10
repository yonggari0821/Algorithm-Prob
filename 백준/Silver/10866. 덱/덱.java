import java.io.*;
import java.util.*;

class Node {
    int val;
    Node left;
    Node right;

    public Node() {};
    public Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    };
}

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
        Node root = new Node(0, null, null);
        Node curFront = root;
        Node curBack = root;
        int size = 0;
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
    	StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if (command.equals("push_front"))
            {
                int num = Integer.parseInt(st.nextToken());
                Node tmp = new Node(num, null, curFront);
                curFront.left = tmp;
                if (curFront == curBack && curFront == root)
                {
                	curBack = tmp;
                }
                curFront = tmp;
                size++;
            }
            else if (command.equals("push_back"))
            {
                int num = Integer.parseInt(st.nextToken());
                Node tmp = new Node(num, curBack, null);
                curBack.right = tmp;
                if (curBack == curFront && curBack == root)
                {
                	curFront = tmp;
                }
                curBack = tmp;
                size++;
            }
            else if (command.equals("pop_front"))
            {
                if (size == 0) {
                    ans.append(-1).append('\n');
                    continue;
                }
                else ans.append(curFront.val).append('\n');
                if (curFront == curBack)
                {
                	curFront = root;
                	curBack = root;
                }
                else 
                {
                	curFront.right.left = null;
                	curFront = curFront.right;
                }
                size--;
            }
            else if (command.equals("pop_back"))
            {
                if (size == 0) {
                    ans.append(-1).append('\n');
                    continue;
                }
                else ans.append(curBack.val).append('\n');
                if (curFront == curBack)
                {
                	curFront = root;
                	curBack = root;
                }
                else
                {	
                	curBack.left.right = null;
                	curBack = curBack.left;
                }
                size--;
            }
            else if (command.equals("size"))
            {
                ans.append(size).append('\n');
            }
            else if (command.equals("empty"))
            {
                if (size == 0) ans.append(1).append('\n');
                else ans.append(0).append('\n');
            }
            else if (command.equals("front"))
            {
                if (size == 0) ans.append(-1).append('\n');
                else ans.append(curFront.val).append('\n');
            }
            else if (command.equals("back"))
            {
                if (size == 0) ans.append(-1).append('\n');
                else ans.append(curBack.val).append('\n');
            }
        }
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}