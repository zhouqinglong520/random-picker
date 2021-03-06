package com.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.common.callbacks.IOCallback;

/**
 * Created by Andy-Super on 2018/12/25.
 */
public class MyFileWriter {
    private FileOutputStream fop;
    public MyFileWriter(String path, String fileName){
        File direction = new File(path);
        if(!direction.exists()){
            direction.mkdir();
        }
        String filePath = direction.getAbsolutePath() + File.separator + fileName;
        try {
            fop = new FileOutputStream(filePath);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public MyFileWriter write(String bufferString){
        try {
            fop.write(bufferString.getBytes());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return this;
    }

    public MyFileWriter writeLine(String bufferString){
        try {
            fop.write((bufferString + compatibleWrapString()).getBytes());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return this;
    }

    public void end(){
        try {
            fop.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void end(IOCallback endCb){
        try {
            fop.close();
            endCb.entries((byte)-1);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private String osName = System.getProperties().getProperty("os.name");

    private boolean isWindows(){
        return osName.contains("Windows");
    }

    private String compatibleWrapString(){
        byte[] result;
        if(isWindows()) {
            result = new byte[1];
            result[0] = (byte)0x0A;
        }
        else {
            result = new byte[2];
            result[0] = (byte)0x0D;
            result[1] = (byte)0x0A;
        }
        return new String(result);
    }

}
