package fax.play.generator;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class SQLAccess {

   public String[] schema() throws IOException {
      try (InputStream resourceAsStream = SQLAccess.class.getClassLoader().getResourceAsStream("schema.sql")) {
         return new String(resourceAsStream.readAllBytes(), StandardCharsets.UTF_8).split("\n\n");
      }
   }

   public String[] inserts() throws IOException {
      try (InputStream resourceAsStream = SQLAccess.class.getClassLoader().getResourceAsStream("insert.sql")) {
         return new String(resourceAsStream.readAllBytes(), StandardCharsets.UTF_8).split("\n\n");
      }
   }
}
