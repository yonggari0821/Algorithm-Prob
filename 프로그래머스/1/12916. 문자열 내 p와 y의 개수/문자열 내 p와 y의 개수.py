def solution(s):
    s = s.lower()
    
    pNum : int = s.count('p')
    yNum : int = s.count('y')
    
    if pNum == 0 and yNum == 0:
        return True;
    
    return pNum == yNum;