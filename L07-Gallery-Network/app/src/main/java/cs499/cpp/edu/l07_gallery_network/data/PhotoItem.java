package cs499.cpp.edu.l07_gallery_network.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by yusun on 2/3/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhotoItem {

    private String id;
    private String title;
    private String url_s;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl_s() {
        return url_s;
    }

    public void setUrl_s(String url_s) {
        this.url_s = url_s;
    }
}
