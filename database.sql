create database cursojdbc1;

use cursojdbc1;

CREATE TABLE departamento (
  Id int NOT NULL AUTO_INCREMENT,
  Nome varchar(60) DEFAULT NULL,
  PRIMARY KEY (Id)
)Engine = InnoDB;

CREATE TABLE vendedor (
  Id int NOT NULL AUTO_INCREMENT,
  Nome varchar(60) NOT NULL,
  Email varchar(100) NOT NULL,
  DataAniversario datetime NOT NULL,
  BaseSalarial double NOT NULL,
  DepartamentoId int NOT NULL,
  PRIMARY KEY (Id),
  FOREIGN KEY (DepartamentoId) REFERENCES departamento (id)
)Engine = InnoDB;

INSERT INTO departamento (Nome) VALUES 
  ('Computadores'),
  ('Eletronicos'),
  ('Moda'),
  ('Livros');

INSERT INTO vendedor (Nome, Email, DataAniversario, BaseSalarial, DepartamentoId) VALUES 
  ('Bob Brown','bob@gmail.com','1998-04-21 00:00:00',1000,1),
  ('Maria Green','maria@gmail.com','1979-12-31 00:00:00',3500,2),
  ('Alex Grey','alex@gmail.com','1988-01-15 00:00:00',2200,1),
  ('Martha Red','martha@gmail.com','1993-11-30 00:00:00',3000,4),
  ('Donald Blue','donald@gmail.com','2000-01-09 00:00:00',4000,3),
  ('Alex Pink','bob@gmail.com','1997-03-04 00:00:00',3000,2);
  
  
SELECT vendedor.*,departamento.Nome as DepNome
FROM vendedor INNER JOIN departamento
ON vendedor.DepartamentoId = departamento.Id
WHERE vendedor.Id = 3;

SELECT vendedor.*,departamento.nome as NomeDepartamento
FROM vendedor INNER JOIN departamento
ON vendedor.DepartamentoId = departamento.Id
WHERE vendedor.Id = 3;


SELECT vendedor.*,departamento.Nome as DepNome
FROM vendedor INNER JOIN departamento
ON vendedor.DepartamentoId = departamento.Id
WHERE DepartamentoId = 2
ORDER BY Nome;