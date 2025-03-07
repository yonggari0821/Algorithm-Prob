import Foundation

func solution(_ num_list:[Int]) -> Int {
    var multiples = 1
    var powerOfSum = 0
    
    for num in num_list {
        multiples *= num
        powerOfSum += num
    }
    
    powerOfSum = Int(pow(Double(powerOfSum), 2))
    return multiples < powerOfSum ? 1 : 0 
}