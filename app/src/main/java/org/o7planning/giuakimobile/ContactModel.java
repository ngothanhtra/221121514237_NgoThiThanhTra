package org.o7planning.giuakimobile;



public class ContactModel {
    private int imageResId;
    private String name;
    private String phone;
    public ContactModel(int imageResId, String name, String phone) {
        this.imageResId = imageResId;
        this.name = name;
        this.phone = phone;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}