alter table topico
add constraint uk_topico_titulo_mensagem
unique (titulo, mensagem(255));