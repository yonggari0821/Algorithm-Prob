import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
    	int T = Integer.parseInt(br.readLine());
    	for (int t = 1; t <= T; t++)
    	{
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st= new StringTokenizer(br.readLine());
            long sum = 1;
            PriorityQueue<Long> pq = new PriorityQueue<>();
            PriorityQueue<Long> pqOver = new PriorityQueue<>();
            for (int i = 0; i < N; i++) {
                long tmp = Long.parseLong(st.nextToken());
                pq.offer(tmp);
            }
            while (pq.size() > 1)
            {
                long a = pq.poll();
                long b = pq.poll();
                long tmp = (a * b) ; // 얘는 long 범위 벗어나지 않음이 보장됨
                sum = (sum * (tmp % 1000000007) % 1000000007);
                // sum 은 최대 1000000007이고 tmp는 최대 2*10의 18승임 => 따라서  곱했을 때 오버플로우가 가능!
                pq.offer(tmp);
            }
            ans.append(sum);
    		ans.append('\n');
    	}
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}