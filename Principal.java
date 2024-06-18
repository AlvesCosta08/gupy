import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {
    public static void main(String[] args) {
        //  Inserir todos os funcionários
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 15), new BigDecimal("3500.00"), "Programador"));
        funcionarios.add(new Funcionario("Maria", LocalDate.of(1985, 8, 20), new BigDecimal("4500.00"), "Analista"));
        funcionarios.add(new Funcionario("Pedro", LocalDate.of(1988, 10, 10), new BigDecimal("4200.00"), "Gerente"));
        
        //Remover o funcionário "João"
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));
        
        // Imprimir todos os funcionários com suas informações formatadas
        System.out.println("Lista de Funcionários:");
        funcionarios.forEach(funcionario -> {
            System.out.printf("Nome: %s\n", funcionario.getNome());
            System.out.printf("Data de Nascimento: %s\n", funcionario.formatarData());
            System.out.printf("Salário: %,.2f\n", funcionario.getSalario());
            System.out.printf("Função: %s\n", funcionario.getFuncao());
            System.out.println("--------------");
        });
        
        // Aplicar aumento de 10% no salário dos funcionários
        funcionarios.forEach(funcionario -> {
            BigDecimal aumento = funcionario.getSalario().multiply(new BigDecimal("0.10"));
            funcionario.setSalario(funcionario.getSalario().add(aumento));
        });
        
        // Agrupar os funcionários por função em um Map
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
        
        // Imprimir os funcionários agrupados por função
        System.out.println("\nFuncionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println(funcao + ":");
            lista.forEach(f -> System.out.printf("- %s\n", f.getNome()));
        });
        
        // Imprimir os funcionários que fazem aniversário nos meses 10 e 12
        System.out.println("\nFuncionários que fazem aniversário nos meses 10 e 12:");
        funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
                .forEach(f -> System.out.println(f.getNome()));
        
        // Imprimir o funcionário com a maior idade
        System.out.println("\nFuncionário mais velho:");
        Funcionario maisVelho = funcionarios.stream()
                .min(Comparator.comparing(f -> f.getDataNascimento()))
                .orElse(null);
        if (maisVelho != null) {
            int idade = LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear();
            System.out.printf("Nome: %s, Idade: %d anos\n", maisVelho.getNome(), idade);
        }
        
        // Imprimir a lista de funcionários por ordem alfabética
        System.out.println("\nLista de Funcionários por ordem alfabética:");
        funcionarios.stream()
                .map(Funcionario::getNome)
                .sorted()
                .forEach(System.out::println);
        
        // Imprimir o total dos salários dos funcionários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.printf("\nTotal dos salários dos funcionários: %,.2f\n", totalSalarios);
        
        // Imprimir quantos salários mínimos ganha cada funcionário (salário mínimo: R$ 1212.00)
        System.out.println("\nSalários mínimos ganhos por cada funcionário:");
        funcionarios.forEach(funcionario -> {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(new BigDecimal("1212.00"), 2, BigDecimal.ROUND_DOWN);
            System.out.printf("%s: %.2f salários mínimos\n", funcionario.getNome(), salariosMinimos);
        });
    }
}
