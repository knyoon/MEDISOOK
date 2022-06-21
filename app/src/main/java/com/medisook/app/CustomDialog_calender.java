package com.medisook.app;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateLongClickListener;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.threeten.bp.LocalDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;

public class CustomDialog_calender extends AlertDialog {
    private Context context;
    MaterialCalendarView materialCalendarView;
    int pink = 0;
    TextView show_drug;
    public CustomDialog_calender(Context context) {
        super(context);
        this.context = context;
    }
    final List<String> DateList = Arrays.asList("22/06/15", "22/06/17", "22/06/18");
    final String DATE_FORMAT = "yy/MM/dd";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender_pop);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //materialCalendarView = findViewById(R.id.calendarView);
        materialCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        show_drug = (TextView) findViewById(R.id.show_drug);
        final LocalDate min = getLocalDate("2022-01-01");
        final LocalDate max = getLocalDate("2022-12-31");
        materialCalendarView.state().edit().setMinimumDate(min).setMaximumDate(max);
        setEvent(DateList);
        materialCalendarView.setOnDateLongClickListener(new OnDateLongClickListener() {
            @Override
            public void onDateLongClick(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date) {
                String select = widget.getSelectedDates().toString();
                Log.d("달력", "리스너 테스트  : " + select+date);
                show_drug.setText(date.getDate().toString());
            }
        });
//        String[] result = {"2022,06,18", "2022,06,19", "2022,06,09", "2022,06,01"};

//        new ApiSimulator(result).executeOnExecutor(Executors.newSingleThreadExecutor());

//        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
//            @Override
//            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
//                int Year = date.getYear();
//                int Month = date.getMonth() + 1;
//                int Day = date.getDay();
//
//                Log.i("Year test", Year + "");
//                Log.i("Month test", Month + "");
//                Log.i("Day test", Day + "");
//
//                String shot_Day = Year + "," + Month + "," + Day;
//
//                Log.i("shot_Day test", shot_Day + "");
//                materialCalendarView.clearSelection();
//                //Toast.makeText(setApplicationContext(), shot_Day , Toast.LENGTH_SHORT).show();
//            }
//        });

//    }



            //특정 날짜에 효과 표시 기능
//    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {
//
//        String[] Time_Result;
//
//        ApiSimulator(String[] Time_Result) {
//            this.Time_Result = Time_Result;
//        }
//
//        @Override
//        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            Calendar calendar = Calendar.getInstance();
//            ArrayList<CalendarDay> dates = new ArrayList<>();
//
//            /*특정날짜 달력에 점표시해주는곳*/
//            /*월은 0이 1월 년,일은 그대로*/
//            //string 문자열인 Time_Result 을 받아와서 ,를 기준으로짜르고 string을 int 로 변환
//            for (int i = 0; i < Time_Result.length; i++) {
//                //CalendarDay day = CalendarDay.from(calendar);
//
//                String[] time = Time_Result[i].split(",");
//
//                int year = Integer.parseInt(time[0]);
//                int month = Integer.parseInt(time[1]);
//                int dayy = Integer.parseInt(time[2]);
//
//                calendar.set(year, month, dayy);
////                CalendarDay day = CalendarDay.from(calendar);
////                dates.add(day);
////                //calendar.add(Calendar.DATE, 5);
////                Log.d("캘린더1", dates.toString());
////                Log.d("캘린더2", day.toString());
//            }
//            return dates;
//            //Log.d("캘린더3", dates);
//        }
//

//        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
//            super.onPostExecute(calendarDays);
//            Log.d("캘린더", "들어오나");
//            //materialCalendarView.addDecorator(new EventDecorator(Color.GREEN, calendarDays,MainActivity.this));
//            materialCalendarView.addDecorator(new EventDecorator(Color.GREEN, calendarDays, CustomDialog_calender.this));
//        }

            //    }

//        });
    }
    void setEvent(List<String> dateList) {
        List<LocalDate> localDateList = new ArrayList<>();
        for (String string : dateList) {
            Log.d("달력", "test"+string);
            LocalDate calendar = getLocalDate(string);
            if (calendar != null) {
                localDateList.add(calendar);
            }
        }
        List<CalendarDay> datesLeft = new ArrayList<>();
        List<CalendarDay> datesCenter = new ArrayList<>();
        List<CalendarDay> datesRight = new ArrayList<>();
        List<CalendarDay> datesIndependent = new ArrayList<>();

        for (LocalDate localDate : localDateList) {

            boolean right = false;
            boolean left = false;

            for (LocalDate day1 : localDateList) {


                if (localDate.isEqual(day1.plusDays(1))) {
                    left = true;
                }
                if (day1.isEqual(localDate.plusDays(1))) {
                    right = true;
                }
            }

            if (left && right) {
                datesCenter.add(CalendarDay.from(localDate));
            } else if (left) {
                datesLeft.add(CalendarDay.from(localDate));
            } else if (right) {
                datesRight.add(CalendarDay.from(localDate));
            } else {
                datesIndependent.add(CalendarDay.from(localDate));
            }
        }
        setDecor(datesCenter, R.drawable.radius_navy);
        setDecor(datesLeft, R.drawable.radius_navy);
        setDecor(datesRight, R.drawable.radius_navy);
        setDecor(datesIndependent, R.drawable.radius_navy);
    }

    void setDecor(List<CalendarDay> calendarDayList, int drawable) {
        materialCalendarView.addDecorators(new EventDecorator(this.context, drawable, calendarDayList));
    }

    public void setDialogListener(CustomDialog.CustomDialogListener customDialogListener) {
    }
    LocalDate getLocalDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        try {
            Date input = sdf.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(input);
            return LocalDate.of(cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH) + 1,
                    cal.get(Calendar.DAY_OF_MONTH));


        } catch (NullPointerException e) {
            return null;
        } catch (ParseException e) {
            return null;
        }
    }
}
