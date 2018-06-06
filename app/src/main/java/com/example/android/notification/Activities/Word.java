package com.example.android.notification.Activities;

/**
 * Created by Pragati Baheti on 15-04-2018.
 */

public class Word {
    private String mContactname;


    private String mdefname;


    private int contactnumber;
    private static final int NO_IMAGE_PROVIDED = -1;

    private int mImageResourceId = NO_IMAGE_PROVIDED;
    public Word(String  defaultTranslationId, String  miwokTranslationId, int audioResourceId) {
        mContactname = defaultTranslationId;
        mdefname = miwokTranslationId;
        contactnumber = audioResourceId;
    }
    public Word(String defaultTranslationId, String miwokTranslationId, int imageResourceId,
                int audioResourceId) {
        mContactname = defaultTranslationId;
        mdefname = miwokTranslationId;
        mImageResourceId = imageResourceId;

        contactnumber = audioResourceId;
    }
    public String getDefaultTranslationId() {
        return mContactname;
    }


    public String getMiwokTranslationId() {
        return mdefname;
    }




    public int getImageResourceId() {
        return mImageResourceId;
    }


    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getAudioResourceId() {
        return contactnumber;
    }

}
