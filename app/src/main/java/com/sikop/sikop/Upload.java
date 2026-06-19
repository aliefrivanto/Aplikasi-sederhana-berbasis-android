package com.sikop.sikop;

public class Upload {
    private String nama;
    private String imageUrl;

    public Upload(){

    }
    public Upload(String nama, String imageUrl){
        if (nama.trim().equals("")){
            nama = "No Nama";
        }
        this.nama = nama;
        this.imageUrl = imageUrl;
    }
    public String getNama(){
        return nama;
    }
    public void setNama(String nama){
        this.nama = nama;
    }
    public String getImageUrl(){
        return imageUrl;
    }
    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }
}

