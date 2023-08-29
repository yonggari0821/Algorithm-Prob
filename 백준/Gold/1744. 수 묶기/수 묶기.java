import java.io.*;
import java.util.*;

public class Main {
    static int isZeroIn(int[] nums)
    {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r)
        {
            int mid = (l + r) / 2;
            if (nums[mid] > 0) r = mid - 1;
            else if (nums[mid] < 0) l = mid + 1;
            else {
                while (mid < nums.length && nums[mid] == 0) mid++;
                mid -= 1;
                return mid;
            }
        }
        return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(br.readLine());
        Arrays.sort(nums);
        int rindex = nums.length - 1;
        if (rindex == 0) ans.append(nums[0]);
        else
        {
            int sum = 0;
            int zeroIndex = isZeroIn(nums);
            if (zeroIndex == -1) // 0이 없는 경우
            {
                int mi = 0;
                while (mi + 1 < nums.length && nums[mi + 1] < 0)
                {
                    sum += (nums[mi] * nums[mi + 1]);
                    mi += 2;
                }
                if ((mi + 1 < nums.length && nums[mi] < 0 && nums[mi + 1] > 0) || (mi == nums.length - 1)) sum += nums[mi];
                int pi = nums.length - 1;
                while (pi - 1 >= 0 && nums[pi - 1] > 1)
                {
                    sum += (nums[pi] * nums[pi - 1]);
                    pi -= 2;
                }
                while (pi - 1 >= 0 && nums[pi - 1] == 1)
                {
                    sum += (nums[pi] + nums[pi - 1]);
                    pi -= 2;
                }
                if ((pi - 1 >= 0 && nums[pi - 1] < 0 && nums[pi] > 0) || (pi == 0)) sum += nums[pi];
            }
            else // 0이 있는 경우
            {
                int mi = 0;
                while (mi + 1 <= zeroIndex)
                {
                    sum += (nums[mi] * nums[mi + 1]);
                    mi += 2;
                }
                int pi = nums.length - 1;
                while (pi - 1 > zeroIndex && nums[pi - 1] > 1)
                {
                    sum += (nums[pi] * nums[pi - 1]);
                    pi -= 2;
                }
                while (pi - 1 > zeroIndex && nums[pi - 1] == 1)
                {
                    sum += (nums[pi] + nums[pi - 1]);
                    pi -= 2;
                }
                if (pi == zeroIndex + 1) sum += nums[pi];
            }
            ans.append(sum);
        }
        bw.write(ans.toString());
        br.close();
        bw.close();
    }
}