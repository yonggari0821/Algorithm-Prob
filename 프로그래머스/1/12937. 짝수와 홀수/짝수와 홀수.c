#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>

char* solution(int num) {
    // 리턴할 값은 메모리를 동적 할당해주세요
    char* answer = (char*)malloc(5);
    
    if ((num & 1) == 0) strcpy(answer, "Even");
    else strcpy(answer, "Odd");
    
    // printf("answer = %s\n", answer);
    
    return answer;
}