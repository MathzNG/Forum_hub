package br.alura.forum_hub.controller;

import br.alura.forum_hub.domain.ValidacaoException;
import br.alura.forum_hub.domain.topicos.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarTopico(@RequestBody @Valid DadosTopicos dados) {

        if (topicoRepository.existsByTituloAndMensagem(
                dados.titulo(), dados.mensagem())){

            throw new ValidacaoException("Já existe um tópico com esse título e mensagem.");
        }

        var topico = new Topico(dados);
        topicoRepository.save(topico);

        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(@PageableDefault(size = 10, sort = {"titulo"}) Pageable paginacao) {
        var page = topicoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoTopico> detalhar(@PathVariable long id) {
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosAtualizacaoTopico> atualizacao(
            @PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados){

        var optionalTopico = topicoRepository.findById(id);

        if(optionalTopico.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        if (topicoRepository.existsByTituloAndMensagemAndIdNot(
                dados.titulo(), dados.mensagem(), dados.id())){

            throw new ValidacaoException("Já existe um tópico com esse título e mensagem.");
        }

        var topico = optionalTopico.get();

        if(!topico.isAtivo()){
            throw new ValidacaoException("Este tópico foi removido.");
        }

        topico.atualizarInformacoes(dados);



        return ResponseEntity.ok(new DadosAtualizacaoTopico(topico));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable long id) {

        var optionalTopico = topicoRepository.findById(id);

        if(optionalTopico.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var topico = optionalTopico.get();
        topico.excluir();
        return ResponseEntity.noContent().build();
    }
}

