package test.openerp.models;

/**
 * Created by Anshul Patro on 21-12-2015.
 */
public class HomeScreenEventItem {
    private String mName;
    private int mThumbnail;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public int getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.mThumbnail = thumbnail;
    }

}