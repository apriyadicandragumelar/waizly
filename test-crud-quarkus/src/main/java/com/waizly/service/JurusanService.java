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

@ApplicationScoped
@Transactional
public class JurusanService {
    

    @Inject
    EntityManager entitas;

    public List<Jurusan> getAll() {
        return Jurusan.listAll();
    }

    public Response findByjurusanName(String name) {
        List<Jurusan> jurusan = Jurusan.find(
        "lower(nameJurusan) LIKE CONCAT ('%', lower(?1), '%')", name).list();
        if(jurusan ==  null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("JURUSAN_NOT_FOUND").build();
        }
        return Response.ok(jurusan).build();
    }

    public Response create(@Valid Jurusan jurusan) {
        ResponseData responData = new ResponseData();
        if(jurusan == null) {
            responData.setMessages("Data gagal");
            return Response.status(401).entity(responData).build();
        }
        jurusan.persist();
        responData.setStatus(true);
        responData.setMessages("Berhasil");
        responData.setData(jurusan);
        return Response.ok(responData).build();
    }

    public Response update(String kodeJurusan, @Valid Jurusan newJurusan) {
        ResponseData responData = new ResponseData();
        Optional<Jurusan> optionalJurusan = Jurusan.find(
        "kodeJurusan = ?1", kodeJurusan).firstResultOptional();
        if (optionalJurusan.isEmpty()) {
            responData.setMessages("Fales");
            return Response.status(400).entity("KODEJURUSAN_NOT_FOUND").build();
        }
        Jurusan oldJurusan = optionalJurusan.get();
        oldJurusan.setNameJurusan(newJurusan.getNameJurusan());
        oldJurusan.setJenjang(newJurusan.getJenjang());
        responData.setStatus(true);
        responData.setMessages("Berhasil");
        responData.setData(oldJurusan);
        return Response.ok(responData).build();
    }

    public Response deleteId(Long idJurusan) {
        Jurusan jurusan = Jurusan.findById(idJurusan);
        if(jurusan == null) {
            return Response.status(400).entity("ID NOT_FOUND").build();
        }
        Jurusan.deleteById(idJurusan);
        return Response.ok(jurusan).entity("Jurusan success for delete").build();
    }
}
