--创建usertb表
CREATE TABLE usertb(   
id serial,    
uname  varchar(20) ,   
ucreatetime  datetime  ,   
age  int(11))   
ENGINE=MYISAM 
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci   
AUTO_INCREMENT=1   
ROW_FORMAT=COMPACT;

--创建过程函数
delimiter $$ 
SET AUTOCOMMIT = 0$$   
 
create  procedure test1()  
begin
declare v_cnt decimal (10)  default 0 ; 
dd:loop            
        insert  into usertb values         
        (null,'用户1','2010-01-01 00:00:00',20),         
        (null,'用户2','2010-01-01 00:00:00',20),         
        (null,'用户3','2010-01-01 00:00:00',20),         
        (null,'用户4','2010-01-01 00:00:00',20),         
        (null,'用户5','2011-01-01 00:00:00',20),         
        (null,'用户6','2011-01-01 00:00:00',20),         
        (null,'用户7','2011-01-01 00:00:00',20),         
        (null,'用户8','2012-01-01 00:00:00',20),         
        (null,'用户9','2012-01-01 00:00:00',20),         
        (null,'用户0','2012-01-01 00:00:00',20)             
                ;                   
        commit;                     
        set v_cnt = v_cnt+10 ;                            
            if  v_cnt = 10000000 then leave dd;                           
            end if;          
        end loop dd ; 
end;$$   
 
delimiter ; 

--调用过程函数
call test1;