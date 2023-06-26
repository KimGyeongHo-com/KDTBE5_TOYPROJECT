create table team(
    id int primary KEY auto_increment,
    stadiumId INT NOT null,
    name varchar(20) NOT null UNIQUE,
    created_date TIMESTAMP NOT null
);

create table player(
    id int primary KEY auto_increment,
    teamId INT NOT null,
    position varchar(20) NOT null UNIQUE,
    name varchar(20) NOT null UNIQUE,
    created_date TIMESTAMP NOT null
);
