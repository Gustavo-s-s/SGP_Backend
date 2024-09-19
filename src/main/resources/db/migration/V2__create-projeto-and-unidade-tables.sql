
CREATE TABLE membros(
    id int PRIMARY KEY,
    funcao varchar(50),
    lotacao varchar(50),
    setor varchar(50),
    ramal varchar(50),
    FOREIGN KEY (id) REFERENCES usuarios(id)
);

CREATE TABLE patrocinadores(
    id int PRIMARY KEY,
    posicao varchar(20),
    FOREIGN KEY (id) REFERENCES usuarios(id)
);

CREATE TABLE equipes(
    id int PRIMARY KEY,
    patrocinador_id int REFERENCES patrocinadores (id)
);

CREATE TABLE projetos (
    id int PRIMARY KEY,
    nome varchar(50) NOT NULL,
    versao varchar(20),
    objetivo varchar(200),
    data_inicio DATE,
    data_fim DATE,
    prazo DATE,
    tipo varchar(15),
    status varchar(20),
    equipe_id  int REFERENCES equipes (id)
);

CREATE TABLE unidades (
    id int PRIMARY KEY,
    nome varchar(50),
    direcao varchar(50),
    setor varchar(50),
    responsabilidade varchar(50),
    email varchar(50),
    nome_servidor varchar(50),
    ramal varchar(50)
);

CREATE TABLE projeto_unidades (
 projeto_id    int REFERENCES projetos (id) ON UPDATE CASCADE ON DELETE CASCADE,
 unidade_id int REFERENCES unidades (id) ON UPDATE CASCADE
);

CREATE TABLE equipe_membros(
    equipe_id int REFERENCES equipes (id) ON UPDATE CASCADE ON DELETE CASCADE,
    membro_id int REFERENCES membros (id) ON UPDATE CASCADE ON DELETE CASCADE
);