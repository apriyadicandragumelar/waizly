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

import com.waizly.models.Matakuliah;
import com.waizly.service.MatakuliahService;

@Path("mataKuliah")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MatakuliahController {

    @Inject
    private MatakuliahService matakuliahService;


    @GET
    @Operation(summary = "Get Matakuliah", 
               description = "Method/function ini adalah menampilkan data matakuliah")
    public List<Matakuliah> getAll() {
        return matakuliahService.getAll();
    }
    @GET
    @Path("{name}")
    @Operation(summary = "Find Matakuliah", 
               description = "Method/function ini adalah mencari data matakuliah berdasarkan nama")
    public Response findByMatakuliahName(@PathParam("name") String nameMatakuliah) {
        return matakuliahService.findByMatakuliahName(nameMatakuliah);
    }

    @POST
    @Path("{idMatakuliah}/mahasiswalist/{idMahasiswa}")
    @Operation(summary = "createMatakuliah - Mahasiswa", 
               description = "Method/function ini adalah create/insert data mahasiswa berdasarkan id kedalam id matakuliah")
    public List<Matakuliah> addMatakuliah(@PathParam("idMatakuliah") Long idMatakuliah, @PathParam("idMahasiswa") Long idMahasiswa) {
        return matakuliahService.addMatakuliah(idMatakuliah, idMahasiswa);
    } 

    @POST
    @Operation(summary = "Create Matakuliah", 
               description = "Method/function ini adalah insert atau create data baru matakuliah")
    public Matakuliah createMatakuliah(@Valid Matakuliah matakuliah) {
        return matakuliahService.create(matakuliah);
    }

    @PUT
    @Path("{id}")
    @Operation(summary = "Update Matakuliah", 
               description = "Method/function ini adalah update data matakuliah")
    public Response update(@PathParam("id") Long id, @Valid Matakuliah newMatakuliah) {
        return matakuliahService.update(id, newMatakuliah);
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Delete Matakuliah", 
              description = "Method/function ini adalah update data matakuliah")
    public Response deleteId(@PathParam("id") Long id) {
        return matakuliahService.deleteId(id);
    }
  
}