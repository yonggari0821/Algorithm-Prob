import Foundation

if let input = readLine(),
   let n = Int(input) {
    print("\(n) is ", terminator: "")
    print( n % 2 == 0 ? "even" : "odd")
}