

-- Cliente
INSERT INTO Cliente (Autenticado,Nome,Sobrenome, Cpf, Data_nasc, Email,
    Senha, Genero,  Telefone) VALUES
    (true, 'Luciana','Rita','84125417407','1964-01-20','luciana@gmail.com','12345','F','(84) 98185-4215'),
    (false, 'Ricardo','Pedro','54116644382','1960-04-08','ricardo@outlook.com','12345','M','(96) 92775-2377'),
    (false, 'Victória','Eloá','09316790522','1967-02-14','victoria@gmail.com','12345','F','(41) 92879-1509'),
    (true, 'Caio','Assunção','56312185699','1974-04-16','assuncaocaio@gmail.com','12345','M','(63) 93975-9441'),
    (true, 'Thomas','Ramos','45663113924','2001-03-13','thomas@yahoo.com.br','12345','M','(61) 98984-1622'),
    (false, 'Julio','Nunes','39294488586','1994-04-17','julionunes@gmail.com','12345','M','(81) 99385-8178'),
    (false, 'Isadora','Maitê Souza','44898285350','1995-01-27','maite_isa@uol.com.br','12345','F','(27) 98610-1588');

    -- Colaborador
    INSERT INTO Prestador (Autenticado,Nome,Sobrenome, Cpf, Data_nasc, Email,
        Senha, Genero,  Telefone, Resumo, Atende_Domicilio ) VALUES
        (true, 'Luciana','Rita','84125417407','1964-01-20','luciana@gmail.com','12345','F','(84) 98185-4215', 'dsgdsgdsgds', true),
        (false, 'Ricardo','Pedro','54116644382','1960-04-08','ricardo@outlook.com','12345','M','(96) 92775-2377', 'dsgdsgdsgds', true),
        (false, 'Victória','Eloá','09316790522','1967-02-14','victoria@gmail.com','12345','F','(41) 92879-1509', 'dsgdsgdsgds', true),
        (true, 'Caio','Assunção','56312185699','1974-04-16','assuncaocaio@gmail.com','12345','M','(63) 93975-9441', 'dsgdsgdsgds', true),
        (true, 'Thomas','Ramos','45663113924','2001-03-13','thomas@yahoo.com.br','12345','M','(61) 98984-1622', 'dsgdsgdsgds', true),
        (false, 'Julio','Nunes','39294488586','1994-04-17','julionunes@gmail.com','12345','M','(81) 99385-8178', 'dsgdsgdsgds', true),
        (false, 'Isadora','Maitê Souza','44898285350','1995-01-27','maite_isa@uol.com.br','12345','F','(27) 98610-1588', 'dsgdsgdsgds', true);
