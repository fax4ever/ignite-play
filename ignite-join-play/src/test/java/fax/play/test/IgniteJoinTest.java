package fax.play.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Random;

import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.sql.ResultSet;
import org.apache.ignite.sql.Session;
import org.apache.ignite.sql.SqlRow;
import org.apache.ignite.sql.Statement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import fax.play.generator.Matrix;
import fax.play.generator.SQLAccess;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IgniteJoinTest {

   static final int SIZE = 10;
   final SQLAccess sqlAccess = new SQLAccess();

   IgniteClient client;

   @Test
   public void test() throws Exception {
      try (Session ses = client.sql().createSession()) {
         try (ResultSet rs = ses.execute(null, "SELECT * FROM TABLE_1S")) {
            while (rs.hasNext()) {
               SqlRow row = rs.next();

               System.out.println(row.intValue(0));

//               System.out.println("    "
//                     + row.stringValue(1) + ", "
//                     + row.stringValue(2) + ", "
//                     + row.stringValue(3));
            }
         }
      }
   }

   @BeforeAll
   public void beforeAll() throws Exception {
      client = IgniteClient.builder()
            .addresses("localhost:10800")
            .build();

      schemaAndDeletes();
      inserts();
   }

   @AfterAll
   public void afterAll() throws Exception {
      if (client != null) {
         client.close();
      }
   }

   private void schemaAndDeletes() throws IOException {
      try (Session ses = client.sql().createSession()) {
         for (String command : sqlAccess.schema()) {
            ses.execute(null, command).close();
         }
         for (String command : sqlAccess.deletes()) {
            ses.execute(null, command).close();
         }
      }
   }

   private void inserts() throws Exception {
      String[] commands = sqlAccess.inserts();
      assertThat(commands.length).isEqualTo(5);

      Matrix matrix = new Matrix(commands.length, SIZE, new Random(739));

      long rowsAdded = 0;

      try (Session ses = client.sql().createSession()) {
         try (Statement stmt = client.sql().createStatement(commands[0])) {
            for (int i=0; i<SIZE; i++) {
               try (ResultSet rs = ses.execute(null, stmt, matrix.get(0, i))) {
                  rowsAdded += rs.affectedRows();
               }
            }
         }

         for (int table = 1; table < commands.length; table++){
            try (Statement stmt = client.sql().createStatement(commands[table])) {
               for (int i=0; i<SIZE; i++) {
                  try (ResultSet rs = ses.execute(null, stmt, matrix.get(0, i), matrix.get(table, i))) {
                     rowsAdded += rs.affectedRows();
                  }
               }
            }
         }
      }

      assertThat(rowsAdded).isEqualTo(SIZE * commands.length);
   }
}
