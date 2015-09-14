package young.com.customview_viewpager;

/**
 * Created by yw on 2015/9/11.
 */
public class AdInfo {
    private int imageId;
    private String desc;

    public AdInfo(int imageId, String desc) {
        this.imageId = imageId;
        this.desc = desc;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
