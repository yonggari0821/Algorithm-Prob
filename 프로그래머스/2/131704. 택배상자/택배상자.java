import java.util.*;

/*
    택배 상자 컨테이너 벨트에 일렬로 놓여있고
    놓인 순서대로 상자를 내릴 수 있음
    하지만 배달하는 순서는 다름
    미리 알려준 순서(order)에 맞게 택배 상자를 실어야 함
    
    잠시 보관할 때 보조 컨테이너 벨트에 두어야 함
    보조 컨테이너 벨트는 앞뒤로 이동만 가능하고 입구 외에 다른 면이 막혀 있어 맨 앞의 상자만 뺄 수 있음
    가장 마지막에 보관한 상자부터 꺼내게 됨 => Stack
    근데 순서 변경 불가능하고 못 빼는 경우 stop
    
*/

class Solution {
    public int solution(int[] order) {
        Stack<Integer> sub = new Stack<>();
        int cur = 1;
        int ans = 0;
        for (int i = 0; i < order.length; i++) {
            // 실을 수 있는 경우
            // 현재 cur이 order[i]인 경우
            // or sub가 비어있지 않고 sub.peek()이 order[i]인 경우
            if (!sub.isEmpty() && sub.peek() == order[i]) {
                sub.pop();
                ans++;
            }
            else {
                while(cur < order[i]) sub.push(cur++);
                if (cur == order[i]) {
                    ans++;
                    cur++;
                }
                else {
                    break;
                }
            }
        }
        return ans;
    }
}