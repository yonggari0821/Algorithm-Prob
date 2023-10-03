import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int R, C, T, ans;
    public static int[][] room;
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()); // 방의 행 길이
        C = Integer.parseInt(st.nextToken()); // 방의 열 길이
        T = Integer.parseInt(st.nextToken()); // 경과할 시간
        room = new int[R+1][C+1]; // 방 배열
        boolean upOrDown = false; // 위 아래 공기청정기 판단용 => 처음에 false인 상태에서 -1을 만나면 위 공기청정기 => true로 바꿔주고 다시 -1을 만나면 아래 공기청정기
        int upperAirConditionerRow = 0; // 위 공기청정기의 행 (열은 어차피 1로 고정)
        int downAirConditionerRow = 0; // 아래 공기청정기의 행 (마찬가지로 열은 어차피 1로 고정)

        // (1, 1)부터 (R, C)까지 맵에 미세먼지 상태 받기
        for (int r = 1; r <= R; r++)
        {
            StringTokenizer sta = new StringTokenizer(br.readLine());
            for (int c = 1; c<= C; c++)
            {
                room[r][c] = Integer.parseInt(sta.nextToken());
                // 공기청정기를 만나면 위/아래 공기청정기 행 알맞게 저장해두기
                if (room[r][c] == -1)
                {
                    if (upOrDown == false)
                    {
                        upOrDown = true;
                        upperAirConditionerRow = r;
                    }
                    else
                        downAirConditionerRow = r;
                }
            }
        }

        int t = 0; // 시간
        int[][] diffusion; // 미세먼지 확산용 배열
        while (t < T)
        {
            // 미세 먼지 확산(퍼뜨리기) 단계
            diffusion = new int[R+1][C+1]; // 확산용 배열 새로 만들기
            for (int r = 1; r <= R; r++)
            {
                for (int c = 1; c<= C; c++)
                {
                    // 퍼져야 할 미세먼지가 있다면
                    if (room[r][c] > 0)
                    {
                        int cnt = 0; // 퍼질 수 있는 방향의 수
                        int diff = room[r][c] / 5; // 각 방향으로 퍼뜨릴 미세먼지 양
                        for (int i = 0; i < 4; i++)
                        {
                            int nr = r + dr[i];
                            int nc = c + dc[i];
                            // 범위 밖
                            if (nr < 1 || nc < 1 || nr > R || nc > C) continue;
                            // 공기청정기 자리
                            if (nc == 1 && (nr == upperAirConditionerRow || nr == downAirConditionerRow)) continue;
                            // 두 가지 예외에 걸리지 않으면 퍼뜨리고 퍼뜨린 수 세놓기
                            diffusion[nr][nc] += diff;
                            cnt++;
                        }
                        // 퍼뜨린 수 * 퍼뜨린 양을 원래 배열에서 빼주기
                        diffusion[r][c] -= (cnt * diff);
                    }
                }
            }

            // 확산 값 반영
            for (int r = 1; r <= R; r++)
            {
                for (int c = 1; c <= C; c++)
                {
                    // 확산된 값이 있는 경우에만 반영해주면 됨!
                    if (diffusion[r][c] != 0) room[r][c] += diffusion[r][c];
                }
            }

            // 미세먼지 정화 단계 // 두 공기청정기가 정화하는 부분이 전혀 겹치지 않으므로 어떤 공기청정기부터 해도 무방!
            // 1열에서 위 공기청정기 1칸 위 위치부터 한 칸씩 당겨오는 느낌으로 구현 // 왜? 어차피 공기청정기로 들어오는 미세먼지는 삭제될 것이기 때문에
            for (int r = upperAirConditionerRow - 1; r > 1; r--) room[r][1] = room[r-1][1];
            // 이번에는 1번째 행 오른쪽에서 한 칸씩 당겨오기
            for (int c = 1; c < C; c++) room[1][c] = room[1][c+1];
            // 그 다음에는 맨 오른쪽 열 아래서 한 칸씩 당겨오기
            for (int r = 1; r < upperAirConditionerRow; r++) room[r][C] = room[r+1][C];
            // 마지막으로 위 공기청정기가 위치한 행 왼쪽에서 한 칸씩 당겨오기 // 왜 c > 2 까지만 하나요? // 공기청정기에선 어차피 정화된 공기가 나오므로
            for (int c = C; c > 2; c--) room[upperAirConditionerRow][c] = room[upperAirConditionerRow][c-1];
            room[upperAirConditionerRow][2] = 0;
            
            // 같은 방식으로 범위와 인덱스에 유의하면 아래쪽 공기 청정기도 공기정화
            for (int r = downAirConditionerRow + 1; r < R; r++) room[r][1] = room[r+1][1];
            for (int c = 1; c < C; c++) room[R][c] = room[R][c+1];
            for (int r = R; r > downAirConditionerRow; r--) room[r][C] = room[r-1][C];
            for (int c = C; c > 2; c--) room[downAirConditionerRow][c] = room[downAirConditionerRow][c-1];
            room[downAirConditionerRow][2] = 0;
            
            t++; // 시간 경과 // 중간에 끝나는 경우는 없으므로 그냥 마지막에 더해줘도 무방!
        }

        for (int r = 1; r <= R; r++)
        {
            for (int c = 1; c <= C; c++)
            {
                if (room[r][c] > 0) ans += room[r][c]; // 남은 미세먼지들의 합 구하기
            }
        }
        System.out.println(ans);
    }
}