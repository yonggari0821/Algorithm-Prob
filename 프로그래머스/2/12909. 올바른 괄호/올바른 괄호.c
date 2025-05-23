#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// 파라미터로 주어지는 문자열은 const로 주어집니다. 변경하려면 문자열을 복사해서 사용하세요.
bool solution(const char* s) {
    int len = strlen(s);
    char* stack = (char*)malloc(len + 1);
    int top = -1;
    
    for (int i = 0; s[i] != '\0'; i++) {
        if (s[i] == '(') {
            stack[++top] == ')';
        }
        else if (s[i] == ')') {
            if (top == -1) {
                free(stack);
                return false;
            }
            top--;
        }
    }
    
    return top == -1;
}