package com.blogspot.hongthaiit.slidemenu;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hong Thai.
 */
public class MenuFragment extends Fragment {

    private View rootView;
    private ListView listView;
    private ArrayList<SlidingMenuItem> listMenuItems;
    private final static String TAG = "MenuFragment";

    public static Fragment newInstance() {
        MenuFragment fragment = new MenuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create menu items
        listMenuItems = new ArrayList<SlidingMenuItem>();
        listMenuItems.add(new SlidingMenuItem(R.mipmap.mercury, "Mecury"));
        listMenuItems.add(new SlidingMenuItem(R.mipmap.venus, "Venus"));
        listMenuItems.add(new SlidingMenuItem(R.mipmap.earth, "Earth"));
        listMenuItems.add(new SlidingMenuItem(R.mipmap.mars, "Mars"));
        listMenuItems.add(new SlidingMenuItem(R.mipmap.jupiter, "Jupiter"));
        listMenuItems.add(new SlidingMenuItem(R.mipmap.saturn, "Saturn"));
        listMenuItems.add(new SlidingMenuItem(R.mipmap.uranus, "Uranus"));
        listMenuItems.add(new SlidingMenuItem(R.mipmap.neptune, "Neptune"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        listView = (ListView) rootView.findViewById(R.id.list);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListViewAdapter();
    }

    private void setListViewAdapter() {
        SlidingMenuAdapter adapter = new SlidingMenuAdapter(getActivity(), R.layout.item_menu, listMenuItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClickListener());
        Log.i(TAG, "create adapter " + listMenuItems.size());
    }

    /**
     * Go to fragment when menu item clicked!
     *
     * @return
     */
    private AdapterView.OnItemClickListener onItemClickListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SlidingMenuItem item = (SlidingMenuItem) parent.getItemAtPosition(position);
                ((SlidingActivity) getActivity()).transactionFragments(PageFragment.newInstance(item.getMenuItemName()),
                        R.id.container);
            }
        };
    }

    /**
     * private class to make a listview adapter based on ArrayAdapter
     */
    private class SlidingMenuAdapter extends ArrayAdapter<SlidingMenuItem> {

        private FragmentActivity activity;
        private ArrayList<SlidingMenuItem> items;

        public SlidingMenuAdapter(FragmentActivity activity, int resource, ArrayList<SlidingMenuItem> objects) {
            super(activity, resource, objects);
            this.activity = activity;
            this.items = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            LayoutInflater inflater = (LayoutInflater) activity
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            // If holder not exist then locate all view from UI file.
            if (convertView == null) {
                // inflate UI from XML file
                convertView = inflater.inflate(R.layout.item_menu, parent, false);
                // get all UI view
                holder = new ViewHolder(convertView);
                // set tag for holder
                convertView.setTag(holder);
            } else {
                // if holder created, get tag from view
                holder = (ViewHolder) convertView.getTag();
            }

            holder.itemName.setText(getItem(position).getMenuItemName());
            holder.itemImage.setImageResource(getItem(position).getImageResource());

            return convertView;
        }

        private class ViewHolder {
            private TextView itemName;
            private ImageView itemImage;

            public ViewHolder(View v) {
                itemName = (TextView) v.findViewById(R.id.name);
                itemImage = (ImageView) v.findViewById(R.id.image);
            }
        }
    }
}