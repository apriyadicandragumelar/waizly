package com.waizly.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;

import com.waizly.models.Mahasiswa;
import com.waizly.service.MahasiswaService;

@Path("mahasiswa")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MahasiswaController {

    @Inject
    private MahasiswaService mahasiswaService;

    @GET
    @Operation(summary = "Get Mahasiswa", 
               description = "Method/function ini adalah menampilkan data mahasiswa")
    public List<Mahasiswa> getall() {
        return mahasiswaService.getAll();
    }

    @GET
    @Path("{name}")
    @Operation(summary = "Get Name", 
               description = "Method/function ini adalah menampilkan data matakuliah berdasarkan nama")
    public Response searchNameMahasiswa(@PathParam("name") String name) {
        return mahasiswaService.findByNameMahasiswa(name);
    }

    @POST
    @Path("{idJurusan}")
    @Operation(summary = "Create Mahasiswa", 
               description = "Method/function ini adalah create/insert mahasiswa berdsarkan id jurusan")
    //model mapper atau data transfer object di controller
    public List<Mahasiswa> create(@PathParam("idJurusan") Long idJurusan, @Valid Mahasiswa mahasiswa) {
        return mahasiswaService.create(idJurusan, mahasiswa);
    }

    @PUT
    @Path("{npmMahasiswa}")
    @Operation(summary = "Update Mahasiswa", 
               description = "Method/function ini adalah untuk update mahasiswa berdasarkan npm")
    public Response update(@PathParam("npmMahasiswa") String npm, @Valid Mahasiswa mahasiswa) {
        return mahasiswaService.update(npm, mahasiswa);
    }

    @DELETE
    @Path("{idMahasiswa}")
    @Operation(summary = "Delete Mahasiswa", 
               description = "Method/function ini adalah untuk delete mahasiswa berdasarkan id")
    public Response deleteId(@PathParam("idMahasiswa")Long idMahasiswa) {
        return mahasiswaService.deleteId(idMahasiswa);
    }
}
