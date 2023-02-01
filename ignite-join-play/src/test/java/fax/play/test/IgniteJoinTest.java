package fax.play.test;

import java.io.IOException;

import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.sql.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import fax.play.generator.SchemaGen;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IgniteJoinTest {

   final SchemaGen schemaGen = new SchemaGen();

   IgniteClient client;

   @Test
   public void test() throws Exception {

   }

   @BeforeAll
   public void beforeAll() throws Exception {
      client = IgniteClient.builder()
            .addresses("localhost:10800")
            .build();

      initDDL();
   }

   @AfterAll
   public void afterAll() throws Exception {
      if (client != null) {
         client.close();
      }
   }

   private void initDDL() throws IOException {
      try (Session ses = client.sql().createSession()) {
         for (String ddlCommand : schemaGen.ddlCommands()) {
            ses.execute(null, ddlCommand).close();
         }
      }
   }
}
