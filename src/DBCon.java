

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;

public class DBCon
{
  static String BankType = "CRB";
  static String ServerType = "Xpress";

  static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

  static String connectionString = "";
  static Vector released_connections = new Vector();

  public static String getBankType()
  {
    return BankType;
  }
  public static String getServerType() {
    return ServerType;
  }

  public static Connection getConnection() {
    String stspass = "i";
    if (released_connections.isEmpty())
    {
      try
      {
        Class.forName(driver);
        String pass = "tlbsns";

        connectionString = "jdbc:sqlserver://LGO\\SQLExpress;databaseName=TBS_Master;user=tbsroot;password=" + pass + ";";
      }
      catch (Exception e)
      {
        System.out.println("Inside DBCon : Unable to set Driver " + e);
      }

      try
      {
        Connection con = con = DriverManager.getConnection(connectionString);

        return con;
      }
      catch (Exception e)
      {
        System.out.println("Inside DBCon : Unable to create Connection " + e);
        return null;
      }

    }

    Connection con = (Connection)released_connections.firstElement();
    released_connections.removeElement(con);

    return con;
  }

  public static void returnConnection(Connection con)
  {
    released_connections.addElement(con);
  }
}