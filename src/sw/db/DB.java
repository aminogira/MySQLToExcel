package sw.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;

import sw.proConst;
import amino.ui.util.FoundItem;
import sw.ui.ProgramSetting;
//import sw.ctrl.StringEncryptor;

public class DB {
	
	Connection con;
	
	public DB(){
		con=getConn();
	}
  
//  public static void backupDatabase(){
//  	try{
//  		//Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "C:/Documents and Settings/Mihira Dharmasiri/My Documents/NetBeansProjects/coop/report-out/"+tmpFileName+".pdf");   //open the file chart.pdf
//			//System.out.println(CommonCons.PROPATH + "/backup.bat");
//  		Runtime.getRuntime().exec(CommonCons.PROPATH + "/backup.bat");   //open the file chart.pdf
//  		//String s=CommonCons.PROPATH;
//  	}catch (Exception e){
//  		//System.out.println("Error" + e );  //print the error
//  		//JOptionPane.showMessageDialog(null,"");
//  	}	  
//  }
	
  public ResultSet readData(Envilop env)throws SQLException{
		String sql=env.getSQL();
//		try{
			Statement stm=con.createStatement();
			ResultSet rs=stm.executeQuery(sql);
			return rs;
//		}catch(SQLException e){
//			e.printStackTrace();
//		}		
	//	return null;
	}	
  
