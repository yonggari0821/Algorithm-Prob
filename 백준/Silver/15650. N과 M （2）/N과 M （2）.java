import java.io.*;
import java.util.*;

public class Main {

    static void nmFunc(int numAsBool, int lastNum)
    {
        if (Integer.bitCount(numAsBool) >= M)
        {
            for (int i = 1; i <= N; i++)
            {
                if ((numAsBool & (1 << i)) != 0) sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = lastNum; i <= N; i++) {
            nmFunc(numAsBool | (1 << i), i + 1);
        }
    }

    
    static int N, M;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nmFunc(0, 1);
        System.out.println(sb.toString());
    }
}