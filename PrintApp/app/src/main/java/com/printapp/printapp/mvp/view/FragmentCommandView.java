package com.printapp.printapp.mvp.view;


import android.net.Uri;

public interface FragmentCommandView {

    void setAvailableBtnConnection();
    void setUnavailableBtnConection();
    void setAvailableBtnGallery();
    void setUnavailableBtnGallery();
    void showPreview(Uri uri);
    void hidePreview();

}
