package com.sumerge;

import com.sumerge.service.HelloClient;
import io.quarkus.example.Greeter;
import io.quarkus.grpc.GrpcClient;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class ExampleResource {

    @Inject
    HelloClient helloClient;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from REST";
    }


    @GET
    @Path("/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloGrpc(@PathParam("name") String name) {
        return helloClient.getHello(name).onItem().transform(msg -> "Hello from gRPC: " + msg).await().indefinitely();
    }
}
