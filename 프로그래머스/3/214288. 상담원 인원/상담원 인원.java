import java.util.*;
/*
멘토 n명
1~k번 상담 유형

1 ≤ k ≤ 5
k ≤ n ≤ 20

멘토는 1개의 상담 유형만 담당
동시에 1명의 참가자와만 상담 가능
상담시간은 참가자 요청 시간 만큼
참가자가 기다린 시간 == 참가자가 요청 했을 때부터 멘토와 상담을 시작할 때까지 걸리는 시간
먼저 상담 요청한 참가자 우선

참가자 상담 요청 정보가 주어질 때,
참가자가 상담을 요청했을 때 부터 상담을 시작하기까지 기다린 시간의 합이 최소가 되어야 함
상담 유형별로 멘토인원은 적어도 한 명 이상이어야 함.

reqs는
a b c일 때,
a분에 b분짜리 c번 유형 상담을 신청했다는 것!

----------------[풀이]----------------
일단, 상담 유형별로 멘토인원이 적어도 한 명 이상이어야 하므로
n-k를 구해서
0이라면 뭐 그대로 그냥 걸리는 시간 구하면 되고
1부터는 reqs에 따라 부족한 곳에 배치하는 게 좋음!

reqs에 따라 부족한 곳에 배치하는 법
배치 했을 때 가장 많은 대기시간이 줄어드는 곳부터 한 곳씩 배치해가면됨

가장 많은 대기시간이 줄어드는 것은 어떻게 아는 가?

총 300개의 요청 가능
최대 1000분에 100분 요청 => 최대 1100분까지 상담 가능
300 x 1100 = 330000 => 배열화 자체는 가능하지만 비효율적!

시작 시간과 종료 시간을 가진 class를 만들어서 우선순위 큐로 관리
이 때 우선순위는 시작 시간 오름차순!

상담사를 배정하려고 할 때
1) 남은 상담사가 있는 경우 => 바로 배정
2) 없는 경우 => 마지막 근무 시간 - 시작 시간만큼 기다리는 시간에 더해주고 배정
배정 시에는 해당 class의 종료시간으로 마지막 근무시간을 맞춰줄 것!



*/

class Request {
    int start;
    int end;
    
    Request(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class Mento {
    int lastWorkTime;
    
    Mento(int lastWorkTime) {
        this.lastWorkTime = lastWorkTime;
    }
}

class Solution {
    
    // n명의 멘토로 해당 타입의 상담 요청의 기다리는 시간 구하는 함수
    static int getWaitTime(List<Request>[] reqByType, int type, int mentoNum)
    {
        Queue<Mento> mentoQ = new PriorityQueue<>((a, b) -> a.lastWorkTime - b.lastWorkTime);
        for (int i = 0; i < mentoNum; i++) mentoQ.offer(new Mento(0));
        
        int waitTime = 0;
        int i = 0;
        int size = reqByType[type].size();
        
        while (i < size) {
            Mento available = mentoQ.poll();
            if (available.lastWorkTime > reqByType[type].get(i).start) {
                waitTime += (available.lastWorkTime - reqByType[type].get(i).start);
                mentoQ.offer(
                    new Mento(
                        available.lastWorkTime 
                        + reqByType[type].get(i).end - reqByType[type].get(i).start
                    )
                );
            }
            else {
                mentoQ.offer(
                    new Mento(
                        reqByType[type].get(i).end
                    )
                );
            }
            i++;
        }
        
        return waitTime;
    }
    
    
    public int solution(int k, int n, int[][] reqs) {
        int answer = 0;
        
        // 요청 번호 별 요청의 시작 및 종료 시간 담아둘 리스트
        List<Request>[] reqByType = new List[n + 1];
        for (int i = 0; i <= n; i++) reqByType[i] = new ArrayList<Request>();
        
        // 담아두기
        for (int r = 0; r < reqs.length; r++) {
            int start = reqs[r][0]; // 시작 시간
            int time = reqs[r][1]; // 상담에 걸리는 시간
            int typeNum = reqs[r][2]; // 상담 유형
            
            reqByType[typeNum].add(new Request(start, start + time));
        }
        
        // 상담 유형 별로 멘토수 배치에 따라 달라지는 기다리는 시간
        // waitTimeByType[상담유형번호][배치된 멘토 수]
        // 무조건 1명씩은 배치 될 것이고
        // 만약 남은 멘토를 한 상담 유형에 몰빵한다 했을 때
        // 해당 상담 유형에 배치되는 멘토수가 최대일 것이고 그만큼까지만 구하면 됨
        // 일단 1명은 무조건 들어가므로 +2 (0, 1)
        // 거기에 n-k개 만큼 더한 값을 더해준 값을 열의 크기로 해주기!
        
        int[][] waitTimeByType = new int[k + 1][n - k + 2];
        int[] mentoNum = new int[k + 1];
        Arrays.fill(mentoNum, 1);
        
        for (int r = 1; r <= k; r++) {
            for (int c = 1; c <= n - k + 1; c++) {
                waitTimeByType[r][c] = getWaitTime(reqByType, r, c);
            }
        }
        
        // System.out.println("----waitTimeByType----");
        // System.out.println(Arrays.deepToString(waitTimeByType));
        int left = n - k;
        
        while (left > 0) {
            int maxGap = 0;
            int maxGapIndex = 0;
            for (int r = 1; r <= k; r++) {
                int gap = waitTimeByType[r][mentoNum[r]] - waitTimeByType[r][mentoNum[r] + 1];
                if (gap > maxGap) {
                    maxGap = gap;
                    maxGapIndex = r;
                }
            }
            mentoNum[maxGapIndex]++;
            left--;
        }
        
        // System.out.println("----mentoNum----");
        // System.out.println(Arrays.toString(mentoNum));

        for (int r = 1; r <= k; r++) answer += waitTimeByType[r][mentoNum[r]];
        
        return answer;
    }
}