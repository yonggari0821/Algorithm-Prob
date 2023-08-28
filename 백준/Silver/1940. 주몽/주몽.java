import java.io.*;
import java.util.*;

public class Main {
    static int cnt;
    static void binarySearch(int[] nums, int l, int r, int target)
    {
        while (l < r)
        {
            int sum = nums[l] + nums[r];
            if (sum > target) r--;
            else if (sum < target) l++;
            else
            {
                cnt++;
                l++;
                r--;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        cnt = 0;
        int N = Integer.parseInt(br.readLine());
        int[] ingredients = new int[N];
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) ingredients[i] = Integer.parseInt(st.nextToken());
        if (M > 200000) ans.append(0);
        else
        {
            Arrays.sort(ingredients);
            binarySearch(ingredients, 0, ingredients.length - 1, M);
            ans.append(cnt);
        }
        bw.write(ans.toString());
        br.close();
        bw.close();
    }
}