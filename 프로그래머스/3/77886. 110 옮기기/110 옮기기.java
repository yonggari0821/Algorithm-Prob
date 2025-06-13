import java.util.*;
/*
    문자열 전체를 stack에 저장해가면서
    110을 찾으면 넣지 않고 oneOneZeroCnt++;
    
    그렇게 110을 제외한 문자열에서 
*/
class Solution {
    
    private static Stack<Integer> stack = new Stack<>();
    private int oneOneZeroCnt;
    private String[] ans;
    
    public String[] solution(String[] s) {
        ans = new String[s.length];
        for (int i = 0; i < s.length; i++) {
            // [초기화] 스택 비우기 + 110 카운트 0
            stack.clear();
            oneOneZeroCnt = 0;
            String cur = s[i];
            
            // stack에 110 제외하고 넣으면서 110 갯수 세어두기
            for (int j = 0; j < cur.length(); j++) {
                if (cur.charAt(j) == '1') 
                    stack.push(1);
                else {
                    if (stack.size() >= 2) {
                        int first = stack.pop();
                        int second = stack.pop();
                        if (first == 1 && second == 1) {
                            oneOneZeroCnt++;
                        }
                        else {
                            stack.push(second);
                            stack.push(first);
                            stack.push(0);
                        }
                    }
                    else stack.push(0);
                }
            }
            
            // 110 갯수만큼 0 뒤에 넣기
            
            for (int j = 0; j < oneOneZeroCnt; j++) {
                Stack<Integer> tmp = new Stack<>();
                while ( true ) {
                    // stack이 비어있음 == 110빼고 남은 0과 1이 없거나 있어도 1만 있었어서 이미 tmp에 넣어둔 상태
                    // => stack에 일단 110 넣고 tmp에 넣어둔 게 있으면 넣어주기
                    // ex) 1110
                    // tmp에 1들어가고 나머지 없음 => 110 먼저 넣어주고 1 넣어줘서 1101로 바꿔주기
                    if (stack.isEmpty()) {
                        stack.push(1);
                        stack.push(1);
                        stack.push(0);
                        while (!tmp.isEmpty()) {
                            stack.push(tmp.pop());
                        }
                        break;
                    }
                    // stack에 뭔가 들어있음 == 나머지 일반적인 경우
                    else {
                        int t = stack.pop();
                        // 0인 경우 => 0이랑 110부터 넣고 그전에 살펴본 1들 있으면 넣어주기
                        if (t == 0) {
                            stack.push(t);
                            stack.push(1);
                            stack.push(1);
                            stack.push(0);
                            while (!tmp.isEmpty()) {
                                stack.push(tmp.pop());
                            }
                            break;
                        }
                        // 0이 아닌 경우 == 1인 경우 => 임시 스택에 저장해두기
                        else tmp.push(t);
                    }
                }
            }

            // stack에 넣어뒀던거 빼서 역순으로 답에 넣어주기
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            String Result = sb.reverse().toString();

            ans[i] = Result;
        }

        return ans;
    }
}