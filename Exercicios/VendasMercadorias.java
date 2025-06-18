package Exercicios;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DecimalFormat;
import java.text.ParseException;

public class VendasMercadorias extends JFrame {

    // Componentes da interface
    private JTextField merchNameField;
    private JSpinner merchQuantitySpinner;
    private JSpinner merchPriceSpinner;

    private JTextField itemAName, itemBName, itemCName, itemDName;
    private JSpinner itemAQuantity, itemBQuantity, itemCQuantity, itemDQuantity;
    private JSpinner itemACost, itemBCost, itemCCost, itemDCost;

    private JButton calculateButton, resetMercadoriaButton, resetItensButton, resetAllButton;
    private JButton saveFormulaButton, loadFormulaButton;
    private JLabel resultLabel;

    public VendasMercadorias() {
        initComponents();
    }

    //  Metodo auxiliar para criar botões estilizados com bordas arredondadas e tom de cinza mais suave
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(180, 180, 180)); // tom de cinza mais suave
        button.setForeground(Color.WHITE);
        button.setBorder(new RoundedBorder(10));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    // Classe para borda arredondada
    private static class RoundedBorder implements javax.swing.border.Border {
        private int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius + 1, radius + 1, radius + 1, radius + 1);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

    private void initComponents() {
        setTitle("Sistema de Vendas - Cálculo de Lucro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));
        getContentPane().setBackground(new Color(240, 240, 240));

        // Painel principal
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Seção de Mercadoria
        JPanel merchPanel = new JPanel(new GridLayout(5, 2, 5, 5));  // 5 linhas para incluir os botões
        merchPanel.setBorder(BorderFactory.createTitledBorder("Dados da Mercadoria"));

        // Campos da mercadoria
        merchNameField = new JTextField(15);
        merchQuantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 9999, 1));
        merchPriceSpinner = createPriceSpinner();

        addField(merchPanel, "Nome da Mercadoria*:", merchNameField);
        addSpinner(merchPanel, "Quantidade*:", merchQuantitySpinner);
        addSpinner(merchPanel, "Preço Unitário de Venda (R$)*:", merchPriceSpinner);

        // Botão para resetar os dados da mercadoria
        resetMercadoriaButton = createStyledButton("Resetar Dados da Mercadoria");
        resetMercadoriaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                merchNameField.setText("");
                merchQuantitySpinner.setValue(1);
                merchPriceSpinner.setValue(0.0);
            }
        });
        merchPanel.add(resetMercadoriaButton);

        // Botões de salvar e carregar fórmula
        saveFormulaButton = createStyledButton("Salvar Fórmula");
        saveFormulaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFormula();
            }
        });

        loadFormulaButton = createStyledButton("Carregar Fórmula");
        loadFormulaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFormula();
            }
        });

        merchPanel.add(saveFormulaButton);
        merchPanel.add(loadFormulaButton);

        mainPanel.add(merchPanel, BorderLayout.NORTH);

        // Seção de Itens de Fabricação
        JPanel itemsPanel = new JPanel(new GridLayout(6, 1, 5, 5));  // 6 linhas para incluir o botão de reset geral dos itens
        itemsPanel.setBorder(BorderFactory.createTitledBorder("Itens de Fabricação (por unidade)"));

        // Itens
        itemsPanel.add(createItemPanel("A", true));
        itemsPanel.add(createItemPanel("B", false));
        itemsPanel.add(createItemPanel("C", false));
        itemsPanel.add(createItemPanel("D", false));

        // Botão para resetar os dados de todos os itens de fabricação
        resetItensButton = createStyledButton("Resetar Itens de Fabricação");
        resetItensButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetItemData();
            }
        });
        itemsPanel.add(resetItensButton);

        mainPanel.add(itemsPanel, BorderLayout.CENTER);

        // Painel de resultados
        JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        calculateButton = createStyledButton("Calcular Lucro");
        calculateButton.addActionListener(new CalculateHandler());

        resultLabel = new JLabel(" ");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));

        resultPanel.add(calculateButton);
        resultPanel.add(resultLabel);

        // Botão para resetar todos os dados
        resetAllButton = createStyledButton("Resetar Tudo");
        resetAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetAll();
            }
        });
        resultPanel.add(resetAllButton);

        mainPanel.add(resultPanel, BorderLayout.SOUTH);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(600, 650));
    }

    // Métodos auxiliares de construção de interface
    private JSpinner createPriceSpinner() {
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 9999999.99, 0.1));
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spinner, "#,##0.00");

        JFormattedTextField tf = editor.getTextField();
        DecimalFormat format = new DecimalFormat("#,##0.00");
        NumberFormatter formatter = new NumberFormatter(format);

        formatter.setValueClass(Double.class);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        formatter.setMinimum(0.0);
        formatter.setMaximum(9999999.99);

        tf.setFormatterFactory(new DefaultFormatterFactory(formatter));

        spinner.setEditor(editor);
        return spinner;
    }

    private JPanel createItemPanel(String itemLabel, boolean obrigatorio) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panel.setBorder(BorderFactory.createEtchedBorder());

        // Componentes do item
        JLabel lblItem = new JLabel("Item " + itemLabel + (obrigatorio ? "*:" : ":"));
        JTextField nameField = new JTextField(8);
        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(0, 0, 9999, 1));
        JSpinner costSpinner = createPriceSpinner();

        // Configuração dos spinners
        quantitySpinner.setPreferredSize(new Dimension(70, 25));
        costSpinner.setPreferredSize(new Dimension(80, 25));

        // Botão para resetar os dados deste item específico
        JButton resetItemButton = createStyledButton("Resetar");
        resetItemButton.setPreferredSize(new Dimension(90, 25));
        resetItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                quantitySpinner.setValue(0);
                costSpinner.setValue(0.0);
            }
        });

        // Adiciona componentes ao painel
        panel.add(lblItem);
        panel.add(new JLabel("Nome:"));
        panel.add(nameField);
        panel.add(new JLabel("Qtd/unid.:"));
        panel.add(quantitySpinner);
        panel.add(new JLabel("Custo Unit.:"));
        panel.add(costSpinner);
        panel.add(resetItemButton);

        // Atribuição dos componentes aos campos correspondentes
        switch(itemLabel) {
            case "A":
                itemAName = nameField;
                itemAQuantity = quantitySpinner;
                itemACost = costSpinner;
                break;
            case "B":
                itemBName = nameField;
                itemBQuantity = quantitySpinner;
                itemBCost = costSpinner;
                break;
            case "C":
                itemCName = nameField;
                itemCQuantity = quantitySpinner;
                itemCCost = costSpinner;
                break;
            case "D":
                itemDName = nameField;
                itemDQuantity = quantitySpinner;
                itemDCost = costSpinner;
                break;
        }
        return panel;
    }

    private void addField(JPanel panel, String label, JTextField field) {
        panel.add(new JLabel(label));
        panel.add(field);
    }

    private void addSpinner(JPanel panel, String label, JSpinner spinner) {
        panel.add(new JLabel(label));
        panel.add(spinner);
    }

    // Função para resetar os dados dos itens de fabricação
    private void resetItemData() {
        itemAName.setText("");
        itemAQuantity.setValue(0);
        itemACost.setValue(0.0);

        itemBName.setText("");
        itemBQuantity.setValue(0);
        itemBCost.setValue(0.0);

        itemCName.setText("");
        itemCQuantity.setValue(0);
        itemCCost.setValue(0.0);

        itemDName.setText("");
        itemDQuantity.setValue(0);
        itemDCost.setValue(0.0);
    }

    // Função para resetar todos os dados
    private void resetAll() {
        merchNameField.setText("");
        merchQuantitySpinner.setValue(1);
        merchPriceSpinner.setValue(0.0);

        resetItemData();
        resultLabel.setText(" ");
    }

    // Função para salvar os dados em um arquivo (incluindo os itens)
    private void saveFormula() {
        String formulaName = JOptionPane.showInputDialog(this, "Digite o nome da fórmula:");

        if (formulaName == null || formulaName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome da fórmula é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(formulaName + ".txt"))) {
            // Salva os dados da mercadoria
            writer.write("Mercadoria: " + merchNameField.getText() + "\n");
            writer.write("Quantidade: " + merchQuantitySpinner.getValue() + "\n");
            writer.write("Preço Unitário: " + merchPriceSpinner.getValue() + "\n");
            // Salva os dados dos itens
            writer.write("Item A: " + itemAName.getText() + ", " + itemAQuantity.getValue() + ", " + itemACost.getValue() + "\n");
            writer.write("Item B: " + itemBName.getText() + ", " + itemBQuantity.getValue() + ", " + itemBCost.getValue() + "\n");
            writer.write("Item C: " + itemCName.getText() + ", " + itemCQuantity.getValue() + ", " + itemCCost.getValue() + "\n");
            writer.write("Item D: " + itemDName.getText() + ", " + itemDQuantity.getValue() + ", " + itemDCost.getValue() + "\n");

            JOptionPane.showMessageDialog(this, "Fórmula salva com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar fórmula.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Função para carregar os dados de um arquivo (incluindo os itens)
    private void loadFormula() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecionar fórmula para carregar");
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Mercadoria: ")) {
                        merchNameField.setText(line.substring(12).trim());
                    } else if (line.startsWith("Quantidade: ")) {
                        merchQuantitySpinner.setValue(Integer.parseInt(line.substring(12).trim()));
                    } else if (line.startsWith("Preço Unitário: ")) {
                        merchPriceSpinner.setValue(Double.parseDouble(line.substring(16).trim()));
                    } else if (line.startsWith("Item A: ")) {
                        String data = line.substring(8).trim();
                        String[] parts = data.split(",");
                        if (parts.length == 3) {
                            itemAName.setText(parts[0].trim());
                            itemAQuantity.setValue(Integer.parseInt(parts[1].trim()));
                            itemACost.setValue(Double.parseDouble(parts[2].trim()));
                        }
                    } else if (line.startsWith("Item B: ")) {
                        String data = line.substring(8).trim();
                        String[] parts = data.split(",");
                        if (parts.length == 3) {
                            itemBName.setText(parts[0].trim());
                            itemBQuantity.setValue(Integer.parseInt(parts[1].trim()));
                            itemBCost.setValue(Double.parseDouble(parts[2].trim()));
                        }
                    } else if (line.startsWith("Item C: ")) {
                        String data = line.substring(8).trim();
                        String[] parts = data.split(",");
                        if (parts.length == 3) {
                            itemCName.setText(parts[0].trim());
                            itemCQuantity.setValue(Integer.parseInt(parts[1].trim()));
                            itemCCost.setValue(Double.parseDouble(parts[2].trim()));
                        }
                    } else if (line.startsWith("Item D: ")) {
                        String data = line.substring(8).trim();
                        String[] parts = data.split(",");
                        if (parts.length == 3) {
                            itemDName.setText(parts[0].trim());
                            itemDQuantity.setValue(Integer.parseInt(parts[1].trim()));
                            itemDCost.setValue(Double.parseDouble(parts[2].trim()));
                        }
                    }
                }

                JOptionPane.showMessageDialog(this, "Fórmula carregada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar fórmula.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Calcula o lucro da mercadoria
    private class CalculateHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double merchQuantity = ((Number) merchQuantitySpinner.getValue()).doubleValue();
                double merchPrice = ((Number) merchPriceSpinner.getValue()).doubleValue();

                double itemAQuantityVal = ((Number) itemAQuantity.getValue()).doubleValue();
                double itemACostVal = ((Number) itemACost.getValue()).doubleValue();
                double itemBQuantityVal = ((Number) itemBQuantity.getValue()).doubleValue();
                double itemBCostVal = ((Number) itemBCost.getValue()).doubleValue();
                double itemCQuantityVal = ((Number) itemCQuantity.getValue()).doubleValue();
                double itemCCostVal = ((Number) itemCCost.getValue()).doubleValue();
                double itemDQuantityVal = ((Number) itemDQuantity.getValue()).doubleValue();
                double itemDCostVal = ((Number) itemDCost.getValue()).doubleValue();

                // Cálculo do custo total dos itens
                double totalCost = (itemAQuantityVal * itemACostVal) +
                        (itemBQuantityVal * itemBCostVal) +
                        (itemCQuantityVal * itemCCostVal) +
                        (itemDQuantityVal * itemDCostVal);

                double lucro = (merchQuantity * merchPrice) - totalCost;

                resultLabel.setText("Lucro: R$ " + String.format("%.2f", lucro));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao calcular o lucro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VendasMercadorias frame = new VendasMercadorias();
            frame.setVisible(true);
        });
    }
}
