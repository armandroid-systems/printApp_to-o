package com.printapp.printapp.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.printapp.printapp.R;
import com.printapp.printapp.mvp.presenter.FragmentCommandPresenter;
import com.printapp.printapp.mvp.view.FragmentCommandView;

public class FragmentCommand extends Fragment implements View.OnClickListener, FragmentCommandView {
    private final static int RESULT_OK = -1;

    private EditText editIp;
    private Button btnSend;
    private Button btnSelect;
    private ImageView preview;
    private FragmentCommandPresenter presenter;

    public static FragmentCommand init() {
        FragmentCommand fragment = new FragmentCommand();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_command, container, false);

        presenter = new FragmentCommandPresenter(getActivity());
        presenter.init(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        editIp = (EditText) view.findViewById(R.id.connection);
        btnSelect =(Button) view.findViewById(R.id.btnImage);
        btnSend = (Button) view.findViewById(R.id.btnSend);
        preview = (ImageView) view.findViewById(R.id.imagPreview);

        btnSelect.setOnClickListener(this);
        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnImage:
                presenter.openGallery(this);
                break;
            case R.id.btnSend:
                presenter.connectWithDevice();
                break;
            default:
        }
    }

    @Override
    public void setAvailableBtnConnection() {
        btnSend.setEnabled(true);
    }

    @Override
    public void setUnavailableBtnConection() {
        btnSend.setEnabled(false);
    }

    @Override
    public void setAvailableBtnGallery() {
        btnSelect.setEnabled(true);
    }

    @Override
    public void setUnavailableBtnGallery() {
        btnSelect.setEnabled(false);
    }

    @Override
    public void showPreview(Uri uri) {
        preview.setVisibility(View.VISIBLE);
        preview.setImageURI(uri);
    }

    @Override
    public void hidePreview() {
        preview.setVisibility(View.GONE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == FragmentCommandPresenter.SELECT_PICTURE) {
                presenter.setImagePreview(data.getData());
            }
        }
    }
}
