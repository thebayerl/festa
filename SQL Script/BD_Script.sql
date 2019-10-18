use `6BkoHsgUIY`;

DROP TABLE IF EXISTS coordenador;
DROP TABLE IF EXISTS disciplina_curso;
DROP TABLE IF EXISTS historico;
DROP TABLE IF EXISTS matriculado;
DROP TABLE IF EXISTS pre_requisito;
DROP TABLE IF EXISTS professor_capacidade;
DROP TABLE IF EXISTS turma;
DROP TABLE IF EXISTS sala;
DROP TABLE IF EXISTS disciplina;
DROP TABLE IF EXISTS professor;
DROP TABLE IF EXISTS aluno;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS curso;

CREATE TABLE `usuario` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `senha` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `rg` varchar(9) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `curso` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `codigo_curso` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nome` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 collate=utf8mb4_unicode_ci;

CREATE TABLE `aluno` (
  `usuario_id` int unsigned NOT NULL,
  `matricula` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nome` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `data_nascimento` date NOT NULL,
  `data_ingresso` date NOT NULL,
  `curso_id` int unsigned NOT NULL,
  PRIMARY KEY (`usuario_id`),
  KEY `curso_id` (`curso_id`),
  CONSTRAINT `aluno_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `aluno_ibfk_2` FOREIGN KEY (`curso_id`) REFERENCES `curso` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `coordenador` (
  `usuario_id` int unsigned NOT NULL,
  `nome` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`usuario_id`),
  CONSTRAINT `coordenador_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `disciplina` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `codigo_disciplina` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `nome` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `creditos` int(11) NOT NULL,
  `departamento` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `codigo_disciplina_UNIQUE` (`codigo_disciplina`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `disciplina_curso` (
  `curso_id` int unsigned NOT NULL,
  `disciplina_id` int unsigned NOT NULL,
  PRIMARY KEY (`disciplina_id`,`curso_id`),
  KEY `curso_id` (`curso_id`),
  CONSTRAINT `disciplina_curso_ibfk_1` FOREIGN KEY (`curso_id`) REFERENCES `curso` (`id`),
  CONSTRAINT `disciplina_curso_ibfk_2` FOREIGN KEY (`disciplina_id`) REFERENCES `disciplina` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `professor` (
  `usuario_id` int unsigned NOT NULL,
  `nome` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `matricula` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nivel_formacao` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `curso_id` int unsigned NOT NULL,
  PRIMARY KEY (`usuario_id`),
  KEY `curso_id` (`curso_id`),
  CONSTRAINT `professor_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `professor_ibfk_2` FOREIGN KEY (`curso_id`) REFERENCES `curso` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `professor_capacidade` (
  `professor_id` int unsigned DEFAULT NULL,
  `disciplina_id` int unsigned DEFAULT NULL,
  KEY `professor_id` (`professor_id`),
  KEY `disciplina_id` (`disciplina_id`),
  CONSTRAINT `professor_capacidade_ibfk_1` FOREIGN KEY (`professor_id`) REFERENCES `professor` (`usuario_id`),
  CONSTRAINT `professor_capacidade_ibfk_2` FOREIGN KEY (`disciplina_id`) REFERENCES `disciplina` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `sala` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `codigo_sala` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `capacidade` int(11) NOT NULL,
  `predio` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `turma` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `codigo_turma` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `max_alunos` int(11) NOT NULL,
  `semestre` varchar(2) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ano` varchar(5) COLLATE utf8mb4_unicode_ci NOT NULL,
  `professor_id` int unsigned DEFAULT NULL,
  `disciplina_id` int unsigned DEFAULT NULL,
  `sala_id` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `professor_id` (`professor_id`),
  KEY `disciplina_id` (`disciplina_id`),
  KEY `sala_id` (`sala_id`),
  CONSTRAINT `turma_ibfk_1` FOREIGN KEY (`professor_id`) REFERENCES `professor` (`usuario_id`),
  CONSTRAINT `turma_ibfk_2` FOREIGN KEY (`disciplina_id`) REFERENCES `disciplina` (`id`),
  CONSTRAINT `turma_ibfk_3` FOREIGN KEY (`sala_id`) REFERENCES `sala` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `historico` (
  `nota` double NOT NULL,
  `aluno_id` int unsigned NOT NULL,
  `turma_id` int unsigned NOT NULL,
  PRIMARY KEY (`aluno_id`,`turma_id`),
  KEY `turma_id` (`turma_id`),
  CONSTRAINT `historico_ibfk_1` FOREIGN KEY (`aluno_id`) REFERENCES `aluno` (`usuario_id`),
  CONSTRAINT `historico_ibfk_2` FOREIGN KEY (`turma_id`) REFERENCES `turma` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `matriculado` (
  `aluno_id` int unsigned NOT NULL,
  `turma_id` int unsigned NOT NULL,
  PRIMARY KEY (`aluno_id`,`turma_id`),
  KEY `turma_id` (`turma_id`),
  CONSTRAINT `matriculado_ibfk_1` FOREIGN KEY (`aluno_id`) REFERENCES `aluno` (`usuario_id`),
  CONSTRAINT `matriculado_ibfk_2` FOREIGN KEY (`turma_id`) REFERENCES `turma` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `pre_requisito` (
  `disciplina_id` int unsigned DEFAULT NULL,
  `prerequisito_id` int unsigned DEFAULT NULL,
  KEY `disciplina_id` (`disciplina_id`),
  KEY `prerequisito_id` (`prerequisito_id`),
  CONSTRAINT `pre_requisito_ibfk_1` FOREIGN KEY (`disciplina_id`) REFERENCES `disciplina` (`id`),
  CONSTRAINT `pre_requisito_ibfk_2` FOREIGN KEY (`prerequisito_id`) REFERENCES `disciplina` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


