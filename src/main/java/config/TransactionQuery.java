package config;

public class TransactionQuery {
	public static final String START_TRANSACTION="start transaction;";
	
	public static final String QUERY_UPDATE1 = "Update `AnnualTicketSales` set `TICKETS SOLD` = 0 where `YEAR`=2011;";
	
	public static final String QUERY_SELECT1 = "Select `TICKETS SOLD` from `AnnualTicketSales` where `YEAR`=2011;";
}
