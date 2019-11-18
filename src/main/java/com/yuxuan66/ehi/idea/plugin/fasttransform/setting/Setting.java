package com.yuxuan66.ehi.idea.plugin.fasttransform.setting;

import javax.swing.*;

public class Setting extends JDialog {
    private JPanel contentPane;
    private JTextField textField1;

    public JPanel getContentPane(){
        return contentPane;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public Setting() {
        setContentPane(contentPane);
        setModal(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    }



}
