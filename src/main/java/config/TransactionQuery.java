package config;

public class TransactionQuery {
	public static final String START_TRANSACTION="start transaction;";
	
	public static final String QUERY_INSERT1 = "insert into olist_sellers_dataset (seller_id,seller_zip_code_prefix,seller_city,seller_state) values (\'3442f8959a84dea7ee197c62323b231212\',13023,\'halifax\',	\'NS\'),(\'22334f8959a84dea233dsa62323b231212\',12344,\'toronto\',\'ON\');";
	public static final String QUERY_INSERT2 = "insert into olist_order_items_dataset (order_id	order_item_id,product_id,seller_id,shipping_limit_date,price,freight_value) values (\'00010242fe8c5a6d1ba2dd24fcb12121\',1,\'4244733e06e7ecb4970a6e2683c13e61\',\'3442f8959a84dea7ee197c62323b231212\',2016-12-12 16:12:23,2828.2,8.22), (\'727260242fe8chsgs5r1ba2hsgd7cb12121\',1,\'4244733e06e7ecb4970a6e2683c13e61\',\'22334f8959a84dea233dsa62323b231212\',2016-12-10 16:12:23,2524.2,98.22)";
	
	public static final String QUERY_UPDATE1 = "update olist_order_items_dataset set freight_value=freight_value-freight_value*0.15 where order_id in (select order_id from olist_orders_dataset where order_status=\'canceled\');";
	public static final String QUERY_UPDATE2 = "UPDATE olist_order_payments_dataset SET payment_type=UPPER(payment_type);";
	
	public static final String QUERY_DELETE5 = "delete from olist_orders_dataset where exists (select * from olist_customers_dataset where olist_orders_dataset.customer_id=olist_customers_dataset.customer_id and customer_state='GO')";
	public static final String QUERY_DELETE6 = "delete from olist_geolocation_dataset where geolocation_zip_code_prefix in (select seller_zip_code_prefix from olist_sellers_dataset where seller_state='GO')";
	
	public static final String QUERY_SELECT1 = "select payment_type, count(order_id) as order_count from olist_order_payments_dataset group by payment_type order by count(order_id) desc";
	public static final String QUERY_SELECT2 = "select product_category_name_translation.product_category_name, product_category_name_translation.product_category_name_english, olist_products_dataset.product_photos_qty from product_category_name_translation right join olist_products_dataset on product_category_name_translation.product_category_name=olist_products_dataset.product_category_name;";
	public static final String QUERY_SELECT3 = "select review_id,review_creation_date,review_answer_timestamp,TIMESTAMPDIFF(SECOND, review_answer_timestamp, review_creation_date) from olist_order_reviews_dataset";
}
