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

