package com.waizly.models;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;


@Entity
@Table(name = "mahasiswa")
public class Mahasiswa extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "mahasiswaSequence", sequenceName = "mahasiswa_sequence", allocationSize = 1, initialValue = 2000)
    @GeneratedValue(generator = "mahasiswaSequence", strategy = GenerationType.SEQUENCE)
    public Long id;
    
    @NotBlank(message = "npm is required")
    @Column(name = "npm", unique = true)
    private String npm;

    @NotBlank(message = "name is required")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "JK harus ada")
    @Column(name = "jenis_kelamin")
    private String jenisKelamin;

    @NotBlank(message = "alamat ist required")
    private String alamat;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "mahasiswa_kuliah", joinColumns = 
    @JoinColumn(name = "mahasiswa_id", referencedColumnName = "id"),inverseJoinColumns = 
    @JoinColumn(name = "kuliah_id", referencedColumnName = "id"))
    private List <Matakuliah> matakuliahList;

    @JsonGetter
    public Long getId(){
        return id;
    }

    @JsonIgnore
    public void setId(Long id){
        this.id = id;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public List<Matakuliah> getMatakuliahList() {
        return matakuliahList;
    }

    public void setMatakuliahList(List<Matakuliah> matakuliahList) {
        this.matakuliahList = matakuliahList;
    } 
       
}
