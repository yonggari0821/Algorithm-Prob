def solution(prices):
    answer = [0] * len(prices)  # 결과를 저장할 리스트, prices와 같은 길이로 0으로 초기화
    stack = []                  # 스택 역할을 할 빈 리스트 생성

    for i, price in enumerate(prices):  # prices의 인덱스(i)와 값(price)을 순회
        while stack and stack[-1][0] > price:  # 스택이 비어있지 않고, 스택의 top에 있는 값이 현재 값보다 크면 # python에서 빈 리스트([])는 False로 취급됨
            past_price, past_idx = stack.pop() # 스택에서 값을 꺼냄 (이전 가격, 인덱스)
            answer[past_idx] = i - past_idx    # 가격이 떨어진 시점까지 걸린 시간(초) 저장
        stack.append((price, i))   # (현재 가격, 인덱스)를 스택에 저장

    while stack:                   # 아직 스택에 남아있는 항목들(끝까지 가격이 떨어지지 않은 경우)
        past_price, past_idx = stack.pop()
        answer[past_idx] = len(prices) - 1 - past_idx  # 끝까지 가격이 떨어지지 않았으므로 마지막 시점까지의 시간 저장
    return answer                  