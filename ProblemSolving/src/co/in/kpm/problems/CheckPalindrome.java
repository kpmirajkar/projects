package co.in.kpm.problems;

public class CheckPalindrome {

  public static boolean isPalindrome(String s) {
    char[] charArray = s.toCharArray();
    int i = 0;
    int j = charArray.length - 1;
    while(i <= j) {
      if(charArray[i] == charArray[j]) {
        i++;
        j--;
      }
      else if(charArray[i] == ' ') {
        i++;
      }
      else if(charArray[j] == ' ') {
        j--;
      }
      else {
        return false;
      }
    }
    return true;
  }


  public static void main(String[] args) {
    System.out.println(isPalindrome("MALAYA LAM"));
    System.out.println(isPalindrome("TULIP"));
  }
}
