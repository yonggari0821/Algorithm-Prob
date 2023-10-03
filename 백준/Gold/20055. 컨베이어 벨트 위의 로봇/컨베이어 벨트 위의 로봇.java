import java.io.*;
import java.util.*;

class Robot {
    int where;

    public Robot() {
        this.where = 0;
    }
}

public class Main {
    static int N, K;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken()); // 컨베이어벨트 1줄 길이
    	K = Integer.parseInt(st.nextToken()); // 내구도가 0인 칸의 갯수가 K개가 되면 종료
        int[] conveyerBelt = new int[2 * N]; // 0번 자리가 로봇을 올리는 위치 // n - 1번 자리가 로봇을 내리는 위치
        boolean[] isRobotOn = new boolean[2 * N]; // 0번 자리가 로봇을 올리는 위치 // n - 1번 자리가 로봇을 내리는 위치
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2*N; i++) conveyerBelt[i] = Integer.parseInt(st.nextToken());
        int turn = 0;
        int zeroDurabilityCnt = 0; // till K
        Queue<Robot> q = new LinkedList<>();
        loop :
        while (true)
        {
            turn++;
            // 1번 과정
            // 로봇과 컨베이어 벨트 둘 다 이동!
            int tmpDurability = conveyerBelt[2*N - 1];
            boolean tmpisRobotOn = isRobotOn[2*N - 1];

            for (int i = 2*N - 1; i > 0; i--) {
                conveyerBelt[i] = conveyerBelt[i - 1];
                // 로봇 유무는 도착지점이냐 아니냐에 따라 구분해서!
                if (i != N - 1) isRobotOn[i] = isRobotOn[i - 1];
                else isRobotOn[i] = false;
            }

            conveyerBelt[0] = tmpDurability;
            isRobotOn[0] = tmpisRobotOn;

           // 로봇도 같이 옮겨져야 함! // 이 때는 그냥 컨베이어 벨트 위에서 같이 움직이는 것이기 때문에 내구도 감소는 X
            int size = q.size();
            while (size > 0)
            {
                Robot cur = q.poll();
                if (cur.where + 1 != N - 1) // 도착지점이 아닌 경우만 다시 넣어주고 도착지점이면 빼줄 것!
                {
                    cur.where += 1;
                    q.offer(cur);
                }
                size--;
            }

            // 2번 과정
            // 로봇만 이동 => 이동하려는 칸에 로봇이 없고, 내구도가 0보다 클 때만 이동하며, 만약 도착지점인 경우 다시 넣어주지 않음!
            size = q.size();
            while (size > 0)
            {
                Robot cur = q.poll();
                int toMove = cur.where + 1;
                if (!isRobotOn[toMove] && conveyerBelt[toMove] > 0)
                {
                    isRobotOn[cur.where] = false;
                    conveyerBelt[toMove]--;
                    if (conveyerBelt[toMove] == 0) zeroDurabilityCnt++;
                    if (zeroDurabilityCnt >= K) break loop;
                    cur.where = toMove;
                    if (cur.where == N - 1)
                    {
                        size--;
                        continue;
                    }
                    isRobotOn[cur.where] = true;
                }
                q.offer(cur);
                size--;
            }
            // 3번과정
            // 시작점의 내구도가 0 보다 크면 로봇 하나 올려주면서 시작점 내구도 1 깎기
            if (conveyerBelt[0] > 0)
            {
                isRobotOn[0] = true;
                q.offer(new Robot());
                conveyerBelt[0]--;
                if (conveyerBelt[0] == 0) zeroDurabilityCnt++;
                if (zeroDurabilityCnt >= K) break loop;
            }
        }
        ans.append(turn);
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}