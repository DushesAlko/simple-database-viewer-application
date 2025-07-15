package org.prak.ui;

import org.prak.model.Material;
import org.prak.model.MaterialGroup;
import org.prak.repository.MaterialGroupRepository;
import org.prak.repository.MaterialRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class MainView extends JFrame {

    private JComboBox<MaterialGroup> groupComboBox;
    private JTable materialTable;
    private DefaultTableModel materialTableModel;
    private JButton openButton;

    private final MaterialGroupRepository groupRepo = new MaterialGroupRepository();
    private final MaterialRepository materialRepo = new MaterialRepository();

    public MainView() {
        setTitle("Выбор металла");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        Font mainFont = new Font("Segoe UI", Font.PLAIN, 12);
        Color headerBackground = new Color(240, 240, 240);
        Color gridColor = new Color(220, 220, 220);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel groupLabel = new JLabel("Класс металла:");
        groupLabel.setFont(mainFont);
        topPanel.add(groupLabel);

        groupComboBox = new JComboBox<>();
        groupComboBox.setFont(mainFont);
        groupComboBox.setPreferredSize(new Dimension(250, 25));
        topPanel.add(groupComboBox);
        add(topPanel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Название", "Описание"};
        materialTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        materialTable = new JTable(materialTableModel);
        materialTable.setFont(mainFont);
        materialTable.setRowHeight(25);
        materialTable.setGridColor(gridColor);
        materialTable.setShowGrid(true);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < materialTable.getColumnCount(); i++) {
            materialTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JTableHeader header = materialTable.getTableHeader();
        header.setFont(mainFont);
        header.setBackground(headerBackground);
        header.setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(materialTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        add(scrollPane, BorderLayout.CENTER);

        openButton = new JButton("Открыть свойства");
        openButton.setFont(mainFont);
        openButton.setPreferredSize(new Dimension(150, 30));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
        bottomPanel.add(openButton);
        add(bottomPanel, BorderLayout.SOUTH);

        groupComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                                                          int index, boolean isSelected,
                                                          boolean cellHasFocus) {
                String displayValue = (value == null) ? "Выберите класс" : value.toString();
                JLabel label = (JLabel) super.getListCellRendererComponent(
                        list, displayValue, index, isSelected, cellHasFocus);
                label.setFont(mainFont);
                return label;
            }
        });

        groupComboBox.addActionListener(e -> loadMaterials());

        openButton.addActionListener(e -> {
            int selectedRow = materialTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this,
                        "Выберите металл в таблице.",
                        "Внимание",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id = (int) materialTableModel.getValueAt(selectedRow, 0);
            String name = (String) materialTableModel.getValueAt(selectedRow, 1);
            String desc = (String) materialTableModel.getValueAt(selectedRow, 2);
            MaterialGroup selectedGroup = (MaterialGroup) groupComboBox.getSelectedItem();

            Integer groupId = selectedGroup != null ? selectedGroup.getId() : null;

            Material material = new Material(id, name, desc, groupId);
            new MaterialDetailsWindow(material).setVisible(true);
        });

        loadGroups();
    }

    private void loadGroups() {
        List<MaterialGroup> groups = groupRepo.getAll();
        groupComboBox.removeAllItems();
        groupComboBox.addItem(null);
        for (MaterialGroup group : groups) {
            groupComboBox.addItem(group);
        }
    }

    private void loadMaterials() {
        materialTableModel.setRowCount(0);
        MaterialGroup selectedGroup = (MaterialGroup) groupComboBox.getSelectedItem();

        if (selectedGroup == null) {
            return;
        }

        List<Material> materials = materialRepo.getByGroupId(selectedGroup.getId());
        for (Material m : materials) {
            materialTableModel.addRow(new Object[]{
                    m.getId(), m.getName(), m.getDescription()
            });
        }
    }
}