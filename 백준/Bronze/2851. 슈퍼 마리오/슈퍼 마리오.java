import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        int[] nums = new int[10];
        nums[0] = Integer.parseInt(br.readLine());
        if (nums[0] == 100) ans.append(nums[0]);
        else
        {
            boolean flag = false;
            for (int i = 1; i <= 9; i++)
            {
                nums[i] = Integer.parseInt(br.readLine()) + nums[i - 1];
                if (nums[i] >= 100 && !flag)
                {
                    flag = true;
                    if (nums[i] - 100 <= 100 - nums[i - 1]) ans.append(nums[i]);
                    else ans.append(nums[i - 1]);
                }
            }
            if (nums[9] <= 100) ans.append(nums[9]);
        }
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}