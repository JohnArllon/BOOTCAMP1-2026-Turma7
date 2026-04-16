package br.uninter.medalerta.app;

import br.uninter.medalerta.model.*;
import br.uninter.medalerta.service.MedicamentoService;
import br.uninter.medalerta.service.UsuarioMedicamentoService;
import br.uninter.medalerta.service.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class App implements CommandLineRunner {

private final UsuarioService usuarioService;
private final MedicamentoService medicamentoService;
private final UsuarioMedicamentoService usuarioMedicamentoService;

public App(
        UsuarioService usuarioService,
        MedicamentoService medicamentoService,
        UsuarioMedicamentoService usuarioMedicamentoService
) {
    this.usuarioService = usuarioService;
    this.medicamentoService = medicamentoService;
    this.usuarioMedicamentoService = usuarioMedicamentoService;
}

@Override
public void run(String... args) {
    Scanner sc = new Scanner(System.in);
    int opcao;

    do {
        System.out.println("\n===== MEDALERTA =====");
        System.out.println("1 - Usuário");
        System.out.println("2 - Medicamento");
        System.out.println("3 - Vincular");
        System.out.println("0 - Sair");

        opcao = lerInteiro(sc, "Opção: ");

        switch (opcao) {
            case 1 -> cadastrarUsuario(sc);
            case 2 -> cadastrarMedicamento(sc);
            case 3 -> vincular(sc);
        }

    } while (opcao != 0);

    sc.close();
}

private void cadastrarUsuario(Scanner sc) {
    Usuario u = new Usuario();
    u.setNome(lerTexto(sc, "Nome: "));
    u.setTelefone(lerTexto(sc, "Telefone: "));
    u.setEmail(lerTexto(sc, "Email: "));
    usuarioService.salvar(u);
}

private void cadastrarMedicamento(Scanner sc) {
    Medicamento m = new Medicamento();
    m.setNomeComercial(lerTexto(sc, "Nome: "));
    m.setQuantidade(lerQuantidade(sc));
    medicamentoService.salvar(m);
}

private void vincular(Scanner sc) {
    Integer idUsuario = lerInteiro(sc, "ID Usuário: ");
    Integer idMedicamento = lerInteiro(sc, "ID Medicamento: ");
    String dosagem = lerTexto(sc, "Dosagem: ");

    usuarioMedicamentoService.vincular(idUsuario, idMedicamento, dosagem);
}

private Quantidade lerQuantidade(Scanner sc) {
    while (true) {
        System.out.print("Quantidade [UNIDADE/ML]: ");
        try {
            return Quantidade.valueOf(sc.nextLine().trim());
        } catch (Exception e) {
            System.out.println("Valor inválido.");
        }
    }
}

private Integer lerInteiro(Scanner sc, String msg) {
    while (true) {
        try {
            System.out.print(msg);
            return Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Número inválido.");
        }
    }
}

private String lerTexto(Scanner sc, String msg) {
    System.out.print(msg);
    return sc.nextLine();
}

}