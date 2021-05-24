package com.example.pet_papb.Model;

public class DataAdoption {
    private String gambarHewan;
    private String namaHewan;
    private String umurHewan;
    private String lokasiHewan;
    private String jenisHewan;
    private String deskripsiHewan;
    private String idPemilik;
    private String statusKepemilikan;

    public DataAdoption(){

    }

    public DataAdoption(String gambarHewan, String namaHewan, String umurHewan, String lokasiHewan, String jenisHewan, String deskripsiHewan, String idPemilik, String statusKepemilikan) {
        this.gambarHewan = gambarHewan;
        this.namaHewan = namaHewan;
        this.umurHewan = umurHewan;
        this.lokasiHewan = lokasiHewan;
        this.jenisHewan = jenisHewan;
        this.deskripsiHewan = deskripsiHewan;
        this.idPemilik = idPemilik;
        this.statusKepemilikan = statusKepemilikan;
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

    public String getUmurHewan() {
        return umurHewan;
    }

    public void setUmurHewan(String umurHewan) {
        this.umurHewan = umurHewan;
    }

    public String getLokasiHewan() {
        return lokasiHewan;
    }

    public void setLokasiHewan(String lokasiHewan) {
        this.lokasiHewan = lokasiHewan;
    }

    public String getJenisHewan() {
        return jenisHewan;
    }

    public void setJenisHewan(String jenisHewan) {
        this.jenisHewan = jenisHewan;
    }

    public String getDeskripsiHewan() {
        return deskripsiHewan;
    }

    public void setDeskripsiHewan(String deskripsiHewan) {
        this.deskripsiHewan = deskripsiHewan;
    }

    public String getIdPemilik() {
        return idPemilik;
    }

    public void setIdPemilik(String idPemilik) {
        this.idPemilik = idPemilik;
    }

    public String getStatusKepemilikan() {
        return statusKepemilikan;
    }

    public void setStatusKepemilikan(String statusKepemilikan) {
        this.statusKepemilikan = statusKepemilikan;
    }
}
