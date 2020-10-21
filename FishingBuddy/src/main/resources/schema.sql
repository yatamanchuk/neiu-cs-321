create table if not exists Bait (
id varchar(4) not null,
name varchar(25) not null,
type varchar(10) not null
);

create table if not exists Fish (
    id bigint identity,
    name varchar(50) not null,
    createdAt timestamp not null
);

create table if not exists Fish_Baits (
    fish bigint not null,
    bait varchar (4) not null
);

alter table Fish_Baits
    add foreign key (fish) references Fish(id);

alter table Fish_Baits
    add foreign key (bait) references Bait(id);