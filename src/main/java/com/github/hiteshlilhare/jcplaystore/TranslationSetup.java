package com.github.hiteshlilhare.jcplaystore;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;


/**
 * @author Jiří Horák
 * @version 1.0
 */
public class TranslationSetup extends JFrame {

    private HashMap<String, String> options;

    TranslationSetup(File file, HashMap<String, String> options) {

        this.options = options;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //center this dialog
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setSize(50, 100);

        //available translations
        final String[] langs = new String[] {
               "English", "eng",
               "Česky", "cz"
        };

        JPanel panel = (JPanel) this.getContentPane();
        //populate list with languages
        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> list = new JList<>( model );

        for (int i = 0; i < langs.length; i += 2) {
            model.addElement(langs[i]);
        }

        //on click save choosed and run
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int valueIndex = e.getLastIndex();
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter(file));
                    writer.write("lang=" + langs[(2*valueIndex+1)] + "\n");
                    writer.close();
                    populateOptions(langs[(2*valueIndex+1)]);
                } catch (IOException e1) {
                    e1.printStackTrace();
                    populateOptions("eng");
                }
                TranslationSetup.super.dispose();
                JCPlayStoreClient.run();
            }
        });
        panel.add(list);
    }

    private void populateOptions(String lang) {
        options.put("lang", lang);
    }
}
