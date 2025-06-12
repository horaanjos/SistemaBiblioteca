import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Sistema Bibliotecário - Aplicação Java Swing
 * Com telas modernas, organizadas, responsivas e amigáveis.
 * Funcionalidades: login, dashboard, cadastro e listagem de livros e usuários,
 * empréstimos, devoluções, histórico, estatísticas e configurações básicas.
 */
public class BibliotecaApp {

    // Dados compartilhados — modelos simples em memória
    public static ArrayList<String> livros = new ArrayList<>();
    public static ArrayList<String> historicoEmprestimos = new ArrayList<>();
    public static ArrayList<String> usuarios = new ArrayList<>();
    public static ArrayList<String> categorias = new ArrayList<>();
    public static HashMap<String, String> perfilUsuario = new HashMap<>();

    public static void main(String[] args) {
        // Configura fonte e cores globais para um tema moderno e limpo
        configureGlobalTheme();

        // Inicializar dados básicos
        usuarios.add("admin");
        perfilUsuario.put("Nome", "Administrador");
        perfilUsuario.put("Email", "admin@biblioteca.com");

        categorias.add("Ficção");
        categorias.add("Não-Ficção");
        categorias.add("Tecnologia");

        livros.add("Java Básico [Tecnologia]");
        livros.add("Estruturas de Dados [Tecnologia]");
        livros.add("Design Patterns [Tecnologia]");

        // Executar tela de login (thread de evento Swing)
        EventQueue.invokeLater(LoginTela::new);
    }

