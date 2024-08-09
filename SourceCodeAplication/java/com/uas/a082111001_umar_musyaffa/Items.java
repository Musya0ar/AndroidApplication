package com.uas.a082111001_umar_musyaffa;

public class Items {
    private int iditems;
    private final String cpuName;
    private final String namaBarang;
    private final String hargaBarang;
    private final String deskripsiBarang;
    private final String gambarBarang;


    public Items(int id, String cpuName, String namaBarang, String hargaBarang, String deskripsiBarang, String gambarBarang) {
        this.iditems = id;
        this.cpuName = cpuName;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
        this.deskripsiBarang = deskripsiBarang;
        this.gambarBarang = gambarBarang;
    }

    // Getters and setters
    public int getId() {
        return iditems;
    }


    public void setId(int id) {
        this.iditems = id;
    }

    public String getCpuName() {
        return cpuName;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public String getHargaBarang() {
        return hargaBarang;
    }

    public String getDeskripsiBarang() {
        return deskripsiBarang;
    }

    public String getGambarBarang() {
        return gambarBarang;
    }

}

