#drop database copa;

create database copa;
use copa;
create table usuario (
	nome varchar(100) primary key,
    email varchar(150) not null,
    senha varchar(100) not null,
    acesso int not null
);

create table time (
	nome varchar(100) primary key
);

insert into usuario(nome, email, senha, acesso) values("victor", "victor@email.com", "1234", 0);
insert into usuario(nome, email, senha, acesso) values("admin", "admin@email.com", "1234", 1);

insert into time(nome) value("Alemanha");
insert into time(nome) value("Suiça");
insert into time(nome) value("Inglaterra");
insert into time(nome) value("Espanha");
insert into time(nome) value("EUA");
insert into time(nome) value("Australia");
insert into time(nome) value("Camarões");
insert into time(nome) value("Senegal");
insert into time(nome) value("Sérvia");
insert into time(nome) value("Holanda");
insert into time(nome) value("Catar");
insert into time(nome) value("Irã");
insert into time(nome) value("Pais de Gales");
insert into time(nome) value("Arábia Saudita");
insert into time(nome) value("México");
insert into time(nome) value("Polônia");
insert into time(nome) value("Dinamarca");
insert into time(nome) value("França");
insert into time(nome) value("Tunísia");
insert into time(nome) value("Costa Rica");
insert into time(nome) value("Bélgica");
insert into time(nome) value("Canadá");
insert into time(nome) value("Croácia");
insert into time(nome) value("Marrocos");
insert into time(nome) value("Japão");
insert into time(nome) value("Coreia do Sul");
insert into time(nome) value("Portugal");
insert into time(nome) value("Uruguai");
insert into time(nome) value("Gana");
insert into time(nome) value("Equador");
insert into time(nome) value("Argentina");
insert into time(nome) value("Brasil");