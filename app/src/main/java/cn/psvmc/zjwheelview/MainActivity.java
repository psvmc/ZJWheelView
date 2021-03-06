package cn.psvmc.zjwheelview;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.psvmc.wheelview.ZJWheelItem;
import cn.psvmc.wheelview.ZJWheelView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "MainActivity";
    private String[] Months = new String[]{"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"};
    String[] months_big = {"1", "3", "5", "7", "8", "10", "12"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.main_show_dialog_btn).setOnClickListener(this);
        findViewById(R.id.main_show_dialog_btn2).setOnClickListener(this);


        ZJWheelView wva = (ZJWheelView) findViewById(R.id.main_wv);
        wva.setItemsNew(ZJWheelItem.listFromStringArray(Months));
        wva.setSelectIndex(2, false);
        wva.setOnWheelViewListener(new ZJWheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, ZJWheelItem item) {
                Log.d(TAG, "索引: " + selectedIndex + ", 值: " + item.text);
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.main_show_dialog_btn) {
            View outerView = LayoutInflater.from(this).inflate(R.layout.wheel_view, null);
            ZJWheelView wv = (ZJWheelView) outerView.findViewById(R.id.wheel_view_wv);
            wv.setOffset(2);
            wv.setItemsNew(ZJWheelItem.listFromStringArray(Months));
            wv.setSelectIndex(3, false);
            wv.setOnWheelViewListener(new ZJWheelView.OnWheelViewListener() {
                @Override
                public void onSelected(int selectedIndex, ZJWheelItem item) {
                    Log.d(TAG, "月份: " + item.text + ", 索引: " + selectedIndex);
                }
            });

            ZJWheelView wv2 = (ZJWheelView) outerView.findViewById(R.id.wheel_view_wv2);
            wv2.setOffset(2);
            List<ZJWheelItem> sexList = new ArrayList<>();
            sexList.add(new ZJWheelItem("0", "女"));
            sexList.add(new ZJWheelItem("1", "男"));
            wv2.setItemsNew(sexList);
            wv2.setOnWheelViewListener(new ZJWheelView.OnWheelViewListener() {
                @Override
                public void onSelected(int selectedIndex, ZJWheelItem item) {
                    Log.d(TAG, "性别: " + item.text + ", 对应的值: " + item.value);
                }
            });

            new AlertDialog.Builder(this)
                    .setTitle("弹窗选择")
                    .setView(outerView)
                    .setPositiveButton("确认", null)
                    .show();

        } else if (v.getId() == R.id.main_show_dialog_btn2) {
            View outerView = LayoutInflater.from(this).inflate(R.layout.wheel_view2, null);
            final ZJWheelView yearView, monthView, dayView;
            final ZJWheelView hourView, minuteView;

            yearView = (ZJWheelView) outerView.findViewById(R.id.wheel_view_year);
            monthView = (ZJWheelView) outerView.findViewById(R.id.wheel_view_month);
            dayView = (ZJWheelView) outerView.findViewById(R.id.wheel_view_day);
            hourView = (ZJWheelView) outerView.findViewById(R.id.wheel_view_hour);
            minuteView = (ZJWheelView) outerView.findViewById(R.id.wheel_view_minute);

            yearView.setOffset(2);
            yearView.setItemsNew(ZJWheelItem.listFromNum(2000, 2100));
            yearView.setSelectIndex(0, false);
            yearView.setOnWheelViewListener(new ZJWheelView.OnWheelViewListener() {
                @Override
                public void onSelected(int selectedIndex, ZJWheelItem item) {
                    jiaozhengDate(yearView, monthView, dayView);
                }
            });


            monthView.setOffset(2);
            monthView.setItemsNew(ZJWheelItem.listFromNum(1, 12));
            monthView.setSelectIndex(0, false);
            monthView.setOnWheelViewListener(new ZJWheelView.OnWheelViewListener() {
                @Override
                public void onSelected(int selectedIndex, ZJWheelItem item) {
                    jiaozhengDate(yearView, monthView, dayView);
                }
            });


            dayView.setOffset(2);
            dayView.setItemsNew(ZJWheelItem.listFromNum(1, 31));
            dayView.setOnWheelViewListener(new ZJWheelView.OnWheelViewListener() {
                @Override
                public void onSelected(int selectedIndex, ZJWheelItem item) {
                    jiaozhengDate(yearView, monthView, dayView);
                }
            });

            hourView.setOffset(2);
            hourView.setItemsNew(ZJWheelItem.listFromNum(0, 23));

            minuteView.setOffset(2);
            minuteView.setItemsNew(ZJWheelItem.listFromNum(0, 59));

            new AlertDialog.Builder(this)
                    .setTitle("日期选择")
                    .setView(outerView)
                    .setPositiveButton("确认", null)
                    .show();

        }

    }

    private void jiaozhengDate(ZJWheelView yearView, ZJWheelView monthView, ZJWheelView dayView) {
        try {
            Log.i(TAG, "yearView.getSeletedItem().value "+yearView.getSeletedItem().value);
            Log.i(TAG, "monthView.getSeletedItem().value "+monthView.getSeletedItem().value);
            Log.i(TAG, "dayView.getSeletedItem().value "+dayView.getSeletedItem().value);
            int year = Integer.valueOf(yearView.getSeletedItem().value);
            int month = Integer.valueOf(monthView.getSeletedItem().value);
            int day = Integer.valueOf(dayView.getSeletedItem().value);
            int maxDay = Integer.valueOf(dayView.getItemsOriginal().get(dayView.getItemsOriginal().size() - 1).value);
            Log.i(TAG, "maxDay "+maxDay);
            if (month == 2) {
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                    if (maxDay != 29) {
                        dayView.setItemsNew(ZJWheelItem.listFromNum(1, 29));
                        if (day > 29) {
                            dayView.setSelectValue("29", false);
                        }
                    }

                } else {
                    if (maxDay != 28) {
                        dayView.setItemsNew(ZJWheelItem.listFromNum(1, 28));
                        if (day > 28) {
                            dayView.setSelectValue("28", false);
                        }
                    }
                }

            } else if (Arrays.asList(months_big).contains(""+month)) {
                if (maxDay != 31) {
                    dayView.setItemsNew(ZJWheelItem.listFromNum(1, 31));
                    if (day > 31) {
                        dayView.setSelectValue("31", false);
                    }
                }
            } else {
                if (maxDay != 30) {
                    dayView.setItemsNew(ZJWheelItem.listFromNum(1, 30));
                    if (day > 30) {
                        dayView.setSelectValue("30", false);
                    }
                }
            }
        } catch (Exception e) {

            Log.i(TAG, "jiaozhengDate " + e.getMessage());
        }


    }
}
