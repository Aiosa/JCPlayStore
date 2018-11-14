package com.github.hiteshlilhare.jcplaystore;

import sun.misc.JavaLangAccess;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Jiří Horák
 * @version 1.0
 */
public class Welcome extends JDialog  {

    private JDialog context = this;
    private JPanel panel;
    private Dimension windowSize;


    public Welcome(JFrame parentFrame) {

        JFrame.setDefaultLookAndFeelDecorated(false); //ugly
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        windowSize = new Dimension(550,
                300);

        this.setSize(windowSize);


        panel = (JPanel) this.getContentPane();

        panel.add(title());
        panel.add(closeIcon());
        //panel.add(text("It seems you have no card or card reader plugged in your computer."));
        //TODO: detect card reader and close on input
        this.setLocationRelativeTo(parentFrame);
    }



    private JLabel closeIcon() {
        JLabel label = new JLabel();
        ImageIcon icon = new ImageIcon("src/img/baseline_close_black_18dp.png");
        label.setIcon(icon);
        label.setBorder(new EmptyBorder(10, 10, 10, 10));
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setVerticalAlignment(SwingConstants.TOP);
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() >= 1) {
                    System.exit(0);
                }
            }
        });
        return label;
    }

    private JLabel title() {
        JLabel label = new JLabel();
        Font c = new Font("Courier", Font.PLAIN, 22);
        label.setFont(c);
        label.setText("Welcome to JCAppStore");
        label.setSize(windowSize.width, 120);

        Border border = new EmptyBorder(20, 20, 20, 20);
        label.setBorder(border);

        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.TOP);
        return label;
    }

    private JLabel text(String text) {
        JLabel label = new JLabel();
        Font c = new Font("Courier", Font.PLAIN, 18);
        label.setFont(c);
        label.setText(text);
        label.setSize(windowSize.width, 80);

        Border border = new EmptyBorder(10, 10, 10, 10);
        label.setBorder(border);

        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        return label;
    }
}