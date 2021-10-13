/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sw.ui;

import amino.data.DateObservation;
import amino.data.ReadWrite;
import amino.data.TextFile;
import amino.ui.ExternelForm;
import amino.ui.PanelGridBag;
import amino.ui.util.AButton;
import amino.ui.util.LabledComponent;
import amino.ui.util.TextBox;
import amino.ui.util.i18n;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.swing.JButton;
import javax.swing.JOptionPane;
//import org.apache.commons.vfs.FileName;
import process.UpdateIndex;
import sw.db.DB;
import sw.db.Envilop;
import sw.db.ctrlData;

/**
 *
 * @author User
 */
public class MigrateMySQLtoExcel extends ExternelForm {

  /**
   *
   */
  private static final long serialVersionUID = 20101101L;
  PanelGridBag pgb;
  TextBox tbOutFolderName;//tbTable,tbKeyField,tbDateField,
  AButton jbMig;//,jbFineCheck,jbFineCheckAllDB,jbMigAllDB,jbUpdt2SpaceBetweenDateTime,jbUpdateIndex;
  AButton jbGenTblList;
  
  public MigrateMySQLtoExcel() {
    super("MySQL Translate Tool", 400, 400);
    initcomponent();
    addListener();
    initDefaultValues();
    setVisible(true);
  }

  public void actionPerformed(ActionEvent ae) {
    super.actionPerformed(ae);
    
 
    jbGenTblList= new AButton("Gen Table List");
    
    if(ae.getActionCommand().equals("Translate to Excel")){
      migMySQL();
    }else if(ae.getActionCommand().equals("Gen Table List")){
      genFullTblList();
    }
    // dd
//    if (ae.getActionCommand().equals("Translate to Excel")){
//      checkFroDate();
//    }else if (ae.getActionCommand().equals(i18n.getString("DateChecker.FineCheckButton"))){
//      finecheckFroDate();
//    }else if (ae.getActionCommand().equals(i18n.getString("DateChecker.FineCheckAllDBButton"))){
//      fineCheckAllDB();
//    }else if (ae.getActionCommand().equals(i18n.getString("DateChecker.CheckAllDBButton"))){
//      CheckAllDB();
//    }else if (ae.getActionCommand().equals(i18n.getString("DateChecker.Update2Space"))){
//      update2spaceBetweenDateTime();
//    }else if (ae.getActionCommand().equals(i18n.getString("DateChecker.UpdateIndex"))){
//      UpdateIndex.updateFile();
//    }
  }

  private void migMySQL() {

    String path = tbOutFolderName.getText().trim();

    DB db = new DB();
    //  try {
    // ResultSet rs = db.readData(new Envilop(sql));

    //String tblList[] = {"accounts", "monthtrn"};
    
    String tblList[] = null;
    try {
      tblList = ReadWrite.readFile("TableList.txt");
    } catch (FileNotFoundException ex) {
      Logger.getLogger(MigrateMySQLtoExcel.class.getName()).log(Level.SEVERE, null, ex);
    }

    System.out.println("Path = " + path);
    //while(rs.next()){
    for (int x = 0; x < tblList.length; x++) {

      //String tbl=rs.getString(1);
      String tbl = tblList[x];

      System.out.println("Generate -- " + tbl);
      //generateFile(tbl,path,db);
      System.out.println("Col Name Update " + tbl);
      // findColName(tbl,path,db);
      String colList[] = findColNameList(tbl, path, db);
      // generateFile(tbl,path,db);
      readToExcelMultiFile(tbl, colList, path);
    }
    //readAccountsTable();
    // readMonthTrnsTableMultiFile();

//    } catch (SQLException ex) {
//      Logger.getLogger(MigrateMySQLtoExcel.class.getName()).log(Level.SEVERE, null, ex);
//    }
  }

