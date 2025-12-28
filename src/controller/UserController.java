///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//
//package controller;
//
//
//import dao.UserDao;
//import java.awt.HeadlessException;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.JOptionPane;
//import model.UserModel;
//import view.Registration;
///**
// *
// * @author A plus
// */
//public class UserController {
//
//    private final UserDao userDao = new UserDao();
//    private final Registration userView;
//
//    public UserController(Registration userView) {
//        this.userView = userView;
//        userView.addUserListner(new RegisterListener());
//    }
//
//    public void open() {
//        userView.setVisible(true);
//    }
//
//    public void close() {
//        userView.dispose();
//    }
//}