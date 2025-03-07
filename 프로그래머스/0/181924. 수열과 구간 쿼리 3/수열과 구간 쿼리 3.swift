import Foundation

func solution(_ arr:[Int], _ queries:[[Int]]) -> [Int] {
    var mutableArr = arr
    for query in queries {
        let a = query[0]
        let b = query[1]
        
        var tmp = mutableArr[a]
        mutableArr[a] = mutableArr[b]
        mutableArr[b] = tmp
    }
    return mutableArr
}