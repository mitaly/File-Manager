package com.mit.readingsdcard;

/**
 * Created by Mitaly on 6/30/2016.
 */
public class SdObjects {
    int icon;
    String fileName;
    String absolutePath;

    public SdObjects(int icon, String fileName,String absolutePath) {
        this.icon = icon;
        this.fileName = fileName;
        this.absolutePath=absolutePath;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
