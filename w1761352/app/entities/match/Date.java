package entities.match;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
* Class that handle Date data
*/
public class Date implements Serializable {

    private static final int maxDay_Range = 30;
    private static final int maxYear_Range = 2020;
    private static final int maxMonth_Range = 12;

    private List<Integer> months = new ArrayList<>();
    private List<Integer> num = new ArrayList<>();
    private List<Integer> years = new ArrayList<>();

    public Date() {
        initDates();
    }

    public List<Integer> getNum() {
        return num;
    }

    public void setNum(List<Integer> num) {
        this.num = num;
    }

    public List<Integer> getYears() {
        return years;
    }

    public void setYears(List<Integer> years) {
        this.years = years;
    }

    public List<Integer> getMonths() {
        return months;
    }

    public void setMonths(List<Integer> months) {
        this.months = months;
    }

    public boolean isValidDay(int day) {
        int key = 0;

        if (num.contains(day)) {
            if (day <= maxDay_Range && day > 0) {
                key++;
            }
        }
        return key > 0;
    }

    public boolean isValidMonth(int str) {
        int key = 0;

        if (str <= maxMonth_Range && str > 0) {
            key++;
        }

        return key > 0;
    }

    public boolean isValidYear(int str) {
        int key = 0;
        if (years.contains(str)) {
            if (str <= maxYear_Range) {
                key++;
            }
        }
        return key > 0;
    }

    private void initDates() {
        for (int i = 0; i < 31; i++) {
            num.add(i);
        }
        for (int i = 1986; i < 2022; i++) {
            years.add(i);
        }
    }
}
