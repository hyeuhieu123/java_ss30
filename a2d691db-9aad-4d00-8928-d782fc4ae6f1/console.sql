create  database demo1;
use demo1;
create table admin (
    name varchar(20) not null,
    email varchar(20) not null,
    password varchar(20) not null
);
insert into admin values ('trung hieu','test','1');
create table department (
    id int primary key auto_increment,
    name varchar(20) not null,
    description varchar(20) not null,
    status enum('active','inactive')

);INSERT INTO department (name, description, status) VALUES
('Department 1', 'Description 1', 'active'),
('Department 2', 'Description 2', 'inactive'),
('Department 3', 'Description 3', 'active'),
('Department 4', 'Description 4', 'inactive'),
('Department 5', 'Description 5', 'active'),
('Department 6', 'Description 6', 'inactive'),
('Department 7', 'Description 7', 'active'),
('Department 8', 'Description 8', 'inactive'),
('Department 9', 'Description 9', 'active')
;
create table employee (
    id int primary key auto_increment,
    name varchar(20) not null,
    email varchar(20) not null,
    sex enum ('male','female','other'),
    bod date,
    department_id int,
    salary decimal(10,2),
    status enum('active','inactive','onleave','policyleave'),
    foreign key (department_id) references department(id)
);
alter table employee
add column phone varchar(20) ;

INSERT INTO employee (name, email, phone, sex, bod, department_id, salary, status) VALUES
                                                                                       ('John Smith', 'john@company.com', '0912345678', 'male', '1990-05-15', 1, 55000.00, 'active'),
                                                                                       ('Mary Johnson', 'mary@company.com', '0923456789', 'female', '1992-08-21', 2, 48000.00, 'active'),
                                                                                       ('David Lee', 'david@company.com', '0934567890', 'male', '1988-03-10', 2, 62000.00, 'active'),
                                                                                       ('Sarah Wilson', 'sarah@company.com', '0945678901', 'female', '1995-11-30', 1, 51000.00, 'active'),
                                                                                       ('Michael Brown', 'michael@company.com', '0956789012', 'male', '1991-07-25', 4, 59000.00, 'inactive'),
                                                                                       ('Emma Davis', 'emma@company.com', '0967890123', 'female', '1993-12-05', 2, 53000.00, 'active'),
                                                                                       ('James Chen', 'james@company.com', '0978901234', 'male', '1987-09-18', 5, 65000.00, 'onleave'),
                                                                                       ('Lisa Wang', 'lisa@company.com', '0989012345', 'female', '1994-04-22', 2, 54000.00, 'active'),
                                                                                       ('Robert Kim', 'robert@company.com', '0990123456', 'male', '1989-01-14', 6, 57000.00, 'active'),
                                                                                       ('Anna Park', 'anna@company.com', '0901234567', 'female', '1996-06-08', 4, 49000.00, 'policyleave'),
                                                                                       ('Thomas Nguyen', 'thomas@company.com', '0912345670', 'male', '1992-02-28', 7, 56000.00, 'active'),
                                                                                       ('Jessica Liu', 'jessica@company.com', '0923456781', 'female', '1990-10-12', 5, 52000.00, 'active'),
                                                                                       ('William Wu', 'william@company.com', '0934567892', 'male', '1993-07-07', 8, 58000.00, 'inactive'),
                                                                                       ('Sophie Zhang', 'sophie@company.com', '0945678903', 'female', '1991-04-16', 6, 51000.00, 'active'),
                                                                                       ('Daniel Tan', 'daniel@company.com', '0956789014', 'male', '1994-09-25', 9, 53000.00, 'onleave'),
                                                                                       ('Emily Wong', 'emily@company.com', '0967890125', 'female', '1988-12-30', 7, 60000.00, 'active'),
                                                                                       ('Kevin Chang', 'kevin@company.com', '0978901236', 'male', '1995-03-19', 8, 50000.00, 'active'),
                                                                                       ('Grace Lin', 'grace@company.com', '0989012347', 'female', '1992-08-11', 9, 55000.00, 'active'),
                                                                                       ('Andrew Yang', 'andrew@company.com', '0990123458', 'male', '1989-05-24', 1, 63000.00, 'active'),
                                                                                       ('Rachel Kim', 'rachel@company.com', '0901234569', 'female', '1993-11-17', 2, 54000.00, 'active');

delimiter //


create  procedure getDataPag( page_size int, page int)
    begin
        select * from department
        limit page_size offset page;
    end //

create  procedure getDataPagEmp(
    page_size int,
    page int
)
begin
    select * from employee
        limit page_size offset page;

end //

create  procedure   insert_department(
    name_in varchar(20),
    description_in varchar(20),
    status_in enum('active','inactive')
)
begin
    insert into department (name, description, status)
    values (name_in, description_in, status_in);
end //

create procedure delete_department(
    id_in int
)begin
    delete  from department
    where id = id_in;
end //
create procedure update_department(
    id_in int,
    name_in varchar(20),
    description_in varchar(20),
    status_in enum('active','inactive')
)
begin
    update department
    set name = name_in,
        description = description_in,
        status = status_in
    where id = id_in;
end //

create procedure finDepartmentByName(
    name_in varchar(255)
)
begin
select * from department
    where name like concat('%',name_in, '%');
end //

create procedure updateEmployee(
    id_in int,
    name_in varchar(255),
    email_in varchar(255),
    sex_in enum('male','female','other'),
    bod_in date,
    department_id_in int,
    salary_in decimal(10,2),
    status_in enum('active','inactive','onleave','policyleave')
)
begin
    update employee
    set name = name_in,
        email = email_in,
        sex = sex_in,
        bod = bod_in,
        department_id = department_id_in,
        salary = salary_in,
        status = status_in
    where id = id_in;
end //
create procedure insertEmployee(
    name_in varchar(255),
    email_in varchar(255),
    sex_in enum('male','female','other'),
    bod_in date,
    department_id_in int,
    salary_in decimal(10,2),
    status_in enum('active','inactive','onleave','policyleave')
)
begin
    insert into  employee(name,email,sex,bod,department_id,salary,status)
        values(name_in,email_in,sex_in,bod_in,department_id_in,salary_in,status_in);
end //
create procedure findEmployeeByNameAndAge(
    name_in varchar(255),
    age_from int,
    age_to int
)
begin
    select * from employee
    where name like concat('%',name_in,'%') and TIMESTAMPDIFF(YEAR, bod, CURDATE()) between age_from and age_to  ;
end //

create procedure compileEmployeeEachDepartment()
begin
    select d.name,count(e.id) as count from department d
    join employee e on d.id = e.department_id
    group by d.name;
end //

create  procedure countEmployee(
)
begin
    select count(id) as count from employee;
end //

create procedure departmentWithMostEmployee()
begin
    select d.name, count(e.id) as total
    from department d join employee e
                           on e.department_id = d.id
    group by d.id, d.name
    having total = (select  count(e.id) as total
                    from department d join employee e
                                           on e.department_id = d.id
                    group by d.id, d.name
                    order by total desc
                    limit 1);
end //

create procedure departmentWithhighestSalary()
begin
    select d.name, sum(e.salary) as total
    from department d join employee e
                           on e.department_id = d.id
    group by d.name

    having total =(
        select sum(e.salary) as total
        from department d join employee e
                               on e.department_id = d.id
        group by d.name
        order by total desc
        limit 1
    );

end //



delimiter //


# test










