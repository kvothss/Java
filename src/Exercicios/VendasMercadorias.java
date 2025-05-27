
import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vendas extends JFrame {

    private JTextField merchNameField;
    private JSpinner merchQuantitySpinner;
    private JSpinner merchPriceSpinner;

    private JTextField itemAName;
    private JSpinner itemAQuantity;
    private JSpinner itemACost;

    private JTextField itemBName;
    private JSpinner itemBQuantity;
    private JSpinner itemBCost;

    private JTextField itemCName;
    private JSpinner itemCQuantity;
    private JSpinner itemCCost;

    private JTextField itemDName;
    private JSpinner itemDQuantity;
    private JSpinner itemDCost;

    private JButton calculateButton;
    private JLabel resultLabel;

    public Vendas() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Vendas de Mercadorias");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel de detalhes da mercadoria
        JPanel merchPanel = new JPanel(new GridLayout(3, 2));
        merchPanel.add(new JLabel("Nome da Mercadoria:"));
        merchNameField = new JTextField();
        merchPanel.add(merchNameField);

        merchPanel.add(new JLabel("Quantidade:"));
        merchQuantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        merchPanel.add(merchQuantitySpinner);

        merchPanel.add(new JLabel("Valor de Venda (R$):"));
        merchPriceSpinner = new JSpinner(new SpinnerNumberModel(0.0, 0.0, Double.MAX_VALUE, 0.1));
        merchPanel.add(merchPriceSpinner);

        add(merchPanel, BorderLayout.NORTH);

        // Painel de itens de fabricação
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));

        // Item A (obrigatório)
        JPanel itemAPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        itemAPanel.add(new JLabel("Item A (obrigatório):"));
        itemAName = new JTextField(10);
        itemAPanel.add(new JLabel("Nome:"));
        itemAPanel.add(itemAName);
        itemAPanel.add(new JLabel("Quantidade:"));
        itemAQuantity = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        itemAPanel.add(itemAQuantity);
        itemAPanel.add(new JLabel("Custo (R$):"));
        itemACost = new JSpinner(new SpinnerNumberModel(0.0, 0.0, Double.MAX_VALUE, 0.1));
        itemAPanel.add(itemACost);
        itemsPanel.add(itemAPanel);

        // Item B (opcional)
        JPanel itemBPanel = createOptionalItemPanel("B");
        itemsPanel.add(itemBPanel);

        // Item C (opcional)
        JPanel itemCPanel = createOptionalItemPanel("C");
        itemsPanel.add(itemCPanel);

        // Item D (opcional)
        JPanel itemDPanel = createOptionalItemPanel("D");
        itemsPanel.add(itemDPanel);

        add(itemsPanel, BorderLayout.CENTER);

        // Painel de botão e resultado
        JPanel buttonPanel = new JPanel();
        calculateButton = new JButton("Calcular Lucro");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateProfit();
            }
        });
        buttonPanel.add(calculateButton);

        resultLabel = new JLabel(" ");
        buttonPanel.add(resultLabel);

        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private JPanel createOptionalItemPanel(String itemLabel) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel("Item " + itemLabel + " (opcional):"));

        JTextField nameField = new JTextField(10);
        panel.add(new JLabel("Nome:"));
        panel.add(nameField);

        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        panel.add(new JLabel("Quantidade:"));
        panel.add(quantitySpinner);

        JSpinner costSpinner = new JSpinner(new SpinnerNumberModel(0.0, 0.0, Double.MAX_VALUE, 0.1));
        panel.add(new JLabel("Custo (R$):"));
        panel.add(costSpinner);

        switch (itemLabel) {
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
                itemDQuantity = (JSpinner) quantitySpinner;
                itemDCost = costSpinner;
                break;
        }

        return panel;
    }

    private void calculateProfit() {
        // Validação da mercadoria
        String merchName = merchNameField.getText().trim();
        if (merchName.isEmpty()) {
            showError("Nome da mercadoria é obrigatório.");
            return;
        }

        int merchQty = (Integer) merchQuantitySpinner.getValue();
        double merchPrice = (Double) merchPriceSpinner.getValue();
        if (merchPrice <= 0) {
            showError("Valor de venda deve ser positivo.");
            return;
        }

        // Validação do item A
        String itemANameStr = itemAName.getText().trim();
        if (itemANameStr.isEmpty()) {
            showError("Nome do item A é obrigatório.");
            return;
        }
        int itemAQty = (Integer) itemAQuantity.getValue();
        double itemACostVal = (Double) itemACost.getValue();
        if (itemAQty <= 0 || itemACostVal <= 0) {
            showError("Quantidade e custo do item A devem ser positivos.");
            return;
        }
        double totalCost = itemAQty * itemACostVal;

        // Processar itens opcionais B, C, D
        totalCost += processOptionalItem(itemBName, itemBQuantity, itemBCost, "B");
        totalCost += processOptionalItem(itemCName, itemCQuantity, itemCCost, "C");
        totalCost += processOptionalItem(itemDName, itemDQuantity, itemDCost, "D");

        double saleValue = merchQty * merchPrice;
        double profit = saleValue - totalCost;

        resultLabel.setText(String.format("Lucro: R$ %.2f", profit));
    }

    private double processOptionalItem(JTextField nameField, JSpinner quantitySpinner, JSpinner costSpinner, String itemLabel) {
        String name = nameField.getText().trim();
        if (!name.isEmpty()) {
            int qty = (Integer) quantitySpinner.getValue();
            double cost = (Double) costSpinner.getValue();
            if (qty <= 0 || cost <= 0) {
                showError("Quantidade e custo do item " + itemLabel + " devem ser positivos.");
                return -1; // Valor inválido, o cálculo será interrompido
            }
            return qty * cost;
        }
        return 0;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VendasMercadorias().setVisible(true));
    }
}