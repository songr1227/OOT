class Sudoku:  
    def __init__(self, input_str):  
        self.board = [[0] * 9 for _ in range(9)]  
        self.candidates = {cell: set(range(1, 10)) for cell in range(81)}  
        for i, char in enumerate(input_str):  
            value = int(char) if char.isdigit() else 0  
            row, col = divmod(i, 9)  
            self.board[row][col] = value  
            if value:  
                self.candidates[i].clear()  
                self.candidates[i].add(value)  
  
    def init_candidates(self):  
        for i in range(81):  
            row, col = divmod(i, 9)  
            if self.board[row][col] == 0:  
                self.remove_invalid_candidates(i)  
  
    def remove_invalid_candidates(self, cell):  
        row, col = divmod(cell, 9)  
        box_row = row // 3  
        box_col = col // 3  
          
        # Remove from row  
        for j in range(9):  
            if self.board[row][j] and self.board[row][j] in self.candidates[cell]:  
                self.candidates[cell].remove(self.board[row][j])  
          
        # Remove from column  
        for i in range(9):  
            if self.board[i][col] and self.board[i][col] in self.candidates[cell]:  
                self.candidates[cell].remove(self.board[i][col])  
          
        # Remove from 3x3 box  
        for i in range(box_row * 3, box_row * 3 + 3):  
            for j in range(box_col * 3, box_col * 3 + 3):  
                if self.board[i][j] and self.board[i][j] != self.board[row][col] and self.board[i][j] in self.candidates[cell]:  
                    self.candidates[cell].remove(self.board[i][j])  
  
    def print_board(self):  
        for i in range(9):  
            for j in range(9):  
                print(self.board[i][j] if self.board[i][j] else ".", end=" ")  
                if j == 2 or j == 5:  
                    print("| ", end="")  
            print()  
            if i == 2 or i == 5:  
                print("-----------")  
  
# 测试代码  
input_str = "017903600000080000900000507072010430000402070064370250701000065000030000005601720"  
sudoku = Sudoku(input_str)  
sudoku.init_candidates()  
sudoku.print_board()
