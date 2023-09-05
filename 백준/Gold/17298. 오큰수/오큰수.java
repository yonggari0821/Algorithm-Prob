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
		int[] nums = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) stackA.add(Integer.parseInt(st.nextToken()));
		int index = N;
		while (index > 0)
		{
			if (stackB.isEmpty()) {
				stackB.add(stackA.pop());
				nums[index--] = -1;
			}
			else
			{
				int tmp = stackA.pop();
				while (!stackB.isEmpty() && tmp >= stackB.peek()) stackB.pop();
				if (!stackB.isEmpty()) nums[index--] = stackB.peek();
				else nums[index--] = -1;
				stackB.add(tmp);
			}
		}
		for (int i = 1; i <= N; i++) ans.append(nums[i]).append(" ");
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}