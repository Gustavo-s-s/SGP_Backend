INSERT INTO public.usuarios (id,nome,tipo,email,senha) VALUES
                                                           (1,'Lucas','GERENTE','lucas@mail.com','123'),
                                                           (2,'Ravel','FUNCIONARIO','ravel@mail.com','123'),
                                                           (3,'Luis','CLIENTE','Luis@mail.com','123'),
                                                           (4,'Miguel','ADMIN','MIGUEL@mail.com','123');
INSERT INTO public.membros (id,funcao,lotacao,setor,ramal) VALUES
                                                               (1,'GERENTE','A','A','999'),
                                                               (2,'FUNCIONARIO','B','B','998'),
                                                               (4,'ADMIN','D','D','996');
INSERT INTO public.patrocinadores (id,posicao) VALUES
    (3,'Governador');
INSERT INTO public.equipes (id,patrocinador_id) VALUES
                                                     (1,3),
                                                     (2,3);

INSERT INTO public.equipe_membros (equipe_id,membro_id) VALUES
                                                            (1,1),
                                                            (1,2),
                                                            (2,4),
                                                            (2,1);
INSERT INTO public.unidades (id,nome,direcao,setor,responsabilidade,email,nome_servidor,ramal) VALUES
                                                                                                   (1,'unidade 1','teste','teste','teste','teste','teste','teste'),
                                                                                                   (2,'unidade 2','teste','teste','teste','teste','teste','teste');
INSERT INTO public.projetos (id,nome,versao,objetivo,data_inicio,data_fim,prazo,tipo,status,equipe_id) VALUES
                                                                                                           (1,'teste','versao','funcionar','2000-06-28',NULL,'2000-06-28','ESTRATEGICO','EM_ANDAMENTO',1),
                                                                                                           (2,'teste2','versao2','funcionar2','2000-06-28','2024-06-28','2001-06-28','OPERACIONAL','FINALIZADO',2);
INSERT INTO public.projeto_unidades (projeto_id,unidade_id) VALUES
                                                                (1,1),
                                                                (2,2);