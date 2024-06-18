import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    
    // Método para formatar data no formato dd/mm/aaaa
    public String formatarData() {
        return dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
