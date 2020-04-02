package geekbrains.java2.client.view;

import geekbrains.java2.client.controller.ClientController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.util.List;

public class ClientChat extends JFrame {


    private JPanel mainPanel;
    private JTextArea chatText;
    private JTextField messageTextField;
    private JButton sendButton;
    private JList<String> usersList;


    private ClientController controller;

    public ClientChat(ClientController controller) {
        this.controller = controller;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(640, 480);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
        addListeners();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                controller.shutdown();
            }
        });

        // мы создаем графические компоненты, но не вызываем метод визибл!
        // Когда нам нужно будет тогда и запустим по ходу логики.

//        setTitle(controller.getUsername());
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setSize(640, 480);
//        setLocationRelativeTo(null);
//        setContentPane(mainPanel);
//        addListeners();
//        addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//                controller.shutdown();
//            }
//        });
    }

    private void addListeners() {
        sendButton.addActionListener(e -> ClientChat.this.sendMessage());
        messageTextField.addActionListener(e -> sendMessage());
    }

    private void sendMessage() {
        String message = messageTextField.getText().trim();
        if (message.isEmpty()) {
            return;
        }
        appendOwnMessage(message);
//        controller.sendMessage(message);
//        messageTextField.setText(null);
        if (usersList.getSelectedIndex() < 1) {
            controller.sendMessageToAllUsers(message); //отправляем сообщение всем пользователям
        } else {
            String username = usersList.getSelectedValue();
            controller.sendPrivateMessage(username, message); //приватное сообщение
        }
        messageTextField.setText(null);
    }

    public void appendMessage(String message) {
        SwingUtilities.invokeLater(new Runnable() { //с помощью invokeLater сообщаем потоку по графическому интерфейсу, что есть некое событие, которое
            //должно было быть обработано в птоке, который занимается обработкой графического интерфейса, чтоб он чуть позже, когда будет обрабатывать
            // граф. интерфейс он смог обработать и эти данные. Поставили в очередь к потоку графического интерфейса.
            @Override
            public void run() {
                chatText.append(message); //получили сообщение и отправляем в чаттекст
                chatText.append(System.lineSeparator());
            }
        });
    }

    private void appendOwnMessage(String message) {
        appendMessage("Я: " + message);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, "Failed to send message!");
    }

    public void updateUsers(List<String> users) {
        SwingUtilities.invokeLater(() -> {
            DefaultListModel<String> model = new DefaultListModel<>();
            model.addAll(users);
            usersList.setModel(model);
        });
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.setBackground(new Color(-16346993));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-16362927));
        mainPanel.add(panel1, BorderLayout.CENTER);
        chatText = new JTextArea();
        chatText.setBackground(new Color(-10241349));
        chatText.setForeground(new Color(-16777216));
        panel1.add(chatText, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        sendButton = new JButton();
        sendButton.setBackground(new Color(-16346993));
        sendButton.setForeground(new Color(-393729));
        sendButton.setText("Send");
        panel2.add(sendButton, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        messageTextField = new JTextField();
        messageTextField.setBackground(new Color(-393729));
        panel2.add(messageTextField, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.setForeground(new Color(-11206662));
        mainPanel.add(panel3, BorderLayout.WEST);
        usersList = new JList();
        usersList.setBackground(new Color(-11206662));
        usersList.setForeground(new Color(-16777216));
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        defaultListModel1.addElement("All");
        defaultListModel1.addElement("username1");
        defaultListModel1.addElement("username2");
        defaultListModel1.addElement("username3");
        usersList.setModel(defaultListModel1);
        usersList.setSelectionForeground(new Color(-16346993));
        usersList.setSelectionMode(0);
        usersList.setVerifyInputWhenFocusTarget(true);
        panel3.add(usersList, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
