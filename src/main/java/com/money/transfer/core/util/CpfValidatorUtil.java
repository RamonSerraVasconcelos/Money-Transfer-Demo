package com.money.transfer.core.util;

import java.util.InputMismatchException;

public class CpfValidatorUtil {

    public static boolean validate(String cpf) {
        if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222") ||
                cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888") ||
                cpf.equals("99999999999")) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = cpf.charAt(i) - '0';
            sum += digit * (10 - i);
        }
        int remainder = sum % 11;
        int firstDigit = (remainder < 2) ? 0 : (11 - remainder);

        sum = 0;
        for (int i = 0; i < 10; i++) {
            int digit = cpf.charAt(i) - '0';
            sum += digit * (11 - i);
        }
        remainder = sum % 11;
        int secondDigit = (remainder < 2) ? 0 : (11 - remainder);

        return (firstDigit == cpf.charAt(9) - '0') && (secondDigit == cpf.charAt(10) - '0');
    }
}
