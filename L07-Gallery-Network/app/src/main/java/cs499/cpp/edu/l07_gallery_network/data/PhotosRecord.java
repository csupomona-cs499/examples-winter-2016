package cs499.cpp.edu.l07_gallery_network.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by yusun on 2/3/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhotosRecord {
    private List<PhotoItem> photo;

    public List<PhotoItem> getPhoto() {
        return photo;
    }

    public void setPhoto(List<PhotoItem> photo) {
        this.photo = photo;
    }
}
