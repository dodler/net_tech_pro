DROP TABLE IF EXISTS  action ;
DROP TABLE IF EXISTS  task ;
DROP TABLE IF EXISTS  parameters ;
		
CREATE TABLE  action  (
   id serial PRIMARY KEY,
   name varchar(50),
   class_path varchar(100)
);

CREATE TABLE  task  (
   id serial PRIMARY KEY,
   name varchar(50),
   status  boolean default false,
   time  DATE,
   action  int default null references action(id),
   commentary  varchar(100),
   alarm  boolean default false
); 
		
CREATE TABLE  parameters  (
   id serial primary key,
   option_value varchar(50),
   option_key varchar(50),
   idx int default null,
   task_id int default null references task(id)
);

