import java.io.*;
import java.util.*;

/*
[투 포인터 15565]_귀여운 라이언_안상준
라이언은 1
어피치는 2
라이언은 K개 이상 포함하는 가장 작은 연속된 인형들의 집합의 크기
만약에 없으면 -1

두 포인터로 맨 왼쪽부터 보면서 K개를 만족할 때 까지는 계속 오른쪽 경계를 늘려나가고
만족하면 왼쪽 경계를 줄여가면서 가장 작은 길이가 될 때까지 줄인다.

 */

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        // 둘 다 최소 1에서 1000000
        
        st = new StringTokenizer(br.readLine());
        int[] dolls = new int[N];
        for (int i = 0; i < N; i++) dolls[i] = Integer.parseInt(st.nextToken());

        int min = Integer.MAX_VALUE;
        int cur = dolls[0] == 1 ? 1 : 0;
        int left = 0, right = 0;
        while (left <= right && right < N)
        {
            if (cur < K) {
                right++;
                if (right < N && dolls[right] == 1) cur++;
            }
            else {
                min = Math.min(min, right - left + 1);
                if (dolls[left] == 1) cur--;
                left++;
            }
        }
        if (min == Integer.MAX_VALUE) min = -1;
        ans.append(min);
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}