CREATE DATABASE teste;

USE DATABASE teste;

CREATE TABLE concessionaria (
  id    INTEGER PRIMARY KEY AUTO_INCREMENT, 
  name  VARCHAR(250),
  endereco  VARCHAR(250),
  descricao  VARCHAR(250),
  dataCriacao  VARCHAR(250),
  tipo  VARCHAR(250),
  oficina BOOLEAN,
  importado BOOLEAN,
  capacidadeVeiculos INTEGER
);

CREATE TABLE carro (
	id    INTEGER PRIMARY KEY AUTO_INCREMENT, 
	marca     VARCHAR(250), 
	modelo    VARCHAR(250), 
	motor DOUBLE,
    concessionariaId INTEGER,
    FOREIGN KEY fk_concessionaria(concessionariaId) REFERENCES concessionaria(id)
);

CREATE TABLE CARROCONCESSIONARIA (
	ID INTEGER PRIMARY KEY AUTO_INCREMENT,
	CARRO_ID INTEGER,
    CONCESSIONARIA_ID INTEGER,
    FOREIGN KEY FK_CARRO(CARRO_ID) REFERENCES CARRO(ID),
    FOREIGN KEY FK_CONCESSIONARIA(CONCESSIONARIA_ID) REFERENCES CONCESSIONARIA(ID)
);