  public boolean isTableAvailabal(String tblName){
  	boolean ans=false;
  	String sql="show tables where tables_in_"+ proConst.dbName.trim() +"='"+ tblName +"'";
  	try {
			ResultSet rs=readData(new Envilop(sql));
			if (rs.next()) ans=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
  	return ans;
  }
  
  
	@ Deprecated
  public void updateData(Envilop env)throws SQLException {
		String sql=env.getSQL();
		//System.out.println("updateData@DB \n" + sql);
		Statement stm=con.createStatement();
		stm.execute(sql);
		//System.out.println("Data Updated");
		if (isTmpTblUpdation(sql)) return;
//		if ((LogConst.currentUser==null)) return;
		if(sql.indexOf("create")<0) {	
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date transAt=new Date();
//			String logSql="insert into loglist(date, transtype, branchid, transid, userid, modification, pcuser, quary) " +
//	    "values('"+ sdf.format(transAt) +"', '', "+ proConst.branchID +", 0, "+ LogConst.currentUser.getId() +", '"+ env.getTableModification() +"', '" + env.getDescription() +"', '"+ StringEncryptor.toHide(sql) +"')";
//	    stm.execute(logSql);
		}
    //System.out.println("Data Updated");
	}	
	
	private boolean isTmpTblUpdation(String sql){
		boolean ans=false;
		for(int x=0;x<proConst.tmpTbllist.length;x++){
			if (sql.indexOf(proConst.tmpTbllist[x])>0)
				ans=true;
		}
		return ans;
	}

	public void updateData(Envilop env,String transType,int transId)throws SQLException {
		String sql=env.getSQL();
		//System.out.println("updateData@DB \n" + sql);
		Statement stm=con.createStatement();
		stm.execute(sql);
		if (isTmpTblUpdation(sql)) return;
	//	if ((LogConst.currentUser==null)) return;
		if(sql.indexOf("create")<0){
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date transAt=new Date();
//			String logSql="insert into loglist(date, transtype, branchid, transid, userid, modification, pcuser, quary) " +
//	    "values('"+ sdf.format(transAt) +"', '"+ transType +"', "+ proConst.branchID +", "+ transId +", "+ LogConst.currentUser.getId() +", '"+ env.getTableModification() +"', '" + env.getDescription() +"', '"+ StringEncryptor.toHide(sql) +"')";
//	    stm.execute(logSql);
		}
    //System.out.println("Data Updated");
	}	
  
	public Connection getConn(){
	  try{
	  	con=null;
	  	////System.out.println( "Connecting Server .\n" + proConst.serverIp + ":" + proConst.serverPort + "/" + proConst.dbName);
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			//connect with password 
			con=DriverManager.getConnection("jdbc:mysql://" + proConst.serverIp + ":" + proConst.serverPort + "/" + proConst.dbName , proConst.user ,  proConst.passwd );
			////System.out.println("jdbc:mysql://" + proConst.serverIp + ":" + proConst.serverPort + "/" + proConst.dbName);
			//jdbc:mysql://192.168.1.7:3306/gl
			//connect without password 
			//con=DriverManager.getConnection("jdbc:mysql://" + proConst.serverIp + ":" + proConst.serverPort + "/" + proConst.dbName);

			con.setAutoCommit(false);
	    return con;
	  }catch (Exception e){
	    //System.out.println("DB Connection ERROR");
	  	new ProgramSetting();
	    e.printStackTrace();
	  }
	  return null;
	}
	public void commit(){
		try{
			con.commit();
		}catch(SQLException e){
			e.printStackTrace();
		}		
	}
	public void rollback(){
		try {
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void rollbackclose(){
		try {
			con.rollback();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void commitclose(){
		try{
			if (!(con.isClosed())){
				con.commit();
				con.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public void close(){
		try{
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}		
	}
	
	public int getNewNo(String tblName,String colName){
		String sql="select max(" + colName + ")+1 as NextID from " + tblName;
		////System.out.println(sql);
		ResultSet rs=null;
//		try{
//			Statement stm=con.createStatement();
//			rs=stm.executeQuery(sql);
//		}catch (SQLException e){
//			e.printStackTrace();
//		}		
		try {
			rs=readData(new Envilop(sql));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		int ans=1;
		try{
			rs.next();
			ans=rs.getInt("NextID");
		}catch(SQLException e){
			ans=1;
		}
		if (ans<=0) ans=1; 
		commit();
		return ans;
	}	
	
	public int getId(String tblName, String name){
		int ans=-2;
		ResultSet rs=null;
		String sql="select id from " + tblName + " where name = '" + name + "'";
		try {
			rs=readData(new Envilop(sql));
			rs.first();
			ans=rs.getInt("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ans;
	}
	public String getName(String tblName, int id){
		String ans="";
		ResultSet rs=null;
		String sql="select name from " + tblName + " where id = " + id;
		
		try {
			rs=readData(new Envilop(sql));
			ans=rs.getString("name");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ans;
	}
	
	public int getId(String tblName,String colName, String value){
		int ans=-5;
		ResultSet rs=null;
		String sql="select id from " + tblName + " where "+ colName +" = '" + value + "'";
		try {
			rs=readData(new Envilop(sql));
			rs.first();
			ans=rs.getInt("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ans;
	}
	
	//TODO check this
	public ResultSet findData(String tblName,String colName,String value){
		String sql="SELECT * FROM " + tblName + " where " + colName + "='" + value + "'";
		//System.out.println(sql);
		return findRecord(sql);
	}

	public ResultSet findData(String tblName,String colName,int datum){
		String sql="SELECT * FROM " + tblName + " where " + colName + "=" + datum;
		return findRecord(sql);
	}
	
	public Object findDatum(String tblName,String findColName,int findValue,String datumColumn) throws SQLException{
		Object datum;
		ResultSet rs=findData(tblName,findColName,findValue);
		rs.next();
		datum=rs.getObject(datumColumn);
		return datum;
	}
	public Object findDatum(String tblName,String findColName,String findValue,String datumColumn) throws SQLException{
		Object datum;
		ResultSet rs=findData(tblName,findColName,findValue);
		rs.next();
		datum=rs.getObject(datumColumn);
		return datum;
	}
//	public Object findDatum(String tblName,String findColName,String findValue,int datumColumn) throws SQLException{
//		Object datum;
//		ResultSet rs=findData(tblName,findColName,findValue);
//		datum=rs.getObject(datumColumn);
//		return datum;
//	}
	
	
	public ResultSet findRecord(String sql){
		
		try {
			return readData(new Envilop(sql));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
//		try{
//			Statement stm=con.createStatement();
//			return stm.executeQuery(sql);
//		}catch (SQLException e){
//			e.printStackTrace();
//		}		
		commitclose();
		return null;
	}
	//TODO check this its not working correctly
//	public boolean isNotFoundDatum(String tblName,String colName,String datum){
//		
//		boolean ans=true;
//		ResultSet rs=findData(tblName,colName,datum);
//		try {
//			rs.next();
//			ans=false;
//		} catch (SQLException e) {
//			ans=true;
//		}
//		return ans;
//	}
	
//	public boolean isNotFoundDatum(String tblName,String colName,int datum){
//		boolean ans=true;
//		ResultSet rs=findData(tblName,colName,datum);
//		try {
//			rs.next();
//			ans=false;
//		} catch (SQLException e) {
//			ans=true;
//		}
//		return ans;
//	}
	
	public boolean isDataFound(String tblName,String colName,int datum){
		boolean ans = false;
		String sql="Select * from "+ tblName +" where "+ colName +" = " + datum;
		ResultSet rs=findRecord(sql);
		try{
			if (rs.next()) {
				ans=true;
			}else{
				ans=false;
			}
		}catch(Exception e){
			ans=false;
		}
		return ans;
	}
	
	public boolean isDataFound(String tblName,String colName,String datum){
		boolean ans=false;
		String sql="Select * from "+ tblName +" where "+ colName +" = '" + datum + "'";
		ResultSet rs=findRecord(sql);
		try{
			if (rs.next()) {
				ans=true;
			}else{
				ans=false;
			}
		}catch(Exception e){
			ans=false;
		}
		return ans;
	}
	
	public FoundItem[] getFoundItem(ResultSet rs,String showingData){
		FoundItem fi[] = null;
		Stack<FoundItem> stk=new Stack<FoundItem>();
		//Queue<FoundItem> q=new Queue<FoundItem>();
		int count=0;
		try {
			while(rs.next()){
				////System.out.println(rs.getInt(1));
				////System.out.println(rs.getInt(1) + " : (" + rs.getString("code") + ") " + rs.getString("name") );
				FoundItem tmp=new FoundItem(rs.getInt("id"),rs.getString("code"),rs.getString("name"),showingData);
				count++;
				stk.push(tmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		fi=new FoundItem[count];
		for (int n=0;n<count;n++){
			fi[n]=stk.pop();
			//fi[n]=q.remove();
		}
		return fi;
	}
	
	public FoundItem[] getFoundItem(String tblName,String showingData){
//		FoundItem fi[] = null;
		String sql="";
		try{
			if (tblName.substring(0, 6).trim().compareToIgnoreCase("SELECT")==0)
				sql=tblName;
			//######################################################################################
			//EXAMPLE
			//"SELECT transId,socityId,CONCAT(transId,':',name,' ', socityId) as name,code FROM tLoanAggrement,mSocity where socityId=socId order by transId"
			//THE FIRST COLUMN MUST BE THE ID
			//THERE MUST BE COLUMNs AS name AND code 
			else
				sql="SELECT * FROM " + tblName + " group by " + showingData;
		}catch(StringIndexOutOfBoundsException e){
			sql="SELECT * FROM " + tblName + " group by " + showingData;
		}
		////System.out.println(sql);
		ResultSet rs=findRecord(sql);
		return getFoundItem(rs,showingData);
		
//		Stack<FoundItem> stk=new Stack<FoundItem>();
//		//Queue<FoundItem> q=new Queue<FoundItem>();
//		int count=0;
//		try {
//			while(rs.next()){
//				////System.out.println(rs.getInt(1));
//				////System.out.println(rs.getInt(1) + " : (" + rs.getString("code") + ") " + rs.getString("name") );
//				FoundItem tmp=new FoundItem(rs.getInt(1),rs.getString("code"),rs.getString("name"),showingData);
//				count++;
//				stk.push(tmp);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		fi=new FoundItem[count];
//		for (int n=0;n<count;n++){
//			fi[n]=stk.pop();
//			//fi[n]=q.remove();
//		}
//		return fi;
	}
	
	
	//public void updateTransDetail(Date fromdate,Date todate,int transno,int accno,int loanno,boolean isduplicate,Date transdate,String refno,int areaid,double OpeningBalances){
//	public void updateTransDetail(int transid,boolean isduplicate){
//		String sql="insert into tmpprinttrans(trnsid , isduplicate , branchid , userid) " +
//		"values( "+ transid +" , "+ isduplicate +" , "+ proConst.branchID +" , "+ LogConst.currentUser.getId() +")";
//		Envilop env=new Envilop(sql);
//		try {
//			updateData(new Envilop("DELETE FROM tmpprinttrans"));
//      updateData(env);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		//
//	}
	
//	public void updateTransDetail(int transid,boolean isduplicate,double amount){
//		Long lng=new Long((long) amount);
//		String amounttext=Converter.printValue(lng);
//		String sql="insert into tmpprinttrans(trnsid , isduplicate , branchid , userid, amounttext) " +
//		"values( "+ transid +" , "+ isduplicate +" , "+ proConst.branchID +" , "+ LogConst.currentUser.getId() +",left('"+ amounttext +"',150))";
//		Envilop env=new Envilop(sql);
//		try {
//			updateData(new Envilop("DELETE FROM tmpprinttrans"));
//      updateData(env);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		//amounttext
//	}
//	
		
	
//	public void updateReportDetail(Date trnsfrom , Date trnsto , int loanNo ,int accno ,int trnsno , Date trnsdate ,   double openingbalance  ){
//		
//		String sql="insert into tmpprintreport (trnsno,trnsfrom,trnsto,accno,loanno,trnsdate,openingbalance,userid,branchid) " +
//		"values("+ trnsno+",'"+ ctrlData.getDateString(trnsfrom) +"' , '"+ ctrlData.getDateString(trnsto) +"',"+ accno +","+ loanNo+",'"+ctrlData.getDateString(trnsdate) +"',"+ openingbalance +","+ LogConst.currentUser.getId() +","+ proConst.branchID +")";
//		Envilop env=new Envilop(sql);
//		try {
//			updateData(new Envilop("DELETE FROM tmpprintreport"));
//      updateData(env);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
//	public void updateReportDetail(Date trnsfrom , Date trnsto ,int areaid,int schmeeid,int officerid, int loanNo ,int accno ,int trnsno , Date trnsdate ,   double openingbalance  ){
//		String sql="insert into tmpprintreport(trnsfrom , trnsto , accno , loanno ,  trnsdate , areaid , schmeeid ,officerid, openingbalance , userid , branchid) " + 
//		"values('"+ ctrlData.getDateString(trnsfrom) +"' , '"+ ctrlData.getDateString(trnsto) +"' , "+ accno +" , "+ loanNo+" ,  '"+ctrlData.getDateString(trnsdate) +"' , "+ areaid +" , "+ schmeeid +" , "+ officerid +" , "+ openingbalance +" ,"+ LogConst.currentUser.getId() +","+ proConst.branchID +")";
//		System.out.println(sql);
//
//        Envilop env=new Envilop(sql);
//		try {
//			updateData(new Envilop("DELETE FROM tmpprintreport"));
//      updateData(env);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
}
