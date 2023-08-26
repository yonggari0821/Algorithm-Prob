import java.io.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] nums;
    static int[] LIS;
    static int[] LISmin;
    static int[] whereLISmin;
    static int[] Tracing;

    static int BS(int start, int end, int target) // target = nums[i]
    {
        while (start <= end)
        {
            int mid = start + (end - start) / 2;

            if (target <= LISmin[mid]) end = mid - 1;
            else start = mid + 1;
        }
        return end;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        nums = new int[N+1]; // 입력 받은 숫자 배열
        LIS = new int[N+1]; // 자기까지 가능한 LIS 길이 배열
        LISmin = new int[N+1]; // LIS 판단 위한 각 길이별 최솟값 배열
        whereLISmin = new int[N+1]; // 해당 최솟값이 nums 배열에서 어디 있는 지 배열 => Tracing 배열 때문에 쓰임
        Tracing = new int[N+1]; //
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++)
        {
            nums[i] = Integer.parseInt(st.nextToken());
            LISmin[i] = Integer.MAX_VALUE;
        }

        int maxLIS = 1; // 지금까지 나온 최대 LIS 길이
        int maxindex = 1; // 최대 LIS 길이를 갖는 수의 위치
        for (int i = 1; i <= N; i++)
        {
            if (nums[i] < LISmin[maxLIS])
            {
//                while (tmpmaxLISminindex > 0 && nums[i] <= LISmin[tmpmaxLISminindex])
//                    tmpmaxLISminindex--;
//                => 이렇게 하면 시간초과남 => 100만개 이므로!! => 이분탐색이 필요!

                int tmpmaxLISminindex = BS(1, maxLIS, nums[i]);
                if (tmpmaxLISminindex == 0)
                {
                    LISmin[1] = nums[i];
                    whereLISmin[1] = i;
                }
                else
                {
                    LISmin[tmpmaxLISminindex + 1] = nums[i];
                    whereLISmin[tmpmaxLISminindex + 1] = i;
                }
                LIS[i] = tmpmaxLISminindex + 1;
                Tracing[i] = whereLISmin[tmpmaxLISminindex];
            }
            else if (nums[i] > LISmin[maxLIS])
            {
                maxindex = i;
                Tracing[i] = whereLISmin[maxLIS];
                maxLIS++;
                LISmin[maxLIS] = nums[i];
                whereLISmin[maxLIS] = i;
                LIS[i] = maxLIS;
            }

        }
//        System.out.println(maxindex);
//        System.out.println(Arrays.toString(nums));
//        System.out.println(Arrays.toString(LIS));
//        System.out.println(Arrays.toString(LISmin));
//        System.out.println(Arrays.toString(whereLISmin));
//        System.out.println(Arrays.toString(Tracing));
//        System.out.println("-----");

        Stack<Integer> Output = new Stack<>();
        for (int i = maxLIS-1; i >= 0; i--)
        {
            Output.push(nums[maxindex]);
            maxindex = Tracing[maxindex];
        }
        StringBuilder sb = new StringBuilder();
        sb.append(maxLIS).append('\n');
        for (int i = 0; i < maxLIS; i++)
            sb.append(Output.pop()).append(" ");
        System.out.println(sb.toString());
        br.close();
    }
}