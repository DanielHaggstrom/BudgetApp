package com.daniel.bugdetapp;

import android.util.Log;

import androidx.lifecycle.LiveData;

import org.threeten.bp.LocalDate;

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

    public static float getWeekBalance(List<Transaction> transactions){
        float balance = 0;
        if (transactions == null) {
            return 0;
        }
        else {
            for (int i = 0; i < transactions.size(); i++) {
                balance += transactions.get(i).getQuantity();
            }
            return balance;
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
