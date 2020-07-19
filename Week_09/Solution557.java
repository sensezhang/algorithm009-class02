class Solution557 {
  public String reverseWords(String s) {
    String[] strs = s.split(" ");
    List<String> words = new ArrayList<>();
    for (String str : strs) {
      words.add(revertString(str));
    }
    return String.join(" ", words);
  }

  private String revertString(String s){
    char[] sc = s.toCharArray();
    int left = 0;
    int right = sc.length - 1;
    while (left < right){
      char tmp = sc[left];
      sc[left++] = sc[right];
      sc[right--] = tmp;
    }
    return new String(sc);
  }
}