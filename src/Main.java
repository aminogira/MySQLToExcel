
import java.awt.Font;
import java.awt.Frame;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import amino.ui.util.AOptionPane;

import sw.EConst;
import sw.RegQuery;
import sw.db.ctrlData;
import sw.proConst;
import sw.ui.MigrateMySQLtoExcel;
//import ui.proConst;
//import ui.TBSMainForm;
//import ui.Login;
//import org.apache.poi.hssf.record.formula.functions.FinanceLib;

public class Main {
//static int a;

  /**
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("Good Luck");
    RegQuery.GetIP();
    proConst.getOpenID();
    //EConst.getOpenID();
    new MigrateMySQLtoExcel();
  }

  private static void findOldLoans() {
    String lnList[][] = NewSTSSavings.getCusOldLoan("6518-1171-50113", "2011-07-07");
    for (int x = 0; x < lnList.length; x++) {
      System.out.println(lnList[x][0] + "\t" + lnList[x][1] + "\t" + lnList[x][2] + "\t");
    }
  }

  private static void checkAndCheck() {
    System.out.println("1dddd");
    if (true | true) {
      System.out.println("2");
    }
    if (true || true) {
      System.out.println("3");
    }
    if (true | false) {
      System.out.println("4");
    }
    if (true || false) {
      System.out.println("5");
    }
    if (false | true) {
      System.out.println("6");
    }
    if (false || true) {
      System.out.println("7");
    }
    if (false | false) {
      System.out.println("8");
    }
    if (false || false) {
      System.out.println("9");
    }
  }

  private static void checkDateDef() {
    Date first, second;
    first = ctrlData.getDate("2010-01-01");
    second = ctrlData.getDate("2010-01-05");
    long a = second.getTime() - first.getTime();

    //Date third=first-second;
    //int a=first.compareTo(second);
    System.out.println(a);

  }

  public static void checkFor() {
    for (int x = 0; x < 10; x++) {
      if (x == 9) {
        System.out.println("last no");
      }
    }
  }

  public static void dateCheck() {
    Date da = new Date("2010/02/05");
    Date db = new Date("2010/05/15");
    Date dc = new Date("2010/05/15");

    System.out.println(db.before(da));
    System.out.println(db.after(da));
    System.out.println(da.before(db));
    System.out.println(da.after(db));
    System.out.println(da.before(dc));
    System.out.println(da.after(dc));
    System.out.println(db.before(dc));
    System.out.println(db.after(dc));
  }

  public static void test() {
    System.out.println(new Date());
    Properties p = System.getProperties();
    p.list(System.out);

    System.out.println("--- Memory Usage:");
    Runtime rt = Runtime.getRuntime();

    System.out.println("Total Memory = "
            + rt.totalMemory()
            + " Free Memory = "
            + rt.freeMemory());
  }

  private static void check2() {
    String ss = "1001-1651-10008";
    String s = ss.substring(6, 8);
    System.out.println(s);
  }

  private static void check() {

    double a = 23.345345, b = 56.633;

    System.out.println(a + "\t\t\t" + b);
    System.out.println(ctrlData.roundNoDecimals(a) + "\t\t\t" + ctrlData.roundNoDecimals(b));
    System.out.println(ctrlData.roundTwoDecimals(a) + "\t\t\t" + ctrlData.roundTwoDecimals(b));

//  System.out.println(System.getProperty("user.dir"));
//  System.out.println(System.getProperty("java.home"));
//  System.out.println(System.getProperty("java.io.tmpdir"));
//  System.out.println(System.getProperty("java.ext.dirs"));
//  System.out.println(System.getProperty("user.home"));
//  System.out.println(System.getProperty("user.dir"));
//  System.out.println(System.getProperty("file.separator"));
//  System.out.println(System.getProperty(""));
//  System.out.println(System.getProperty(""));
  }

}
