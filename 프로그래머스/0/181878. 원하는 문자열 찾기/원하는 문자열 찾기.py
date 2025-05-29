def solution(myString, pat):
    answer =  0 if myString.lower().count(pat.lower()) == 0 else 1
    return answer