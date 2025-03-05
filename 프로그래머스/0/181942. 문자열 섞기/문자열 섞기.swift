import Foundation

func solution(_ str1:String, _ str2:String) -> String {
    return zip(str1, str2).map{ (s1, s2) in String(s1) + String(s2) }.joined()
}