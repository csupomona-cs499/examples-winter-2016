package cs499.cpp.edu.l07_gallery_network.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by yusun on 2/3/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhotoData {
    private PhotosRecord photos;

    public PhotosRecord getPhotos() {
        return photos;
    }

    public void setPhotos(PhotosRecord photos) {
        this.photos = photos;
    }
}
