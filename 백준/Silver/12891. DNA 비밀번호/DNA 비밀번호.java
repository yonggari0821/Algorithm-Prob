import java.io.*;
import java.util.*;
public class Main {
    static int changeDNA(char dna)
    {
        if (dna == 'A') return 0;
        else if (dna == 'C') return 1;
        else if (dna == 'G') return 2;
        else return 3;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        String str = br.readLine();
        st = new StringTokenizer(br.readLine());
        int[] ACGT = new int[4];
        int[] ACGTNum = new int[4];
        int ans = 0;
        for (int i = 0; i < 4; i++) ACGT[i] = Integer.parseInt(st.nextToken());
        for (int i = 0; i < P; i++) ACGTNum[changeDNA(str.charAt(i))]++;
        for (int i = 0; i < 4; i++) {
            if (ACGTNum[i] < ACGT[i]) break;
            if (i == 3) ans++;
        }
        for (int i = P; i < S; i++) {
            ACGTNum[changeDNA(str.charAt(i))]++;
            ACGTNum[changeDNA(str.charAt(i-P))]--;
            for (int j = 0; j < 4; j++) {
                if (ACGTNum[j] < ACGT[j]) break;
                if (j == 3) ans++;
            }
        }
        System.out.println(ans);
        br.close();
    }
}