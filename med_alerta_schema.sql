-- Criado por John Arllon para MedAlerta
DROP DATABASE IF EXISTS MedAlerta;
CREATE DATABASE MedAlerta;
USE MedAlerta;

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