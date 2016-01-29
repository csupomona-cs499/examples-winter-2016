package cs499.cpp.edu.l04_adapterviews;

/**
 * Created by yusun on 1/27/16.
 */
public class Friend {

    private String name;
    private int numFriends;
    private int profilePhotoId;

    public Friend(String name, int numFriends, int profilePhotoId) {
        this.name = name;
        this.numFriends = numFriends;
        this.profilePhotoId = profilePhotoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumFriends() {
        return numFriends;
    }

    public void setNumFriends(int numFriends) {
        this.numFriends = numFriends;
    }

    public int getProfilePhotoId() {
        return profilePhotoId;
    }

    public void setProfilePhotoId(int profilePhotoId) {
        this.profilePhotoId = profilePhotoId;
    }
}
