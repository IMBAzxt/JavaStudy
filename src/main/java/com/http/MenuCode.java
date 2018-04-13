/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.http;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MenuCode {

    public static void main(String args[]) {
        JFrame win = new JFrame("浏览器");

        //创建并添加菜单栏
        JMenuBar menuBar = new JMenuBar();
        win.setJMenuBar(menuBar);

        //创建并添加各菜单，注意：菜单的快捷键是同时按下Alt键和字母键，方法setMnemonic('F')是设置快捷键为Alt +Ｆ
        JMenu menuFile = new JMenu("文件(F)"), menuEdit = new JMenu("编辑(E)"), menuView = new JMenu("查看(V)");
        menuFile.setMnemonic('F');
        menuEdit.setMnemonic('E');
        menuView.setMnemonic('V');
        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuView);

        //添加“文件”菜单的各菜单项
        JMenu itemOpen = new JMenu("打开");
        itemOpen.setMnemonic('O');
        JMenuItem itemOpen1 = new JMenuItem("打开x");
        JMenuItem itemOpen2 = new JMenuItem("打开y");
        itemOpen.add(itemOpen1);
        itemOpen.add(itemOpen2);
        JMenuItem itemSave = new JMenuItem("保存");
        itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        menuFile.add(itemOpen);
        menuFile.add(itemSave);

        //添加“编辑”菜单的各菜单项
        JMenuItem itemCopy = new JMenuItem("复制");
        itemCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        menuEdit.add(itemCopy);

        //添加“查看”菜单的各菜单项
        JMenuItem itemStop = new JMenuItem("停止"), itemRefresh = new JMenuItem("刷新");
        itemStop.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        itemRefresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
        menuView.add(itemStop);
        menuView.add(itemRefresh);

        win.setSize(400, 300);
        win.setLocation(250, 250);
        win.setVisible(true);

        win.validate();
        win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
