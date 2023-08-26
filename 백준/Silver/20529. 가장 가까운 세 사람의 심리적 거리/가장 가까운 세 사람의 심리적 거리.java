import java.io.*;
import java.util.*;
public class Main {
    static StringBuilder ans = new StringBuilder();
    static int T;

    static int changeDistToPoint(int mbti1, int mbti2)
    {
        int EI;
        int NS;
        int FT;
        int JP;

        if ((mbti1 & 8) == (mbti2 & 8)) EI = 0;
        else EI = 1;
        if ((mbti1 & 4) == (mbti2 & 4)) NS = 0;
        else NS = 1;
        if ((mbti1 & 2) == (mbti2 & 2)) FT = 0;
        else FT = 1;
        if ((mbti1 & 1) == (mbti2 & 1)) JP = 0;
        else JP = 1;
        return (EI + NS + FT + JP);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine()); // 1 ~ 50
        for (int t = 0; t < T; t++)
        {
            int N = Integer.parseInt(br.readLine());
            int[] howManyMbti = new int[16]; // 1112
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int n = 0; n < N; n++) {
                char[] mbti = st.nextToken().toCharArray();
                int mbtiVal = 0;
                if (mbti[0] == 'E') mbtiVal += 8; // 1000
                if (mbti[1] == 'N') mbtiVal += 4; // 100
                if (mbti[2] == 'F') mbtiVal += 2; // 10
                if (mbti[3] == 'J') mbtiVal += 1; // 1
                howManyMbti[mbtiVal]++;
            }
            // 1차 필터링
            if (N > 32) // 비둘기집의 원리
            {
                ans.append("0\n");
                continue;
            }
            // 2차 필터링
            int[] mbtis = new int[N];
            int index = 0;
            boolean flag = false;
            for (int i = 0; i < 16; i++)
            {
                if (howManyMbti[i] >= 3)
                {
                    ans.append("0\n");
                    flag = true;
                    break;
                }
                while (howManyMbti[i] > 0)
                {
                    mbtis[index++] = i;
                    howManyMbti[i]--;
                }
            }
            if (flag) continue;
//            Arrays.sort(mbtis);
//            System.out.println(Arrays.toString(mbtis));
            int res = 8; // (비둘기집 원래 때문에 최대 8임!)
            for (int n1 = 0; n1 < N; n1++)
            {
                for (int n2 = n1 + 1; n2 < N; n2++)
                {
                    for (int n3 = n2 + 1; n3 < N; n3++)
                    {
                        int distOfMind = changeDistToPoint(mbtis[n1], mbtis[n2]) + changeDistToPoint(mbtis[n1], mbtis[n3]) + changeDistToPoint(mbtis[n2], mbtis[n3]);
                        res = res > distOfMind ? distOfMind : res;
                    }
                }
            }
            ans.append("" + res + "\n");
        }
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}