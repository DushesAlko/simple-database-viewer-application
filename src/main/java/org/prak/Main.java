package org.prak;

import org.prak.ui.MainView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Настройки Swing по умолчанию
        SwingUtilities.invokeLater(() -> {
            try {
                // Установим системную тему оформления
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            new MainView().setVisible(true);
        });
    }
}
