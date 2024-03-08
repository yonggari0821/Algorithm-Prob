import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, size;
    static long ans = 0;
    static int[] A;
    static int[] B;
    static int[] C;
    static int[] D;
    static int[] AB;
    static int[] CD;
    static void BS(int start, int end)
    {
        while (start < size && end >= 0)
        {
            int v = AB[start] + CD[end];
            if (v == 0)
            {
                int abcnt = 1;
                int cdcnt = 1;
                while (start < size - 1 && AB[start] == AB[start+1])
                {
                    start++;
                    abcnt++;
                }
                while (end > 0 && CD[end] == CD[end-1])
                {
                    end--;
                    cdcnt++;
                }
                ans += ((long)abcnt * (long)cdcnt);
            }
            if (v < 0)
                start++;
            else
                end--;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        size = N * N;
        A = new int[N];
        B = new int[N];
        C = new int[N];
        D = new int[N];
        AB = new int[size];
        CD = new int[size];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                AB[N*i+j] = A[i] + B[j];
                CD[N*i+j] = C[i] + D[j];
            }
        }

        Arrays.sort(AB);
        Arrays.sort(CD);

        BS(0, size - 1);

//        System.out.println(Arrays.toString(AB));
//        System.out.println(Arrays.toString(CD));

        System.out.println(ans);
    }
}
