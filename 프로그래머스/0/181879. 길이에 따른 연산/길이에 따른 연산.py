def solution(num_list):
    answer = 0;
    
    if (len(num_list) >= 11):
        for num in num_list:
            answer += num
    elif (len(num_list) <= 10):
        answer = 1
        for num in num_list:
            answer *= num
    return answer