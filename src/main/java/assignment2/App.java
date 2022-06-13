package assignment2;

import java.sql.SQLException;

import config.Config;
import config.TransactionQuery;
import dbhandler.DBConnector;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        task1();
    }
    
    /**
     * performs Task2 of the assignment and shows the execution time
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public static void task1() throws ClassNotFoundException, SQLException
    {
    	DBConnector dbConnector = new DBConnector(Config.DB_URL,Config.USER,Config.PASS, Config.DB_NAME);
    	
    	dbConnector.connect();
    	
    	
    	dbConnector.executeQuery(TransactionQuery.TRANSACTION1_OP1);
    	
    	dbConnector.disconnect();
    }
}
