import java.util.*;  
  
public class Sudoku {  
    private int[][] board;  
    private Set<Set<Integer>> candidates;  
  
    public Sudoku(String input) {  
        board = new int[9][9];  
        candidates = new HashSet<>();  
        for (int i = 0; i < 9; i++) {  
            candidates.add(new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));  
            for (int j = 0; j < 9; j++) {  
                int value = Character.digit(input.charAt(i * 9 + j), 10);  
                board[i][j] = (value == -1) ? 0 : value;  
                if (value != 0) {  
                    candidates.get(i * 9 + j).clear();  
                    candidates.get(i * 9 + j).add(value);  
                }  
            }  
        }  
    }  
  
    // 初始化候选值  
    public void initCandidates() {  
        for (int i = 0; i < 9; i++) {  
            for (int j = 0; j < 9; j++) {  
                if (board[i][j] == 0) {  
                    removeInvalidCandidates(i, j);  
                }  
            }  
        }  
    }  
  
    // 移除行列和3x3格中的无效候选  
    private void removeInvalidCandidates(int row, int col) {  
        Set<Integer> currentCellCandidates = candidates.get(row * 9 + col);  
  
        // 移除行中已存在的数字  
        for (int j = 0; j < 9; j++) {  
            if (j != col && board[row][j] != 0) {  
                currentCellCandidates.remove(board[row][j]);  
            }  
        }  
  
        // 移除列中已存在的数字  
        for (int i = 0; i < 9; i++) {  
            if (i != row && board[i][col] != 0) {  
                currentCellCandidates.remove(board[i][col]);  
            }  
        }  
  
        // 移除3x3格中已存在的数字  
        int startRow = (row / 3) * 3;  
        int startCol = (col / 3) * 3;  
        for (int i = startRow; i < startRow + 3; i++) {  
            for (int j = startCol; j < startCol + 3; j++) {  
                if (i != row && j != col && board[i][j] != 0) {  
                    currentCellCandidates.remove(board[i][j]);  
                }  
            }  
        }  
  
        candidates.set(row * 9 + col, currentCellCandidates);  
    }  
  
    // 打印棋盘  
    public void printBoard() {  
        for (int i = 0; i < 9; i++) {  
            for (int j = 0; j < 9; j++) {  
                System.out.print(board[i][j] == 0 ? "." : board[i][j] + " ");  
                if (j == 2 || j == 5) System.out.print("| ");  
            }  
            System.out.println();  
            if (i == 2 || i == 5) System.out.println("-----------");  
        }  
    }  
  
    // 测试主函数  
    public static void main(String[] args) {  
        String input = "017903600000080000900000507072010430000402070064370250701000065000030000005601720";  
        Sudoku sudoku = new Sudoku(input);  
        sudoku.initCandidates();  
        sudoku.printBoard();  
    }  
}
