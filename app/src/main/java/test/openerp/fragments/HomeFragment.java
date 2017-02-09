package test.openerp.fragments;

import android.content.Context;
import android.content.Intent;
import android.location.Geocoder;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.openerp.MainActivity;
import test.openerp.R;
import test.openerp.activities.AttendanceActivity;
import test.openerp.activities.CalendarActivity;
import test.openerp.activities.LeaveActivity;
import test.openerp.activities.ProjectActivity;
import test.openerp.activities.TimesheetsActivity;
import test.openerp.adapters.HomeScreenGridAdapter;

public class HomeFragment extends Fragment {

    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Home");

        mRecyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);

        mRecyclerView.setHasFixedSize(true);

        // The number of Columns
        mLayoutManager = new GridLayoutManager(getActivity(), 4);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new HomeScreenGridAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecylerTouchListener(getActivity(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View v, int position) {

                if(position == 0){
                    Intent i = new Intent(getActivity(),CalendarActivity.class);
                    startActivity(i);
                } else if(position==1){
                    Intent i = new Intent(getActivity(),ProjectActivity.class);
                    startActivity(i);
                } else if(position==2){
                    Intent i = new Intent(getActivity(),TimesheetsActivity.class);
                    startActivity(i);
                } else if(position==3){
                    Intent i = new Intent(getActivity(),AttendanceActivity.class);
                    startActivity(i);
                } else if(position==4){
                    Intent i = new Intent(getActivity(),LeaveActivity.class);
                    startActivity(i);
                }
            }


        }));
        return v;
    }

    private class RecylerTouchListener implements RecyclerView.OnItemTouchListener {
        ClickListener clickListener;
        GestureDetector gestureDetector;

        public RecylerTouchListener(Context c, RecyclerView recyclerView, ClickListener clickListener) {
            this.clickListener=clickListener;
            gestureDetector =new GestureDetector(c,new GestureDetector.SimpleOnGestureListener()
            {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null&&clickListener!=null && gestureDetector.onTouchEvent(e))
            {
                clickListener.onClick(child,rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
    public static interface ClickListener
    {
        public void onClick(View v, int position);
    }

}
