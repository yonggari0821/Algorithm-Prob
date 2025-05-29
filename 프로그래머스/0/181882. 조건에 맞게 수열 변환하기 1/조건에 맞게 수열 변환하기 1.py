def solution(arr):
    answer = []
    
    arr = [num * 2 
           if num < 50 and (num & 1) == 1 
           else num / 2 if num >= 50 and (num & 1) == 0
           else num
           for num in arr
          ]
    
    answer = arr
    
    return answer