
/*      */ import java.sql.Connection;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.Statement;

/**
 *
 * @author amila
 */
public class NewSTSSavings {
static Connection con = null;

   public static String[][] getCusOldLoan(String savAcno,String gdate) {
     String cusid="";
     String [][] ans=new String[5][3];
     int savcnt = 0;
     try {
       con = DBCon.getConnection();
       Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT cusid FROM tbl_SavAcCus WHERE sacno='"+ savAcno +"' and remarks='MAIN CUSTOMER'");

        if(rs.next()){
          cusid=rs.getString("cusid");
          System.out.println("cusid " + cusid);
        }
        rs.close();
      }catch (Exception e) {
      } finally {
        DBCon.returnConnection(con);
      }

     try {
       con = DBCon.getConnection();
       Statement s = con.createStatement();
       s.executeUpdate("drop view vFindOldDueLoans");
     }catch (Exception e) {
      } finally {
        DBCon.returnConnection(con);
      }

     try {
       con = DBCon.getConnection();
       Statement s = con.createStatement();
      String sql="CREATE VIEW vFindOldDueLoans as  " +
      " SELECT tbl_LOANAc.loanacno, tbl_LOANAc.lcbal, tbl_LOANAc.cusid, tbl_LOANAc.Status, tbl_LOANDues.ldno, tbl_LOANDues.duedate, tbl_LOANDues.dueamt, tbl_LOANDues.dubal " +
      " FROM tbl_LOANAc INNER JOIN tbl_LOANDues ON tbl_LOANAc.loanacno = tbl_LOANDues.lacno " +
      " WHERE (tbl_LOANAc.Status <> 'CLOSED') AND (tbl_LOANDues.dubal > 0) AND (CONVERT(DATETIME, tbl_LOANDues.duedate, 102) BETWEEN CONVERT(DATETIME, '1950-01-01 00:00:00', 102) AND CONVERT(DATETIME, '"+ gdate +"', 102)) AND (tbl_LOANAc.cusid = "+ cusid +")";
       s.executeUpdate(sql);
     }catch (Exception e) {
      } finally {
        DBCon.returnConnection(con);
      }

     try {
       con = DBCon.getConnection();
       Statement s = con.createStatement();
//      String sql="SELECT  top(5) loanacno, SUM(dubal) AS duBalTot, DATEDIFF(mm, MIN(duedate), '"+ gdate.substring(0,10) +"') AS dueMonths FROM vLoanDueMonthDetailAll " +
//      " WHERE (cusid = "+ savAcno +") AND (Status <> 'CLOSED') GROUP BY bid, loanacno, cusid ORDER BY dueMonths DESC, loanacno " ;
      String sql="SELECT top(5)  loanacno, SUM(dubal) AS duBalTot, DATEDIFF(mm, MIN(duedate), '"+ gdate.substring(0,10) +"') AS dueMonths " +
      " FROM vFindOldDueLoans GROUP BY loanacno, cusid ORDER BY cusid, loanacno " ;
       System.out.println("sql " + sql);
      int r=0;
       ResultSet   rs = s.executeQuery(sql);
     while(rs.next()){
       ans[r][0]=rs.getString("loanacno");
       ans[r][1]=rs.getString("dueMonths");
       ans[r][2]=rs.getDouble("duBalTot") + "0";
       r++;
     }
     while(r<5){
       ans[r][0]="-";
       ans[r][1]="-";
       ans[r][2]="-";
       r++;
     }

     }catch (Exception e) {
      } finally {
        DBCon.returnConnection(con);
      }

     return ans;
  }

}
