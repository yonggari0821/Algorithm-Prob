import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        int[] dwarfs = new int[9];
        int sum = 0;
        for (int i = 0; i < 9; i++) sum += (dwarfs[i] = Integer.parseInt(br.readLine()));
        Arrays.sort(dwarfs);
        loop :
        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (sum - dwarfs[i] - dwarfs[j] == 100)
                {
                    for (int k = 0; k < 9; k++) {
                        if (k == i || k == j) continue;
                        ans.append(dwarfs[k]).append('\n');
                    }
                    break loop;
                }
            }
        }
        bw.write(ans.toString());
        br.close();
        bw.close();
    }
}