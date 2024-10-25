package com.example.PetShop.models;


import jakarta.persistence.*;

@Entity
@Table(name = "BangPet")
public class LPetModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


private int Id;
private float Gia;
private String Loai;
private String Giong;
private String Mau;
private int Stt;


public LPetModel(int STT, String Loai, String Giong, String Mau, float Gia, int id) {
    this.Id = Id;
    this.Loai = Loai;
    this.Giong = Giong;
    this.Gia = Gia;
    this.Stt = Stt;
}

    public LPetModel() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public float getGia() {
        return Gia;
    }

    public void setGia(float gia) {
        Gia = gia;
    }

    public String getLoai() {
        return Loai;
    }

    public void setLoai(String loai) {
        Loai = loai;
    }

    public String getGiong() {
        return Giong;
    }

    public void setGiong(String giong) {
        Giong = giong;
    }

    public String getMau() {
        return Mau;
    }

    public void setMau(String mau) {
        Mau = mau;
    }

    public int getStt() {
        return Stt;
    }

    public void setStt(int stt) {
        Stt = stt;
    }
}
