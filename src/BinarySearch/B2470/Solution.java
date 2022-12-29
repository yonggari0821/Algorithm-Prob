package BinarySearch.B2470;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    public static void BS(int[] solution)
    {
        // solution[start] = 더해질 작은 값 // solution[end] = 더해질 큰 값
        // sum = solution[start] + solution[end];
        // 경우 1) sum> 0 // 경우 2) sum <= 0
        // if 경우 1) end 가 더 작아져야함
        // else 경우 2) start가 더 커져야 함

        int start = 0; // 시작 인덱스
        int end = solution.length - 1; // 끝 인덱스
        int min = Integer.MAX_VALUE; // 디폴트 값은 int형 범위 최댓값으로!
        int minstart = solution[start]; // 차이가 min일때의 더해질 작은 값
        int minend = solution[end]; // 차이가 min일때의 더해질 큰 값

        while (start < end) // 서로 다른 두 수 이어야 하기 때문에 인덱스가 같을 수는 없음!
        {
            int sum = solution[start] + solution[end];

            if (sum == 0) // 더해서 0 이면 특성값이 더 작아질 수는 없으므로 바로 출력!
            {
                System.out.println(solution[start] + " " + solution[end]);
                return ;
            }

            if (Math.abs(sum) < min) // 더한 값이 여태까지 나온 최소값보다 잡다면
            {
                min = Math.abs(sum); // 최소값 리뉴얼
                minstart = solution[start]; // 차이가 min일때의 더해질 작은 값 리뉴얼
                minend = solution[end]; // 차이가 min일때의 더해질 큰 값 리뉴얼
            }

            if (sum > 0) // 경우 1
                end--;
            else // 경우 2
                start++;
        }

        System.out.println(minstart + " " + minend);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] sol = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            sol[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(sol);

        BS(sol);

        br.close();
    }
}
