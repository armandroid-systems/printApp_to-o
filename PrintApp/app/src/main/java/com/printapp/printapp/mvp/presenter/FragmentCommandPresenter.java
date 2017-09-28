
package com.printapp.printapp.mvp.presenter;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.printapp.printapp.fragments.FragmentCommand;
import com.printapp.printapp.mvp.view.FragmentCommandView;

public class FragmentCommandPresenter {
    private Activity activity;
    public static final int SELECT_PICTURE = 1;
    private FragmentCommandView view;

    public FragmentCommandPresenter(Activity activity) {
        this.activity = activity;
    }

    public void init(FragmentCommandView theView) {
        this.view = theView;
        setInitialScreen();
    }

    public void openGallery(FragmentCommand fc) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        fc.startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), SELECT_PICTURE);
    }

    public void setImagePreview(Uri uri) {
        view.showPreview(uri);
    }

    public void sendImageToDevice() {

    }

    public void connectWithDevice() {
        view.setUnavailableBtnConection();
        view.setUnavailableBtnGallery();

    }

    private void setInitialScreen() {
        view.hidePreview();
        view.setAvailableBtnConnection();
        view.setAvailableBtnGallery();
    }
}