  private void genFullTblList() {
    String sql = "show tables";
    String path = tbOutFolderName.getText().trim();
    DB db = new DB();
    try {
      ResultSet rs = db.readData(new Envilop(sql));
      while (rs.next()) {
        String tbl = rs.getString(1);
        findColName(tbl, path, db);
      }
    } catch (SQLException ex) {
      Logger.getLogger(MigrateMySQLtoExcel.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  private void readToExcelMultiFile(String tblName, String[] colList, String path) {
    String sql = "select * from " + tblName;
    DB db = new DB();
    ResultSet rs = null;
    try {
      rs = db.readData(new Envilop(sql));
    } catch (SQLException ex) {
      Logger.getLogger(MigrateMySQLtoExcel.class.getName()).log(Level.SEVERE, null, ex);
    }

    int nc = colList.length;
    String dt[] = new String[nc];
    int nRows = 1;
    int fileNo = 10;
    ReadWrite.fileAppend(path + "/" + tblName + "_" + fileNo + ".csv", colList);
    try {
      while (rs.next()) {
        for (int c = 0; c < nc; c++) {
          dt[c] = rs.getString(c + 1);
        }
        System.out.println(dt[0] + " :: " + dt[1]);
        ReadWrite.fileAppend(path + "/" + tblName + "_" + fileNo + ".csv", dt);
        nRows++;
        if (nRows > 100000) {
          fileNo++;
          nRows = 1;
          ReadWrite.fileAppend(path + "/" + tblName + "_" + fileNo + ".csv", colList);
        }
      }
    } catch (SQLException ex) {
      Logger.getLogger(MigrateMySQLtoExcel.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  private void readMonthTrnsTableMultiFile() {
//    String sql="select * from accounts where AccNo = '1000001' or AccNo ='1000062' or AccNo ='1000063' or AccNo ='1000065' ";
    String sql = "select * from monthtrn";
    DB db = new DB();
    ResultSet rs = null;
    try {
      rs = db.readData(new Envilop(sql));
    } catch (SQLException ex) {
      Logger.getLogger(MigrateMySQLtoExcel.class.getName()).log(Level.SEVERE, null, ex);
    }

    int nc = 27;
    String dt[] = new String[nc];
    int nRows = 0;
    int fileNo = 10;
    try {
      while (rs.next()) {
        for (int c = 0; c < nc; c++) {
          dt[c] = rs.getString(c + 1);
        }
        System.out.println(dt[0] + " :: " + dt[1]);
        ReadWrite.fileAppend("MonthTrans" + fileNo + ".csv", dt);
        nRows++;
        if (nRows > 100000) {
          fileNo++;
          nRows = 0;
        }
      }
    } catch (SQLException ex) {
      Logger.getLogger(MigrateMySQLtoExcel.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  private void readMonthTrnsTable() {
//    String sql="select * from accounts where AccNo = '1000001' or AccNo ='1000062' or AccNo ='1000063' or AccNo ='1000065' ";
    String sql = "select * from monthtrn";
    DB db = new DB();
    ResultSet rs = null;
    try {
      rs = db.readData(new Envilop(sql));
    } catch (SQLException ex) {
      Logger.getLogger(MigrateMySQLtoExcel.class.getName()).log(Level.SEVERE, null, ex);
    }

    int nc = 27;
    String dt[] = new String[nc];
    try {
      while (rs.next()) {
        for (int c = 0; c < nc; c++) {
          dt[c] = rs.getString(c + 1);
        }
        System.out.println(dt[0] + " :: " + dt[1]);
        ReadWrite.fileAppend("MonthTrans.csv", dt);
      }
    } catch (SQLException ex) {
      Logger.getLogger(MigrateMySQLtoExcel.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  private void readAccountsTable() {
//    String sql="select * from accounts where AccNo = '1000001' or AccNo ='1000062' or AccNo ='1000063' or AccNo ='1000065' ";
    String sql = "select * from accounts";
    DB db = new DB();
    ResultSet rs = null;
    try {
      rs = db.readData(new Envilop(sql));
    } catch (SQLException ex) {
      Logger.getLogger(MigrateMySQLtoExcel.class.getName()).log(Level.SEVERE, null, ex);
    }

    int nc = 54;
    String dt[] = new String[nc];
    try {
      while (rs.next()) {
        for (int c = 0; c < nc; c++) {
          dt[c] = rs.getString(c + 1);
        }
        System.out.println(dt[0]);
        ReadWrite.fileAppend("accounts.csv", dt);
      }
    } catch (SQLException ex) {
      Logger.getLogger(MigrateMySQLtoExcel.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  private String[] findColNameList(String tblName, String path, DB db) {
    String colName = "\n" + tblName + ",";
    String colListTmp[] = new String[1000];
    int nCols = 0;
    String sql = "SHOW COLUMNS FROM " + tblName;
    try {
      ResultSet rsCol = db.readData(new Envilop(sql));
      while (rsCol.next()) {
        String col = rsCol.getString(1);
        colName = colName + "," + col;
        colListTmp[nCols] = col;
        nCols++;
      }
    } catch (SQLException ex) {
      Logger.getLogger(MigrateMySQLtoExcel.class.getName()).log(Level.SEVERE, null, ex);
    }
    String colList[] = new String[nCols];
    for (int x = 0; x < nCols; x++) {
      colList[x] = colListTmp[x];
    }

    //TextFile.fileAppend(path + "AAAcolName.csv", colName);
    return colList;
  }

  private void findColName(String tblName, String path, DB db) {
    String colName = "\n" + tblName + ",";
    String sql = "SHOW COLUMNS FROM " + tblName;
    try {
      ResultSet rsCol = db.readData(new Envilop(sql));
      while (rsCol.next()) {
        colName = colName + "," + rsCol.getString(1);
      }
    } catch (SQLException ex) {
      Logger.getLogger(MigrateMySQLtoExcel.class.getName()).log(Level.SEVERE, null, ex);
    }

    TextFile.fileAppend(path + "AAAcolName.csv", colName);
  }

  private void generateFile(String tblName, String path, DB db) {
    String sql = "SELECT * INTO OUTFILE '" + path + tblName + ".csv' "
            + "    FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"' "
            + "    LINES TERMINATED BY '\\n' "
            + " FROM " + tblName + " ";
    System.out.println("sql " + sql);

    try {
      db.updateData(new Envilop(sql));
    } catch (SQLException ex) {
      System.out.println("sql " + sql);

      Logger.getLogger(MigrateMySQLtoExcel.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  private void initDefaultValues() {
//    tbTable.setText("tbl_MCustomer");
//    tbKeyField.setText("cusid");
//    tbDateField.setText("DOB");
    tbOutFolderName.setText("d:/tmp/");
  }

//  private void fineCheckAllDB() {
//    DB db = new DB();
//    String sql ="SELECT t.name AS tblName, c.name AS colName " +
//    " FROM sys.tables AS t INNER JOIN sys.columns AS c ON t.object_id = c.object_id " +
//    " WHERE (c.name LIKE N'%date%') AND (c.user_type_id = 167) ORDER BY tblName";
//    try {
//      ResultSet rs = db.readData(new Envilop(sql));
//      while(rs.next()){
//        finecheckFroAllDBDate(rs.getString("tblName"),rs.getString("colName"));
//      }
//    } catch (SQLException ex) {
//      Logger.getLogger(MigrateMySQLtoExcel.class.getName()).log(Level.SEVERE, null, ex);
//    }
//  }
//  private void CheckAllDB() {
//    DB db = new DB();
//    String sql ="SELECT t.name AS tblName, c.name AS colName " +
//    " FROM sys.tables AS t INNER JOIN sys.columns AS c ON t.object_id = c.object_id " +
//    " WHERE (c.name LIKE N'%date%') AND (c.user_type_id = 167) ORDER BY tblName";
//    try {
//      ResultSet rs = db.readData(new Envilop(sql));
//      while(rs.next()){
////        finecheckFroAllDBDate(rs.getString("tblName"),rs.getString("colName"));
//        checkFroDate(rs.getString("tblName"),rs.getString("colName"));
//      }
//    } catch (SQLException ex) {
//      Logger.getLogger(MigrateMySQLtoExcel.class.getName()).log(Level.SEVERE, null, ex);
//    }
//  }
//  private void finecheckFroAllDBDate(String tblName,String colName){
//    DB db = new DB();
//
//
//    String tmpTbl="CREATE TABLE [tmpDateChecker]( " +
//        " [rowid] [numeric](18, 0) IDENTITY(1,1) NOT NULL, " +
//        " [gDate] [datetime] NOT NULL default '2000-01-01', [sDate] [smalldatetime] NOT NULL default '2000-01-01' " +
//        " ) ON [PRIMARY]";
//    try {
//        db.updateTmpRptData(new Envilop(tmpTbl));
//    } catch (SQLException ex) {
//        System.out.println("temp table createion error");
//    }
//    ResultSet rsOrigTable=null;
//    String sql = "select "+ colName +" as dateCol  from " + tblName;
//    try {
//        System.out.println(sql);
//        rsOrigTable = db.readData(new Envilop(sql));
//        String fileName= tbOutFolderName.getText().trim() + "/fineAllDB-" + tblName + "-" + colName + ".txt";
//        TextFile.fileWrite(fileName ,null);
//        int r=0;
//        while (rsOrigTable.next()) {
//            insertData(db,rsOrigTable.getString("dateCol"),fileName);
//            r++;
//            if(r==1000){
//              r=0;
//              String sqlDel="DELETE FROM tmpDateChecker";
//              db.updateTmpRptData(new Envilop(sqlDel));
//            }
//        }
//    } catch (SQLException ex) {
//        System.out.println(" opening error ");
//    }
//
//    String sqlDrop="drop table tmpDateChecker";
//        try {
//            db.updateTmpRptData(new Envilop(sqlDrop));
//        } catch (SQLException ex) {
//            Logger.getLogger(MigrateMySQLtoExcel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//  }
//
//  private void insertData(DB db,String date,String fileName){
//
//    try {
////      String sqlInst=" INSERT INTO tmpDateChecker ( gDate,sDate) " +
////      "VALUES ( CONVERT(DATETIME, '"+ date +"', 102),CONVERT(DATETIME, '"+ date.substring(0,10) +"', 102))";
//      String sqlInst=" INSERT INTO tmpDateChecker ( gDate,sDate) " +
//      "VALUES ( CONVERT(DATETIME, '"+ date +"', 102),CONVERT(DATETIME, '2000-01-01', 102))";
//     // System.out.println("sss " + sqlInst);
//        db.updateTmpRptData(new Envilop(sqlInst));
//    } catch (SQLException ex) {
//      System.out.println(" exception found ");
//        TextFile.fileAppend(fileName,"Date >> " + date +"\n");
//    } catch (Exception ex){
//      TextFile.fileAppend(fileName," ---  Date  >> " + date +"\n");
//    }
//  }
//
//  private void finecheckFroDate(){
//    DB db = new DB();
//
//
//    String tmpTbl="CREATE TABLE [tmpDateChecker]( " +
//        " [rowid] [numeric](18, 0) IDENTITY(1,1) NOT NULL, [id] [nvarchar](50) NOT NULL default 0, " +
//        " [gDate] [datetime] NOT NULL default '2000-01-01', [sDate] [smalldatetime] NOT NULL default '2000-01-01' " +
//        " ) ON [PRIMARY]";
//
//    String sql = "select "+ tbKeyField.getText() +" as keyCol , "+ tbDateField.getText() +" as dateCol  from " + tbTable.getText();
//    try {
//        db.updateTmpRptData(new Envilop(tmpTbl));
//    } catch (SQLException ex) {
//        System.out.println("temp table createion error");
//    }
//    ResultSet rsOrigTable=null;
//    try {
//        System.out.println(sql);
//        rsOrigTable = db.readData(new Envilop(sql));
//        String fileName= tbOutFolderName.getText().trim() + "/fine" + tbTable.getText().trim() + "-" + tbDateField.getText().trim() + ".txt";
//        TextFile.fileWrite(fileName ,null);
//        while (rsOrigTable.next()) {
//            insertData(db,rsOrigTable.getString("keyCol"),rsOrigTable.getString("dateCol"),fileName);
//            //try to del 1000
//        }
//    } catch (SQLException ex) {
//        System.out.println(" opening error ");
//    }
//
//    String sqlDrop="drop table tmpDateChecker";
//        try {
//            db.updateTmpRptData(new Envilop(sqlDrop));
//        } catch (SQLException ex) {
//            Logger.getLogger(MigrateMySQLtoExcel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//  }
//  private void insertData(DB db,String key,String date,String fileName){
//    try {
//      String sqlInst=" INSERT INTO tmpDateChecker (id, gDate,sDate) " +
//      "VALUES (N'"+ key +"', CONVERT(DATETIME, '"+ date +"', 102),CONVERT(DATETIME, '"+ date.substring(0,10) +"', 102))";
//      db.updateTmpRptData(new Envilop(sqlInst));
//    } catch (SQLException ex) {
//        TextFile.fileAppend(fileName,"Date >> " + date +" KeyValue " + key +"\n");
//    }catch (Exception ex){
//      TextFile.fileAppend(fileName," ---  Date  >> " + date +"\n");
//    }
//  }
//
//
//
//  private void checkFroDate(String tableName,String dateField){
//    System.out.println("Start Checking Process");
//    //tbTable,tbKeyField,tbDateField
//    DB db = new DB();
//    String sql = "select * from " + tableName + " order by " + dateField ;
//    ResultSet rs = null;
//    String fileName= tbOutFolderName.getText().trim() + "/all-" + tableName.trim() + "-" + dateField.trim() + ".txt";
//    TextFile.fileWrite(fileName ,null);
//    try {
//      rs = db.readData(new Envilop(sql));
//      int countTot=0,countVal=0,countInV=0;
//      DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//      while (rs.next()) {
//        String stDate=rs.getString(dateField.trim());
//        countTot++;
//        Date cDate=null;
//        if(DateObservation.isValid(stDate)){
//          countVal++;
//        }else{
//          countInV++;
//          String invaliedDate=rs.getString(dateField.trim());
//         // String keyValue=rs.getString(tbKeyField.getText().trim());
//          //System.out.println("Date >> " + invaliedDate +" KeyValue " + keyValue );
//          TextFile.fileAppend(fileName,"Date >> " + invaliedDate  +"\n");
//        }
//      }
//      TextFile.fileAppend(fileName,"===================== Summary ===================="+"\n");
//      TextFile.fileAppend(fileName,"Total : " + countTot + " Valied : " +countVal+ " InValied : " +countInV+"\n");
//    } catch (SQLException ex) {
//      Logger.getLogger(MigrateMySQLtoExcel.class.getName()).log(Level.SEVERE, null, ex);
//    }
//   // JOptionPane.showMessageDialog(this, i18n.getString("msg.DateCheckingProcessCompleted"));
//  }
//
//
//  private void checkFroDate(){
//    System.out.println("Start Checking Process");
//    //tbTable,tbKeyField,tbDateField
//    DB db = new DB();
//    String sql = "select * from " + tbTable.getText() + " order by " + tbDateField.getText() ;
//    ResultSet rs = null;
//    String fileName= tbOutFolderName.getText().trim() + "/" + tbTable.getText().trim() + "-" + tbDateField.getText().trim() + ".txt";
//    TextFile.fileWrite(fileName ,null);
//    try {
//      rs = db.readData(new Envilop(sql));
//      int countTot=0,countVal=0,countInV=0;
//      DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//      while (rs.next()) {
//        String stDate=rs.getString(tbDateField.getText().trim());
//        countTot++;
//        Date cDate=null;
//        if(DateObservation.isValid(stDate)){
//          countVal++;
//        }else{
//          countInV++;
//          String invaliedDate=rs.getString(tbDateField.getText().trim());
//          String keyValue=rs.getString(tbKeyField.getText().trim());
//          //System.out.println("Date >> " + invaliedDate +" KeyValue " + keyValue );
//          TextFile.fileAppend(fileName,"Date >> " + invaliedDate +" KeyValue " + keyValue +"\n");
//        }
//      }
//      TextFile.fileAppend(fileName,"===================== Summary ===================="+"\n");
//      TextFile.fileAppend(fileName,"Total : " + countTot + " Valied : " +countVal+ " InValied : " +countInV+"\n");
//    } catch (SQLException ex) {
//      Logger.getLogger(MigrateMySQLtoExcel.class.getName()).log(Level.SEVERE, null, ex);
//    }
//    JOptionPane.showMessageDialog(this, i18n.getString("msg.DateCheckingProcessCompleted"));
//  }
  private void addListener() {
    jbMig.addActionListener(this);
    jbGenTblList.addActionListener(this);
//    jbFineCheck.addActionListener(this);
//    jbFineCheckAllDB.addActionListener(this);
//    jbMigAllDB.addActionListener(this);
//    jbUpdt2SpaceBetweenDateTime.addActionListener(this);
//    jbUpdateIndex.addActionListener(this);
  }

  private void initcomponent() {
    pgb = new PanelGridBag();
//    tbTable=new TextBox(15);
//    tbDateField=new TextBox(15);
//    tbKeyField=new TextBox(15);
    tbOutFolderName = new TextBox(15);
    jbMig = new AButton("Translate to Excel");
    jbGenTblList= new AButton("Gen Table List");
//    jbFineCheck=new AButton(i18n.getString("DateChecker.FineCheckButton"));
//    jbFineCheckAllDB=new AButton(i18n.getString("DateChecker.FineCheckAllDBButton"));
//    jbMigAllDB=new AButton(i18n.getString("DateChecker.CheckAllDBButton"));
//    jbUpdt2SpaceBetweenDateTime=new AButton(i18n.getString("DateChecker.Update2Space"));
//    jbUpdateIndex=new AButton(i18n.getString("DateChecker.UpdateIndex"));

//    pgb.add(new LabledComponent(i18n.getString("DateChecker.TableName"), tbTable),0,5,50,1);
//    pgb.add(new LabledComponent(i18n.getString("DateChecker.DateField"), tbDateField),0,10,50,1);
//    pgb.add(new LabledComponent(i18n.getString("DateChecker.KeyField"), tbKeyField),0,15,50,1);
//
//    jbMigAllDB.setBackground(Color.YELLOW);
//
//    jbFineCheckAllDB.setBackground(Color.YELLOW);
            
                    pgb.add(jbGenTblList, 0, 18, 50, 1);
    pgb.add(jbMig, 0, 20, 50, 1);
    
//    pgb.add(jbFineCheck,0,22,50,1);
//    pgb.add(jbMigAllDB,0,23,50,1);
//    pgb.add(jbFineCheckAllDB,0,25,50,1);
//
//
//    jbUpdt2SpaceBetweenDateTime.setBackground(Color.GREEN);
//    jbUpdateIndex.setBackground(Color.GREEN);
    pgb.add(new LabledComponent(i18n.getString("DateChecker.LogFilePath"), tbOutFolderName), 0, 27, 50, 1);
//    pgb.add(jbUpdt2SpaceBetweenDateTime,0,29,50,1);
//    pgb.add(jbUpdateIndex,0,30,50,1);

    add(pgb);

  }

//  private void update2spaceBetweenDateTime() {
//    DB db = new DB();
//    String sql ="SELECT t.name AS tblName, c.name AS colName " +
//    " FROM sys.tables AS t INNER JOIN sys.columns AS c ON t.object_id = c.object_id " +
//    " WHERE (c.name LIKE N'%date%') AND (c.user_type_id = 167) ORDER BY tblName";
//
//    try {
//      ResultSet rs = db.readData(new Envilop(sql));
//      while(rs.next()){
//        String tbl=rs.getString("tblName");
//        String col=rs.getString("colName");
//        System.out.println("" + tbl + " >> " +col);
//        update2Space(tbl,col);
//      }
//    } catch (SQLException ex) {
//
//      System.err.print(ex);
//    }
//  }
//  private void update2Space(String tbl,String col){
//    DB db=new DB();
//    String sqlUpdt="";
//    sqlUpdt="UPDATE "+ tbl +" SET "+col+" = left("+col+",10) + ' ' + right("+col+",len("+col+")-12) WHERE ("+col+" LIKE '%%%%-%%-%%  %%:%')";
//    //System.out.println("" + sqlUpdt);
//    try {
//      db.updateTmpRptData(new Envilop(sqlUpdt));
//    } catch (SQLException ex) {
//      JOptionPane.showMessageDialog(this, "please check " + tbl + "." + col + " manualy");
//    }
//
//  }
}
