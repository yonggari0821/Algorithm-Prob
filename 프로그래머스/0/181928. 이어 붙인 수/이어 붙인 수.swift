import Foundation

func solution(_ num_list:[Int]) -> Int {
    
    var oddNum = num_list.filter{$0 & 1 == 1}.reduce(0) {$0 * 10 + $1}
    var evenNum = num_list.filter{$0 & 1 == 0}.reduce(0) {$0 * 10 + $1}
    
    return oddNum + evenNum
}