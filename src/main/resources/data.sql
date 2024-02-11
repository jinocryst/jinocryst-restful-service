insert into users(id, join_date, name, password, ssn) values(90001, now(), 'User1', 'test111' , '700101-1111111');
insert into users(id, join_date, name, password, ssn) values(90002, now(), 'User2', 'test111' , '700102-1111111');
insert into users(id, join_date, name, password, ssn) values(90003, now(), 'User3', 'test111' , '700103-1111111');

insert into post(description, user_id) values('first', 90001);
insert into post(description, user_id) values('second', 90001);