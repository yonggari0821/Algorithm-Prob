import Foundation

func solution(_ num_list:[Int]) -> [Int] {
    
    var resultList = num_list
    
    if let lastElement = num_list.last {
        let beforeLastElement = num_list[num_list.count - 2]
        if (lastElement > beforeLastElement) {
            resultList.append(lastElement - beforeLastElement)
        } else {
            resultList.append(2 * lastElement)
        }
    }
    
    return resultList
}