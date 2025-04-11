#include <stdio.h>
#include <string.h>
#include <math.h>
#include <ctype.h>
#include <stdbool.h>
#include <stdlib.h>

// 파라미터로 주어지는 문자열은 const로 주어집니다. 변경하려면 문자열을 복사해서 사용하세요.
bool solution(const char* s) {
    int len = strlen(s);
    
    if (len != 4 && len != 6) {
        return false;
    }
    
    for (int i = 0; i < len; i++) {
        if (!isdigit(s[i])) {
            return false;
        }
    }
    
    return true;
}