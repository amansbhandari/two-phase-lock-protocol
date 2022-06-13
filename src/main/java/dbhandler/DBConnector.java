package dbhandler;


import java.sql.*;

public class DBConnector {
   public String db_url;
   public String user;
   public String pass;
   public String dbname;
   public Connection connection;
  
  
   public DBConnector(String db_url, String user , String pass, String dbname)
   {
	  this.db_url = db_url;
	  this.user = user;
	  this.pass = pass;
	  this.dbname = dbname;
	  this.connection = null;
   }
   
   /**
    * Connects with mysql server
 * @throws ClassNotFoundException 
    */
   public void connect() throws SQLException, ClassNotFoundException
   {
	   if(connection == null)
	   {
		   //Connect to database
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   connection = DriverManager.getConnection(db_url+dbname, user, pass);
		   System.out.println("Database " + dbname+ " connection established..");
	   }
   }
   
   /**
    * close the connection with mysql
    */
   public void disconnect() throws SQLException
   {
	   
	   if(connection != null)		//connection is established
	   {
		   connection.close();		//close connection
		   connection = null; 		//making it null so that class can understand that there is no more connection
	   }

	   System.out.println("Database " + dbname+ " connection closed..");

   }
   
   /**
    * execute query
    * @param query sql query 
    * @return result of query
 * @throws SQLException 
    */
   public int executeQuery(String query) throws SQLException
   {
	   ResultSet rs = null;
	   Statement stmt = this.connection.createStatement();
	   rs = stmt.executeQuery(query);
	   
	   //-------------
		int rowno = 0;
		while(rs.next()) {
		 rowno++;
		}
	
		System.out.println(rowno + " records returned..");

	   return rowno++;
   }
   
   /**
    * Performs Create delete and update operations
    * @param query to update the table
    * @return result 
    * @throws SQLException 
    */
   public int executeUpdate(String query) throws SQLException
   {
	   int rs = 0; 
	  
	   Statement stmt = this.connection.createStatement();
	   rs = stmt.executeUpdate(query);
	     
	   return rs;
   }
}