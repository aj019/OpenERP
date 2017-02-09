package test.openerp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import test.openerp.R;
import test.openerp.models.HomeScreenEventItem;

/**
 * Created by Anshul Patro on 21-12-2015.
 */
public class HomeScreenGridAdapter extends RecyclerView.Adapter<HomeScreenGridAdapter.ViewHolder> {

    List<HomeScreenEventItem> mItems;

    public HomeScreenGridAdapter() {
        super();
        mItems = new ArrayList<HomeScreenEventItem>();
        HomeScreenEventItem categories = new HomeScreenEventItem();
        categories.setName("Calendar");
        categories.setThumbnail(R.drawable.calendar_icon);
        mItems.add(categories);

        categories  = new HomeScreenEventItem();
        categories.setName("Project");
        categories.setThumbnail(R.drawable.project_icon);
        mItems.add(categories);

        categories  = new HomeScreenEventItem();
        categories.setName("Timesheets");
        categories.setThumbnail(R.drawable.timesheet_icon);
        mItems.add(categories);

        categories  = new HomeScreenEventItem();
        categories.setName("Attendance");
        categories.setThumbnail(R.drawable.attendance_icon);
        mItems.add(categories);

        categories  = new HomeScreenEventItem();
        categories.setName("Leaves");
        categories.setThumbnail(R.drawable.leave_icon);
        mItems.add(categories);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.main_screen_grid, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        HomeScreenEventItem nature = mItems.get(i);
        viewHolder.title.setText(nature.getName());
        viewHolder.imgThumbnail.setImageResource(nature.getThumbnail());
    }

    @Override
    public int getItemCount() {

        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgThumbnail;
        public TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView)itemView.findViewById(R.id.main_img_thumbnail);
            title = (TextView)itemView.findViewById(R.id.maingridtitle);
        }
    }
}