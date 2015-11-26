package com.blogspot.hongthaiit.slidemenu;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PageFragment extends Fragment {
    private static final String TAG = "PageFragment";
    private String fragmentName;
    private View root;

    public static PageFragment newInstance(String fragmentName) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putString("fragment_name", fragmentName);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fragmentName = getArguments().getString("fragment_name");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_page, container, false);

        //locate view element and setting data
        TextView textView = (TextView)root.findViewById(R.id.fragment_name);
        textView.setText(fragmentName);

        return  root;
    }
}
