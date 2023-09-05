import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
		Stack<Integer> stackA = new Stack<>();
		Stack<Integer> stackB = new Stack<>();
		int[] nums = new int[N];
		int[] OBigNum = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
		int index = N - 1;
		while (index >= 0)
		{
			if (stackB.isEmpty()) {
				stackB.add(nums[index]);
				OBigNum[index--] = -1;
			}
			else
			{
				int tmp = nums[index];
				while (!stackB.isEmpty() && tmp >= stackB.peek()) stackB.pop();
				if (!stackB.isEmpty()) OBigNum[index--] = stackB.peek();
				else OBigNum[index--] = -1;
				stackB.add(tmp);
			}
		}
		for (int i = 0; i < N; i++) ans.append(OBigNum[i]).append(" ");
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}