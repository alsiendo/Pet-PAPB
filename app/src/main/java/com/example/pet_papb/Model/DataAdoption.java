package com.example.pet_papb.Model;

public class DataAdoption {
    private String gambarHewan;
    private String namaHewan;
    private String alamatHewan;
    private String id;

    public DataAdoption(){

    }

    public DataAdoption(String gambarHewan, String namaHewan, String alamathewan, String id) {
        this.gambarHewan = gambarHewan;
        this.namaHewan = namaHewan;
        this.alamatHewan = alamathewan;
        this.id = id;
    }

    public String getGambarHewan() {
        return gambarHewan;
    }

    public void setGambarHewan(String gambarHewan) {
        this.gambarHewan = gambarHewan;
    }

    public String getNamaHewan() {
        return namaHewan;
    }

    public void setNamaHewan(String namaHewan) {
        this.namaHewan = namaHewan;
    }

    public String getAlamathewan() {
        return alamatHewan;
    }

    public void setAlamathewan(String alamathewan) {
        this.alamatHewan = alamathewan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
