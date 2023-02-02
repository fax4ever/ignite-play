package fax.play.test.util;

import java.util.Iterator;

import org.apache.ignite.sql.ResultSet;
import org.apache.ignite.sql.SqlRow;

public final class ResultSetDumper {

   private ResultSetDumper() {
   }

   public static String dump(ResultSet resultSet) {
      StringBuilder builder = new StringBuilder();


      while (resultSet.hasNext()) {
         builder.append("\n[ ");
         dumpTo(resultSet.next(), builder);
         builder.append(" ]");
      }

      return builder.toString();
   }

   public static void dumpTo(SqlRow row, StringBuilder builder) {
      Iterator<Object> iterator = row.iterator();

      while (iterator.hasNext()) {
         Object element = iterator.next();
         builder.append(element);
         builder.append("\t");
      }
   }


}
