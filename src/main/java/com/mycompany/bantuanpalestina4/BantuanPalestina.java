package com.mycompany.bantuanpalestina4;

import controller.DanaController;
import controller.LogistikController;
import dao.LembagaDAO;
import model.Lembaga;
import view.BantuanView;

import java.util.ArrayList;
import java.util.Scanner;

public class BantuanPalestina {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        LembagaDAO lembagaDAO = new LembagaDAO();
        ArrayList<Lembaga> listLembaga = lembagaDAO.tampilSemuaLembaga();

        DanaController danaController = new DanaController(scanner, listLembaga);
        LogistikController logistikController = new LogistikController(scanner, listLembaga);
        
        BantuanView view = new BantuanView(scanner, danaController, logistikController);
        view.tampilkanMenuUtama();
    }
}