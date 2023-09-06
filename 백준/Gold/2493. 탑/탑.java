 import java.io.*;
 import java.util.*;

 public class Main {
     public static void main(String[] args) throws IOException {
     	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
     	StringBuilder ans = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] towers = new int[N];
		int[] receivingTowers = new int[N];
		Stack<Integer> stack = new Stack<>();
		Stack<Integer> idxStack = new Stack<>();
		for (int i = 0; i < N; i++)	towers[i] = Integer.parseInt(st.nextToken());
		int idx = N - 1;
		while (idx >= 0)
		{
			int tmp = towers[idx];
			if (!stack.isEmpty() && stack.peek() <= tmp)
			{
				while (!stack.isEmpty() && stack.peek() <= tmp)
				{
					receivingTowers[idxStack.pop()] = idx + 1;
					stack.pop();
				}
			}
			stack.add(tmp);
			idxStack.add(idx);
			idx--;
		}
		for (int i = 0; i < N; i++) ans.append(receivingTowers[i]).append(" ");
     	bw.write(ans.toString());
     	br.close();
     	bw.close();
     }
}