    private static void configureGlobalTheme() {
        UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 14));
        UIManager.put("TextField.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("TextArea.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("ComboBox.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("List.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("PasswordField.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("OptionPane.messageFont", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("OptionPane.buttonFont", new Font("Segoe UI", Font.BOLD, 14));
        UIManager.put("Panel.background", new Color(245, 247, 250));
        UIManager.put("Button.background", new Color(44, 102, 252));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.borderPainted", false);
        UIManager.put("Button.focusPainted", false);
        UIManager.put("Button.contentAreaFilled", true);
        UIManager.put("TextField.background", Color.WHITE);
        UIManager.put("TextField.foreground", Color.BLACK);
        UIManager.put("TextArea.background", Color.WHITE);
        UIManager.put("TextArea.foreground", Color.BLACK);
        UIManager.put("ComboBox.background", Color.WHITE);
        UIManager.put("ComboBox.foreground", Color.BLACK);
        UIManager.put("ScrollPane.background", new Color(245, 247, 250));
    }

    // Método auxiliar para botões estilizados com efeito hover liso e consistente
    public static JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setBackground(new Color(44, 102, 252));
        btn.setForeground(Color.WHITE);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(31, 70, 219));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(new Color(44, 102, 252));
            }
        });
        return btn;
    }

    // Classe base para janelas com padding, fonte e cor de fundo consistentes
    public static class BaseWindow extends JFrame {
        public BaseWindow(String title) {
            super(title);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(500, 400);
            setMinimumSize(new Dimension(450, 380));
            setLocationRelativeTo(null);
            getContentPane().setBackground(UIManager.getColor("Panel.background"));
            ((JComponent) getContentPane()).setBorder(new EmptyBorder(20, 20, 20, 20));
        }
    }

    // ===================== Tela Login =====================
    public static class LoginTela extends JFrame {
        public LoginTela() {
            setTitle("Login - Sistema Bibliotecário");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(380, 280);
            setLocationRelativeTo(null);
            getContentPane().setBackground(UIManager.getColor("Panel.background"));

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBackground(UIManager.getColor("Panel.background"));
            panel.setBorder(new EmptyBorder(20, 40, 20, 40));

            Dimension verticalSpacer = new Dimension(0, 10);

            JLabel lblTitulo = new JLabel("Sistema Bibliotecário");
            lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
            lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
            lblTitulo.setForeground(new Color(44, 102, 252));

            JLabel lblSubtitulo = new JLabel("Bem bonito e organizado");
            lblSubtitulo.setFont(new Font("Segoe UI", Font.ITALIC, 14));
            lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
            lblSubtitulo.setForeground(new Color(80, 80, 80));

            JLabel lblUser = new JLabel("Usuário:");
            lblUser.setAlignmentX(Component.LEFT_ALIGNMENT);
            JTextField txtUser = new JTextField();
            txtUser.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
            txtUser.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel lblPass = new JLabel("Senha:");
            lblPass.setAlignmentX(Component.LEFT_ALIGNMENT);
            JPasswordField txtPass = new JPasswordField();
            txtPass.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
            txtPass.setAlignmentX(Component.LEFT_ALIGNMENT);

            JButton btnLogin = createStyledButton("Login");
            btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

            panel.add(lblTitulo);
            panel.add(lblSubtitulo);
            panel.add(Box.createRigidArea(new Dimension(0, 20)));
            panel.add(lblUser);
            panel.add(Box.createRigidArea(verticalSpacer));
            panel.add(txtUser);
            panel.add(Box.createRigidArea(verticalSpacer));
            panel.add(lblPass);
            panel.add(Box.createRigidArea(verticalSpacer));
            panel.add(txtPass);
            panel.add(Box.createRigidArea(new Dimension(0, 20)));
            panel.add(btnLogin);

            add(panel);

            btnLogin.addActionListener(e -> {
                String user = txtUser.getText().trim();
                // Senha não validada no momento, somente usuário
                if (usuarios.contains(user)) {
                    dispose();
                    new DashboardTela();
                } else {
                    JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            });

            pack();
            setResizable(false);
            setVisible(true);
        }
    }

    // ===================== Tela Dashboard (Home) =====================
    public static class DashboardTela extends BaseWindow {
        public DashboardTela() {
            super("Dashboard - Sistema Bibliotecário");
            setLayout(new GridLayout(5, 2, 20, 15));

            add(createDashboardButton("Cadastro Livro", e -> new CadastroLivroTela()));
            add(createDashboardButton("Listar Livros", e -> new ListagemLivrosTela()));
            add(createDashboardButton("Emprestar Livro", e -> new CadastroEmprestimoTela()));
            add(createDashboardButton("Devolver Livro", e -> new DevolucaoLivroTela()));
            add(createDashboardButton("Histórico", e -> new HistoricoEmprestimosTela()));
            add(createDashboardButton("Usuários", e -> new GerenciamentoUsuariosTela()));
            add(createDashboardButton("Categorias", e -> new CadastroCategoriasTela()));
            add(createDashboardButton("Estatísticas", e -> new EstatisticasTela()));
            add(createDashboardButton("Perfil Usuário", e -> new PerfilUsuarioTela()));
            add(createDashboardButton("Logout", e -> {
                int resp = JOptionPane.showConfirmDialog(this, "Deseja sair do sistema?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (resp == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }));

            setVisible(true);
        }

        private JButton createDashboardButton(String text, ActionListener action) {
            JButton btn = createStyledButton(text);
            btn.addActionListener(action);
            return btn;
        }
    }

    // ===================== Cadastro Livro =====================
    public static class CadastroLivroTela extends BaseWindow {
        public CadastroLivroTela() {
            super("Cadastro de Livro");
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(8, 15, 8, 15);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel lblTitulo = new JLabel("Título do Livro:");
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(lblTitulo, gbc);

            JTextField txtTitulo = new JTextField(20);
            gbc.gridx = 1;
            add(txtTitulo, gbc);

            JLabel lblCategoria = new JLabel("Categoria:");
            gbc.gridx = 0;
            gbc.gridy = 1;
            add(lblCategoria, gbc);

            JComboBox<String> comboCategoria = new JComboBox<>();
            categorias.forEach(comboCategoria::addItem);
            gbc.gridx = 1;
            add(comboCategoria, gbc);

            JButton btnSalvar = createStyledButton("Salvar Livro");
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            add(btnSalvar, gbc);

            btnSalvar.addActionListener(e -> {
                String titulo = txtTitulo.getText().trim();
                String categoria = (String) comboCategoria.getSelectedItem();
                if (!titulo.isEmpty()) {
                    String livroCompleto = titulo + " [" + categoria + "]";
                    livros.add(livroCompleto);
                    JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso!");
                    txtTitulo.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Preencha o título do livro.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            });

            setVisible(true);
        }
    }

    // ===================== Listagem Livros =====================
    public static class ListagemLivrosTela extends BaseWindow {
        public ListagemLivrosTela() {
            super("Listagem de Livros");
            setLayout(new BorderLayout(10, 10));

            DefaultListModel<String> model = new DefaultListModel<>();
            livros.forEach(model::addElement);

            JList<String> listLivros = new JList<>(model);
            listLivros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane scrollPane = new JScrollPane(listLivros);

            JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
            JButton btnEditar = createStyledButton("Editar");
            JButton btnDetalhes = createStyledButton("Detalhes");
            JButton btnExcluir = createStyledButton("Excluir");
            JButton btnFechar = createStyledButton("Fechar");

            panelBotoes.add(btnEditar);
            panelBotoes.add(btnDetalhes);
            panelBotoes.add(btnExcluir);
            panelBotoes.add(btnFechar);

            add(scrollPane, BorderLayout.CENTER);
            add(panelBotoes, BorderLayout.SOUTH);

            btnEditar.addActionListener(e -> {
                int idx = listLivros.getSelectedIndex();
                if (idx >= 0) {
                    dispose();
                    new EdicaoLivroTela(idx);
                } else {
                    JOptionPane.showMessageDialog(this, "Selecione um livro para editar.");
                }
            });

            btnDetalhes.addActionListener(e -> {
                int idx = listLivros.getSelectedIndex();
                if (idx >= 0) {
                    new DetalhesLivroTela(livros.get(idx));
                } else {
                    JOptionPane.showMessageDialog(this, "Selecione um livro para ver detalhes.");
                }
            });

            btnExcluir.addActionListener(e -> {
                int idx = listLivros.getSelectedIndex();
                if (idx >= 0) {
                    int resp = JOptionPane.showConfirmDialog(this, "Confirma exclusão do livro?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (resp == JOptionPane.YES_OPTION) {
                        livros.remove(idx);
                        model.remove(idx);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Selecione um livro para excluir.");
                }
            });

            btnFechar.addActionListener(e -> dispose());

            setVisible(true);
        }
    }

    // ===================== Edicao Livro =====================
    public static class EdicaoLivroTela extends BaseWindow {
        public EdicaoLivroTela(int idx) {
            super("Edição de Livro");
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(8, 15, 8, 15);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel lblTitulo = new JLabel("Título do Livro:");
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(lblTitulo, gbc);

            JTextField txtTitulo = new JTextField(livros.get(idx), 25);
            gbc.gridx = 1;
            add(txtTitulo, gbc);

            JButton btnSalvar = createStyledButton("Salvar");
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 2;
            add(btnSalvar, gbc);

            btnSalvar.addActionListener(e -> {
                String novoTitulo = txtTitulo.getText().trim();
                if (!novoTitulo.isEmpty()) {
                    livros.set(idx, novoTitulo);
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

    // ===================== Detalhes Livro =====================
    public static class DetalhesLivroTela extends BaseWindow {
        public DetalhesLivroTela(String livro) {
            super("Detalhes do Livro");
            setLayout(new BorderLayout(10, 10));

            JTextArea txtArea = new JTextArea();
            txtArea.setEditable(false);
            txtArea.setText("Título:\n" + livro + "\n\nCategoria extraída do título entre colchetes.\nExemplo: \"Livro ABC [Categoria]\"");
            txtArea.setLineWrap(true);
            txtArea.setWrapStyleWord(true);
            txtArea.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            add(new JScrollPane(txtArea), BorderLayout.CENTER);

            JPanel panel = new JPanel();
            JButton btnFechar = createStyledButton("Fechar");
            panel.add(btnFechar);
            add(panel, BorderLayout.SOUTH);

            btnFechar.addActionListener(e -> dispose());

            setVisible(true);
        }
    }

    // ===================== Cadastro Empréstimo =====================
    public static class CadastroEmprestimoTela extends BaseWindow {
        public CadastroEmprestimoTela() {
            super("Cadastro de Empréstimo");
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(8, 15, 8, 15);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel lblLivro = new JLabel("Selecione o Livro:");
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(lblLivro, gbc);

            JComboBox<String> comboLivros = new JComboBox<>();
            livros.forEach(comboLivros::addItem);
            gbc.gridx = 1;
            add(comboLivros, gbc);

            JButton btnEmprestar = createStyledButton("Emprestar Livro");
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 2;
            add(btnEmprestar, gbc);

            btnEmprestar.addActionListener(e -> {
                String livro = (String) comboLivros.getSelectedItem();
                if (livro != null) {
                    historicoEmprestimos.add("Emprestado: " + livro);
                    JOptionPane.showMessageDialog(this, "Livro emprestado: " + livro);
                }
            });

            setVisible(true);
        }
    }

    // ===================== Devolução Livro =====================
    public static class DevolucaoLivroTela extends BaseWindow {
        public DevolucaoLivroTela() {
            super("Devolução de Livro");
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(8, 15, 8, 15);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel lblLivro = new JLabel("Selecione o Livro:");
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(lblLivro, gbc);

            JComboBox<String> comboLivros = new JComboBox<>();
            livros.forEach(comboLivros::addItem);
            gbc.gridx = 1;
            add(comboLivros, gbc);

            JButton btnDevolver = createStyledButton("Devolver Livro");
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 2;
            add(btnDevolver, gbc);

            btnDevolver.addActionListener(e -> {
                String livro = (String) comboLivros.getSelectedItem();
                if (livro != null) {
                    historicoEmprestimos.add("Devolvido: " + livro);
                    JOptionPane.showMessageDialog(this, "Livro devolvido: " + livro);
                }
            });

            setVisible(true);
        }
    }

    // ===================== Histórico Empréstimos =====================
    public static class HistoricoEmprestimosTela extends BaseWindow {
        public HistoricoEmprestimosTela() {
            super("Histórico de Empréstimos");
            setLayout(new BorderLayout(10, 10));

            JTextArea txtHistorico = new JTextArea();
            txtHistorico.setEditable(false);
            txtHistorico.setLineWrap(true);
            txtHistorico.setWrapStyleWord(true);
            txtHistorico.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            historicoEmprestimos.forEach(s -> txtHistorico.append(s + "\n"));
            add(new JScrollPane(txtHistorico), BorderLayout.CENTER);

            JButton btnFechar = createStyledButton("Fechar");
            JPanel panelBotoes = new JPanel();
            panelBotoes.add(btnFechar);
            add(panelBotoes, BorderLayout.SOUTH);

            btnFechar.addActionListener(e -> dispose());

            setVisible(true);
        }
    }

    // ===================== Gerenciamento Usuários =====================
    public static class GerenciamentoUsuariosTela extends BaseWindow {
        public GerenciamentoUsuariosTela() {
            super("Gerenciamento de Usuários");
            setLayout(new BorderLayout(10, 10));

            DefaultListModel<String> model = new DefaultListModel<>();
            usuarios.forEach(model::addElement);
            JList<String> listUsuarios = new JList<>(model);
            listUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane scrollPane = new JScrollPane(listUsuarios);

            JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
            JButton btnAdicionar = createStyledButton("Adicionar");
            JButton btnRemover = createStyledButton("Remover");
            JButton btnFechar = createStyledButton("Fechar");

            panelBotoes.add(btnAdicionar);
            panelBotoes.add(btnRemover);
            panelBotoes.add(btnFechar);

            add(scrollPane, BorderLayout.CENTER);
            add(panelBotoes, BorderLayout.SOUTH);

            btnAdicionar.addActionListener(e -> {
                String nome = JOptionPane.showInputDialog(this, "Digite nome do novo usuário:");
                if (nome != null && !nome.trim().isEmpty()) {
                    if (!usuarios.contains(nome.trim())) {
                        usuarios.add(nome.trim());
                        model.addElement(nome.trim());
                        JOptionPane.showMessageDialog(this, "Usuário adicionado!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Usuário já existe.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            btnRemover.addActionListener(e -> {
                int index = listUsuarios.getSelectedIndex();
                if (index >= 0) {
                    int resp = JOptionPane.showConfirmDialog(this, "Remover usuário selecionado?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if (resp == JOptionPane.YES_OPTION) {
                        usuarios.remove(index);
                        model.remove(index);
                        JOptionPane.showMessageDialog(this, "Usuário removido!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Selecione um usuário para remover.");
                }
            });

            btnFechar.addActionListener(e -> dispose());

            setVisible(true);
        }
    }

    // ===================== Cadastro Categorias =====================
    public static class CadastroCategoriasTela extends BaseWindow {
        public CadastroCategoriasTela() {
            super("Cadastro de Categorias");
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(8, 15, 8, 15);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel lblCategoria = new JLabel("Nova Categoria:");
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(lblCategoria, gbc);

            JTextField txtCategoria = new JTextField(20);
            gbc.gridx = 1;
            add(txtCategoria, gbc);

            JTextArea areaCategorias = new JTextArea(7, 25);
            areaCategorias.setEditable(false);
            areaCategorias.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            JScrollPane scrollPane = new JScrollPane(areaCategorias);
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            add(scrollPane, gbc);

            JButton btnAdicionar = createStyledButton("Adicionar Categoria");
            gbc.gridy = 1;
            gbc.gridwidth = 2;
            add(btnAdicionar, gbc);

            updateCategorias(areaCategorias);

            btnAdicionar.addActionListener(e -> {
                String cat = txtCategoria.getText().trim();
                if (!cat.isEmpty()) {
                    if (!categorias.contains(cat)) {
                        categorias.add(cat);
                        updateCategorias(areaCategorias);
                        txtCategoria.setText("");
                        JOptionPane.showMessageDialog(this, "Categoria adicionada!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Categoria já existe.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Digite uma categoria válida.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            });

            setVisible(true);
        }

        private void updateCategorias(JTextArea area) {
            area.setText("");
            categorias.forEach(c -> area.append(c + "\n"));
        }
    }

    // ===================== Estatísticas =====================
    public static class EstatisticasTela extends BaseWindow {
        public EstatisticasTela() {
            super("Estatísticas da Biblioteca");
            setLayout(new BorderLayout(10, 10));

            JTextArea areaStats = new JTextArea();
            areaStats.setEditable(false);
            areaStats.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            StringBuilder sb = new StringBuilder();
            sb.append("Total de Livros: ").append(livros.size()).append("\n");
            sb.append("Total de Usuários: ").append(usuarios.size()).append("\n");
            sb.append("Total de Categorias: ").append(categorias.size()).append("\n");
            sb.append("Total de Empréstimos e Devoluções: ").append(historicoEmprestimos.size()).append("\n");
            areaStats.setText(sb.toString());

            add(new JScrollPane(areaStats), BorderLayout.CENTER);

            JButton btnFechar = createStyledButton("Fechar");
            JPanel panelBotao = new JPanel();
            panelBotao.add(btnFechar);
            add(panelBotao, BorderLayout.SOUTH);

            btnFechar.addActionListener(e -> dispose());

            setVisible(true);
        }
    }

    // ===================== Perfil de Usuário =====================
    public static class PerfilUsuarioTela extends BaseWindow {
        public PerfilUsuarioTela() {
            super("Perfil do Usuário");
            setLayout(new BorderLayout(10, 10));

            JTextArea txtPerfil = new JTextArea();
            txtPerfil.setEditable(false);
            txtPerfil.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            StringBuilder sb = new StringBuilder();
            perfilUsuario.forEach((k, v) -> sb.append(k).append(": ").append(v).append("\n"));
            txtPerfil.setText(sb.toString());

            add(new JScrollPane(txtPerfil), BorderLayout.CENTER);

            JButton btnFechar = createStyledButton("Fechar");
            JPanel panelBtn = new JPanel();
            panelBtn.add(btnFechar);
            add(panelBtn, BorderLayout.SOUTH);

            btnFechar.addActionListener(e -> dispose());

            setVisible(true);
        }
    }

    // ===================== Configurações do Sistema =====================
    public static class ConfiguracoesSistemaTela extends BaseWindow {
        public ConfiguracoesSistemaTela() {
            super("Configurações do Sistema");
            setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));

            JLabel lblTema = new JLabel("Tema:");
            JComboBox<String> comboTema = new JComboBox<>(new String[]{"Claro", "Escuro"});
            JButton btnSalvar = createStyledButton("Salvar");

            add(lblTema);
            add(comboTema);
            add(btnSalvar);

            btnSalvar.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Configurações salvas (simulação).");
            });

            setVisible(true);
        }
    }

    // ===================== Ajuda e Suporte =====================
    public static class AjudaSuporteTela extends BaseWindow {
        public AjudaSuporteTela() {
            super("Ajuda / Suporte");
            setLayout(new BorderLayout(10, 10));

            JTextArea areaAjuda = new JTextArea();
            areaAjuda.setEditable(false);
            areaAjuda.setLineWrap(true);
            areaAjuda.setWrapStyleWord(true);
            areaAjuda.setFont(new Font("Segoe UI", Font.PLAIN, 14));
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

            JButton btnFechar = createStyledButton("Fechar");
            JPanel panelBtn = new JPanel();
            panelBtn.add(btnFechar);
            add(panelBtn, BorderLayout.SOUTH);

            btnFechar.addActionListener(e -> dispose());

            setVisible(true);
        }
    }

    // ===================== Logout (Confirmação) =====================
    public static class LogoutTela extends BaseWindow {
        public LogoutTela() {
            super("Logout");
            setLayout(new FlowLayout(FlowLayout.CENTER, 15, 20));

            JLabel lblConfirm = new JLabel("Confirma saída do sistema?");
            lblConfirm.setFont(new Font("Segoe UI", Font.BOLD, 16));
            add(lblConfirm);

            JButton btnSim = createStyledButton("Sim");
            JButton btnNao = createStyledButton("Não");

            add(btnSim);
            add(btnNao);

            btnSim.addActionListener(e -> System.exit(0));
            btnNao.addActionListener(e -> dispose());

            setVisible(true);
        }
    }
}
