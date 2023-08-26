import java.io.*;
import java.util.StringTokenizer;


public class Main {
    static int N, S;
    static int[] ftStack;
    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        ftStack = new int[N];
        int index = -1;
        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String tmp = st.nextToken();
            if (tmp.equals("push"))
            {
                int numToPush = Integer.parseInt(st.nextToken());
                ftStack[++index] = numToPush;
            }
            else
            {
                if (tmp.equals("pop"))
                {
                    if (index == -1) ans.append(-1).append('\n');
                    else
                    {
                        ans.append(ftStack[index]).append('\n');
                        ftStack[index--] = 0;
                    }
                }
                if (tmp.equals("size"))
                    ans.append(index+1).append('\n');
                if (tmp.equals("empty"))
                {
                    if (index == -1)
                        ans.append(1).append('\n');
                    else
                        ans.append(0).append('\n');
                }
                if (tmp.equals("top"))
                {
                    if (index == -1)
                        ans.append(-1).append('\n');
                    else
                        ans.append(ftStack[index]).append('\n');
                }
            }
        }
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}
