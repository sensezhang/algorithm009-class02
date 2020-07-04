# 第七周
## 字典树
    定义：trie树，又称单词查找树，是一种树形结构。典型的应用于统计和排序大量的字符串，（但不仅限于字符串），所以经常被搜索引擎用于文本词频统计
    优点：最大限度的减少无谓的字符串匹配，查询效率比哈希表高

实现Trie
```java

class Trie{
    int R = 26;
    Trie[] tries;
    boolean isEnd;
    
    public Trie(){
        tries = new Trie[R];
    }

    private void putKey(char key, Trie trie) {
        this.tries[key - 'a'] = trie;
    }

    private Trie getKey(char key) {
        return tries[key - 'a'];
    }

    private boolean containsKey(char key) {
        return tries[key - 'a'] != null;
    }

    public void setEnd(){
        isEnd = true;
    }
    
    public boolean isEnd(){
        return isEnd;
    }
    public void insertWord(String word) {
        Trie trie = this;
        for (int i = 0; i < word.length(); i++) {
            if (trie.containsKey(word.toCharAt(i))) {
                trie = trie.getKey(word.toCharAt(i));
            } else {
                Trie next = new Trie();
                trie.putKey(word.toCharAt(i), next);
                trie = next;
            }
        }
        trie.setEnd();
    }

    public boolean searchWord(String word) {
        Trie trie = this;
        for (int i = 0; i < word.length(); i++) {
            if (trie.containsKey(word.toCharAt(i))) {
                trie = trie.getKey(word.toCharAt(i));
            } else {
                return false;
            }
        }
        return node.isEnd();
    }
}
```

## 并查集

代码模板

```java
class UnionFind { 
	private int count = 0; 
	private int[] parent; 
	public UnionFind(int n) { 
		count = n; 
		parent = new int[n]; 
		for (int i = 0; i < n; i++) { 
			parent[i] = i;
		}
	} 
	public int find(int p) { 
		while (p != parent[p]) { 
			parent[p] = parent[parent[p]]; 
			p = parent[p]; 
		}
		return p; 
	}
	public void union(int p, int q) { 
		int rootP = find(p); 
		int rootQ = find(q); 
		if (rootP == rootQ) return; 
		parent[rootP] = rootQ; 
		count--;
	}
}
```

## 双向BFS

双向BFS模板
```java
public void bothBfs(String start, String end, Set<String> words) {
    Set<String> visited = new HashSet<>();
    Set<String> startSet = new HashSet<>();
    Set<String> endSet = new HashSet<>();
    startSet.add(start);
    endSet.add(end);
    while (!startSet.isEmpty() && !endSet.isEmpty()) {
        // 从最小的端开始遍历
        if (startSet.size() > endSet.size()) {
            // 交换start、end
            swapSet(startSet, endSet);
        }
        Set<String> tmp = new HashSet<>();
        for (String str : startSet) {
          
            // 处理str
            String newStr = process(str);
            if (endSet.contains(newStr) && !visited.contains(newStr)) {
                // 如果结尾集合中包含处理后的字符串，则返回
                return;
            }
            if (words.contains(newStr) && !visited.contains(newStr)) {
                tmp.add(newStr);
            }
        }
        // 设置startSet的值
        startSet = tmp;
    }
    
}

private void swapSet(Set<String> startSet, Set<String> endSet) {
    Set<String> tmp = startSet;
    startSet = endSet;
    endSet = tmp;
}
```