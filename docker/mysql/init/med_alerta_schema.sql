-- ======================================================
-- PROJETO: MedAlerta
-- AUTOR: John Arllon Batista
-- DESCRIÇÃO: Script completo de estrutura, dados e consultas.
-- ======================================================

DROP DATABASE IF EXISTS MedAlerta;
CREATE DATABASE MedAlerta;
USE MedAlerta;

-- ======================================================
-- 1. ESTRUTURA DAS TABELAS (DDL)
-- ======================================================

CREATE TABLE Usuario (
    idUsuario INT AUTO_INCREMENT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL,
    enderecoRua VARCHAR(100),
    enderecoNumero INT,
    enderecoComplemento VARCHAR(50),
    enderecoBairro VARCHAR(50),
    enderecoCEP VARCHAR(10),
    enderecoCidade VARCHAR(50),
    enderecoEstado CHAR(2),
    PRIMARY KEY (idUsuario)
) ENGINE=InnoDB;

CREATE TABLE Medicamento (
    idMedicamento INT AUTO_INCREMENT NOT NULL,
    nomeComercial VARCHAR(100) NOT NULL,
    nomeGenerico VARCHAR(100),
    quantidade ENUM('unidade','ml'),
    formaUso VARCHAR(100),
    observacao VARCHAR(200),
    PRIMARY KEY (idMedicamento)
) ENGINE=InnoDB;

