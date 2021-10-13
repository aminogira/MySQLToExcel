/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package process;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sw.db.Envilop;
import sw.db.DB;
/**
 *
 * @author amino
 */
public class UpdateIndex {
  public static void updateFile() {
    File file = new File("sql/IndexQuery.txt");
    //StringBuffer contents = new StringBuffer();
    BufferedReader reader = null;

    try {
      reader = new BufferedReader(new FileReader(file));
      String text = null;
      DB db=new DB();
      // repeat until all lines is read
      while ((text = reader.readLine()) != null) {
        System.out.println(text);
        try {
          db.updateData(new Envilop(text));
        } catch (SQLException ex) {
          System.out.println(" not runned " + text );
          Logger.getLogger(UpdateIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
        //db.updateData(new Envilop(text));
        //  contents.append(text).append(System.getProperty("line.separator"));
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (reader != null) {
          reader.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    // show file contents here
    //System.out.println(contents.toString());
  }



}
