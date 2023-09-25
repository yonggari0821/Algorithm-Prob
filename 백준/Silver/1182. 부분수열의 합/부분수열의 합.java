import java.io.*;
import java.util.*;

public class Main {
    static int N, S, cnt;
    static int[] nums;
    static void dfs (int depth, int used)
    {
        // 탈출 조건
        if (depth == N)
        {
            int sum = 0;
            boolean flag = false;
            for (int i = 0; i < N; i++) {
                if ((used & (1 << i)) != 0) {
                    sum += nums[i];
                    flag = true;
                }
            }
            if (flag && sum == S) cnt++;
            return ;
        }
        // 진행 부분
        /*
        부분 수열 구현 하는 방법
        모든 원소들을 고르거나 안고르거나로 구분해서 재귀 진행 => 재귀 진행용 깊이 표현용 변수 필요 => 이 때 최종 탈출 깊이는 원소들 총 갯수
        고르든 안고르든 재귀는 진행되어야 하고 아무것도 안 고른 경우만 제외하면 되므로, 해당 경우만 예외 처리 해줄 것!
         */
        dfs(depth + 1, used);
        dfs(depth + 1, (used | (1 << depth)));
    }

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
    	StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
        // 부분 수열은 주어지는 배열에서 연속되지 않은 경우도 고려해줘야 함!! => 슬라이딩 윈도우식 풀이가 불가능!!
        // 따라서 모든 부분 수열들을 따져서 그 중에 S인 것을 찾아내야 함! => 브루트포스
        cnt = 0; // 가짓 수
        dfs(0, 0);
        ans.append(cnt);
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}