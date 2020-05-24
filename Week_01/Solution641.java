class Solution641 {

  /**
   * 循环双端队列
   */
  static class MyCircularDeque {

    private int[] arr;
    private int size;
    private int front;
    private int last;

    /**
     * 初始化双端队列
     *
     * @param k
     */
    public MyCircularDeque(int k) {
      arr = new int[k];
      size = 0;
      front = 0;
      last = 0;
    }

    /**
     * 前插入
     *
     * @param value
     * @return
     */
    public boolean insertFront(int value) {
      if (isFull()) {
        return false;
      }
      front = front == 0 ? arr.length - 1 : --front;
      arr[front] = value;
      size++;
      return true;
    }

    /**
     * 后插入
     *
     * @param value
     * @return
     */
    public boolean insertLast(int value) {
      if (isFull()) {
        return false;
      }
      arr[last] = value;
      last = last == arr.length - 1 ? 0 : ++last;
      size++;
      return true;
    }

    /**
     * 删除最前
     *
     * @return
     */
    public boolean deleteFront() {
      if (isEmpty()) {
        return false;
      }
      front = front == arr.length - 1 ? 0 : ++front;
      size--;
      return true;
    }

    /**
     * 删除最后
     *
     * @return
     */
    public boolean deleteLast() {
      if (isEmpty()) {
        return false;
      }
      last = last == 0 ? arr.length - 1 : --last;
      size--;
      return true;
    }

    public int getFront() {
      if (isEmpty()) {
        return -1;
      }
      return arr[front];
    }

    /**
     * 获取最后元素
     *
     * @return
     */
    public int getRear() {
      if (isEmpty()) {
        return -1;
      }
      return last == 0 ? arr[arr.length - 1] : arr[last-1];
    }

    /**
     * 队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
      return size == 0;
    }

    /**
     * 队列是否已满
     *
     * @return
     */
    public boolean isFull() {
      return size == arr.length;
    }
  }
}