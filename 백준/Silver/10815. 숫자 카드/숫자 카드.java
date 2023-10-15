import java.io.*;
import java.util.*;

public class Main {
    static int checkBS(int[] nums, int target)
    {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right)
        {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) right = mid - 1;
            else if (nums[mid] == target) return 1;
            else left = mid + 1;
        }
        return 0;
    }

    public static void main(String[] args) throws IOException { 
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
    	int N = Integer.parseInt(br.readLine());
        int[] numCards = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) numCards[n] = Integer.parseInt(st.nextToken());
        Arrays.sort(numCards);
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int m = 0; m < M; m++)
        {
            int tmp = Integer.parseInt(st.nextToken());
            ans.append(checkBS(numCards, tmp)).append(" ");
        }
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}