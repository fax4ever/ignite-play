package fax.play.generator;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class SchemaGen {

   public String[] ddlCommands() throws IOException {
      try (InputStream resourceAsStream = SchemaGen.class.getClassLoader().getResourceAsStream("ddl.sql")) {
         return new String(resourceAsStream.readAllBytes(), StandardCharsets.UTF_8).split("\n\n");
      }
   }
}
