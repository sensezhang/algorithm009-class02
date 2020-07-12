class LRUCache {

   
    private Map<Integer, DListNode> map = null;
    int size = 0;
    private DListNode head = null;
    private DListNode tail = null;


    public LRUCache(int capacity) {
        size = capacity;
        map = new HashMap<>(capacity);
        head = new DListNode();
        tail = new DListNode();
        head.next = tail;
        tail.pre = head;
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            // 移动到头部
            DListNode dListNode = map.get(key);
            removeToHead(dListNode);
            return dListNode.value;
        }
        // 没有就返回-1
        return -1;
    }

    private void removeNode(DListNode node) {
        if (node.pre != null) {
            node.pre.next = node.next;
        }
        if (node.next != null) {
            node.next.pre = node.pre;
        }
        node.next = null;
        node.pre = null;
    }

    private void addToHead(DListNode node) {
        node.next = head.next;
        node.pre = head;
        head.next.pre = node;
        head.next = node;
    }

    private int removeTail() {
        DListNode node = tail.pre;
        removeNode(node);
        return node.key;
    }

    private void removeToHead(DListNode dListNode) {
        removeNode(dListNode);
        addToHead(dListNode);
    }

    public void put(int key, int value) {
       // 看元素是否存在
        if (map.containsKey(key)) {
            // 更新元素
            DListNode node = map.get(key);
            node.value = value;
            // 移动元素到头部
            removeToHead(node);
        } else {
            // 元素不存在
            DListNode node = new DListNode(key, value);
            addToHead(node);
            if (map.size() >= size) {
                map.remove(removeTail());
            }
            map.put(key, node);
        }
    }

    class DListNode{
        int key;
        int value;
        DListNode pre;
        DListNode next;
        public DListNode() {}
        public DListNode(int key, int value) {
            this.key = key;
            this.value = value;

        }
    }

}