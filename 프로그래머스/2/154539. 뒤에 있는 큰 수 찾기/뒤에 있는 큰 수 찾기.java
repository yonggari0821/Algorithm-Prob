import java.util.*;

/*
    뒤에 가장 가까이 있으면서 큰 수(같은 수 아님 주의!)
    없으면 -1
    
    numbers의 길이가 100만이므로 일반적인 브루트포스 방법으로는 풀 수 없음
    numbers에 들어가는 숫자들의 범위가 최대 100만 => 자료구조 만들 수 있음
*/

class Solution {
    public int[] solution(int[] numbers) {
        // number index 정보를 담을 Stack
        Stack<Integer> stack = new Stack<>();

        // 정답 배열 생성
        int[] answer = new int[numbers.length];

        // 첫 번째 number 인덱스 stack에 push
        stack.push(0);

        // 두 번째 원소부터 numbers 탐색
        for (int i = 1; i < numbers.length; i++) {
            // Stack이 비어있지 않으면서 
            // 현재 Stack 맨 위 값보다 number의 값이 크면 뒤에 있는 큰 수
            // 아니면 그냥 현재 '값'이 아니라 '인덱스'를 넣어주기만 하면 됨
            while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
                // 뒤에 있는 큰 수에 해당하는 모든 값 pop
                answer[stack.pop()] = numbers[i];
            }


            // 현재 인덱스 넣기
            stack.push(i);
        }

        // 모든 index를 탐색 후 뒤에 있는 큰 수가 없는 경우 -1 
        while (!stack.isEmpty()) {
            answer[stack.pop()] = -1;
        }

        return answer;
    }   
}