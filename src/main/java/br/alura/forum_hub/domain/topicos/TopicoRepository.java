package br.alura.forum_hub.domain.topicos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    boolean existsByTituloAndMensagem(String titulo, String mensagem);

    boolean existsByTituloAndMensagemAndIdNot(String titulo, String mensagem, Long id);

    Page<Topico> findAllByAtivoTrue(Pageable paginacao);

}
