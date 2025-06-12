import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class BibliotecaApp {
    // Dados compartilhados
    public static ArrayList<String> livros = new ArrayList<>();
    public static ArrayList<String> historicoEmprestimos = new ArrayList<>();
    public static ArrayList<String> usuarios = new ArrayList<>();
    public static ArrayList<String> categorias = new ArrayList<>();
    public static HashMap<String, String> perfilUsuario = new HashMap<>();

    public static void main(String[] args) {
        // Inicializar com dados básicos
        usuarios.add("admin");
        perfilUsuario.put("Nome", "Administrador");
        perfilUsuario.put("Email", "admin@biblioteca.com");

        categorias.add("Ficção");
        categorias.add("Não-Ficção");
        categorias.add("Tecnologia");

        livros.add("Java Básico");
        livros.add("Estruturas de Dados");
        livros.add("Design Patterns");

        new LoginTela();
    }
}

//parte do código Carlos Eduardo continua
class LoginTela extends JFrame {
    public LoginTela() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Usuário:");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        loginButton.addActionListener(e -> {
            // Simulação simples de login: se usuário existe na lista
            if(BibliotecaApp.usuarios.contains(userText.getText())) {
                dispose();
                new DashboardTela();
            } else {
                JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos.");
            }
        });
    }
}

