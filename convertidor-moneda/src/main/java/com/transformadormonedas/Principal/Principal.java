package com.transformadormonedas.Principal;

import com.transformadormonedas.Api.ExchangeAPI;
import com.transformadormonedas.Modelos.Moneda;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        boolean exit = false;


        ExchangeAPI exchangeAPI = new ExchangeAPI("61297f6d735adf6083ab85fb");

        while (!exit) {
            System.out.println("Menú de Conversión de Monedas");
            System.out.println("1. Convertir USD a MXN");
            System.out.println("2. Convertir EUR a MXN");
            System.out.println("3. Convertir MXN a USD");
            System.out.println("4. Convertir MXN a EUR");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            int opcion = lectura.nextInt();

            try {
                switch (opcion) {
                    case 1:
                        System.out.print("Ingresa la cantidad de dólares: ");
                        double montoUSD = lectura.nextDouble();
                        double montoMxnFromUsd = exchangeAPI.convertir(Moneda.USD, Moneda.MXN, montoUSD);
                        System.out.println(montoUSD + " dólares es igual a " + montoMxnFromUsd + " pesos mexicanos.");
                        break;
                    case 2:
                        System.out.print("Ingresa la cantidad de euros: ");
                        double montoEUR = lectura.nextDouble();
                        double montoMxnFromEur = exchangeAPI.convertir(Moneda.EUR, Moneda.MXN, montoEUR);
                        System.out.println(montoEUR + " euros es igual a " + montoMxnFromEur + " pesos mexicanos.");
                        break;
                    case 3:
                        System.out.print("Ingresa la cantidad de pesos mexicanos: ");
                        double montoMXN = lectura.nextDouble();
                        double montoUsdFromMxn = exchangeAPI.convertir(Moneda.MXN, Moneda.USD, montoMXN);
                        System.out.println(montoMXN + " pesos mexicanos es igual a " + montoUsdFromMxn + " dólares.");
                        break;
                    case 4:
                        System.out.print("Ingresa la cantidad de pesos mexicanos: ");
                        double montoMXN2 = lectura.nextDouble();
                        double montoEurFromMxn = exchangeAPI.convertir(Moneda.MXN, Moneda.EUR, montoMXN2);
                        System.out.println(montoMXN2 + " pesos mexicanos es igual a " + montoEurFromMxn + " euros.");
                        break;
                    case 5:
                        exit = true;
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, intenta de nuevo.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        lectura.close();
    }
}