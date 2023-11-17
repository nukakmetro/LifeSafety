package com.example.lifesafetysem;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {
    public static String HashingPaassword(String password) {
        try {
            // Создание экземпляра MessageDigest с алгоритмом SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            if(password != null) {
                md.update(password.getBytes());

                // Вычисление хэш-значения
                byte[] hashedPassword = md.digest();

                // Преобразование байтового хэша в строку в шестнадцатеричном формате
                StringBuilder hexString = new StringBuilder();
                for (byte b : hashedPassword) {
                    String hex = Integer.toHexString(0xff & b);
                    if (hex.length() == 1) {
                        hexString.append('0');
                    }
                    hexString.append(hex);
                }
                return hexString.toString();
            }
            return null;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}





