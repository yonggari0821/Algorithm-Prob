import java.util.*;

/*
    일렬로 나열된 n개 집에 택배 배달
    cap 만큼 물건 실음
    배달 / 수거 다 하면서 최소 이동거리
    각 집 들를 때 마다 원하는 만큼 배달 및 수거 가능
    배달한 걸 수거해오는 게 아니라 배달과 수거는 무관
    그냥 한 번에 들고 있을 수 있는 상자 수만 cap 이하임을 보증하면 됨
    
    가장 먼 곳부터 배달
    배달 및 수거위해서 어쨌든 책임질 양이 남아있는 곳 까지는 가야 함
    이 때, 배달을 위해서 가든 수거를 위해서 가든 가장 먼 곳부터 간다면 가장 효율적으로 업무가 가능함
*/

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        // 배달과 수거를 처리해야 할 '가장 먼 집'을 가리키는 포인터
        int dPointer = n-1;
        int pPointer = n-1;

        // 배달 또는 수거할 일이 남아있는 동안 반복
        while(dPointer >= 0 || pPointer >= 0) {

            // 남은 배달 or 수거 없는 집은 skip
            while(dPointer >= 0 && deliveries[dPointer] == 0) dPointer--;
            while(pPointer >= 0 && pickups[pPointer] == 0) pPointer--;
            
            // 여기부턴 배달이든 수거든 둘 중에 적어도 하나는 해야하는 곳에 도착한 것!

            // 이번 트립(왕복)에서 배달/수거 처리한 개수 기록용
            int sum;

            // 배달 또는 수거 중 더 먼 곳까지 가야 하므로 이동 거리 누적
            // (거리 = 인덱스+1, 왕복이므로 *2)
            answer += (Math.max(dPointer, pPointer) + 1) * 2;

            // === '배달' 처리 === [시작] //
            sum = 0;
            while(dPointer >= 0 && sum < cap) {
                // 해당 지역의 배달량을 sum에 더함 // 실제로는 없을 수도 있음!
                sum += deliveries[dPointer];
                // 해당 집의 배달량을 0으로
                deliveries[dPointer--] = 0;
            }

            // 만약, sum이 cap을 넘었다면
            // (한 번에 다 못 처리한 경우 남은 양을 다시 할당)
            if (sum > cap) {
                // 마지막에 cap 초과된 부분 값을 복구
                deliveries[++dPointer] = sum - cap;
            }
            // === '배달' 처리 === [완료] //

            
            
            // === '수거' 처리 (배달과 동일 원리) [시작] ===
            sum = 0;
            while(pPointer >= 0 && sum < cap) {
                // 남은 수거량을 sum에 더함
                sum += pickups[pPointer];

                // 해당 집의 수거량을 0으로
                pickups[pPointer--] = 0;
            }

            // 만약, 마지막에 더한 sum이 cap을 넘었다면
            // (한 번에 다 못 처리한 경우 남은 양을 다시 할당)
            if(sum > cap) {
                pickups[++pPointer] = sum - cap;
            }
            
            // === '수거' 처리 [완료] ===
        }

        // 이동한 총 거리를 반환
        return answer;
    }
}
