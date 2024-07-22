import java.io.*;
import java.util.*;

/*
백준 2504 괄호의 값
() 2
[] 3

괄호 안에 있는 것들은 x
밖에 있는 것들은 +
로 생각하기

올바르지 못한 입력 값인 경우(괄호의 쌍이 맞지 않는 경우, 여는 괄호 없이 닫는 괄호가 먼저 생긴 경우 등)는 0 출력하기

ex)  () ()

<풀이>
괄호의 쌍이 맞아야 함 => 닫는 괄호가 나왔을 때 이전 값이 해당 괄호와 맞는 쌍이어야 함.

최대 길이가 30이므로 최대 값은 3의 15승이므로 int형으로 해도 무방

최종 값 / 곱해지는 값을 위한 2개의 값을 활용
 */

public class Main {
    static int isOpenBracket(char bracket)
    {
        if (bracket == '(') return 2;
        else if ( bracket == '[') return 3;
        else if ( bracket == ')') return -2;
        else if ( bracket == ']') return -3;
        return 0;
    }

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String brackets = br.readLine();
        Stack<Character> bracketStack = new Stack<>();
        int res = 0; // 최종 결과 값
        int multiple = 1; // 현재 곱해진 값 // 곱셈이므로 기본 값 1

        for (int i = 0; i < brackets.length(); i++) {
            // 현재 괄호 및 값
            char currentBracket = brackets.charAt(i);
            int currentVal = isOpenBracket(currentBracket);

            // 여는 괄호
            if (currentVal > 1)
            {
                multiple *= currentVal;
                bracketStack.push(currentBracket);
            }

            // 닫는 괄호
            else {
                // 비어 있거나 짝이 맞지 않는 경우 => 올바르지 않으므로 0 출력하고 끝내기
                if (bracketStack.isEmpty() || isOpenBracket(bracketStack.peek()) + currentVal != 0) {
                    System.out.println(0);
                    return ;
                }
                // 짝이 맞는 경우
                else {
                    // 바로 앞의 괄호를 확인해서 닫는 여는 괄호인 경우 해당 번째 까지의 값이 최종 값에 더해지면 되고 // 즉 괄호로 묶인 것들은 가장 안쪽 값이 반영되는 것!
                    if (isOpenBracket(brackets.charAt(i - 1)) > 0) res += multiple;
                    // 나머지의 경우 그냥 해당 짝의 값으로 나눠주면 됨.
                    multiple /= isOpenBracket(bracketStack.pop());
                }
            }
        }

        // 다 했는데 스택이 비어있지 않은 경우 => 올바르지 않은 경우
        if (!bracketStack.isEmpty()) {
            System.out.println(0);
            return;
        }

        System.out.println(res);
    }
}

