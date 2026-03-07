package br.alura.forum_hub.domain.topicos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DadosTopicos(


        @NotBlank(message = "Título é obrigatório")
        @Size(min = 5, max = 100, message = "Título deve ter entre 5 e 100 caracteres")
        String titulo,

        @NotBlank(message = "Mensagem é obrigatória")
        @Size(min = 10, message = "Mensagem deve ter no mínimo 10 caracteres")
        String mensagem,

        @NotBlank(message = "Autor é obrigatório")
        String autor,

        @NotBlank(message = "Curso é obrigatório")
        String curso
) {

}
