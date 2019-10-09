use 6BkoHsgUIY;
DROP TABLE aluno, coordenador, curso, disciplina, disciplina_curso, professor, sala, usuario, turma, matriculado;


create table usuario (
	id bigint unsigned auto_increment,
	username varchar(100) not null,
	senha varchar(100) not null,
    rg	int not null,
    cpf int not null,
	primary key (id)
);

create table curso (
	codigo_curso varchar(20) not null,
	nome varchar(20) not null,
	primary key (codigo_curso)
);

create table aluno (
	usuario_id bigint unsigned,
	matricula varchar(20) not null,
	nome varchar(20) not null,
	data_nascimento date not null,
	data_ingresso date not null,
	codigo_curso varchar(20), 
	foreign key (usuario_id) references usuario(id),
	foreign key (codigo_curso) references curso(codigo_curso),
    primary key (usuario_id)
);

create table coordenador (
	usuario_id bigint unsigned,
	nome varchar(100) not null,
	foreign key (usuario_id) references usuario(id),
    primary key (usuario_id)
);

create table professor (
	usuario_id bigint unsigned,
	nome varchar(100) not null,
	matricula varchar(20) not null,
	nivel_formacao varchar(50) not null,
	codigo_curso varchar(20),
	foreign key (usuario_id) references usuario(id),
	foreign key (codigo_curso) references curso(codigo_curso),
	primary key (usuario_id)
);

create table sala ( 
	codigo_sala varchar(20) not null,
	capacidade int not null,
    predio varchar(20),
	primary key (codigo_sala)
);

create table disciplina ( 
	id bigint unsigned auto_increment,
	nome varchar(100) not null,
	creditos int not null,
	departamento varchar(100) not null,
	primary key (id)
);

create table disciplina_curso (
	codigo_curso varchar(20) not null,
    disciplina_id bigint unsigned not null,
    foreign key (codigo_curso) references curso(codigo_curso),
    foreign key (disciplina_id) references disciplina(id),
    primary key (disciplina_id, codigo_curso)
);

create table turma ( 
	codigo_turma varchar(20) not null,
	max_alunos int not null,
	semestre varchar(2) not null,
	ano varchar (5) not null,
	professor_id bigint unsigned,
	disciplina_id bigint unsigned,
	sala_id varchar(20),
	foreign key (professor_id) references professor(usuario_id),
	foreign key (disciplina_id) references disciplina(id),
	foreign key (sala_id) references sala(codigo_sala),
	primary key (codigo_turma)
);

create table matriculado ( 
	aluno_id bigint unsigned,
	codigo_turma varchar(20),
	foreign key (aluno_id) references aluno(usuario_id),
	foreign key (codigo_turma) references turma(codigo_turma),
	primary key (aluno_id, codigo_turma)
);

create table historico (
	nota double not null,
	aluno_id bigint unsigned,
	codigo_turma varchar(20),
	foreign key (aluno_id) references aluno(usuario_id),
	foreign key (codigo_turma) references turma(codigo_turma),
	primary key (aluno_id, codigo_turma)
);

-- evitar em codigo que uma disciplina faça referência a si mesma
create table pre_requisito (
	disciplina_id bigint unsigned,
	prerequisito_id bigint unsigned,
	foreign key (disciplina_id) references disciplina(id),
	foreign key (prerequisito_id) references disciplina(id)
);

create table professor_capacidade (
	professor_id bigint unsigned,
	disciplina_id bigint unsigned,
	foreign key (professor_id) references professor(usuario_id),
	foreign key (disciplina_id) references disciplina(id)	
);