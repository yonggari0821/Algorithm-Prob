import java.io.*;
import java.math.BigInteger;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        BigInteger[][] pasTri = new BigInteger[N+1][M+1];
        for (int n = 1; n <= N; n++) {
            for (int m = 0; m <= M; m++) { // M까지만해도 답은 구해짐 // 파스칼 트리 완성 시키려면 N까지
                if (m == 0 || n == m || n == 1) pasTri[n][m] = BigInteger.valueOf(1);
                else pasTri[n][m] = pasTri[n-1][m-1].add(pasTri[n-1][m]);
            }
        }
        ans.append(pasTri[N][M]);
//        int len = ans.length();
//        char[] unit = {'만', '억', '조', '경', '해', '자', '양', '구', '간', '정', '재', '극'};
//        int ul = 0;
//        for (int i = 4; i < len; i += 4)
//        {
//            ans.insert(len - i, unit[ul++]);
//        }
        bw.write(ans.toString());
        br.close();
        bw.close();
    }
}

/*
90 C 56의 경우 출력 값이 아래와 같음
7077867820068919738609890
이는 주석 처리한 곳을 주석을 풀고 단위 계산을 해보면 해도 아니고 그 다음인 자 단위의 수임
7자0778해6782경0068조9197억3860만9890
따라서 long으로는 표현 불가 => 더 정확히는 표현은 가능하나 Overflow가 난 이상한 수가 표현 됨!!
따라서 Big Integer를 활용해서 풀어야 함!

파스칼의 삼각형의 경우 설명 주소를 첨부함!
https://blog.naver.com/yonggari0821/223008534008
 */
