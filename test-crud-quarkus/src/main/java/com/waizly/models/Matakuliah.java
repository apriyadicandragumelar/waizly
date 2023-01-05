package com.waizly.models;

import javax.persistence.*;
import javax.persistence.SequenceGenerator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "mata_kuliah")
public class Matakuliah extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "matakuliahSequence", sequenceName = "matakuliahSequence", allocationSize = 1, initialValue = 200)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "matakuliahSequence")

    private Long id;

    @Column(name = "nama_matakuliah")
    private String nameMatakuliah;

    @Column(name = "jumlah_sks")
    private String jumlahSKS;

    @JsonGetter
    public Long getId(){
        return id;
    }

    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    public String getNameMatakuliah() {
        return nameMatakuliah;
    }

    public void setNameMatakuliah(String nameMatakuliah) {
        this.nameMatakuliah = nameMatakuliah;
    }

    public String getJumlahSKS() {
        return jumlahSKS;
    }

    public void setJumlahSKS(String jumlahSKS) {
        this.jumlahSKS = jumlahSKS;
    }
}
