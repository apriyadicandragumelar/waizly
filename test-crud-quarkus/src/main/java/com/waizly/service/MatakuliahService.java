package com.waizly.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.core.Response;

import com.waizly.models.Mahasiswa;
import com.waizly.models.Matakuliah;

import io.quarkus.panache.common.Sort;


@ApplicationScoped
@Transactional
public class MatakuliahService {
    
    @Inject
    EntityManager entitas;


    public List<Matakuliah> getAll() {
        return Matakuliah.listAll();
    }
    
    public Response findByMatakuliahName(String nameMatakuliah) {
        Iterable<Matakuliah> mataKuliah = Matakuliah.find(
        "lower(nameMatakuliah) like concat ('%', lower(?1), '%')", nameMatakuliah).list();
        if(mataKuliah == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("MATAKULIAH_NOT_FOUND").build();
        }
        return Response.ok(mataKuliah).build();
    }

    public Matakuliah create(@Valid Matakuliah mataKuliah) {
        mataKuliah.persist();
        return mataKuliah;
    }

    public List<Matakuliah> addMatakuliah(Long idMatakuliah, Long idMahasiswa) {
        Matakuliah matakuliah = Matakuliah.findById(idMatakuliah);
        Mahasiswa mahasiswa = Mahasiswa.findById(idMahasiswa);
        matakuliah.mahasiswaList.add(mahasiswa);
        matakuliah.persist();
        return Matakuliah.listAll(Sort.ascending("id"));
    }

    public Response update(Long idMatakuliah, @Valid Matakuliah newMataKuliah) {
        Matakuliah oldMataKuliah = Matakuliah.findById(idMatakuliah);
        if (oldMataKuliah == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("ID NOT_FOUND").build();
        }
        oldMataKuliah.setNameMatakuliah(newMataKuliah.getNameMatakuliah());
        oldMataKuliah.setJumlahSKS(newMataKuliah.getJumlahSKS());
        return Response.ok(oldMataKuliah).build();
    }

    public Response deleteId(Long idMatakuliah) {
        entitas.createNativeQuery("DELETE FROM mahasiswa_kuliah WHERE kuliah_id = :id")
        .setParameter("id", idMatakuliah).executeUpdate();
        Matakuliah mataKuliah = Matakuliah.findById(idMatakuliah);
        Boolean deleteMatakuliah = Matakuliah.deleteById(idMatakuliah);
        if(deleteMatakuliah) {
            return Response.ok(mataKuliah).entity("Matakuliah succes for delete").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("ID NOT_FOUND").build();
    }
}
