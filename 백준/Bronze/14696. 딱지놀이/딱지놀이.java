import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for (int round = 1; round <= N; round++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int aCardNum = Integer.parseInt(st.nextToken());
            int[] ABD = new int[5];
            for (int i = 1; i <= aCardNum ; i++) ABD[Integer.parseInt(st.nextToken())]++;
            st = new StringTokenizer(br.readLine());
            int bCardNum = Integer.parseInt(st.nextToken());
            for (int i = 1; i <= bCardNum ; i++) ABD[Integer.parseInt(st.nextToken())]--;
            for (int i = 4; i > 0; i--)
            {
                if (ABD[i] > 0)
                {
                    ans.append('A');
                    break ;
                }
                else if (ABD[i] < 0) {
                    ans.append('B');
                    break;
                }
                if (i == 1) ans.append('D');
            }
            ans.append('\n');
        }
        bw.write(ans.toString());
        br.close();
        bw.close();
    }
}

/*
만약 두 딱지의 별의 개수가 다르다면, 별이 많은 쪽의 딱지가 이긴다.
별의 개수가 같고 동그라미의 개수가 다르다면, 동그라미가 많은 쪽의 딱지가 이긴다.
별, 동그라미의 개수가 각각 같고 네모의 개수가 다르다면, 네모가 많은 쪽의 딱지가 이긴다.
별, 동그라미, 네모의 개수가 각각 같고 세모의 개수가 다르다면, 세모가 많은 쪽의 딱지가 이긴다.
별, 동그라미, 네모, 세모의 개수가 각각 모두 같다면 무승부이다.
 */