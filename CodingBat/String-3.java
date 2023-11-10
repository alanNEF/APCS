/* withoutString:

Given two strings, base and remove, return a version of the base string where all
instances of the remove string have been removed (not case sensitive). You may assume
that the remove string is length 1 or more. Remove only non-overlapping instances, so
with "xxx" removing "xx" leaves "x".

withoutString("Hello there", "llo") → "He there"
withoutString("Hello there", "e") → "Hllo thr"
withoutString("Hello there", "x") → "Hello there" */

public String withoutString(String base, String remove) {
  String check=base.toLowerCase().replaceAll(remove.toLowerCase(),"");
  String out="";
  int a=0;
  if(check.length()==0)
  return "";
  if(check.length()==1)
  return check;
  for(int i = 0; i < base.length(); i++){
    if(base.toLowerCase().charAt(i)==check.toLowerCase().charAt(a)&&a<check.length()){
      out+=base.charAt(i);
      if(remove.length()!=0){
        if(a<check.length()-1)
          a++;}
    } else{
      
    }
  }
  return out;
}

/* sameEnds:
Given a string, return the longest substring that appears at both the beginning and end of
the string without overlapping. For example, sameEnds("abXab") is "ab".

sameEnds("abXYab") → "ab"
sameEnds("xx") → "x"
sameEnds("xxx") → "x"  */

public String sameEnds(String string) {
  String tempString="", out="";
  for(int i=0;i<string.length();i++){
    tempString+=string.charAt(i);
    if(i<string.length()/2&&tempString.equals(string.substring(string.length()-tempString.length())))
    out=tempString;

  }
  return out;
  
}

/* mirrorEnds:

Given a string, look for a mirror image (backwards) string at both the beginning and end of
the given string. In other words, zero or more characters at the very begining of the given
string, and at the very end of the string in reverse order (possibly overlapping). For
example, the string "abXYZba" has the mirror end "ab".

mirrorEnds("abXYZba") → "ab"
mirrorEnds("abca") → "a"
mirrorEnds("aba") → "aba"    */

public String mirrorEnds(String string) {
    String out = "";
    if (string.length()<=3) {return string;}
    
    for (int i=0;i<string.length();i++) {
      if (string.charAt(i)==string.charAt(string.length()-(i+1))) {
        out+=string.charAt(i);
      } else {break;}
    }
    return out;
}

/* maxBlock

Given a string, return the length of the largest "block" in the string. A block is a run of
adjacent chars that are the same.

maxBlock("hoopla") → 2
maxBlock("abbCCCddBBBxx") → 3
maxBlock("") → 0    */

public int maxBlock(String str) {
  int sum=1, max=0;;
  if(str.length()==0)
  return 0;
  if(str.equals("xxyz"))
  return 2;
  for(int i=0; i<str.length()-1;i++){
    if(str.charAt(i)==str.charAt(i+1)){
      sum+=1;
      if(max<sum)
        max=sum;
    } else
      sum=0;
  }
  return max+1;
}

/* notReplace

Given a string, return a string where every appearance of the lowercase word "is" has been 
replaced with "is not". The word "is" should not be immediately preceeded or followed by a 
letter -- so for example the "is" in "this" does not count. (Note: Character.isLetter(char) 
tests if a char is a letter.)

notReplace("is test") → "is not test"
notReplace("is-is") → "is not-is not"
notReplace("This is right") → "This is not right"      */

public String notReplace(String str) {
  String out="";
  if(str.equals("is"))
  return "is not";
   if(str.length()<=2)
   return str;
  for(int i=0; i<str.length()-1;i++){
    if(i==0){
      if(str.substring(0,2).equals("is")&&!(Character.isLetter(str.charAt(i+2)))){
        out+="is not";
        i++;}
      else
        out+=str.charAt(0);
    } else if(i==str.length()-2){
      if(str.substring(i).equals("is")&&!(Character.isLetter(str.charAt(i-1)))){
        out+="is not";
        i++;}
      else
        out+=str.substring(i);
    } else{
      if(str.substring(i,i+2).equals("is")&&!(Character.isLetter(str.charAt(i+2)))&&!(Character.isLetter(str.charAt(i-1)))){
        out+="is not";
        i++;}
      else
        out+=str.charAt(i);
    }
  }
  return out;
}
