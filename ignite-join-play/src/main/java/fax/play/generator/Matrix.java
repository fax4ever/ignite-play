package fax.play.generator;

import java.util.Random;

public class Matrix {

   final int rows;
   final int columns;
   final Random rand;

   final Integer[][] data;

   public Matrix(int rows, int columns, Random rand) {
      this.rows = rows;
      this.columns = columns;
      this.rand = rand;
      this.data = new Integer[rows][columns];

      for (int col = 0; col < columns; col++) {
         data[0][col] = col;
      }

      for (int row = 1; row < rows; row++) {
         for (int i = 0; i < columns; i++) {
            data[row][i] = data[row-1][rand.nextInt(40)];
         }
      }
   }

   public String dump() {
      StringBuilder builder = new StringBuilder();
      for (int table = 1; table <= rows; table++) {
         for (int i = 0; i < columns; i++) {
            builder.append(String.format("%1$5s", data[table-1][i]));
         }
         builder.append("\n");
      }
      return builder.toString();
   }

   public int get(int row, int column) {
      return data[row][column];
   }
}
