package com.github.hiteshlilhare.jcplaystore;

import apdu4j.TerminalManager;

import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CardTerminals;
import javax.smartcardio.TerminalFactory;
import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jiří Horák
 * @version 1.0
 */
public class Terminals {

    public static boolean checkTerminals(TreeMap<String, CardTerminal> terminalList, String terminal) {
        try {
            final TerminalFactory tf;
            //(String) args.valueOf(OPT_TERMINALS)
            tf = TerminalManager.getTerminalFactory(terminal);
            CardTerminals terminals = tf.terminals();
            // List terminals if needed
            System.out.println("# Detected readers from " + tf.getProvider().getName());

            int number = 0;
            terminalList.clear();
            for (CardTerminal term : terminals.list()) {
                number++;
                terminalList.put(term.getName(), term);
                System.out.println((term.isCardPresent() ? "[*] " : "[ ] ") + term.getName());
            }

            if (number == 0) {
                System.out.println("no readers.");
                return false;
            }
        } catch (NoSuchAlgorithmException | CardException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Start", JOptionPane.INFORMATION_MESSAGE);
            Logger.getLogger(JCPlayStoreClient.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
