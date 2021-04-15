
create table empProfile(emp_uname varchar(25),emp_pass varchar(25),emp_name varchar(15),emp_id int,emp_dep varchar(10),emp_mobile varchar(11),emp_addr varchar(50));



create table adminCred(aname varchar(25),apass varchar(25));
insert into adminCred values("admin@sad","sad123");

create table work(emp_id int,emp_name varchar(15),project_name varchar(25),role_name varchar(25),perf varchar(25));

DELIMITER //
create procedure isvalid_login(in usr varchar(25),in pass varchar(25),out stts int)
BEGIN
IF EXISTS(select emp_id from empProfile where usr=emp_uname and pass=emp_pass) THEN
set stts=1;
ELSE select 0 into stts;
END IF;
end//
DELIMITER ;





DELIMITER $$
CREATE FUNCTION check_admin(usr varchar(25),pass varchar(25))
RETURNS int
DETERMINISTIC
BEGIN
IF EXISTS(select aname from adminCred where usr=aname and pass=apass) THEN
RETURN (1);
ELSE
RETURN (0);
END IF;
END$$
DELIMITER ;




DELIMITER $$
CREATE FUNCTION check_acc(usr varchar(25),pass varchar(25),name varchar(15),id int,dep varchar(10))
RETURNS varchar(20)
DETERMINISTIC
BEGIN
IF EXISTS(select emp_id from empProfile where usr=emp_uname) THEN
RETURN ("Email already exists");
ELSEIF EXISTS(select emp_dep from empProfile where id=emp_id) THEN
RETURN ("ID already exists");
ELSE
insert into empProfile(emp_uname,emp_pass,emp_name,emp_id,emp_dep) values(usr,pass,name,id,dep);
insert into work(emp_id,emp_name) values(id,name);
RETURN ("created");
END IF;
END$$
DELIMITER ;



