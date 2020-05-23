

public class Solution21{

  /**
   * 合并两个链表
   * @param l1
   * @param l2
   * @return
   */
  public ListNode mergeTwoLists(ListNode l1, ListNode l2){
   /* // 递归法
    if (l1 == null) {
    // 判断l1为空，返回l2
      return l2;
    } else if (l2 == null) {
     // 如果l2为空，返回l1
      return l1;
    } else if (l1.val < l2.val) {
    //如果l1的val小于l2的val，l1的next继续递归判断l1的next与l2谁小，最终返回l1
      l1.next = mergeTwoLists(l1.next, l2);
      return l1;
    } else {
    // 如果l2.val小于等于l1.val，l2.next继续递归判断l2.next与l1谁小，最终返回l2
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