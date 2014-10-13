USE japao;

CREATE TABLE Produto (
	id INTEGER NOT NULL AUTO_INCREMENT,
    nome VARCHAR(55) NOT NULL,
    quantidade_disponivel INTEGER NOT NULL DEFAULT 0,
    peso DOUBLE NOT NULL DEFAULT 0,
    preco_unitario DOUBLE NOT NULL DEFAULT 0,
    PRIMARY KEY(id)
);

CREATE TABLE Cliente (
	cod VARCHAR(15) NOT NULL,
    nome VARCHAR(55) NOT NULL,
	cpf VARCHAR(55) NOT NULL,
    data_nascimento DATE NOT NULL,
    PRIMARY KEY(cod)
);

CREATE TABLE Compra (
	id INTEGER NOT NULL AUTO_INCREMENT,
    data_compra DATE NOT NULL,
    cliente_cod VARCHAR(15) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(cliente_cod) REFERENCES Cliente(cod)
);

CREATE TABLE ItemCompra (
    quantidade INTEGER NOT NULL DEFAULT 0,
    compra_id INTEGER NOT NULL,
    produto_id INTEGER NOT NULL,
    PRIMARY KEY(compra_id, produto_id),
    FOREIGN KEY(compra_id) REFERENCES Compra(id),
    FOREIGN KEY(produto_id) REFERENCES Produto(id)
);

###############################################################################
USE brasil;

CREATE TABLE Filial (
	cod VARCHAR(55) NOT NULL,
    nome VARCHAR(55) NOT NULL,
    porta INTEGER NOT NULL,
    PRIMARY KEY(cod)
);

CREATE TABLE Produto (
	id INTEGER NOT NULL AUTO_INCREMENT,
    nome VARCHAR(55) NOT NULL,
    quantidade_disponivel INTEGER NOT NULL DEFAULT 0,
    peso DOUBLE NOT NULL DEFAULT 0,
    preco_unitario DOUBLE NOT NULL DEFAULT 0,
    PRIMARY KEY(id)
);

CREATE TABLE Cliente (
	cod VARCHAR(15) NOT NULL,
    nome VARCHAR(55) NOT NULL,
	cpf VARCHAR(55) NOT NULL,
    data_nascimento DATE NOT NULL,
    PRIMARY KEY(cod)
);

CREATE TABLE Compra (
	id INTEGER NOT NULL AUTO_INCREMENT,
    data_compra DATE NOT NULL,
    cliente_cod VARCHAR(15) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(cliente_cod) REFERENCES Cliente(cod)
);

CREATE TABLE ItemCompra (
    quantidade INTEGER NOT NULL DEFAULT 0,
    compra_id INTEGER NOT NULL,
    produto_id INTEGER NOT NULL,
    PRIMARY KEY(compra_id, produto_id),
    FOREIGN KEY(compra_id) REFERENCES Compra(id),
    FOREIGN KEY(produto_id) REFERENCES Produto(id)
);

###############################################################################
USE italia;

CREATE TABLE Produto (
	id INTEGER NOT NULL AUTO_INCREMENT,
    nome VARCHAR(55) NOT NULL,
    quantidade_disponivel INTEGER NOT NULL DEFAULT 0,
    peso DOUBLE NOT NULL DEFAULT 0,
    preco_unitario DOUBLE NOT NULL DEFAULT 0,
    PRIMARY KEY(id)
);

CREATE TABLE Cliente (
	cod VARCHAR(15) NOT NULL,
    nome VARCHAR(55) NOT NULL,
	cpf VARCHAR(55) NOT NULL,
    data_nascimento DATE NOT NULL,
    PRIMARY KEY(cod)
);

CREATE TABLE Compra (
	id INTEGER NOT NULL AUTO_INCREMENT,
    data_compra DATE NOT NULL,
    cliente_cod VARCHAR(15) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(cliente_cod) REFERENCES Cliente(cod)
);

CREATE TABLE ItemCompra (
    quantidade INTEGER NOT NULL DEFAULT 0,
    compra_id INTEGER NOT NULL,
    produto_id INTEGER NOT NULL,
    PRIMARY KEY(compra_id, produto_id),
    FOREIGN KEY(compra_id) REFERENCES Compra(id),
    FOREIGN KEY(produto_id) REFERENCES Produto(id)
);

###############################################################################
USE canada;

CREATE TABLE Produto (
	id INTEGER NOT NULL AUTO_INCREMENT,
    nome VARCHAR(55) NOT NULL,
    quantidade_disponivel INTEGER NOT NULL DEFAULT 0,
    peso DOUBLE NOT NULL DEFAULT 0,
    preco_unitario DOUBLE NOT NULL DEFAULT 0,
    PRIMARY KEY(id)
);

CREATE TABLE Cliente (
	cod VARCHAR(15) NOT NULL,
    nome VARCHAR(55) NOT NULL,
	cpf VARCHAR(55) NOT NULL,
    data_nascimento DATE NOT NULL,
    PRIMARY KEY(cod)
);

CREATE TABLE Compra (
	id INTEGER NOT NULL AUTO_INCREMENT,
    data_compra DATE NOT NULL,
    cliente_cod VARCHAR(15) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(cliente_cod) REFERENCES Cliente(cod)
);

CREATE TABLE ItemCompra (
    quantidade INTEGER NOT NULL DEFAULT 0,
    compra_id INTEGER NOT NULL,
    produto_id INTEGER NOT NULL,
    PRIMARY KEY(compra_id, produto_id),
    FOREIGN KEY(compra_id) REFERENCES Compra(id),
    FOREIGN KEY(produto_id) REFERENCES Produto(id)
);