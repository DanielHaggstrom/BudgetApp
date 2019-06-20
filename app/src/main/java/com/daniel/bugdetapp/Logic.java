package com.daniel.bugdetapp;

import android.app.Application;
import org.threeten.bp.*;
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

    public static float getWeekBalance(Application application, String weekStart){
        AppRepository mRepository = new AppRepository(application);
        LocalDate start = LocalDate.parse(weekStart);
        LocalDate end = start.plusDays(7);
        List<Transaction> mWeekTransactions = mRepository.getTransactionsFromWeek(weekStart, end.toString());
        float balance = 0;
        if (mWeekTransactions == null) {
            return 0;
        }
        else {
            for (int i = 0; i < mWeekTransactions.size(); i++) {
                balance += mWeekTransactions.get(i).getQuantity();
            }
            return balance;
        }
    }
}
