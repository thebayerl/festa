use 6BkoHsgUIY;

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

create table disciplina_curso (
	codigo_curso varchar(20) not null,
    disciplina_id bigint not null,
    foreign key (codigo_curso) references curso(codigo_curso),
    foreign key (disciplina_id) references disciplina(id),
    primary key (disciplina_id, codigo_curso)
);

create table aluno (
	matricula varchar(20) not null,
	nome varchar(20) not null,
	data_nascimento date not null,
	data_ingresso date not null,
	usuario_id bigint unsigned,
	curso_id bigint unsigned, 
	foreign key (usuario_id) references usuario(id),
	foreign key (curso_id) references curso(id),
    primary key (usuario_id)
);

create table coordenador (
	nome varchar(100) not null,
	usuario_id bigint unsigned,
	foreign key (usuario_id) references usuario(id),
    primary key (usuario_id)
);

create table professor (
	nome varchar(100) not null,
	matricula varchar(20) not null,
	nivel_formacao varchar(50) not null,
	usuario_id bigint unsigned,
	coordenador_id bigint unsigned,
	foreign key (usuario_id) references usuario(id),
	foreign key (coordenador_id) references coordenador(id),
	primary key (usuario_id)
);

create table sala ( 
	codigo_sala varchar(20) not null,
	capacidade int not null,
	primary key (codigo_sala)
);

create table disciplina ( 
	id bigint unsigned auto_increment,
	nome varchar(100) not null,
	creditos int not null,
	departamento varchar(100) not null,
	primary key (id)
);

create table turma ( 
	id bigint unsigned auto_increment,
	codigo_turma varchar(20) not null,
	max_alunos int not null,
	semestre varchar(2) not null,
	ano varchar (5) not null,
	professor_id bigint unsigned,
	disciplina_id bigint unsigned,
	sala_id bigint unsigned,
	foreign key (professor_id) references professor(id),
	foreign key (disciplina_id) references disciplina(id),
	foreign key (sala_id) references sala(id),
	primary key (id)
);

create table matriculado ( 
	aluno_id bigint unsigned,
	turma_id bigint unsigned,
	foreign key (aluno_id) references aluno(id),
	foreign key (turma_id) references turma(id),
	primary key (aluno_id, turma_id)
);

create table historico (
	id bigint unsigned auto_increment,
	nota double not null,
	aluno_id bigint unsigned,
	turma_id bigint unsigned,
	foreign key (aluno_id) references aluno(id),
	foreign key (turma_id) references turma(id),
	primary key (id)
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
	foreign key (professor_id) references professor(id),
	foreign key (disciplina_id) references disciplina(id)	
);






