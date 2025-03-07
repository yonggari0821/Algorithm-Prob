import Foundation

func solution(_ arr:[Int], _ queries:[[Int]]) -> [Int] {
    var resArr : [Int] = []
    
    for query in queries {
        let s = query[0]
        let e = query[1]
        let k = query[2]
        
        var minVal = 1000001
        
        for i in s ... e {
            if (arr[i] > k) {
                minVal = min(arr[i], minVal)
            }
        }
        
        if minVal == 1000001 {
            minVal = -1
        }
        
        resArr.append(minVal)
    }
    return resArr
}