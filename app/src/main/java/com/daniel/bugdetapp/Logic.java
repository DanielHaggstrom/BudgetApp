package com.daniel.bugdetapp;

import android.app.Application;
import org.threeten.bp.*;
import java.util.List;
import androidx.lifecycle.LiveData;

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

    public static float getWeekBalance(Application application, String weekStart, float target){
        TransactionRepository mRepository = new TransactionRepository(application);
        LocalDate start = LocalDate.parse(weekStart);
        LocalDate end = start.plusDays(7);
        LiveData<List<Transaction>> mWeekTransactions = mRepository.getWeek(weekStart, end.toString());
        float balance = 0;
        if (mWeekTransactions.getValue() == null) {
            return target;
        }
        else {
            for (int i = 0; i < mWeekTransactions.getValue().size(); i++) {
                balance += mWeekTransactions.getValue().get(i).getQuantity();
            }
            return balance + target;
        }
    }
}
