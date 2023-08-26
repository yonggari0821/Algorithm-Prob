import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N+1];
        int[] moves = new int[N+1];
        int[] nowWhere = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            nowWhere[nums[i]] = i;
        }
        for (int i = 1; i <= N; i++)
        {
//            System.out.println("[i = " + i + "]");
//            System.out.println(Arrays.toString(nowWhere));
//            System.out.println(Arrays.toString(nums));
            moves[nums[i]] += Math.abs(nowWhere[i] - i);
            moves[i] += Math.abs(nowWhere[i] - i);
            nowWhere[nums[i]] = nowWhere[i]; // i번째 수가 있던 곳 = i가 있는 곳
            nums[nowWhere[i]] = nums[i]; // i가 있던 곳의 원래 배열에서의 자리 = 원래 배열에서의 i번째 수
            nums[i] = i; // 원래 배열에서의 i의 자리 = i
            nowWhere[i] = i; // i가 있는 곳 = i
//            System.out.println("---");
//            System.out.println(Arrays.toString(nowWhere));
//            System.out.println(Arrays.toString(nums));
        }
        for (int i = 1; i <= N ; i++) ans.append(moves[i] + " ");
        bw.write(ans.toString());
        br.close();
        bw.close();
    }
}