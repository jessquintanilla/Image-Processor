package controller.commands;

public class Pair {
  protected final int x;
  protected final int y;

  public Pair(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int compare(int row, int col) {
    int rowDiff = Math.abs(this.x - row);
    int colDiff = Math.abs(this.y - col);
    return rowDiff + colDiff;
  }
}

