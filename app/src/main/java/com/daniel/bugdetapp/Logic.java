package com.daniel.bugdetapp;

import android.util.Log;

import androidx.lifecycle.LiveData;

import org.threeten.bp.LocalDate;

import java.math.BigDecimal;
import java.util.List;

public class Logic {

    public static boolean isWeekCorrect(String startingDay){
        LocalDate now = LocalDate.now();
        LocalDate mStartingDay = LocalDate.parse(startingDay);
        if (Logic.getWeekFromDate(now).equals(mStartingDay)){
            return true;
        } else {
            return false;
        }
    }

    private static LocalDate getWeekFromDate (LocalDate day){
        return day.minusDays(day.getDayOfWeek().getValue() -1);
    }

    private static LocalDate getCurrentWeekAsTime () {
        LocalDate now = LocalDate.now();
        return Logic.getWeekFromDate(now);
    }

    public static String getCurrentWeek() {
        return Logic.getCurrentWeekAsTime().toString();
    }

    public static String getNextWeek() {
        LocalDate start = LocalDate.parse(Logic.getCurrentWeekAsTime().toString());
        LocalDate end = start.plusDays(6); // el comando sql es inclusivo. No queremos incluir el lunes de la semana siguiente
        return end.toString();
    }

    public static BigDecimal getWeekBalance(List<Transaction> transactions){
        BigDecimal balance = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        if (transactions == null) {
            return balance;
        }
        else {
            for (int i = 0; i < transactions.size(); i++) {
                balance = balance.add(transactions.get(i).getQuantity().setScale(2, BigDecimal.ROUND_HALF_EVEN));
            }
            return balance.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        }
    }

    public static int getNumberOfDecimals(String s) {
        int index = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '.') {
                index = i;
                break;
            }
        }
        if (index == -1){
            return 0;
        }
        return s.length()-index-1;//in order to avoid a off by one error
    }
}
