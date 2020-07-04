class Trie {
    boolean isEnd;
    Trie[] tries;
    int R = 26;
    /** Initialize your data structure here. */
    public Trie() {
        tries = new Trie[R];
    }
    
    private boolean containsKey(char a) {
        return tries[a-'a'] != null;
    }

    private void putKey(char a, Trie trie) {
        tries[a - 'a'] = trie;
    }

    private Trie getKey(char a) {
        return tries[a - 'a'];
    }

    private void setEnd() {
        isEnd = true;
    }

    private boolean isEnd() {
        return isEnd;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char w = word.charAt(i);
            if (!node.containsKey(w)) {
                node.putKey(w, new Trie());
            }
            node = node.getKey(w);
        }
        node.setEnd();
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char w = word.charAt(i);
            if (node.containsKey(w)) {
                node = node.getKey(w);
            } else {
                return false;
            }
        }
        return node.isEnd();
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            char w = prefix.charAt(i);
            if (node.containsKey(w)) {
                node = node.getKey(w);
            } else {
                return false;
            }
        }
        return true;
    }
}