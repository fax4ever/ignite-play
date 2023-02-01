package fax.play.test;

import java.util.Random;

import org.junit.jupiter.api.Test;

import fax.play.generator.Matrix;

public class MatrixTest {

   final Matrix matrix = new Matrix(5, 10, new Random(739));

   @Test
   public void test() {
      System.out.println(matrix.dump());
   }
}
