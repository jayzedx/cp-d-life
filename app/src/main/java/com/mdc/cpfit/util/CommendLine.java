package com.mdc.cpfit.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommendLine {

    public String CallCommand(String Cmd) throws IOException, InterruptedException {
        String Out = null;
        if (Cmd != null) {
            Process P = Runtime.getRuntime().exec(Cmd);

            P.waitFor();

            BufferedReader Output = new BufferedReader(new InputStreamReader(P.getInputStream()));
            if (Output.ready()) {
                ByteArrayOutputStream R = new ByteArrayOutputStream();
                int r;
                while ((r = Output.read()) != -1) {
                    R.write(r);
                }
                R.close();
                P.destroy();
                Out = R.toString();
            }
        }
        return Out;
    }

}
