package org.prak.ui;

import org.prak.model.*;
import org.prak.repository.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class MaterialDetailsWindow extends JFrame {

    private final RMTRepository rmtRepo = new RMTRepository();
    private final PhisMechPropertyRepository phisRepo = new PhisMechPropertyRepository();

    public MaterialDetailsWindow(Material material) {
        setTitle("Свойства: " + material.getName());
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Font tableFont = new Font("Segoe UI", Font.PLAIN, 12);
        Font headerFont = new Font("Segoe UI", Font.PLAIN, 12); // Убрали жирный шрифт
        Color headerBackground = new Color(240, 240, 240);
        Color headerForeground = Color.BLACK;
        Color gridColor = new Color(220, 220, 220);

        JTable phisTable = createStyledTable(
                buildPhisTableModel(phisRepo.getByMaterialId(material.getId())),
                tableFont, headerFont, headerBackground, headerForeground, gridColor
        );

        JTable rmtTable = createStyledTable(
                buildRMTTableModel(rmtRepo.getByMaterialId(material.getId())),
                tableFont, headerFont, headerBackground, headerForeground, gridColor
        );

        JPanel phisPanel = createTablePanel("Физико-механические свойства", phisTable);
        JPanel rmtPanel = createTablePanel("Длительная прочность (RMT)", rmtTable);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, phisPanel, rmtPanel);
        splitPane.setDividerLocation(400);
        splitPane.setDividerSize(3);
        splitPane.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        add(splitPane, BorderLayout.CENTER);
    }

    private JPanel createTablePanel(String title, JTable table) {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        return panel;
    }

    private JTable createStyledTable(DefaultTableModel model, Font tableFont,
                                     Font headerFont, Color headerBackground,
                                     Color headerForeground, Color gridColor) {
        JTable table = new JTable(model);

        table.setFont(tableFont);
        table.setRowHeight(25);
        table.setGridColor(gridColor);
        table.setShowGrid(true);
        table.setIntercellSpacing(new Dimension(0, 0));

        JTableHeader header = table.getTableHeader();
        header.setFont(headerFont);
        header.setBackground(headerBackground);
        header.setForeground(headerForeground);
        header.setReorderingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        return table;
    }

    private DefaultTableModel buildRMTTableModel(List<RMTEntry> list) {
        String[] cols = {"Температура, °C / Время, ч", "10", "30", "100", "300", "1000",
                "3000", "10000", "30000", "100000", "200000", "300000"};
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Integer.class : Double.class;
            }
        };

        for (RMTEntry entry : list) {
            model.addRow(new Object[]{
                    entry.getTemperatureTime(),
                    entry.getT10(),
                    entry.getT30(),
                    entry.getT100(),
                    entry.getT300(),
                    entry.getT1000(),
                    entry.getT3000(),
                    entry.getT10000(),
                    entry.getT30000(),
                    entry.getT100000(),
                    entry.getT200000(),
                    entry.getT300000()
            });
        }
        return model;
    }

    private DefaultTableModel buildPhisTableModel(List<PhisMechProperty> list) {
        String[] cols = {"t", "°C", "20", "50", "100", "150", "200",
                "250", "300", "350", "400", "450", "500"};
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex < 2 ? String.class : Double.class;
            }
        };
        for (PhisMechProperty p : list) {
            model.addRow(new Object[]{
                    p.getTemperature(),
                    p.getC(),
                    p.getT20(),
                    p.getT50(),
                    p.getT100(),
                    p.getT150(),
                    p.getT200(),
                    p.getT250(),
                    p.getT300(),
                    p.getT350(),
                    p.getT400(),
                    p.getT450(),
                    p.getT500()
            });
        }
        return model;
    }
}
