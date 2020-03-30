package com.innovitech.usermanager.microservice.endpoint;

import com.innovitech.usermanager.api.service.AddressService;
import com.innovitech.usermanager.api.vo.AddressVO;
import com.innovitech.usermanager.microservice.response.AddressResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/addresses")
public class AddressEndpoint {

    @Inject
    private AddressService addressService;


    @GET
    @Path("/{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAddressesByUserId(@PathParam("userId") String userId) {
        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setAddresses(addressService.findByUserId(Long.parseLong(userId)));
        return Response.ok(addressResponse).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAddress(AddressVO address) {
        addressService.save(address);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editAddress(AddressVO address) {
        addressService.update(address);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{addressId}")
    public Response deleteAddress(@PathParam("addressId") String addressId) {
        addressService.remove(Long.parseLong(addressId));
        return Response.ok().build();
    }

}
