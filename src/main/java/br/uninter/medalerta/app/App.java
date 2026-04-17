package br.uninter.medalerta.app;

import br.uninter.medalerta.model.*;
import br.uninter.medalerta.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

@Component
public class App implements CommandLineRunner {

    private final UsuarioService usuarioService;
    private final MedicamentoService medicamentoService;
    private final UsuarioMedicamentoService usuarioMedicamentoService;
    private final FrequenciaService frequenciaService;
    private final AlertaService alertaService;
    private final ConsumoService consumoService;

    public App(
            UsuarioService usuarioService,
            MedicamentoService medicamentoService,
            UsuarioMedicamentoService usuarioMedicamentoService,
            FrequenciaService frequenciaService,
            AlertaService alertaService,
            ConsumoService consumoService
    ) {
        this.usuarioService = usuarioService;
        this.medicamentoService = medicamentoService;
        this.usuarioMedicamentoService = usuarioMedicamentoService;
        this.frequenciaService = frequenciaService;
        this.alertaService = alertaService;
        this.consumoService = consumoService;
    }

    @Override
    public void run(String... args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MEDALERTA - TURMA 7 =====");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Cadastrar Medicamento");
            System.out.println("3 - Vincular usuário");
            System.out.println("4 - Configurar Frequência de Uso ");
            System.out.println("5 - Histórico de Alertas");
            System.out.println("6 - Confirmar Consumo");
            System.out.println("0 - Sair");

            opcao = lerInteiro(sc, "Escolha uma Opção: ");

            switch (opcao) {
                case 1 -> cadastrarUsuario(sc);
                case 2 -> cadastrarMedicamento(sc);
                case 3 -> vincular(sc);
                case 4 -> gerenciarFrequencia(sc);
                case 5 -> gerenciarAlertas(sc);
                case 6 -> registrarConsumo(sc);
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }


    private void cadastrarUsuario(Scanner sc) {
        Usuario u = new Usuario();
        u.setNome(lerTexto(sc, "Nome Completo: "));
        u.setTelefone(lerTexto(sc, "Telefone: "));
        u.setEmail(lerTexto(sc, "E-mail: "));
        u.setEnderecoRua(lerTexto(sc, "Rua: "));
        u.setEnderecoNumero(lerInteiro(sc, "Número: "));
        u.setEnderecoBairro(lerTexto(sc, "Bairro: "));
        u.setEnderecoCidade(lerTexto(sc, "Cidade: "));
        u.setEnderecoEstado(lerTexto(sc, "Estado (UF): "));

        usuarioService.salvar(u);
        System.out.println("Usuário salvo com sucesso!");
    }

    private void cadastrarMedicamento(Scanner sc) {
        Medicamento m = new Medicamento();
        m.setNomeComercial(lerTexto(sc, "Nome Comercial: "));
        m.setNomeGenerico(lerTexto(sc, "Nome Genérico: "));
        m.setQuantidade(lerQuantidade(sc));
        m.setFormaUso(lerTexto(sc, "Forma de Uso (Ex: Via Oral): "));
        m.setObservacao(lerTexto(sc, "Observações: "));

        medicamentoService.salvar(m);
        System.out.println("Medicamento salvo com sucesso!");
    }

    private void vincular(Scanner sc) {
        Integer idUsuario = lerInteiro(sc, "ID do Usuário: ");
        Integer idMedicamento = lerInteiro(sc, "ID do Medicamento: ");
        String dosagem = lerTexto(sc, "Dosagem (Ex: 1 cápsula): ");

        usuarioMedicamentoService.vincular(idUsuario, idMedicamento, dosagem);
        System.out.println("Vínculo realizado com sucesso!");
    }


    private void gerenciarFrequencia(Scanner sc) {
        System.out.println("\n--- CONFIGURAR FREQUÊNCIA ---");
        Integer idUsuario = lerInteiro(sc, "ID do Usuário: ");
        Integer idMedicamento = lerInteiro(sc, "ID do Medicamento: ");
        LocalTime horario = lerLocalTime(sc, "Horário de início (HH:mm): ");
        Integer intervalo = lerInteiro(sc, "Intervalo em horas (Ex: 8): ");
        Integer vezes = lerInteiro(sc, "Quantas vezes por dia: ");

        frequenciaService.salvar(idUsuario, idMedicamento, horario, intervalo, vezes);
        System.out.println("Frequência de tratamento salva!");
    }

    private void gerenciarAlertas(Scanner sc) {
        System.out.println("\n--- AGENDAR/LISTAR ALERTAS ---");
        System.out.println("1 - Agendar novo alerta");
        System.out.println("2 - Ver todos os alertas");
        int sub = lerInteiro(sc, "Opção: ");

        if (sub == 1) {
            Integer idFreq = lerInteiro(sc, "ID da Frequência: ");
            LocalDateTime data = lerLocalDateTime(sc, "Data/Hora (yyyy-MM-ddTHH:mm): ");
            String status = lerTexto(sc, "Status (EMITIDO/NAO_EMITIDO): ").toUpperCase();
            alertaService.salvar(idFreq, data, status);
            System.out.println("Alerta agendado!");
        } else {
            alertaService.listarTodos().forEach(System.out::println);
        }
    }

    private void registrarConsumo(Scanner sc) {
        System.out.println("\n--- REGISTRAR CONSUMO NO HISTÓRICO ---");
        Integer idAlerta = lerInteiro(sc, "ID do Alerta correspondente: ");
        LocalDateTime dataConsumo = LocalDateTime.now(); // Pega a hora atual do registro
        String confirmacao = lerTexto(sc, "Confirmar consumo (SIM/NAO): ").toUpperCase();

        consumoService.salvar(idAlerta, dataConsumo, confirmacao);
        System.out.println("Consumo registrado no histórico!");
    }


    private LocalTime lerLocalTime(Scanner sc, String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return LocalTime.parse(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Horário inválido. Use HH:mm.");
            }
        }
    }

    private LocalDateTime lerLocalDateTime(Scanner sc, String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return LocalDateTime.parse(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Formato inválido. Use yyyy-MM-ddTHH:mm (Ex: 2026-04-16T22:00).");
            }
        }
    }

    private Quantidade lerQuantidade(Scanner sc) {
        while (true) {
            System.out.print("Quantidade [UNIDADE/ML]: ");
            try {
                return Quantidade.valueOf(sc.nextLine().trim().toUpperCase());
            } catch (Exception e) {
                System.out.println("Valor inválido. Digite UNIDADE ou ML.");
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