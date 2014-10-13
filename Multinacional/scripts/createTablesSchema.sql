CREATE TABLE Filial (
	cod VARCHAR(55) NOT NULL,
    nome VARCHAR(55) NOT NULL,
    PRIMARY KEY(cod)
);

CREATE TABLE Produto (
	id INTEGER NOT NULL AUTO_INCREMENT,
    nome VARCHAR(55) NOT NULL,
    quantide_disponivel INTEGER NOT NULL DEFAULT 0,
    peso DOUBLE NOT NULL DEFAULT 0,
    preco_unitario DOUBLE NOT NULL DEFAULT 0,
    filial_cod VARCHAR(55) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(filial_cod) REFERENCES Filial(Cod)
);

CREATE TABLE Cliente (
	id INTEGER NOT NULL AUTO_INCREMENT,
    nome VARCHAR(55) NOT NULL,
	cpf VARCHAR(55) NOT NULL,
    data_nascimento DATE NOT NULL,
    filial_cod VARCHAR(55) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(filial_cod) REFERENCES Filial(Cod)
);

CREATE TABLE Compra (
	id INTEGER NOT NULL AUTO_INCREMENT,
    data_compra DATE NOT NULL,
    cliente_id INTEGER NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(cliente_id) REFERENCES Cliente(id)
);

CREATE TABLE ItemCompra (
    quantidade INTEGER NOT NULL DEFAULT 0,
    compra_id INTEGER NOT NULL,
    produto_id INTEGER NOT NULL,
    PRIMARY KEY(compra_id, produto_id),
    FOREIGN KEY(compra_id) REFERENCES Compra(id),
    FOREIGN KEY(produto_id) REFERENCES Produto(id)
);