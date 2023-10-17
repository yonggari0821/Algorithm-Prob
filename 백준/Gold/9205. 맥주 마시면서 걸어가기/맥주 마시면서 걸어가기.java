import java.io.*;
import java.util.*;

// 맨해튼 거리(x 좌표 값의 차이 + y 좌표 값의 차이)가 1000 이하여야 이동 가능!
// 모든 편의점을 들를 필요가 있는 가? => No!
// => 상근이네 집에서 펜타포트 락 페스티벌까지 맥주가 떨어지지 않고(간선의 연결을 유지한 채) 걸어가면 됨!

class Spot {
	int r;
	int c;
	int n;

	public Spot(int r, int c, int n) {
		this.r = r;
		this.c = c;
		this.n = n;
	}

	public Spot(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++)
		{
			int N = Integer.parseInt(br.readLine()); // 편의점 갯수
			int[][] convinienceStores = new int[N + 1][2]; // 편의점 위치
			boolean[] visited = new boolean[N + 1]; // 편의점 방문 체크 용
			int hr = 0, hc = 0, pr = 0, pc = 0;
			for (int i = 0; i < N + 2; i++) {
        		StringTokenizer st = new StringTokenizer(br.readLine());
				if (i == 0) // 상근이네 집
				{
					hc = Integer.parseInt(st.nextToken()); // 상근이네 열
					hr = Integer.parseInt(st.nextToken()); // 상근이네 행
				}
				else if (i < N + 1) // 편의점
				{
					convinienceStores[i][1] = Integer.parseInt(st.nextToken()); // 편의점 열
					convinienceStores[i][0] = Integer.parseInt(st.nextToken()); // 편의점 행
				}
				else // i = N + 1
				{
					pc = Integer.parseInt(st.nextToken()); // 락페 열
					pr = Integer.parseInt(st.nextToken()); // 락페 행
				}
			}
			// 상근이네서 바로 락페까지 갈 수 있는 지 체크
			if (Math.abs(hr - pr) + Math.abs(hc - pc) <= 1000)
			{
				ans.append("happy").append('\n');
				continue;
			}
			String happyOrSad = "sad";
			// 바로 도착 불가능한 경우 => 편의점들 들려가면서 가야 함!
			// 모든 편의점 중 상근이네서 거리가 1000 이하인 점들 큐에 넣기 // with 방문처리
			Queue<Spot> q = new LinkedList<>();
			for (int cvs = 1; cvs <= N; cvs++)
			{
				if (Math.abs(hr - convinienceStores[cvs][0]) + Math.abs(hc - convinienceStores[cvs][1]) <= 1000)
				{
					visited[cvs] = true;
					q.offer(new Spot(convinienceStores[cvs][0], convinienceStores[cvs][1], cvs));
				}
			}
			// 해당 점들로부터 먼저 락페 행과 열까지 도착 가능한 지 체크 후에 다른 편의점들 중 방문하지 않은 편의점으로 이동
			while (!q.isEmpty())
			{
				Spot cur = q.poll();
				if (Math.abs(cur.r - pr) + Math.abs(cur.c - pc) <= 1000)
				{
					happyOrSad = "happy";
					break;
				}
				for (int cvs = 1; cvs <= N; cvs++)
				{
					if (cur.n != cvs && !visited[cvs])
					{
						if (Math.abs(cur.r - convinienceStores[cvs][0]) + Math.abs(cur.c - convinienceStores[cvs][1]) <= 1000)
						{
							visited[cvs] = true;
							q.offer(new Spot(convinienceStores[cvs][0], convinienceStores[cvs][1], cvs));
						}
					}
				}
			}
			ans.append(happyOrSad).append('\n');
		}
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}