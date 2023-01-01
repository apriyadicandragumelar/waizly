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

import com.waizly.models.Jurusan;
import com.waizly.service.JurusanService;

@Path("jurusan")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JurusanController {

    @Inject
    private JurusanService jurusanService;


    @GET
    @Operation(summary = "Get Jurusan", 
               description = "Method/function ini adalah untuk Menampilkan data jurusan")
    public List<Jurusan> getAll() {
        return jurusanService.getAll();
    }

    @GET
    @Path("{name}")
    @Operation(summary = "Get Name", 
               description = "Method/function ini adalah untuk mencari data jurusan berdasarkan nama")
    public Response findByjurusanName(@PathParam("name") String name) {
        return jurusanService.findByjurusanName(name);
    }

    @POST
    @Operation(summary = "Create Jurusan", 
               description = "Method/function ini adalah untuk create/insert data jurusan baru")
    public Jurusan create(@Valid Jurusan jurusan) {
        return jurusanService.create(jurusan);
    }

    @PUT
    @Path("{kodeJurusan}")
    @Operation(summary = "Update Jurusan", 
               description = "Method/function ini adalah untuk update jurusan berdasarkan kode jurusan")
    public Response update(@PathParam("kodeJurusan") String kode, @Valid Jurusan newJurusan) {
        return jurusanService.update(kode, newJurusan);
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Delete Jurusan", 
               description = "Method/function ini adalah untuk delete jurusan berdasarkan id")
    public Response deleteId(@PathParam("id") Long id) {
        return jurusanService.deleteId(id);
    }
    
}
