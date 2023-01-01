package com.waizly.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.core.Response;

import com.waizly.models.Jurusan;

@ApplicationScoped
@Transactional
public class JurusanService {
    
    public List<Jurusan> getAll() {
        return Jurusan.listAll();
    }

    public Response findByjurusanName(String name) {
        Iterable<Jurusan> jurusan = Jurusan.find(
        "lower(nameJurusan) LIKE CONCAT ('%', lower(?1), '%')", name).list();
        if(jurusan ==  null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("JURUSAN_NOT_FOUND").build();
        }
        return Response.ok(jurusan).build();
    }

    public Jurusan create(@Valid Jurusan jurusan) {
        jurusan.persist();
        return jurusan;
    }

    public Response update(String kodeJurusan, @Valid Jurusan newJurusan) {
        Optional<Jurusan> optionalJurusan = Jurusan.find(
        "kodeJurusan = ?1", kodeJurusan).firstResultOptional();
        if (optionalJurusan.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("KODEJURUSAN_NOT_FOUND").build();
        }
        Jurusan oldJurusan = optionalJurusan.get();
        oldJurusan.setNameJurusan(newJurusan.getNameJurusan());
        oldJurusan.setJenjang(newJurusan.getJenjang());
        return Response.ok(oldJurusan).build();
    }

    public Response deleteId(Long idJurusan) {
        Jurusan jurusan = Jurusan.findById(idJurusan);
        Boolean deleteJurusan = Jurusan.deleteById(idJurusan);
        if(deleteJurusan) {
            return Response.ok(jurusan).entity("Jurusan success for delete").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("ID NOT_FOUND").build();
    }
}
