SET CHARSET UTF8;
create table sample.player(
                              id bigint(20) unsigned AUTO_INCREMENT PRIMARY KEY,
                              name varchar(100),
                              score integer,
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              updated_at DATETIME
);
INSERT INTO sample.player(name,score) VALUES ('Jan',300),('Andy',500),('Thomas',100);