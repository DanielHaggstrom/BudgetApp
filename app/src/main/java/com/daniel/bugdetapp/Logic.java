package com.daniel.bugdetapp;

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

    public static float getWeekBalance(TransactionViewModel mTransactionViewModel, String weekStart){
        LocalDate start = LocalDate.parse(weekStart);
        LocalDate end = start.plusDays(7);
        LiveData<List<Transaction>> mWeekTransactions = mTransactionViewModel.getWeek(weekStart, end.toString());
        float balance = 0;
        if (mWeekTransactions.getValue() == null) {
            return 0;
        }
        else {
            for (int i = 0; i < mWeekTransactions.getValue().size(); i++) {
                balance += mWeekTransactions.getValue().get(i).getQuantity();
            }
            return balance;
        }
    }
}
