package com.mit.readingsdcard;

/**
 * Created by Mitaly on 6/30/2016.
 */
public class SdObjectsActOne {
    int icon;
    String folderName;

    public SdObjectsActOne(int icon, String folderName) {
        this.icon = icon;
        this.folderName = folderName;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
}
