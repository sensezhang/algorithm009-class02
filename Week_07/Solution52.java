class Solution52{

  boolean[] col = null;
  boolean[] pie = null;
  boolean[] na = null;

  int count = 0;

  public int totalNQueens(int n) {
    col = new boolean[n];
    pie = new boolean[2 * n - 1];
    na = new boolean[2 * n - 1];
    dfs(n, 0);
    return count;
  }

  private void dfs(int n, int level) {
    if (level == n) {
      count++;
      return;
    }
    for (int i = 0; i < n; i++) {
      if (!col[i] && !pie[i + level] && !na[i - level + n - 1]) {
        col[i] = true;
        pie[i + level] = true;
        na[i - level + n - 1] = true;
        dfs(n, level + 1);

        col[i] = false;
        pie[i + level] = false;
        na[i - level + n - 1] = false;
      }
    }
  }
}