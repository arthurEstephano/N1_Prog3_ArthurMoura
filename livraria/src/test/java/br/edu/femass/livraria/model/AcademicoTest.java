package br.edu.femass.livraria.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


class AcademicoTest {

    Autor autor = new Autor("Autor", "Generico");
    Academico acade = new Academico("Marcos", "11111111111", Estado_Academico.Professor);
    Livro livro = new Livro(1999, 1, GeneroLivro.Terror, "LivroTsT", this.autor);

    public Livro getLivro() {
        return livro;
    }



    //TODO: Para testar os métodos, deve-se comentar todas as linnhas na classe acadêmico que contém "Alert"
    //Teste de aluguel
    @Test
    void alugar_Livro() {
        this.livro.setDisponibolidade(true);
        try {
            alugar_Livro();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.acade.Alugar_Livro(livro);
        Assertions.assertEquals(this.getLivro(), this.acade.getLivros_alugados().get(0));
    }

    @Test
    void alugar_Livro_Leitura() {
        this.acade.Alugar_Livro(livro);
        Assertions.assertTrue(this.acade.getLivros_alugados().isEmpty());
    }

    @Test
    void alugar_Livro_Ja_Alugado() {
        this.livro.setDisponibolidade(true);
        this.acade.Alugar_Livro(livro);
        Academico acadeT = new Academico("Robson", "11111111111", Estado_Academico.Aluno);
        Assertions.assertTrue(acadeT.getLivros_alugados().isEmpty());
    }

    @Test
    void alugar_Livro_Atrasos() {
        this.livro.setDisponibolidade(true);
        this.acade.Alugar_Livro(livro);
        this.livro.setData_prev_delovucao(LocalDate.now().minusDays(60));
        Livro livroTsT2 = new Livro(1998, 3, GeneroLivro.Terror, "LivroTsT2", this.autor);
        livroTsT2.setDisponibolidade(true);
        this.acade.Alugar_Livro(livroTsT2);
        Assertions.assertFalse(this.acade.getLivros_alugados().contains(livroTsT2));
    }

    //TODO: Para testar os métodos, deve-se comentar todas as linnhas na classe acadêmico que contém "Alert"
    //Teste de retorno
    @Test
    void retornar_Livro() {
        this.livro.setDisponibolidade(true);
        this.acade.Alugar_Livro(livro);
        this.acade.Retornar_Livro(livro);
        Assertions.assertFalse(this.acade.getLivros_alugados().contains(this.livro));
    }

    @Test
    void retornar_Livro_NaoPossui() {
        this.livro.setDisponibolidade(true);
        this.acade.Alugar_Livro(livro);
        Academico acadeT = new Academico("Robson", "11111111111", Estado_Academico.Aluno);
        acadeT.Retornar_Livro(livro);
        Assertions.assertTrue(acade.getLivros_alugados().contains(livro) && acadeT.getLivros_alugados().isEmpty());
    }

    @Test
    void test_true(){
        Assertions.assertTrue(true);
    }
}