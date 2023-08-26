import java.io.*;
import java.util.StringTokenizer;
public class Main {
    static int N;
    static int[] ftQueue;
    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        ftQueue = new int[N];
        int frontindex = -1;
        int backindex = -1;
        for (int n = 0; n < N; n++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String tmp = st.nextToken();
            if (tmp.equals("push"))
            {
                int numToPush = Integer.parseInt(st.nextToken());
                ftQueue[++backindex] = numToPush;
                if (frontindex == -1 || frontindex > backindex) frontindex++;
            }
            else {
                if (tmp.equals("pop")) {
                    if (frontindex == -1 || backindex - frontindex < 0) ans.append(-1).append('\n');
                    else {
                        ans.append(ftQueue[frontindex]).append('\n');
                        frontindex++;
                    }
                }
                if (tmp.equals("size"))
                {
                    if (frontindex == -1 || backindex < frontindex) ans.append(0).append('\n');
                    else ans.append(backindex-frontindex+1).append('\n');

                }
                if (tmp.equals("empty"))
                {
                    if (frontindex == -1 || backindex - frontindex < 0)
                        ans.append(1).append('\n');
                    else
                        ans.append(0).append('\n');
                }
                if (tmp.equals("front"))
                {
                    if (frontindex == -1 || backindex - frontindex < 0)
                        ans.append(-1).append('\n');
                    else
                        ans.append(ftQueue[frontindex]).append('\n');
                }
                if (tmp.equals("back"))
                {
                    if (backindex == -1 || backindex - frontindex < 0)
                        ans.append(-1).append('\n');
                    else
                        ans.append(ftQueue[backindex]).append('\n');
                }
            }
        }

        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}
