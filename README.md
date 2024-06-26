# Projeto Gerenciamento de Funcionários

Este projeto demonstra um sistema simples de gerenciamento de funcionários, utilizando classes em Java para modelar os dados e operações necessárias. A aplicação permite criar, manipular e exibir informações de funcionários, aplicando diversas operações como aumentos salariais, agrupamentos e cálculos.

## Estrutura do Projeto

### 1. Classe Pessoa

A classe `Pessoa` serve como uma classe base para representar uma pessoa com nome e data de nascimento.

```java
public class Pessoa {
    protected String nome;
    protected LocalDate dataNascimento;
    
    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }
    
    public String getNome() {
        return nome;
    }
    
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    public String formatarData() {
        return dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
```
### Principais Funcionalidades:
- getNome: Retorna o nome da pessoa.
- getDataNascimento: Retorna a data de nascimento da pessoa.
- setDataNascimento: Define a data de nascimento da pessoa.
- formatarData: Formata a data de nascimento no formato "dd/MM/yyyy".

### Classe Funcionario
A classe Funcionario estende a classe Pessoa e adiciona propriedades específicas de um funcionário, como salário e função.


```java
public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;
    
    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }
    
    public BigDecimal getSalario() {
        return salario;
    }
    
    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }
    
    public String getFuncao() {
        return funcao;
    }
}
```

### Principais Funcionalidades:
- getSalario: Retorna o salário do funcionário.
- setSalario: Define o salário do funcionário.
- getFuncao: Retorna a função do funcionário.

## Classe Principal
A classe Principal contém o método main e implementa as operações solicitadas.

```java

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 15), new BigDecimal("3500.00"), "Programador"));
        funcionarios.add(new Funcionario("Maria", LocalDate.of(1985, 8, 20), new BigDecimal("4500.00"), "Analista"));
        funcionarios.add(new Funcionario("Pedro", LocalDate.of(1988, 10, 10), new BigDecimal("4200.00"), "Gerente"));

        // Remover funcionário "João"
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // Imprimir informações dos funcionários
        System.out.println("Lista de Funcionários:");
        funcionarios.forEach(funcionario -> {
            System.out.printf("Nome: %s\n", funcionario.getNome());
            System.out.printf("Data de Nascimento: %s\n", funcionario.formatarData());
            System.out.printf("Salário: %,.2f\n", funcionario.getSalario());
            System.out.printf("Função: %s\n", funcionario.getFuncao());
            System.out.println("--------------");
        });

        // Aumentar salários em 10%
        funcionarios.forEach(funcionario -> {
            BigDecimal aumento = funcionario.getSalario().multiply(new BigDecimal("0.10"));
            funcionario.setSalario(funcionario.getSalario().add(aumento));
        });

        // Agrupar funcionários por função
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // Imprimir funcionários por função
        System.out.println("\nFuncionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println(funcao + ":");
            lista.forEach(f -> System.out.printf("- %s\n", f.getNome()));
        });

        // Imprimir funcionários com aniversário nos meses 10 e 12
        System.out.println("\nFuncionários que fazem aniversário nos meses 10 e 12:");
        funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
                .forEach(f -> System.out.println(f.getNome()));

        // Imprimir o funcionário com a maior idade
        System.out.println("\nFuncionário mais velho:");
        Funcionario maisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElse(null);
        if (maisVelho != null) {
            int idade = LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear();
            System.out.printf("Nome: %s, Idade: %d anos\n", maisVelho.getNome(), idade);
        }

        // Imprimir lista de funcionários por ordem alfabética
        System.out.println("\nLista de Funcionários por ordem alfabética:");
        funcionarios.stream()
                .map(Funcionario::getNome)
                .sorted()
                .forEach(System.out::println);

        // Imprimir total dos salários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.printf("\nTotal dos salários dos funcionários: %,.2f\n", totalSalarios);

        // Imprimir salários mínimos ganhos por cada funcionário
        System.out.println("\nSalários mínimos ganhos por cada funcionário:");
        funcionarios.forEach(funcionario -> {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(new BigDecimal("1212.00"), 2, BigDecimal.ROUND_DOWN);
            System.out.printf("%s: %.2f salários mínimos\n", funcionario.getNome(), salariosMinimos);
        });
    }
}

```


### Principais Funcionalidades:
- Inserção de funcionários: Adiciona funcionários à lista.
- Remoção de funcionário: Remove um funcionário específico da lista.
- Exibição de informações: Imprime detalhes formatados dos funcionários.
- Aumento salarial: Aplica um aumento de 10% no salário de todos os funcionários.
- Agrupamento por função: Agrupa funcionários por sua função.
- Filtragem por aniversário: Filtra e exibe funcionários que fazem aniversário em meses específicos.
- Identificação do mais velho: Encontra e exibe o funcionário mais velho.
- Ordenação alfabética: Ordena e imprime os nomes dos funcionários em ordem alfabética.
- Cálculo de salários totais: Calcula e exibe o total dos salários de todos os funcionários.
- Comparação com salário mínimo: Calcula e exibe quantos salários mínimos cada funcionário ganha.


### Recursos Utilizados
- List
### A interface List é utilizada para armazenar a lista de funcionários. Ela permite a inserção, remoção e iteração sobre os elementos.

- Map
#### A interface Map é usada para agrupar os funcionários por função, onde a chave é a função e o valor é uma lista de funcionários que exercem essa função.

- Stream
#### A API Stream é utilizada para processar coleções de maneira funcional. As operações principais incluem:

- filter: Filtra elementos de acordo com um predicado.
- forEach: Executa uma ação para cada elemento do stream.
- map: Aplica uma função a cada elemento do stream e retorna um novo stream.
- sorted: Retorna um stream ordenado.
- collect: Agrupa ou coleta os elementos do stream em uma coleção ou outro tipo de resultado.
- min: Retorna o menor elemento de acordo com um comparador.
- reduce: Reduz os elementos do stream a um único valor usando uma função de acumulação.


### Como Executar
Para executar o projeto, compile e execute a classe Principal. A classe contém o método main que realiza todas as operações descritas.


O código foi desenvolvido em `Java 21` e utiliza as bibliotecas padrão do JDK, como java.time para manipulação de datas e java.math para operações com valores monetários.

Francisco  
### Alves da Costa