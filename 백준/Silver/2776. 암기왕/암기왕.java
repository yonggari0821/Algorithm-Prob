import java.io.*;
import java.util.*;

public class Main {
	static boolean bs (int[] nums, int target)
	{
		int l = 0;
		int r = nums.length - 1;
		while (l <= r)
		{
			int m = (l+r) / 2;
			if (nums[m] < target) l = m + 1;
			else if (nums[m] > target) r = m - 1;
			else return true;
		}
		return false;
	}

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++)
        {
			// 노트 1
			int N = Integer.parseInt(br.readLine());
			int[] note1 = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) note1[n] = Integer.parseInt(st.nextToken());
			Arrays.sort(note1); // 정렬까지 해둘 것! (이분탐색으로 찾을 것이기 때문에)
			
			// 노트 2
			int M = Integer.parseInt(br.readLine());
			int[] note2 = new int[M];
			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < M; n++) {
				int tmp = Integer.parseInt(st.nextToken());
				note2[n] = bs(note1, tmp) ? 1 : 0; // 노트 1에 있으면 1 // 없으면 0 
				ans.append(note2[n]).append('\n');
			}
        }
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}