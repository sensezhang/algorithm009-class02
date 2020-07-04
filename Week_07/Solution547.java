class Solution547{

  public int findCircleNum(int[][] M) {
    int count = 0;
    boolean[] visited = new boolean[M.length];
    for (int i = 0; i < M.length; i++) {
      if (!visited[i]) {
        dfs(M, i, visited);
        count ++;
      }
    }
    return count;
  }

  private void dfs(int[][] M, int i, boolean[] visited) {

    for (int j = 0; j < M.length; j++) {
      if (M[i][j] == 1 && !visited[j]) {
        visited[j] = true;
        dfs(M, j, visited);
      }
    }
  }

}