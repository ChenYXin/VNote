package com.donkor.demo.japanesestyle.vnote.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.donkor.demo.japanesestyle.vnote.R;

/**
 * 笔记页面
 * Created by donkor
 */
public class NoteFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, null);
        return view;
    }
}