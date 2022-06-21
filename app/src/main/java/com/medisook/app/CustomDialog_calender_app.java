package com.medisook.app;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
//import com.applandeo.materialcalendarview.CalendarUtils;
//import com.applandeo.materialcalendarview.CalendarView;
//import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
//
//public class CustomDialog_calender_app extends AlertDialog {
//    private Context context;
////    MaterialCalendarView materialCalendarView;
//    private CalendarView calendarView;
//    public CustomDialog_calender_app(Context context) {
//        super(context);
//        this.context = context;
//    }
//    List<String> drugperiodList = Arrays.asList("2022-06-13", "2022-06-14");
//    String DATE_FORMAT = "yyyy-mm-dd";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.calender_pop);
//
//        //materialCalendarView = findViewById(R.id.calendarView);
////        materialCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
//        calendarView = (CalendarView) findViewById(R.id.calendarView);
////        final LocalDate min = getLocalDate("2022-01-01");
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2022, 6, 13);
//        try {
//            calendarView.setDate(calendar);
//        } catch (OutOfDateRangeException e) {
//            e.printStackTrace();
//        }
////        String[] result = {"2022,06,18", "2022,06,19", "2022,06,09", "2022,06,01"};
////        List<Calendar> calendars = new ArrayList<>();
//
////        calendarView.setSelectedDates(calendars);
//
////
////        new ApiSimulator(result).executeOnExecutor(Executors.newSingleThreadExecutor());
////
////        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
////            @Override
////            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
////                int Year = date.getYear();
////                int Month = date.getMonth() + 1;
////                int Day = date.getDay();
////
////                Log.i("Year test", Year + "");
////                Log.i("Month test", Month + "");
////                Log.i("Day test", Day + "");
////
////                String shot_Day = Year + "," + Month + "," + Day;
////
////                Log.i("shot_Day test", shot_Day + "");
////                materialCalendarView.clearSelection();
////                //Toast.makeText(setApplicationContext(), shot_Day , Toast.LENGTH_SHORT).show();
////            }
////        });
//    }
//
//    public void setDialogListener(CustomDialog.CustomDialogListener customDialogListener) {
//    }
////
////    //특정 날짜에 효과 표시 기능
////    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {
////
////        String[] Time_Result;
////
////        ApiSimulator(String[] Time_Result) {
////            this.Time_Result = Time_Result;
////        }
////
////        @Override
////        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
////            try {
////                Thread.sleep(500);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
////
////            Calendar calendar = Calendar.getInstance();
////            ArrayList<CalendarDay> dates = new ArrayList<>();
////
////            /*특정날짜 달력에 점표시해주는곳*/
////            /*월은 0이 1월 년,일은 그대로*/
////            //string 문자열인 Time_Result 을 받아와서 ,를 기준으로짜르고 string을 int 로 변환
////            for (int i = 0; i < Time_Result.length; i++) {
////                //CalendarDay day = CalendarDay.from(calendar);
////
////                String[] time = Time_Result[i].split(",");
////
////                int year = Integer.parseInt(time[0]);
////                int month = Integer.parseInt(time[1]);
////                int dayy = Integer.parseInt(time[2]);
////
////                calendar.set(year, month, dayy);
//////                CalendarDay day = CalendarDay.from(calendar);
//////                dates.add(day);
//////                //calendar.add(Calendar.DATE, 5);
//////                Log.d("캘린더1", dates.toString());
//////                Log.d("캘린더2", day.toString());
////            }
////            return dates;
////            //Log.d("캘린더3", dates);
////        }
//
//
////        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
////            super.onPostExecute(calendarDays);
////            Log.d("캘린더", "들어오나");
////            //materialCalendarView.addDecorator(new EventDecorator(Color.GREEN, calendarDays,MainActivity.this));
////            materialCalendarView.addDecorator(new EventDecorator(Color.GREEN, calendarDays, CustomDialog_calender.this));
////        }
//    }
