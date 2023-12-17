package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private JTextField paperTitleField;
    private JComboBox<String> paperTypeComboBox;
    private JTextArea paperDescriptionArea;
    private JButton registerButton;

    public Main() {
        this.setTitle("Архивная библиотека");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel paperTitleLabel = new JLabel("Название:");
        paperTitleField = new JTextField();

        JLabel paperTypeLabel = new JLabel("Тип:");
        String[] paperTypes = new String[]{"Книга", "Документ", "Журнал"};
        paperTypeComboBox = new JComboBox<>(paperTypes);

        JLabel paperDescriptionLabel = new JLabel("Описание :");
        paperDescriptionArea = new JTextArea();
        JScrollPane descriptionScrollPane = new JScrollPane(paperDescriptionArea);

        registerButton = new JButton("Регистрация");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerPaper();
            }
        });

        formPanel.add(paperTitleLabel);
        formPanel.add(paperTitleField);
        formPanel.add(paperTypeLabel);
        formPanel.add(paperTypeComboBox);
        formPanel.add(paperDescriptionLabel);
        formPanel.add(descriptionScrollPane);
        formPanel.add(new JLabel());
        formPanel.add(registerButton);

        this.add(formPanel, BorderLayout.CENTER);
    }

    private void registerPaper() {
        String paperTitle = paperTitleField.getText();
        String paperType = (String) paperTypeComboBox.getSelectedItem();
        String paperDescription = paperDescriptionArea.getText();

        if (!paperTitle.isEmpty() && paperType != null && !paperDescription.isEmpty()) {
            try {
                FileWriter fileWriter = new FileWriter("paper_data.txt", true);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println("Название: " + paperTitle);
                printWriter.println("Тип: " + paperType);
                printWriter.println("Описание: " + paperDescription);
                printWriter.println();
                printWriter.close();

                JOptionPane.showMessageDialog(this, "Успешно добавленно!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Ошибка сохранения.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Не все поля заполнены!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Main registrationForm = new Main();
                registrationForm.setVisible(true);
            }
        });
    }
}