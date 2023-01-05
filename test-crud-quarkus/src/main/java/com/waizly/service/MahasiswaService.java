package com.waizly.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.core.Response;

import com.waizly.dto.ResponseData;
import com.waizly.models.Jurusan;
import com.waizly.models.Mahasiswa;
import com.waizly.models.Matakuliah;


@ApplicationScoped
@Transactional
public class MahasiswaService {
    
    @Inject
    EntityManager entitas;

    public List<Mahasiswa> getAll() {
        return Mahasiswa.listAll();
    }

    public Response findByNameMahasiswa(String name) {
        List<Mahasiswa> optionalMahasiswa = Mahasiswa.find(
        "lower(name) like concat('%', lower(?1), '%')", name).list();
        if(optionalMahasiswa.equals(optionalMahasiswa)) {
            return Response.ok(optionalMahasiswa).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("DATA_NOT_FOUND").build();
    }
    
    public Response createMahasiswaIdJurusan(Long jurusanId, @Valid Mahasiswa mahasiswa) {
        ResponseData responseData = new ResponseData();
        Jurusan jurusan = Jurusan.findById(jurusanId);
        if(jurusan == null){
            responseData.setMessages("ID JURUSAN NOT FOUND");
            return Response.status(400).entity(responseData).build();
        }
        // mahasiswa.jurusan = jurusan;
        jurusan.getMahasiswaList().add(mahasiswa);
        mahasiswa.persist();
        responseData.setStatus(true);
        responseData.setMessages("Data berhasil dipublih");
        responseData.setData(Mahasiswa.findById(jurusanId));
        return Response.status(201).entity(responseData).build();
    }

     public Response addMahasiswaIdMahasiswaIdJurusan(Long idMahasiswa, Long idMatakuliah) {
        ResponseData responseData = new ResponseData();
        Mahasiswa mahasiswa = Mahasiswa.findById(idMahasiswa);
        Matakuliah matakuliah = Matakuliah.findById(idMatakuliah);

        if (mahasiswa == null || matakuliah == null) {
            if (mahasiswa == null && matakuliah == null) {
                responseData.setMessages("ID Customer dan ID Product tidak ditemukan");
                return Response.status(400).entity(responseData).build();

            }
            if (matakuliah == null) {
                responseData.setMessages("ID MATAKULIAH NOT FOUND");
                return Response.status(400).entity(responseData).build();

            } else {
                responseData.setMessages("ID MAHASISWA NOT FOUND");
                return Response.status(400).entity(responseData).build();
            }

        } else {
            mahasiswa.getMatakuliahList().add(matakuliah);
            mahasiswa.persist();
            responseData.setMessages("Data mahasiswa matakuliah berhasil dipublish");
            responseData.setStatus(true);
            responseData.setData(Mahasiswa.findById(idMahasiswa));
            return Response.status(201).entity(responseData).build();
        }
    }

    public Response update(String npm, @Valid Mahasiswa newMahasiswa) {
        ResponseData responseData = new ResponseData();
        Optional<Mahasiswa> optionalMahasiswa = Mahasiswa.find(
        "npm = ?1", npm).firstResultOptional();
            if(optionalMahasiswa.isEmpty()) {
                responseData.setMessages("NPM_NOT_FOUND");
                return Response.status(400).entity(responseData).build();
            }
            Mahasiswa oldMahasiswa = optionalMahasiswa.get();
            oldMahasiswa.setName(newMahasiswa.getName());
            oldMahasiswa.setJenisKelamin(newMahasiswa.getJenisKelamin());
            oldMahasiswa.setAlamat(newMahasiswa.getAlamat());

            responseData.setStatus(true);
            responseData.setMessages("Data berhasil diupdate");
            responseData.setData(oldMahasiswa);
            return Response.ok(responseData).build();
    }
    
    public Response deleteId(Long idMahasiswa) {
        entitas.createNativeQuery("DELETE FROM mahasiswa_kuliah WHERE mahasiswa_id = :id")
        .setParameter("id", idMahasiswa).executeUpdate();
        Mahasiswa mahasiswa = Mahasiswa.findById(idMahasiswa);
        if(mahasiswa == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Data NOT_FOUND").build();
        }
        Mahasiswa.deleteById(idMahasiswa);
        return Response.ok(mahasiswa).entity("Data mahasiswa success for delete").build();
    }  
}
