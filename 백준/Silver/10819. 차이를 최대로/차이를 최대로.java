import java.io.*;
import java.util.*;

public class Main {
    static int N, max = 0;
    static void getMax(int[] nums, int[] picked, boolean[] checked, int pickNum)
    {
        if (pickNum == N)
        {
            int tmp = 0;
            for (int i = 1; i < N; i++) tmp += Math.abs(picked[i] - picked[i-1]);
            if (tmp > max) max = tmp;
            return ;
        }
        for (int i = 0; i < N; i++)
        {
            if (pickNum == 0 && i == N - 1) continue;
            if (pickNum == N - 1 && picked[0] > nums[i]) continue;
            if (checked[i]) continue;
            picked[pickNum] = nums[i];
            checked[i] = true;
            getMax(nums, picked, checked, pickNum + 1);
            picked[pickNum] = 0;
            checked[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        N = Integer.parseInt(br.readLine()); // 숫자 갯수
        int[] nums = new int[N]; // 숫자들 배열
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken()); // 숫자들 받아두기
        Arrays.sort(nums); // 정렬 => Q) 갑자기 정렬을 왜해요?
        // A) 이 문제는 백트래킹으로 최적화하는 것이 필요! 단순히 탐색 시 최대 8! * 7! * 7의 연산이 필요하므로 문제가 생김
//        System.out.println(Arrays.toString(nums));
        getMax(nums, new int[N], new boolean[N], 0);
        ans.append(max);
        bw.write(ans.toString());
        br.close();
        bw.close();
    }
}