// Tela de Cadastro de Usuário simples, agora funcional
class CadastroUsuarioTela extends JFrame {
    public CadastroUsuarioTela() {
        setTitle("Cadastro de Usuário");
        setSize(350,250);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        add(new JLabel("Cadastrar novo usuário"));

        JTextField campoUsuario = new JTextField(25);
        JButton btnCadastrar = new JButton("Cadastrar");
        add(campoUsuario);
        add(btnCadastrar);

        btnCadastrar.addActionListener(e -> {
            String novoUser = campoUsuario.getText().trim();
            if(!novoUser.isEmpty()) {
                if (!BibliotecaApp.usuarios.contains(novoUser)) {
                    BibliotecaApp.usuarios.add(novoUser);
                    JOptionPane.showMessageDialog(this, "Usuário cadastrado!");
                } else {
                    JOptionPane.showMessageDialog(this, "Usuário já existe.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Digite um nome de usuário válido.");
            }
        });

        setVisible(true);
    }
}

// Dashboard com botões para todas as telas
class DashboardTela extends JFrame {
    public DashboardTela() {
        setTitle("Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        JButton cadastroLivroBtn = new JButton("Cadastro Livro");
        JButton listarLivroBtn = new JButton("Listar Livros");
        JButton emprestimoBtn = new JButton("Emprestar Livro");
        JButton devolucaoBtn = new JButton("Devolver Livro");
        JButton historicoBtn = new JButton("Histórico");
        JButton usuariosBtn = new JButton("Usuários");
        JButton categoriasBtn = new JButton("Categorias");
        JButton estatisticasBtn = new JButton("Estatísticas");
        JButton perfilBtn = new JButton("Perfil Usuário");
        JButton logoutBtn = new JButton("Logout");

        cadastroLivroBtn.addActionListener(e -> new CadastroLivroTela());
        listarLivroBtn.addActionListener(e -> new ListagemLivrosTela());
        emprestimoBtn.addActionListener(e -> new CadastroEmprestimoTela());
        devolucaoBtn.addActionListener(e -> new DevolucaoLivroTela());
        historicoBtn.addActionListener(e -> new HistoricoEmprestimosTela());
        usuariosBtn.addActionListener(e -> new GerenciamentoUsuariosTela());
        categoriasBtn.addActionListener(e -> new CadastroCategoriasTela());
        estatisticasBtn.addActionListener(e -> new EstatisticasTela());
        perfilBtn.addActionListener(e -> new PerfilUsuarioTela());
        logoutBtn.addActionListener(e -> {
            int resp = JOptionPane.showConfirmDialog(this, "Deseja sair do sistema?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if(resp == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        panel.add(cadastroLivroBtn);
        panel.add(listarLivroBtn);
        panel.add(emprestimoBtn);
        panel.add(devolucaoBtn);
        panel.add(historicoBtn);
        panel.add(usuariosBtn);
        panel.add(categoriasBtn);
        panel.add(estatisticasBtn);
        panel.add(perfilBtn);
        panel.add(logoutBtn);

        add(panel);
        setVisible(true);
    }
}

// Tela de Cadastro de Livro interativa, permite adicionar título e categoria
class CadastroLivroTela extends JFrame {
    public CadastroLivroTela() {
        setTitle("Cadastro de Livro");
        setSize(350, 220);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        add(new JLabel("Título do Livro:"));
        JTextField campoTitulo = new JTextField(25);
        add(campoTitulo);

        add(new JLabel("Categoria:"));
        JComboBox<String> comboCategorias = new JComboBox<>();
        for (String cat : BibliotecaApp.categorias) {
            comboCategorias.addItem(cat);
        }
        add(comboCategorias);

        JButton btnSalvar = new JButton("Salvar Livro");
        add(btnSalvar);

        btnSalvar.addActionListener(e -> {
            String titulo = campoTitulo.getText().trim();
            String categoria = (String) comboCategorias.getSelectedItem();
            if (!titulo.isEmpty()) {
                String livroComCategoria = titulo + " [" + categoria + "]";
                BibliotecaApp.livros.add(livroComCategoria);
                JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso!");
                campoTitulo.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "O título não pode ser vazio.");
            }
        });

        setVisible(true);
    }
}

//parte do código Giovanna
class ListagemLivrosTela extends JFrame {
    public ListagemLivrosTela() {
        setTitle("Listagem de Livros");
        setSize(350,300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5,5));

        DefaultListModel<String> model = new DefaultListModel<>();
        BibliotecaApp.livros.forEach(model::addElement);

        JList<String> listaLivros = new JList<>(model);
        listaLivros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaLivros);

        JPanel botoesPanel = new JPanel();
        botoesPanel.setLayout(new FlowLayout());

        JButton btnEditar = new JButton("Editar");
        JButton btnDetalhes = new JButton("Detalhes");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnFechar = new JButton("Fechar");

        botoesPanel.add(btnEditar);
        botoesPanel.add(btnDetalhes);
        botoesPanel.add(btnExcluir);
        botoesPanel.add(btnFechar);

        btnEditar.addActionListener(e -> {
            int idx = listaLivros.getSelectedIndex();
            if(idx >= 0) {
                new EdicaoLivroTela(idx);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um livro para editar.");
            }
        });

        btnDetalhes.addActionListener(e -> {
            int idx = listaLivros.getSelectedIndex();
            if(idx >= 0) {
                new DetalhesLivroTela(BibliotecaApp.livros.get(idx));
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um livro para detalhes.");
            }
        });

        btnExcluir.addActionListener(e -> {
            int idx = listaLivros.getSelectedIndex();
            if(idx >= 0) {
                int resp = JOptionPane.showConfirmDialog(this, "Confirma exclusão do livro?", "Excluir", JOptionPane.YES_NO_OPTION);
                if(resp == JOptionPane.YES_OPTION) {
                    BibliotecaApp.livros.remove(idx);
                    model.remove(idx);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um livro para excluir.");
            }
        });

        btnFechar.addActionListener(e -> dispose());

        add(scrollPane, BorderLayout.CENTER);
        add(botoesPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
}

// Tela para editar título do livro
class EdicaoLivroTela extends JFrame {
    private int indiceLivro;
    public EdicaoLivroTela(int idx) {
        this.indiceLivro = idx;

        setTitle("Edição de Livro");
        setSize(350, 180);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        String tituloAtual = BibliotecaApp.livros.get(idx);

        add(new JLabel("Título do Livro:"));
        JTextField campoTitulo = new JTextField(tituloAtual, 25);
        add(campoTitulo);

        JButton btnSalvar = new JButton("Salvar");
        add(btnSalvar);

        btnSalvar.addActionListener(e -> {
            String novoTitulo = campoTitulo.getText().trim();
            if (!novoTitulo.isEmpty()) {
                BibliotecaApp.livros.set(indiceLivro, novoTitulo);
                JOptionPane.showMessageDialog(this, "Livro atualizado!");
                dispose();
                new ListagemLivrosTela();
            } else {
                JOptionPane.showMessageDialog(this, "Título não pode ser vazio.");
            }
        });

        setVisible(true);
    }
}

// Tela de detalhes do livro simples
class DetalhesLivroTela extends JFrame {
    public DetalhesLivroTela(String tituloLivro) {
        setTitle("Detalhes do Livro");
        setSize(350, 180);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTextArea areaDetalhes = new JTextArea();
        areaDetalhes.setEditable(false);
        areaDetalhes.setText("Título:\n" + tituloLivro + "\n\nCategoria retirada do título entre colchetes.\nExemplo: \"Livro ABC [Categoria]\"");

        add(new JScrollPane(areaDetalhes), BorderLayout.CENTER);

        JButton fechar = new JButton("Fechar");
        fechar.addActionListener(e -> dispose());

        JPanel panel = new JPanel();
        panel.add(fechar);
        add(panel, BorderLayout.SOUTH);

        setVisible(true);
    }
}

// Tela cadastro de empréstimo interativa
class CadastroEmprestimoTela extends JFrame {
    public CadastroEmprestimoTela() {
        setTitle("Cadastro de Empréstimo");
        setSize(350, 220);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        add(new JLabel("Selecione o Livro para Empréstimo:"));

        JComboBox<String> comboLivros = new JComboBox<>();
        for (String livro : BibliotecaApp.livros) {
            comboLivros.addItem(livro);
        }
        add(comboLivros);

        JButton btnEmprestar = new JButton("Emprestar Livro");
        add(btnEmprestar);

        btnEmprestar.addActionListener(e -> {
            String livro = (String) comboLivros.getSelectedItem();
            if (livro != null) {
                BibliotecaApp.historicoEmprestimos.add("Emprestado: " + livro);
                JOptionPane.showMessageDialog(this, "Livro emprestado: " + livro);
            }
        });

        setVisible(true);
    }
}

//parte do código Bianca
class DevolucaoLivroTela extends JFrame {
    public DevolucaoLivroTela() {
        setTitle("Devolução de Livro");
        setSize(350, 220);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        add(new JLabel("Selecione o Livro para Devolução:"));

        JComboBox<String> comboLivros = new JComboBox<>();
        for (String livro : BibliotecaApp.livros) {
            comboLivros.addItem(livro);
        }
        add(comboLivros);

        JButton btnDevolver = new JButton("Devolver Livro");
        add(btnDevolver);

        btnDevolver.addActionListener(e -> {
            String livro = (String) comboLivros.getSelectedItem();
            if (livro != null) {
                BibliotecaApp.historicoEmprestimos.add("Devolvido: " + livro);
                JOptionPane.showMessageDialog(this, "Livro devolvido: " + livro);
            }
        });

        setVisible(true);
    }
}

class RenovacaoEmprestimoTela extends JFrame {
    public RenovacaoEmprestimoTela() {
        setTitle("Renovação de Empréstimo");
        setSize(350, 180);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        add(new JLabel("Selecione o Livro para Renovação:"));

        JComboBox<String> comboLivros = new JComboBox<>();
        for (String livro : BibliotecaApp.livros) {
            comboLivros.addItem(livro);
        }
        add(comboLivros);

        JButton btnRenovar = new JButton("Renovar Empréstimo");
        add(btnRenovar);

        btnRenovar.addActionListener(e -> {
            String livro = (String) comboLivros.getSelectedItem();
            if (livro != null) {
                BibliotecaApp.historicoEmprestimos.add("Renovado: " + livro);
                JOptionPane.showMessageDialog(this, "Empréstimo renovado para: " + livro);
            }
        });

        setVisible(true);
    }
}

class ReservasTela extends JFrame {
    public ReservasTela() {
        setTitle("Reservas");
        setSize(350, 180);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        add(new JLabel("Funcionalidade de Reservas não implementada ainda, aguarde futuras atualizações."));

        setVisible(true);
    }
}

class HistoricoEmprestimosTela extends JFrame {
    public HistoricoEmprestimosTela() {
        setTitle("Histórico de Empréstimos");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTextArea area = new JTextArea();
        area.setEditable(false);
        BibliotecaApp.historicoEmprestimos.forEach(registro -> area.append(registro + "\n"));
        add(new JScrollPane(area), BorderLayout.CENTER);

        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());

        JPanel panel = new JPanel();
        panel.add(btnFechar);
        add(panel, BorderLayout.SOUTH);

        setVisible(true);
    }
}

//parte do código Maria Luiza
class GerenciamentoUsuariosTela extends JFrame {
    public GerenciamentoUsuariosTela() {
        setTitle("Gerenciamento de Usuários");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5,5));

        DefaultListModel<String> model = new DefaultListModel<>();
        BibliotecaApp.usuarios.forEach(model::addElement);

        JList<String> listaUsuarios = new JList<>(model);
        listaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaUsuarios);

        JPanel botoesPanel = new JPanel(new FlowLayout());

        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnRemover = new JButton("Remover");
        JButton btnFechar = new JButton("Fechar");

        btnAdicionar.addActionListener(e -> {
            String novoUsuario = JOptionPane.showInputDialog(this, "Nome do novo usuário:");
            if(novoUsuario != null && !novoUsuario.trim().isEmpty()) {
                if (!BibliotecaApp.usuarios.contains(novoUsuario.trim())) {
                    BibliotecaApp.usuarios.add(novoUsuario.trim());
                    model.addElement(novoUsuario.trim());
                    JOptionPane.showMessageDialog(this, "Usuário adicionado!");
                } else {
                    JOptionPane.showMessageDialog(this, "Usuário já existe.");
                }
            }
        });

        btnRemover.addActionListener(e -> {
            int idx = listaUsuarios.getSelectedIndex();
            if(idx >= 0) {
                int resp = JOptionPane.showConfirmDialog(this, "Remover usuário selecionado?", "Confirmar remoção", JOptionPane.YES_NO_OPTION);
                if(resp == JOptionPane.YES_OPTION) {
                    BibliotecaApp.usuarios.remove(idx);
                    model.remove(idx);
                    JOptionPane.showMessageDialog(this, "Usuário removido.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um usuário para remover.");
            }
        });

        btnFechar.addActionListener(e -> dispose());

        botoesPanel.add(btnAdicionar);
        botoesPanel.add(btnRemover);
        botoesPanel.add(btnFechar);

        add(scrollPane, BorderLayout.CENTER);
        add(botoesPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}

class CadastroCategoriasTela extends JFrame {
    public CadastroCategoriasTela() {
        setTitle("Cadastro de Categorias");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        add(new JLabel("Nova Categoria:"));
        JTextField campoCategoria = new JTextField(25);
        add(campoCategoria);

        JButton btnAdicionar = new JButton("Adicionar Categoria");
        add(btnAdicionar);

        JTextArea listaCategorias = new JTextArea(8, 25);
        listaCategorias.setEditable(false);
        updateLista(listaCategorias);
        add(new JScrollPane(listaCategorias));

        btnAdicionar.addActionListener(e -> {
            String novaCat = campoCategoria.getText().trim();
            if(!novaCat.isEmpty()) {
                if(!BibliotecaApp.categorias.contains(novaCat)) {
                    BibliotecaApp.categorias.add(novaCat);
                    updateLista(listaCategorias);
                    campoCategoria.setText("");
                    JOptionPane.showMessageDialog(this, "Categoria adicionada.");
                } else {
                    JOptionPane.showMessageDialog(this, "Categoria já existe.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Digite uma categoria válida.");
            }
        });

        setVisible(true);
    }

    private void updateLista(JTextArea area) {
        area.setText("");
        BibliotecaApp.categorias.forEach(cat -> area.append(cat + "\n"));
    }
}

class RelatoriosEmprestimosTela extends JFrame {
    public RelatoriosEmprestimosTela() {
        setTitle("Relatórios de Empréstimos");
        setSize(350, 220);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        add(new JLabel("Funcionalidade não implementada ainda."));
        setVisible(true);
    }
}

class EstatisticasTela extends JFrame {
    public EstatisticasTela() {
        setTitle("Estatísticas da Biblioteca");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10,10));

        JTextArea area = new JTextArea();
        area.setEditable(false);

        StringBuilder stats = new StringBuilder();
        stats.append("Total de Livros: ").append(BibliotecaApp.livros.size()).append("\n");
        stats.append("Total de Usuários: ").append(BibliotecaApp.usuarios.size()).append("\n");
        stats.append("Total de Categorias: ").append(BibliotecaApp.categorias.size()).append("\n");
        stats.append("Total de Empréstimos/Devoluções: ").append(BibliotecaApp.historicoEmprestimos.size()).append("\n");

        area.setText(stats.toString());
        add(new JScrollPane(area), BorderLayout.CENTER);

        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());

        JPanel panel = new JPanel();
        panel.add(btnFechar);
        add(panel, BorderLayout.SOUTH);

        setVisible(true);
    }
}

//parte do código João
class PerfilUsuarioTela extends JFrame {
    public PerfilUsuarioTela() {
        setTitle("Perfil do Usuário");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10,10));

