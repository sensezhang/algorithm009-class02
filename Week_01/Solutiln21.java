

public class Solution21{

  public ListNode mergeTwoLists(ListNode l1, ListNode l2){
   /* // 递归法
    if (l1 == null) {
      return l2;
    } else if (l2 == null) {
      return l1;
    } else if (l1.val < l2.val) {
      l1.next = mergeTwoLists(l1.next, l2);
      return l1;
    } else {
      l2.next = mergeTwoLists(l1, l2.next);
      return l2;
    }*/
    // 迭代法
    ListNode preHead = new ListNode(0);
    ListNode tmp = preHead;
    while (l1 != null && l2 != null) {
      if (l1.val < l2.val) {
        tmp.next = l1;
        l1 = l1.next;
      } else {
        tmp.next = l2;
        l2 = l2.next;
      }
      tmp = tmp.next;
    }
    tmp.next = l1 == null ? l2 : l1;
    return preHead.next;
  }

}