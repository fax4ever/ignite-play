package fax.play.test;

import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.sql.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IgniteJoinTest {

   IgniteClient client;

   @Test
   public void test() throws Exception {
      try (Session ses = client.sql().createSession()) {

      }
   }

   @BeforeAll
   public void beforeAll() {
      client = IgniteClient.builder()
            .addresses("localhost:10800")
            .build();
   }

   @AfterAll
   public void afterAll() throws Exception {
      if (client != null) {
         client.close();
      }
   }
}
