package SamsungAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14501 {

    public static int[] Time; // 상담에 걸리는 시간
    public static int[] Pay; // 상담 완료시 받는 금액
    public static int max; // 최대로 받을 수 있는 금액
    public static int N; // 상담 문의 갯수

    public static void DFS(int index, int cnt)
    {
        // 상담 시작 가능 && 상담 완료 가능
        if (index <= N && index + Time[index] <= N + 1)
        {
            DFS(index + Time[index], cnt + Pay[index]); // 보수 받고 다음 상담 가능 여부 체크
            DFS(index + 1, cnt); // 그 다음 상담도 체크
        }

        else // 상담 시작 불가능 or 상담 완료 불가능
        {
            if (index <= N) // 상담 시작은 가능
                DFS(index + 1, cnt); // 그 다음 상담 체크(완료 가능한 상담이 다음 상담 중에도 있을 수 있으므로)
            if (max < cnt) // 더 이상 상담 시작 불가능하면
                max = cnt; // 그동안 받은 보수와 여태까지의 최대 보수를 비교해서 최대 보수 리뉴얼
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        Time = new int[N + 1]; // 편하게 1일차는 1번 인덱스부터 받아서 N일차는 N번 인덱스에 넣기 위해 배열 크기 N + 1로!
        Pay = new int[N + 1];
        max = 0;

        for (int i = 1; i < N + 1; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Time[i] = Integer.parseInt(st.nextToken());
            Pay[i] = Integer.parseInt(st.nextToken());
        }

        DFS(1, 0);

        System.out.println(max);
    }
}
