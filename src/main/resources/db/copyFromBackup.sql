insert into sales_list.sales SELECT * from backup.sales_copy;

insert into sales_list.payouts SELECT * from backup.payouts_copy;
