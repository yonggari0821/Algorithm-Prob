import java.io.*;
import java.util.*;

class Pipe
{
	int r;
	int c;
	int t;

	public Pipe(int r, int c, int t) {
		this.r = r;
		this.c = c;
		this.t = t;
	}
}

public class Main {
	// 0 => 0, 2
	// 1 => 1, 2
	// 2 => 0, 1, 2
	// 0 가로 1 세로 2 대각선
	static int[] dr = {0, -1, -1};
	static int[] dc = {-1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine()); // 3 이상 16 이하
		int[][] house = new int[N][N]; // 집 상태 입력 받을 배열
		int[][][] dp = new int[N][N][3]; // dp용 배열 // 도착점부터 해당 점까지의 파이프 모양별 가능한 가짓수를 메모
		// dp[0][1][0] + dp[0][1][2] 를 구하면 됨!!
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) house[r][c] = Integer.parseInt(st.nextToken());
		}
		// 도착점부터 가능한 이전 파이프 위치를 파악해가기! => 메모제이션 활용!
		Queue<Pipe> q = new LinkedList<>();
		int endPoint = N - 1;
		// N이 3 이상이므로 endPoint는 2 이상이고 따라서 처음에는 범위를 걱정할 필요는 없고 벽 유무만 파악하면 됨!
		// 마지막 위치가 벽인 경우 결코 파이프를 옮길 수 없으므로 바로 0 출력하고 끝!
		if (house[endPoint][endPoint] == 1) {
			System.out.println(0);
			return ;
		}
		// 가로
		if (house[endPoint][endPoint - 1] == 0) {
			q.offer(new Pipe(endPoint, endPoint - 1, 0));
			dp[endPoint][endPoint - 1][0] = 1;
		}
		// 세로
		if (house[endPoint - 1][endPoint] == 0) {
			q.offer(new Pipe(endPoint - 1, endPoint, 1));
			dp[endPoint - 1][endPoint][1] = 1;
		}
		// 대각선
		if (house[endPoint][endPoint - 1] == 0 && house[endPoint - 1][endPoint] == 0 && house[endPoint - 1][endPoint - 1] == 0) {
			q.offer(new Pipe(endPoint - 1, endPoint - 1, 2));
			dp[endPoint - 1][endPoint - 1][2] = 1;
		}
		while(!q.isEmpty())
		{
			Pipe cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			int t = cur.t;
			if (t <= 1) // 현재 위치 전에 가로 파이프 또는 세로 파이프를 통해서 온 경우
			{
				// 각 방향을 토대로 한 번 더 보기
				int nr = r + dr[t];
				int nc = c + dc[t];
				if (nr < 0 || nc < 1 || house[nr][nc] == 1) continue; // continue 해도 되는 이유는 여기서 걸리면 어차피 대각선도 불가능하므로!
				dp[nr][nc][t] += 1;
				q.offer(new Pipe(nr, nc, t));
				// 대각선도 가능한 지 확인해서 넣어주기
				nr = r - 1;
				nc = c - 1;
				if (nr < 0 || nc < 1 || house[nr][c] == 1 || house[r][nc] == 1 || house[nr][nc] == 1) continue;
				dp[nr][nc][2] += 1;
				q.offer(new Pipe(nr, nc, 2));
			}
			else // 현재 위치 전에 대각선 파이프를 통해서 온 경우
			{
				int nr, nc, cnt = 0;
				// 가로 체크 <0>
				nr = r;
				nc = c - 1;
				if (nc > 0 && house[r][nc] == 0)
				{
					dp[nr][nc][0] += 1;
					q.offer(new Pipe(nr, nc, 0));
					cnt++;
				}
				// 세로 체크 <1>
				nr = r - 1;
				nc = c;
				if (nr >= 0 && house[nr][c] == 0)
				{
					dp[nr][nc][1] += 1;
					q.offer(new Pipe(nr, nc, 1));
					cnt++;
				}
				// 대각선 체크 <2>
				nr = r - 1;
				nc = c - 1;
				// 대각선으로 가려면 가로 세로 모두 체크 => cnt == 2 일때만 진행하면 됨!
				if (cnt == 2 && house[nr][nc] == 0)
				{
					dp[nr][nc][2] += 1;
					q.offer(new Pipe(nr, nc, 2));
				}
			}
		}
		int Answer = dp[0][1][0] + dp[0][1][2];
		ans.append(Answer);
		bw.write(ans.toString());
		br.close();
		bw.close();
	}
}