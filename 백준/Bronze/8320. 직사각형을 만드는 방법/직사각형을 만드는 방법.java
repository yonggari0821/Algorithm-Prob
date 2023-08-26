import java.io.*;
import java.util.*;
public class Main {
    static final int nMax = 10000;
    static int[] powerNum = new int[(int)Math.sqrt(nMax) + 1];
    static {
        for (int i = 1; i <= (int)Math.sqrt(nMax); i++) powerNum[i] = i * i;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int maxEdge = 1;
        for (int i = (int)Math.sqrt(nMax); i >= 1; i--) {
            if (powerNum[i] <= n) {
                maxEdge = i;
                break;
            }
        }
        int rectangleNum = 0;
        for (int i = 1; i <= maxEdge; i++) rectangleNum += (n / i  - (i-1));
        ans.append(rectangleNum);
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}