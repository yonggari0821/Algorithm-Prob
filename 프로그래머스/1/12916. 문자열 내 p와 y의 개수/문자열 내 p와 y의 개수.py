def solution(s):
    
    pNum : int = 0;
    yNum : int = 0;
    
    for c in s:
        if c == 'p' or c == 'P': pNum += 1;
        elif c == 'y' or c == 'Y': yNum += 1;
    
    return pNum == yNum;