package com.example.grpc.service;

import com.example.grpc.*;
import com.example.grpc.model.UserEntity;
import com.example.grpc.repository.UserRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {

    private final UserRepository userRepository;

    public UserGrpcService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(CreateUserRequest request, StreamObserver<UserResponse> responseObserver) {
        UserEntity user = new UserEntity(null, request.getName(), request.getEmail());
        UserEntity saved = userRepository.save(user);

        User protoUser = User.newBuilder()
                .setId(saved.getId())
                .setName(saved.getName())
                .setEmail(saved.getEmail())
                .build();

        UserResponse response = UserResponse.newBuilder().setUser(protoUser).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getUser(GetUserRequest request, StreamObserver<UserResponse> responseObserver) {
        UserEntity user = userRepository.findById(request.getId()).orElseThrow();
        User protoUser = User.newBuilder()
                .setId(user.getId())
                .setName(user.getName())
                .setEmail(user.getEmail())
                .build();

        UserResponse response = UserResponse.newBuilder().setUser(protoUser).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void updateUser(UpdateUserRequest request, StreamObserver<UserResponse> responseObserver) {
        UserEntity user = userRepository.findById(request.getId()).orElseThrow();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        UserEntity updated = userRepository.save(user);

        User protoUser = User.newBuilder()
                .setId(updated.getId())
                .setName(updated.getName())
                .setEmail(updated.getEmail())
                .build();

        UserResponse response = UserResponse.newBuilder().setUser(protoUser).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteUser(DeleteUserRequest request, StreamObserver<DeleteUserResponse> responseObserver) {
        userRepository.deleteById(request.getId());
        DeleteUserResponse response = DeleteUserResponse.newBuilder()
                .setMessage("User deleted successfully")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
