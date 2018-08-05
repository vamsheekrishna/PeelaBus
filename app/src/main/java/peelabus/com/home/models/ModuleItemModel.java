package peelabus.com.home.models;

import java.io.Serializable;

import peelabus.com.R;

public class ModuleItemModel implements Serializable {
    public int mTitle;
    public int mImageID;

    public ModuleItemModel(int title, int imageID) {
        mTitle = title;
        mImageID = imageID;
    }

}
