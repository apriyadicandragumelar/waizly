package com.waizly.models;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "jurusan")
public class Jurusan extends PanacheEntityBase {
    
    @Id
    @SequenceGenerator(name = "jurusanSequence", sequenceName = "jurusanSequence", allocationSize = 1, initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jurusanSequence")
    private Long id;

    @Column(name = "kode_jurusan", unique = true)
    private String kodeJurusan;
    
    @NotBlank(message = "nama jurusan is required")
    @Column(name = "name_jurusan")
    private String nameJurusan;
    
    @NotBlank(message = "jenjang is required")
    private String jenjang;
    

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "jurusan_id", referencedColumnName = "id")
    private List<Mahasiswa> mahasiswaList;
    
    @JsonGetter
    public Long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getKodeJurusan() {
        return kodeJurusan;
    }

    public void setKodeJurusan(String kodeJurusan) {
        this.kodeJurusan = kodeJurusan;
    }

    public String getNameJurusan() {
        return nameJurusan;
    }

    public void setNameJurusan(String nameJurusan) {
        this.nameJurusan = nameJurusan;
    }

    public String getJenjang() {
        return jenjang;
    }

    public void setJenjang(String jenjang) {
        this.jenjang = jenjang;
    }

    public List<Mahasiswa> getMahasiswaList() {
        return mahasiswaList;
    }

    public void setMahasiswaList(List<Mahasiswa> mahasiswaList) {
        this.mahasiswaList = mahasiswaList;
    }  
}