CREATE TABLE UsuarioMedicamento (
    idUsuario INT NOT NULL,
    idMedicamento INT NOT NULL,
    dosagem VARCHAR(50) NOT NULL,
    PRIMARY KEY (idUsuario, idMedicamento),
    CONSTRAINT fk_um_usuario FOREIGN KEY (idUsuario) REFERENCES Usuario (idUsuario) ON DELETE CASCADE,
    CONSTRAINT fk_um_medicamento FOREIGN KEY (idMedicamento) REFERENCES Medicamento (idMedicamento) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE Frequencia (
    idFrequencia INT AUTO_INCREMENT NOT NULL,
    idUsuario INT NOT NULL,
    idMedicamento INT NOT NULL,
    horarioUso TIME NOT NULL,
    intervaloHoras INT,
    vezesPorDia INT,
    PRIMARY KEY (idFrequencia),
    CONSTRAINT fk_freq_usumed FOREIGN KEY (idUsuario, idMedicamento) 
        REFERENCES UsuarioMedicamento (idUsuario, idMedicamento) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE Alerta (
    idAlerta INT AUTO_INCREMENT NOT NULL,
    idFrequencia INT NOT NULL,
    dataHoraAlerta DATETIME NOT NULL,
    statusAlerta ENUM('emitido','nao_emitido') NOT NULL DEFAULT 'nao_emitido',
    PRIMARY KEY (idAlerta),
    CONSTRAINT fk_alerta_freq FOREIGN KEY (idFrequencia) REFERENCES Frequencia (idFrequencia) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE Consumo (
    idConsumo INT AUTO_INCREMENT NOT NULL,
    idAlerta INT NOT NULL,
    dataHoraConsumo DATETIME,
    confirmacao ENUM('sim','nao') NOT NULL DEFAULT 'nao',
    PRIMARY KEY (idConsumo),
    CONSTRAINT fk_consumo_alerta FOREIGN KEY (idAlerta) REFERENCES Alerta (idAlerta) ON DELETE CASCADE
) ENGINE=InnoDB;

-- ======================================================
-- 2. POPULANDO O BANCO DE DADOS (DML)
-- ======================================================

INSERT INTO Usuario (nome, telefone, email, enderecoRua, enderecoNumero, enderecoComplemento, enderecoBairro, enderecoCEP, enderecoCidade, enderecoEstado) VALUES
('Ana Souza', '41999990001', 'ana.souza@email.com', 'Rua A', 10, NULL, 'Centro', '80000-001', 'Curitiba', 'PR'),
('Bruno Lima', '41999990002', 'bruno.lima@email.com', 'Rua B', 20, 'Casa', 'Batel', '80000-002', 'Curitiba', 'PR'),
('Carla Mendes', '41999990003', 'carla.mendes@email.com', 'Rua C', 30, NULL, 'Água Verde', '80000-003', 'Curitiba', 'PR'),
('Daniel Rocha', '41999990004', 'daniel.rocha@email.com', 'Rua D', 40, 'Apto 101', 'Portão', '80000-004', 'Curitiba', 'PR'),
('Elisa Torres', '41999990005', 'elisa.torres@email.com', 'Rua E', 50, NULL, 'Santa Quitéria', '80000-005', 'Curitiba', 'PR'),
('Felipe Alves', '41999990006', 'felipe.alves@email.com', 'Rua F', 60, NULL, 'Cabral', '80000-006', 'Curitiba', 'PR'),
('Gabriela Nunes', '41999990007', 'gabriela.nunes@email.com', 'Rua G', 70, 'Fundos', 'Boqueirão', '80000-007', 'Curitiba', 'PR'),
('Henrique Pires', '41999990008', 'henrique.pires@email.com', 'Rua H', 80, NULL, 'Xaxim', '80000-008', 'Curitiba', 'PR'),
('Isabela Costa', '41999990009', 'isabela.costa@email.com', 'Rua I', 90, 'Apto 202', 'Rebouças', '80000-009', 'Curitiba', 'PR'),
('João Martins', '41999990010', 'joao.martins@email.com', 'Rua J', 100, NULL, 'Centro Cívico', '80000-010', 'Curitiba', 'PR');

INSERT INTO Medicamento (nomeComercial, nomeGenerico, quantidade, formaUso, observacao) VALUES
('Tylenol', 'Paracetamol', 'unidade', 'Via oral', 'Não exceder a dose'),
('Advil', 'Ibuprofeno', 'unidade', 'Via oral', 'Tomar após refeições'),
('Amoxil', 'Amoxicilina', 'ml', 'Via oral', 'Agitar antes de usar'),
('Dipirona', 'Dipirona Sódica', 'unidade', 'Via oral', NULL),
('Buscopan', 'Butilbrometo', 'unidade', 'Via oral', 'Uso em dor abdominal'),
('Novalgina', 'Dipirona', 'ml', 'Via oral', NULL),
('Atenol', 'Atenolol', 'unidade', 'Via oral', 'Controle da pressão'),
('Losartana', 'Losartana Potássica', 'unidade', 'Via oral', NULL),
('Vick', 'Camphora', 'ml', 'Uso tópico', 'Não ingerir'),
('Cataflam', 'Diclofenaco', 'unidade', 'Via oral', 'Uso com cautela');

INSERT INTO UsuarioMedicamento (idUsuario, idMedicamento, dosagem) VALUES
(1,1,'1 comprimido'), (1,2,'1 comprimido'), (2,3,'10 ml'), (2,4,'1 comprimido'),
(3,5,'1 comprimido'), (3,6,'15 ml'), (4,7,'1 comprimido'), (4,8,'1 comprimido'),
(5,9,'5 ml'), (6,10,'1 comprimido'), (7,1,'1 comprimido'), (8,2,'1 comprimido'),
(9,3,'10 ml'), (10,4,'1 comprimido'), (10,5,'1 comprimido');

INSERT INTO Frequencia (idUsuario, idMedicamento, horarioUso, intervaloHoras, vezesPorDia) VALUES
(1,1,'08:00:00',8,NULL), (1,2,'20:00:00',NULL,1), (2,3,'09:00:00',12,NULL), (2,4,'18:00:00',NULL,1),
(3,5,'07:00:00',8,NULL), (3,6,'22:00:00',NULL,1), (4,7,'08:00:00',NULL,1), (4,8,'20:00:00',NULL,1),
(5,9,'12:00:00',NULL,2), (6,10,'19:00:00',NULL,1), (7,1,'08:00:00',NULL,1), (8,2,'21:00:00',NULL,1),
(9,3,'10:00:00',12,NULL), (10,4,'18:30:00',NULL,1), (10,5,'22:00:00',NULL,1);

INSERT INTO Alerta (idFrequencia, dataHoraAlerta, statusAlerta) VALUES
(1,'2026-04-15 07:55:00','emitido'), (2,'2026-04-15 19:55:00','emitido'), (3,'2026-04-15 08:55:00','nao_emitido'),
(4,'2026-04-15 17:55:00','emitido'), (5,'2026-04-15 06:55:00','emitido'), (6,'2026-04-15 21:55:00','nao_emitido'),
(7,'2026-04-15 07:55:00','emitido'), (8,'2026-04-15 19:55:00','emitido'), (9,'2026-04-15 11:55:00','emitido'),
(10,'2026-04-15 18:55:00','nao_emitido'), (11,'2026-04-15 07:55:00','emitido'), (12,'2026-04-15 20:55:00','emitido'),
(13,'2026-04-15 09:55:00','nao_emitido'), (14,'2026-04-15 18:25:00','emitido'), (15,'2026-04-15 21:55:00','emitido');

INSERT INTO Consumo (idAlerta, dataHoraConsumo, confirmacao) VALUES
(1,'2026-04-15 08:05:00','sim'), (2,'2026-04-15 20:10:00','sim'), (3,NULL,'nao'), (4,'2026-04-15 18:03:00','sim'),
(5,'2026-04-15 07:05:00','sim'), (6,NULL,'nao'), (7,'2026-04-15 08:00:00','sim'), (8,'2026-04-15 20:02:00','sim'),
(9,'2026-04-15 12:06:00','sim'), (10,NULL,'nao'), (11,'2026-04-15 08:01:00','sim'), (12,'2026-04-15 21:10:00','sim'),
(13,NULL,'nao'), (14,'2026-04-15 18:40:00','sim'), (15,'2026-04-15 22:05:00','sim');

-- ======================================================
-- 3. CONSULTAS E RELATÓRIOS (DQL)
-- ======================================================

/* Relatório de Resumo Geral - Corrigindo Duplicidade Histórica */
SELECT 
    U.nome AS 'Usuário',
    M.nomeComercial AS 'Medicamento',
    F.horarioUso AS 'Horário de uso',
    COALESCE(CONCAT(F.intervaloHoras, 'h'), CONCAT(F.vezesPorDia, 'x/dia')) AS 'Frequência de uso',
    C.confirmacao AS 'Confirmação de consumo'
FROM Usuario U
INNER JOIN UsuarioMedicamento UM ON U.idUsuario = UM.idUsuario
INNER JOIN Medicamento M ON UM.idMedicamento = M.idMedicamento
INNER JOIN Frequencia F ON (UM.idUsuario = F.idUsuario AND UM.idMedicamento = F.idMedicamento)
LEFT JOIN Alerta A ON F.idFrequencia = A.idFrequencia
LEFT JOIN Consumo C ON A.idAlerta = C.idAlerta
WHERE C.idConsumo = (
    SELECT MAX(idConsumo) 
    FROM Consumo C2 
    JOIN Alerta A2 ON C2.idAlerta = A2.idAlerta 
    WHERE A2.idFrequencia = F.idFrequencia
) OR C.idConsumo IS NULL
ORDER BY U.nome, F.horarioUso;