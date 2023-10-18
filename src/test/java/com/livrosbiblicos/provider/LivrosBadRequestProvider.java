package com.livrosbiblicos.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

import static com.livrosbiblicos.common.constante.TesteConstantes.*;
import static com.livrosbiblicos.common.enums.ParametroEnum.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class LivrosBadRequestProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {

        return Stream.of(
                arguments(PARAM_TESTAMENTO, MSG_TESTAMENTO_INVALIDO, "O parâmetro 'testamento' é inválido. Deve ser 'VT' ou 'NT'.", "Livro com parametro testamento inválido"),
                arguments(PARAM_AUTOR, MSG_AUTOR_INVALIDO, mensagemParametroInvalido("autor", "2"), "Livro com parametro autor inválido"),
                arguments(PARAM_GRUPO, MSG_GRUPO_INVALIDO, mensagemParametroInvalido("grupo", "3"), "Livro com parametro grupo inválido")
        );
    }
}
