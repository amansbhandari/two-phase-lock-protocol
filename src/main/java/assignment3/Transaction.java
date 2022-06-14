package assignment3;

import java.sql.SQLException;
import config.Config;
import config.TransactionQuery;
import dbhandler.DBConnector;

/**
 * Extends Thread to instantiate multiple thread objects through this class
 * Each object represents a transaction
 * We will be creating two objects in our solution as we need only two transaction in our problem
 *   
 * @author amansinghbhandari
 *
 */
public class Transaction extends Thread
{
	String name = "";				//Thread name
	static DBConnector dbconnector;	//need to keep it static to avoid multiple objects of it - SINGLETON

	static Boolean isLockAvailable=true;	//false when lock is available for thread
	static String threadWithLock="";	//thread who has possession of lock

	Transaction(String name)	
	{
		this.name = name;	
		try {
			if(dbconnector == null)	//Create one single instance of this static data member
			{
				dbconnector = new DBConnector(Config.DB_URL_LOCAL, Config.USER_LOCAL, Config.PASS_LOCAL, Config.DB_NAME_LOCAL);
				dbconnector.connect();
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

	synchronized void acquire_locks() throws InterruptedException
	{
		if(isLockAvailable == true)		//lock is available for this thread
		{
			isLockAvailable = false;		//lock is not available now
			threadWithLock = this.name;		//save the name of the thread who acquired lock
			System.out.println("-- Lock by Thread " + this.name + "--");
		}
		else
		{
			this.wait();					//Lock acquired, thread has to wait
		}
	}

	synchronized void release_locks() throws InterruptedException
	{
		if(threadWithLock == this.name && isLockAvailable == false)	//check if the same thread is releasing lock
		{
			isLockAvailable = true;			//make lock available again
			threadWithLock = "";			//No thread jas acquired lock
			this.notifyAll();				//Wake every other thread who has been waiting to acquire lock
			System.out.println("-- Unlock by Thread " + this.name + "--");
		}
	}
	
	/**
	 * Executes a query in database 
	 * 
	 * @param query query as string
	 * @param isSelectQuery true if the query is a select query
	 * @throws SQLException
	 */
	void transaction(String query , Boolean isSelectQuery) throws SQLException
	{
		if(isSelectQuery == false)	
		{
			System.out.println("Executing Update Statement by " + this.name);
			dbconnector.executeUpdate(query);
		}
		else
		{
			System.out.println("Executing Select Statement by " + this.name);
			dbconnector.executeQuery(query);
		}
	}
	
	public void run()
	{
		
		try
		{
			System.out.println("Setting Autocommit false by " + this.name);
			dbconnector.connection.setAutoCommit(false);	//Set auto commit false
			
			if(this.name == "T1")//Run the first select statement for thread T1 and put it into wait so that T2 can acquire lock
			{//No lock is required since it is read operation. However we can apply "shared lock" here. But in this solution we are limiting to no locks for read operations
				this.transaction(TransactionQuery.QUERY_SELECT1, true);
				this.sleep(2000);
			}
			
			if(this.name == "T2")
			{
				acquire_locks();	//to execute a write operation
				this.transaction(TransactionQuery.QUERY_UPDATE1, false);	//write operation by T2
				this.transaction(TransactionQuery.QUERY_SELECT1, true);		//read operation by T2
				release_locks();	//Release lock to be acquired by T1 now
			}
			
			if(this.name == "T1")
			{
				acquire_locks();	//lock by T1 to write operation
				this.transaction(TransactionQuery.QUERY_UPDATE1, false);	//write op by T1
				release_locks();	//release lock by T1
			}
			
			if(this.name == "T2")
			{
				this.sleep(3000);	//to put T2 in sleep so that T1 can commit first 
			}
			
			
			System.out.println("Commit by " + this.name);
			dbconnector.connection.setAutoCommit(true);
			
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
}
	

}