        JTextArea area = new JTextArea();
        area.setEditable(false);

        StringBuilder perfil = new StringBuilder();
        BibliotecaApp.perfilUsuario.forEach((k,v) -> perfil.append(k).append(": ").append(v).append("\n"));

        area.setText(perfil.toString());
        add(new JScrollPane(area), BorderLayout.CENTER);

        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());

        JPanel panel = new JPanel();
        panel.add(btnFechar);

        add(panel, BorderLayout.SOUTH);
        setVisible(true);
    }
}

class ConfiguracoesSistemaTela extends JFrame {
    public ConfiguracoesSistemaTela() {
        setTitle("Configurações do Sistema");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JLabel lblTema = new JLabel("Tema (exemplo):");
        JComboBox<String> comboTema = new JComboBox<>(new String[]{"Claro", "Escuro"});
        JButton btnSalvar = new JButton("Salvar");

        add(lblTema);
        add(comboTema);
        add(btnSalvar);

        btnSalvar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Configurações salvas (simulação).");
        });

        setVisible(true);
    }
}

class AjudaSuporteTela extends JFrame {
    public AjudaSuporteTela() {
        setTitle("Ajuda/Suporte");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTextArea areaAjuda = new JTextArea();
        areaAjuda.setEditable(false);
        areaAjuda.setText(
            "Bem-vindo ao sistema de Biblioteca.\n\n" +
            "Use o menu para acessar as funcionalidades:\n" +
            "- Cadastro e listagem de livros\n" +
            "- Empréstimos e devoluções\n" +
            "- Gerenciamento de usuários e categorias\n" +
            "- Estatísticas e histórico\n\n" +
            "Para suporte, contate: suporte@biblioteca.com"
        );

        add(new JScrollPane(areaAjuda), BorderLayout.CENTER);

        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());

        JPanel panel = new JPanel();
        panel.add(btnFechar);
        add(panel, BorderLayout.SOUTH);

        setVisible(true);
    }
}

class LogoutTela extends JFrame {
    public LogoutTela() {
        setTitle("Logout");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        add(new JLabel("Você tem certeza que deseja sair?"));

        JButton btnSim = new JButton("Sim");
        JButton btnNao = new JButton("Não");
        add(btnSim);
        add(btnNao);

        btnSim.addActionListener(e -> System.exit(0));
        btnNao.addActionListener(e -> dispose());

        setVisible(true);
    }
}

