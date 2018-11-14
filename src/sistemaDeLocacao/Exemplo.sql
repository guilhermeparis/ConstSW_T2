CREATE DATABASE teste;

USE teste;

CREATE TABLE VEICULO (
  id INTEGER PRIMARY KEY AUTO_INCREMENT, 
  renavam VARCHAR(250),
  marca VARCHAR(250),
  modelo  VARCHAR(250),
  valorDiaria DOUBLE
);

CREATE TABLE CLIENTE (
	id INTEGER PRIMARY KEY AUTO_INCREMENT, 
	cpf VARCHAR(250), 
	nome VARCHAR(250) 
);