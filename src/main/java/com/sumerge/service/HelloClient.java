package com.sumerge.service;

import io.quarkus.example.HelloReply;
import io.quarkus.example.HelloRequest;
import io.quarkus.example.MutinyGreeterGrpc;
import io.quarkus.grpc.GrpcClient;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HelloClient {

    @GrpcClient("hello-client")
    MutinyGreeterGrpc.MutinyGreeterStub greeterClient;

    public Uni<String> getHello(String name) {
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        return greeterClient.sayHello(request)
                .onItem()
                .transform(HelloReply::getMessage);
    }


}
