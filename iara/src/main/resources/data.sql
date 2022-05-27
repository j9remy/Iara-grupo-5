
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
INSERT INTO Prestador (Autenticado, Nome, Sobrenome, Cpf, Data_nasc, Email, Senha, Genero,  Telefone, Resumo, Atende_Domicilio ) VALUES
    (true, 'Claudia','Rita','98895457056','1964-01-20','claudia@hotmail.com','12345','F','(17) 96728-6724', 'Cabelereira dedicada e com longa experiência na área estética', true),
    (false, 'Paulo','Henrique','63513178000','1960-04-08','pauloh@outlook.com','12345','M','(21) 99365-1619', 'Barbeiro e cabeleireiro com diversos prêmios nacionais, 15 anos no ramo', true),
    (false, 'Leticia','Maia','81587717030','1967-02-14','leticia@gmail.com','12345','F','(89) 98719-6305', 'Designer de Sobrancelhas nova no mundo estético procurando clientes', true),
    (true, 'Victor','Assunção','81408412004','1974-04-16','victorassuncao@gmail.com','12345','M','(93) 99518-6231', 'Especialista em limpeza de pele e cuidados com o rosto', true),
    (true, 'Kelvin','Ramos','10419961011','2001-03-13','kelvin@gmail.com.br','12345','M','(68) 96942-6542', 'Massagista premiado mundialmente, técnicas tailandesas e experiência na área', true),
    (false, 'Bruno','Aluísio','65638818073','1994-04-17','alubruno@bing.com','12345','M','(87) 98169-4246', 'Cabeleireiro feminino, experiência com cortes curtos e cachos', true),
    (false, 'Antonio','Ferreira','32339010039','1995-01-27','antoniofer@safra.com.br','12345','F','(88) 98734-1451', 'Designer de unhas com vasta experiência no mercado procurando clientes', true);

-- Endereco
INSERT INTO Endereco (Cep, Rua, Numero, Bairro, Cidade, Uf, Complemento) VALUES
    ('09411190', 'Rua São Francisco', '200', 'Roncon', 'Ribeirão Pires', 'SP', 'Fundos');


