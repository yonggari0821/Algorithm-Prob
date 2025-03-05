import Foundation

func solution(_ a: Int, _ b: Int) -> Int {
    let aDigits = numberOfDigits(a)
    let bDigits = numberOfDigits(b)
    
    let ab = a * pow10(bDigits) + b
    let ba = b * pow10(aDigits) + a
    
    return ab >= ba ? ab : ba
}

// 자릿수 계산 함수
func numberOfDigits(_ num: Int) -> Int {
    if num == 0 { return 1 }
    return Int(log10(Double(abs(num)))) + 1
}

// 10의 거듭제곱 계산 함수
func pow10(_ n: Int) -> Int {
    return Int(pow(10.0, Double(n)))
}