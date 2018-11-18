package com.github.hiteshlilhare.jcplaystore;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

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
public class Welcome extends JDialog {

    private JDialog context = this;
    private Dimension windowSize;
    private Translation translation;

    private GridBagConstraints constraints;
    private JCPlayStoreClient parent;

    private JLabel loading;

    public Welcome(JCPlayStoreClient parent, Translation translation) {
        this.parent = parent;
        this.translation = translation;
        JFrame.setDefaultLookAndFeelDecorated(false); //ugly
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        windowSize = new Dimension(550, 300);
        this.setSize(windowSize);

        JPanel panel = new background();
        this.setContentPane(panel);

        //panel = (JPanel) this.getContentPane();
        GridBagLayout layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        panel.setLayout(layout);

//        addToLayout(title(), 0, 0, 4);
//        addToLayout(closeIcon(), 5, 0, 1);
        addToLayout(closeIcon(), 6, 0, 1);
        addToLayout(icon("src/img/baseline_usb_black_18dp"), 0, 1, 1);
        addToLayout(text(translation.get(2)), 1, 1, 3);
        addToLayout(refresh(), 1, 2, 3);
        loading = loading();
        addToLayout(loading, 4, 2,1);
        setLocationRelativeTo(parent);
    }

    class background extends JPanel{
        Image img;
        private background(){
            img = Toolkit.getDefaultToolkit().createImage("src/img/bg.jpg");
        }
        @Override
        public void paintComponent(Graphics g)
        {
            g.drawImage(img,0,0,this);
        }
    }

    private void addToLayout(Component what, int xwhere, int ywhere, int width) {
        constraints.gridx = xwhere;
        constraints.gridy = ywhere;
        constraints.gridwidth = width;
        add(what, constraints);
    }

    private void removeFromLayout(Component what) {
        remove(what);
        revalidate();
        repaint();
    }

    private JLabel refresh() {
        JLabel label = new JLabel();
        Font c = new Font("Courier", Font.PLAIN, 18);
        label.setFont(c);
        label.setText(translation.get(6));
        label.setSize(windowSize.width, 80);
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() >= 1) {
                    showLoading();
                    if (parent.checkTerminals()) {
                        parent.initAppComponents();
                        parent.setVisible(true);
                        context.dispose();
                    }
                }
            }
        });
        Border border = new EmptyBorder(10, 10, 10, 10);
        label.setBorder(border);
        return label;
    }


    private JLabel closeIcon() {
        JLabel label = new JLabel();
        ImageIcon icon = new ImageIcon("src/img/baseline_close_black_18dp.png");
        label.setIcon(icon);
        label.setBorder(new EmptyBorder(10, 250, 10, 10));
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


//    private JLabel title() {
//        JLabel label = new JLabel();
//        Font c = new Font("Courier", Font.PLAIN, 22);
//        label.setFont(c);
//        label.setText(translation.get(1));
//        label.setSize(windowSize.width, 120);
//
//        Border border = new EmptyBorder(30, 140, 30, 110);
//        label.setBorder(border);
//        return label;
//    }

    private JLabel text(String text) {
        JLabel label = new JLabel();
        Font c = new Font("Courier", Font.PLAIN, 18);
        label.setFont(c);
        label.setText(text);
        label.setSize(windowSize.width, 80);
        Border border = new EmptyBorder(10, 10, 10, 10);
        label.setBorder(border);
        return label;
    }

    private JLabel icon(String path) {
        JLabel label = new JLabel();
        ImageIcon icon = new ImageIcon(path);
        label.setIcon(icon);
        label.setBorder(new EmptyBorder(20, 20, 20, 20));
        return label;
    }

    private JLabel loading() {
        JLabel label = new JLabel();
        ImageIcon icon = new ImageIcon("src/img/loading.gif");
        label.setIcon(icon);
        label.setVisible(false);
        return label;
    }

    private void showLoading() {
        if (loading.isVisible()) return;
        loading.setVisible(true);
        PauseTransition pause = new PauseTransition( Duration.seconds(1) );
        //TODO doesn't hide
        pause.setOnFinished(event ->
                loading.setVisible(false)
        );
        pause.play();
    }
}
