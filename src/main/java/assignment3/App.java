package assignment3;

import java.sql.SQLException;

import config.Config;
import config.TransactionQuery;
import dbhandler.DBConnector;

/**
 * 
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	Transaction t1 = new Transaction("T1");	//Thread 
    	t1.setPriority(10);
    	
    	Transaction t2 = new Transaction("T2");
    
    	t1.start();			//Transaction 1 should start first 
    	Thread.sleep(500);	//Delay to make sure that transaction 2 starts later
    	t2.start();			//Transaction 2 should start later. 

    }
}
