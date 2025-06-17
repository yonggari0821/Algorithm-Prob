import java.util.*;

class Combination {
    int a;
    int b;
    char op;
    int ans;
    
    public Combination(int a, int b, char op, int ans) {
        this.a = a;
        this.b = b;
        this.op = op;
        this.ans = ans;
    }
}

class Solution {
    
    // n진법 수를 10진법으로 변환
    private int toDecimal(int num, int base) {
        int result = 0;
        int power = 1;
        while (num > 0) {
            int digit = num % 10;
            
            // 유효하지 않은 진법
            if (digit >= base) return -1; 
            
            // 결과에는 현재 power 곱해서 더해주고
            result += digit * power;
            // power에 진수 곱해주면서
            power *= base;
            // 원래 n진법 수의 다음 자릿수 살펴보러가기
            num /= 10;
        }
        return result;
    }
    
    // 10진법 수를 n진법 문자열로 변환
    private String toBase(int num, int base) {
        if (num == 0) return "0";
        StringBuilder sb = new StringBuilder();
        // 10진법 수로 진수로 나눈 나머지를 계속해서 붙혀나가기!
        while (num > 0) {
            sb.append(num % base);
            num /= base;
        }
        // 뒤집어줘야 맞음!
        // ex) 38을 5진법으로 변환
        /*
        38 % 5 = 3
        7 % 5 = 2
        1 % 5 = 1
        => 321 => 123
        */
        return sb.reverse().toString();
    }
    
    // 주어진 진법(base)에서 해당 수식(a op b = result)이 성립하는지 확인
    private boolean isValidInBase(int a, int b, char op, int result, int base) {
        int decA = toDecimal(a, base);
        int decB = toDecimal(b, base);
        int decResult = toDecimal(result, base);
        // toDecimal은 유효하지 않으면 -1을 return 함
        if (decA == -1 || decB == -1 || decResult == -1) return false;
        
        if (op == '+') {
            return decA + decB == decResult;
        } else {
            return decA - decB == decResult;
        }
    }
    
    // 수식에서 사용된 최대 숫자 찾기 (최소 진법 결정용)
    private int getMaxDigit(String[] expressions) {
        int maxDigit = 0;
        for (String expr : expressions) {
            String[] parts = expr.split(" ");
            for (String part : parts) {
                // 숫자만 봐야 함!
                if (!part.equals("+") && !part.equals("-") && !part.equals("=") && !part.equals("X")) {
                    for (char c : part.toCharArray()) {
                        // 숫자인 경우 최댓값 리뉴얼
                        if (Character.isDigit(c)) {
                            maxDigit = Math.max(maxDigit, c - '0');
                        }
                    }
                }
            }
        }
        return maxDigit;
    }
    
    public String[] solution(String[] expressions) {
        List<Combination> unknowns = new ArrayList<>();
        List<Combination> knowns = new ArrayList<>();
        
        // 수식 파싱
        for (String expr : expressions) {
            // 각 식들 띄어쓰기로 나눠주기
            String[] parts = expr.split(" ");
            int a = Integer.parseInt(parts[0]);
            char op = parts[1].charAt(0);
            int b = Integer.parseInt(parts[2]);
            
            // X면 알아내야 하므로 unknowns에 넣어주기
            if (parts[4].equals("X")) unknowns.add(new Combination(a, b, op, -1));
            else {
                int result = Integer.parseInt(parts[4]);
                knowns.add(new Combination(a, b, op, result));
            }
        }
        
        // 가능한 진법 범위 설정
        int minBase = getMaxDigit(expressions) + 1;
        Set<Integer> possibleBases = new HashSet<>();
        
        // 2~9진법 중에서 모든 알려진 수식이 성립하는 진법 찾기 (단, 식에 등장했던 모든 수들 중 가장 큰값보다 큰 값이어야 하므로 minBase 활용!)
        for (int base = Math.max(2, minBase); base <= 9; base++) {
            boolean valid = true;
            for (Combination known : knowns) {
                if (!isValidInBase(known.a, known.b, known.op, known.ans, base)) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                possibleBases.add(base);
            }
        }
        
        // 결과 계산
        String[] answer = new String[unknowns.size()];
        for (int i = 0; i < unknowns.size(); i++) {
            Combination unknown = unknowns.get(i);
            Set<String> possibleResults = new HashSet<>();
            
            for (int base : possibleBases) {
                int decA = toDecimal(unknown.a, base);
                int decB = toDecimal(unknown.b, base);
                
                if (decA != -1 && decB != -1) {
                    int decResult;
                    if (unknown.op == '+') {
                        decResult = decA + decB;
                    } else {
                        decResult = decA - decB;
                    }
                    
                    if (decResult >= 0) {
                        String baseResult = toBase(decResult, base);
                        possibleResults.add(baseResult);
                    }
                }
            }
            
            // 만약 가능하다면
            if (possibleResults.size() == 1) {
                String result = possibleResults.iterator().next();
                answer[i] = unknown.a + " " + unknown.op + " " + unknown.b + " = " + result;
            } 
            // 불가능하다면
            else {
                answer[i] = unknown.a + " " + unknown.op + " " + unknown.b + " = ?";
            }
        }
        
        return answer;
    }
}
