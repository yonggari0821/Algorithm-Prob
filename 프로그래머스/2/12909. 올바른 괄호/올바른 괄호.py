def solution(s):
    stack = [];
    
    for c in s:
        if c == '(':
            stack.append(c);
        else:
            if not stack: return False
            stack.pop()

    return len(stack) == 0