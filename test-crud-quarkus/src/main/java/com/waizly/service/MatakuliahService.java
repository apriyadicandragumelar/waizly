package com.waizly.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.core.Response;

import com.waizly.dto.ResponseData;
import com.waizly.models.Matakuliah;


@ApplicationScoped 
@Transactional
public class MatakuliahService {
    
    @Inject
    EntityManager entitas;


    public List<Matakuliah> getAll() {
        return Matakuliah.listAll();
    }
    
    public Response findByMatakuliahName(String nameMatakuliah) {
        List<Matakuliah> mataKuliah = Matakuliah.find(
        "lower(nameMatakuliah) like concat ('%', lower(?1), '%')", nameMatakuliah).list();
        if(mataKuliah == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("MATAKULIAH_NOT_FOUND").build();
        }
        return Response.ok(mataKuliah).build();
    }

    public Response create(@Valid Matakuliah mataKuliah) {
        ResponseData responData = new ResponseData();
        if(mataKuliah == null) {
            responData.setMessages("Gagal");
            return Response.status(401).entity(responData).build();
        }
        mataKuliah.persist();
        responData.setStatus(true);
        responData.setMessages("Matakuliah berhasil ditambahkan");
        responData.setData(mataKuliah);
        return Response.ok(responData).build();
    }

    public Response update(Long idMatakuliah, @Valid Matakuliah newMataKuliah) {
        ResponseData responData = new ResponseData();
        Matakuliah oldMataKuliah = Matakuliah.findById(idMatakuliah);
        if (oldMataKuliah == null) {
            responData.setMessages("False");
            return Response.status(401).entity(responData).build();
        }
        oldMataKuliah.setNameMatakuliah(newMataKuliah.getNameMatakuliah());
        oldMataKuliah.setJumlahSKS(newMataKuliah.getJumlahSKS());
        responData.setStatus(true);
        responData.setMessages("berhasil");
        responData.setData(oldMataKuliah);
        return Response.ok(responData).build();
    }

    public Response deleteId(Long idMatakuliah) {
        entitas.createNativeQuery("DELETE FROM mahasiswa_kuliah WHERE kuliah_id = :id")
        .setParameter("id", idMatakuliah).executeUpdate();
        Matakuliah mataKuliah = Matakuliah.findById(idMatakuliah);
        if(!mataKuliah.isPersistent()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("ID NOT_FOUND").build();
        }
        Matakuliah.deleteById(idMatakuliah);
        return Response.ok(mataKuliah).entity("Matakuliah succes for delete").build();
    }
}
