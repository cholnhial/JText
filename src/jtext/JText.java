/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import java.io.*;

/**
 *
 * @author chol
 */
public class JText extends JFrame implements ActionListener, DocumentListener {

    final String programName = "JText";
    boolean changed;
    String openFileName = "Untitled";
    JMenuItem menuItemSave = new JMenuItem("Save");
    JMenuItem menuItemOpen = new JMenuItem("Open");
    JMenuItem menuItemExit = new JMenuItem("Exit");
    JFileChooser fileChooser = new JFileChooser();
    JTextArea textArea = new JTextArea(8, 40);

    public JText() {
        super("JText");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 480);

        JPanel panel = new JPanel();
        JMenuBar menubar = new JMenuBar();

        /* Create menu bar */

        JMenu menuFile = new JMenu("File");
        menubar.add(menuFile);
        menuFile.add(menuItemSave);
        menuFile.add(menuItemOpen);
        menuFile.addSeparator();
        menuFile.add(menuItemExit);
        add("North", menubar);

        /* Add text area */

        JScrollPane textAreaScrollPane = new JScrollPane(textArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        add("Center", textAreaScrollPane);
        textArea.getDocument().addDocumentListener(this);
        String title = openFileName + " - " + programName;
        setTitle(title);
        menuItemOpen.addActionListener(this);
        menuItemSave.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == menuItemOpen) {

            /* Warn user */
            if (changed == true) {
                int response = JOptionPane.showConfirmDialog(rootPane,
                        "Do you want to save the current file?",
                        "File Changed",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    /* Save the file */
                    int returnVal = fileChooser.showSaveDialog(JText.this);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();

                        try {
                            saveFile(file.getAbsolutePath(), textArea.getText());
                        } catch (Exception e) {
                            JOptionPane.showConfirmDialog(rootPane, e.getMessage(),
                                    "Error saving file", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }

                return;
            }

            int returnVal = fileChooser.showOpenDialog(JText.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                /* Set contents */
                try {

                    String fileContents = openFile(file.getAbsolutePath());
                    textArea.setText(fileContents);
                    openFileName = file.getName();

                    String title = openFileName + " - " + programName;
                    setTitle(title);
                    changed = false;

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(rootPane, e.getMessage());
                }

            }

        } else if (event.getSource() == menuItemSave) {
            int returnVal = fileChooser.showSaveDialog(JText.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    saveFile(file.getAbsolutePath(), textArea.getText());
                    changed = false;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(rootPane, e.getMessage());
                }

            }
        }

    }

    public static String openFile(String filename) throws Exception {
        String fileBuffer = new String("");

        try {
            FileInputStream fileStream = new FileInputStream(filename);

            DataInputStream dataInputStream = new DataInputStream(fileStream);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
            String line = new String("");

            while ((line = bufferedReader.readLine()) != null) {
                fileBuffer += line + "\n";
            }

        } catch (IOException e) {
            System.out.println("Error trying to read file contents: " + e.getMessage());
            throw (e);
        }

        return fileBuffer;
    }

    public static void saveFile(String filename, String contents) throws Exception {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            fileWriter.write(contents);
            fileWriter.close();


        } catch (IOException e) {
            System.out.println("Error trying to read file contents: " + e.getMessage());
            throw (e);
        }

    }

    public void changedUpdate(DocumentEvent e) {
        changed = true;
        String title = "*" + openFileName + " - " + programName;
        setTitle(title);
    }

    public void insertUpdate(DocumentEvent e) {
        changed = true;
        String title = "*" + openFileName + " - " + programName;
        setTitle(title);
    }

    public void removeUpdate(DocumentEvent e) {
        changed = true;
        String title = "*" + openFileName + " - " + programName;
        setTitle(title);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JText jtext = new JText();
    }
}
