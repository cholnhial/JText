/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtext;

import javax.swing.*;
/**
 *
 * @author chol
 */
public class JText extends JFrame {

   
   public JText() {
       super("JText");
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setSize(640,480);
       
       JPanel panel = new JPanel();
       JMenuBar menubar = new JMenuBar();
       
       /* Create menu bar */
       JMenuItem menuItemSave = new JMenuItem("Save");
       JMenuItem menuItemOpen = new JMenuItem("Open");
       JMenuItem menuItemExit = new JMenuItem("Exit");
       JMenu menuFile = new JMenu("File");
       menubar.add(menuFile);
       menuFile.add(menuItemSave);
       menuFile.add(menuItemOpen);
       menuFile.addSeparator();
       menuFile.add(menuItemExit);
       add("North", menubar);
       
       /* Create and add text area */
       JTextArea textArea = new JTextArea(8, 40);
       JScrollPane textAreaScrollPane = new JScrollPane(textArea, 
               ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
               ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS
               );
       add("Center", textAreaScrollPane);
       setVisible(true);
      
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JText jtext = new JText();
    }
}
