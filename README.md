# TransportAppOOP
transport logistic application, connect to MySQL database


### Datbase Name: logistic
##### run this MySQL for initalization
```
create table transport (
	id int not null, 
	type varchar(12) not null, 
	status varchar(10) not null, #Available / Shipping / Maintenance  
	primary key (id));

create table transaction (
	shipping_id int not null,
	package_id varchar(10) not null,
	transport_id int not null,
	created_date date,
	eta date,
	arrival_date date,
	primary key (shipping_id));
	
INSERT INTO `transport`(`id`, `type`, `status`) VALUES ('1','Road','Available');
INSERT INTO `transport`(`id`, `type`, `status`) VALUES ('2','Sea','Available');

```
