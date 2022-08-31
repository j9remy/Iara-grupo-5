
-- Cliente
INSERT INTO Cliente (Autenticado, Nome, Sobrenome, Cpf, Data_nasc, Email, Senha, Genero, Telefone) VALUES
    (true, 'Luciana','Rita','84125417407','1984-02-22','luciana@gmail.com','12345','F','(84) 98185-4215'),
    (false, 'Ricardo','Pedro','54116644382','1990-06-09','ricardo@outlook.com','12345','M','(96) 92775-2377'),
    (false, 'Victória','Eloá','09316790522','1969-01-12','victoria@gmail.com','12345','F','(41) 92879-1509'),
    (true, 'Caio','Assunção','56312185699','1964-03-17','assuncaocaio@gmail.com','12345','M','(63) 93975-9441'),
    (true, 'Thomas','Ramos','45663113924','2000-08-14','thomas@yahoo.com.br','12345','M','(61) 98984-1622'),
    (false, 'Julio','Nunes','39294488586','1991-09-11','julionunes@gmail.com','12345','M','(81) 99385-8178'),
    (false, 'Isadora','Maitê Souza','44898285350','1985-10-19','maite_isa@uol.com.br','12345','F','(27) 98610-1588');

-- Colaborador
INSERT INTO Prestador (Autenticado, Nome, Sobrenome, Cpf, Data_nasc, Email, Senha, Genero,  Telefone, Resumo, Atende_Domicilio, Atende_Estabelecimento, Distancia) VALUES
    (true, 'Claudia','Rita','98895457056','1964-01-20','claudia@hotmail.com','12345','F','(17) 96728-6724', 'Cabelereira dedicada e com longa experiência na área estética', true,false, 0.0),
    (false, 'Paulo','Henrique','63513178000','1960-04-08','pauloh@outlook.com','12345','M','(21) 99365-1619', 'Barbeiro e cabeleireiro com diversos prêmios nacionais, 15 anos no ramo', true,false, 0.0),
    (false, 'Leticia','Maia','26301579038','1967-02-14','leticia@gmail.com','12345','F','(89) 98719-6305', 'Designer de Sobrancelhas nova no mundo estético procurando clientes', true,true, 2.5),
    (true, 'Victor','Assunção','81408412004','1974-04-16','victorassuncao@gmail.com','12345','M','(93) 99518-6231', 'Especialista em limpeza de pele e cuidados com o rosto', false,true, 4.5),
    (true, 'Kelvin','Ramos','10419961011','2001-03-13','kelvin@gmail.com.br','12345','M','(68) 96942-6542', 'Massagista premiado mundialmente, técnicas tailandesas e experiência na área',false, true,8.0),
    (false, 'Bruno','Aluísio','65638818073','1994-04-17','alubruno@bing.com','12345','M','(87) 98169-4246', 'Cabeleireiro feminino, experiência com cortes curtos e cachos', false, true, 3.1),
    (false, 'Antonio','Ferreira','32339010039','1995-01-27','antoniofer@safra.com.br','12345','F','(88) 98734-1451', 'Designer de unhas com vasta experiência no mercado procurando clientes', true, true, 10.3);

-- Agenda
INSERT INTO Agenda (Prestador_Id) VALUES
   (1),
   (2),
   (3),
   (4),
   (5),
   (6),
   (7);

-- Portifolio
INSERT INTO Portifolio (Prestador_Id) VALUES
   (1),
   (2),
   (3),
   (4),
   (5),
   (6),
   (7);

-- Endereco
INSERT INTO Endereco (Cep, Rua, Numero, Bairro, Cidade, Uf, Complemento) VALUES
    ('09411190', 'Rua São Francisco', '200', 'Roncon', 'Ribeirão Pires', 'SP', 'Fundos'),
    ('09402190', 'Rua Rússia (Jd Dois Melros)', '510', 'Colônia', 'Ribeirão Pires', 'SP', null);

-- Avaliação do cliente
INSERT INTO Avaliacao_Cliente (Avaliacao, Data_Hora, Cliente_Id) VALUES
    (5,'2022-03-10 20:34', 1),
    (4,'2022-03-10 20:34', 1),
    (4,'2022-03-10 20:34', 1),
    (5,'2022-03-10 20:34', 2),
    (5,'2022-03-10 20:34', 2),
    (3,'2022-03-10 20:34', 2),
    (1,'2022-03-10 20:34', 3),
    (5,'2022-03-10 20:34', 3),
    (5,'2022-03-10 20:34', 3),
    (4,'2022-03-10 20:34', 4),
    (5,'2022-03-10 20:34', 4),
    (5,'2022-03-10 20:34', 4),
    (5,'2022-03-10 20:34', 5),
    (4,'2022-03-10 20:34', 5),
    (2,'2022-03-10 20:34', 5),
    (5,'2022-03-10 20:34', 6),
    (5,'2022-03-10 20:34', 6),
    (5,'2022-03-10 20:34', 6),
    (5,'2022-03-10 20:34', 7),
    (4,'2022-03-10 20:34', 7),
    (3,'2022-03-10 20:34', 7);

-- -- Habilidade
-- INSERT INTO Habilidade (Habilidade, Descricao) VALUES
--     ('Corte','Corte Masculino (incluso shampoo)'),
--     ('Barba', 'Barba completa'),
--     ('Depilação','Cera corporal'),
--     ('Depilação','Cera facial'),
--     ('Procedimento','Luzes'),
--     ('Procedimento','Botox capilar'),
--     ('Podologia','Pedicure sem Esmaltação'),
--     ('Podologia','Cauterização de Verruga'),
--     ('Manicure','Esmaltação'),
--     ('Manicure','Francesinha'),
--     ('Pedicure','Spa dos Pés');

-- Servicos
INSERT INTO Servico (Ativo, Descricao, Duracao_Estimada, Tipo, Valor, Prestador_Id) VALUES
    (true,'Corte Masculino','01:00','Corte',40.00,7),
    (false,'Pedicure sem Esmaltação','1:20','Podologia',50.00,6),
    (true,'Luzes','1:30','Corte Feminino',150,7),
    (true,'Spa dos Pés','00:45','Pedicure',50,6),
    (true,'Esmaltação','1:00','Manicure',20,6),
    (true,'Esmaltação Artistica','1:45','Manicure',200,6),
    (true,'Aparação na barba','00:30','Barba',35,7);

-- Serviços atribuidos
INSERT INTO Servico_Atribuido (Cliente_Id, Servico_Id, Avaliacao, Data_Hora_Fim, Status, Observacoes, Finalizado) VALUES
    (1,7,-1.0,null,'Agendado',null,false),
    (1,6,-1.0,'2022-06-09T14:37','Finalizado','Devo me atrasar alguns minutos',true),
    (2,5,-1.0,null,'Agendado','Desejo minhas unhas da cor vermelha',false),
    (3,4,-1.0,null,'Agendado',null,false),
    (4,3,-1.0,null,'Agendado',null,false),
    (5,2,-1.0,null,'Agendado',null,false),
    (6,1,-1.0,null,'Agendado',null,false);

