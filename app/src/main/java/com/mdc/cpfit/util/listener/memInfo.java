package com.mdc.cpfit.util.listener;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class memInfo {

    public double getFreeMemory() {
        return getFreeMemory(null);
    }

    public double getFreeMemory(Context Con) {
        double AvailableMemory;
        if (Build.VERSION.SDK_INT >= 16) {
            try {
                Runtime info = Runtime.getRuntime();
                if (info != null) {
                    AvailableMemory = info.freeMemory();
                } else {
                    AvailableMemory = 0L;
                }
            } catch (Exception e) {
                e.printStackTrace();
                AvailableMemory = 0L;
            }
            if (AvailableMemory <= 0L && Con != null) {
                ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
                ActivityManager activityManager = (ActivityManager) Con.getSystemService(Con.ACTIVITY_SERVICE);
                activityManager.getMemoryInfo(mi);
                AvailableMemory = mi.availMem / 1048576L;
            }
        } else {
            AvailableMemory = 0L;
        }
        return AvailableMemory;
    }

    public double getTotalMemory() {

        RandomAccessFile reader;
        String load;
//        DecimalFormat twoDecimalForm = new DecimalFormat("#.##");
//        String lastValue = "";
        try {
            reader = new RandomAccessFile("/proc/meminfo", "r");
            if( reader != null ){
                load = reader.readLine();

                Pattern p = Pattern.compile("(\\d+)");
                Matcher m = p.matcher(load);
                String value = "";

                while (m.find()) {
                    value = m.group(1);
                }
                reader.close();

                double totRam = Double.parseDouble(value);
                // totRam = totRam / 1024;
                double mb = totRam / 1024.0;
//            double gb = totRam / 1048576.0;
//            double tb = totRam / 1073741824.0;

//            if (tb > 1) {
//                lastValue = twoDecimalForm.format(tb).concat(" TB");
//            } else if (gb > 1) {
//                lastValue = twoDecimalForm.format(gb).concat(" GB");
//            } else if (mb > 1) {
//            lastValue = twoDecimalForm.format(mb).concat(" MB");
//            } else {
//                lastValue = twoDecimalForm.format(totRam).concat(" KB");
//            }

                return mb;
            }


        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}
