package com.mdc.cpfit.util.listener;

import android.os.Build;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

public class cpuInfo implements FileFilter
{

    @Override
    public boolean accept(File pathname) {
        if( pathname != null ){
            String FileName =  pathname.getName();
            if( FileName != null ){
                if(Pattern.matches("cpu[0-9]+", FileName)) {
                    return true;
                }
            }
        }
        return false;
    }

    private int getCpuCoreFromSystemCMD() {
        int CpuCore;
        try {
            File dir = new File("/sys/devices/system/cpu/");
            if( dir != null ){
                File[] files = dir.listFiles(this);
                /**
                 * Can Get Cpu Core
                 */
                CpuCore = files.length;
            }else{
                /**
                 * Default Cpu Core if Cannot Read From System
                 */
                CpuCore = 2;
            }
        } catch(Exception e) {
            /**
             * Default Cpu Core if Cannot Read From System
             */
            CpuCore = 2;
        }
        return CpuCore;
    }

    public int getTotalCpuCore(){
        int TotalCpuCore = 0;
        if(Build.VERSION.SDK_INT >= 17) {
            Runtime RT =  Runtime.getRuntime();
            if( RT != null ){
                TotalCpuCore = RT.availableProcessors();
            }
        }
        if( TotalCpuCore < 1 ){
            TotalCpuCore = getCpuCoreFromSystemCMD();
        }
        return TotalCpuCore < 1 ? 2 : TotalCpuCore;
    }
}
