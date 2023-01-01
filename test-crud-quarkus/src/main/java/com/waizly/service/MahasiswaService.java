package com.waizly.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.core.Response;

import com.waizly.models.Jurusan;
import com.waizly.models.Mahasiswa;

@ApplicationScoped
@Transactional
public class MahasiswaService {
    
    @Inject
    EntityManager entitas;

    public List<Mahasiswa> getAll() {
        return Mahasiswa.listAll();
    }

    public Response findByNameMahasiswa(String name) {
        Iterable<Mahasiswa> optionalMahasiswa = Mahasiswa.find(
        "lower(name) like concat('%', lower(?1), '%')", name).list();
        if(optionalMahasiswa.equals(optionalMahasiswa)) {
            return Response.ok(optionalMahasiswa).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("DATA_NOT_FOUND").build();
    }
    
    public List<Mahasiswa> create(Long id, @Valid Mahasiswa mahasiswa) {
        Jurusan jurusan = Jurusan.findById(id);
        jurusan.mahasiswaList.add(mahasiswa);
        mahasiswa.persist();
        return Mahasiswa.listAll();
    }

    public Response update(String npm, @Valid Mahasiswa newMahasiswa) {
        Optional<Mahasiswa> optionalMahasiswa = Mahasiswa.find(
        "npm = ?1", npm).firstResultOptional();
        if(optionalMahasiswa.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("NPM_NOT_FOUND").build();
        }
        Mahasiswa oldMahasiswa = optionalMahasiswa.get();
        oldMahasiswa.setName(newMahasiswa.getName());
        oldMahasiswa.setJenisKelamin(newMahasiswa.getJenisKelamin());
        oldMahasiswa.setAlamat(newMahasiswa.getAlamat());

        return Response.ok(oldMahasiswa).build();
    }
    
    public Response deleteId(Long idMahasiswa) {
        entitas.createNativeQuery("DELETE FROM mahasiswa_kuliah WHERE mahasiswa_id = :id")
        .setParameter("id", idMahasiswa).executeUpdate();
        Boolean deleteMahasiswa = Mahasiswa.deleteById(idMahasiswa);
        List<Mahasiswa> mahasiswa = Mahasiswa.findById(idMahasiswa);
        if(deleteMahasiswa) {
            return Response.ok(mahasiswa).entity("Data mahasiswa success for delete").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Data NOT_FOUND").build();
    }  
}
