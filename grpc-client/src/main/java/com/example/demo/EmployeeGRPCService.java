package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.demo.schema.EmployeeRequest;
import com.demo.schema.EmployeeResponse;
import com.demo.schema.EmployeeServiceGrpc;

import net.devh.boot.grpc.client.inject.GrpcClient;

import com.google.protobuf.Descriptors;

import io.grpc.stub.StreamObserver;


@Service
class  EmployeeGRPCService {

    @GrpcClient("grpc-server")
     EmployeeServiceGrpc.EmployeeServiceBlockingStub synchronousClient;

    @GrpcClient("grpc-server")
    EmployeeServiceGrpc.EmployeeServiceStub asynchronousClient;
    
    
    public Map<Descriptors.FieldDescriptor, Object> getEmployeeId(int empId) {
        EmployeeRequest empRequest = EmployeeRequest.newBuilder().setEmpId(empId).build();
        EmployeeResponse empResponse = synchronousClient.getEmployeeById(empRequest);
        return empResponse.getAllFields();
    }
    
    public List<Map<Descriptors.FieldDescriptor, Object>> getEmployeeByGender(String gender) throws InterruptedException {
        EmployeeRequest empRequest = EmployeeRequest.newBuilder().setGender(gender).build();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final List<Map<Descriptors.FieldDescriptor, Object>> response = new ArrayList<>();
        StreamObserver<EmployeeResponse>   empStreamObserver =new StreamObserver<EmployeeResponse>() {
            @Override
            public void onNext(EmployeeResponse emp) {
                response.add(emp.getAllFields());
            }

            @Override
            public void onError(Throwable throwable) {
                countDownLatch.countDown();
            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }
        };
        asynchronousClient.getEmployeeByGender(empRequest, empStreamObserver);
        boolean await = countDownLatch.await(1, TimeUnit.MINUTES);
  
        return await ? response : Collections.emptyList();
    }
    
    
    public Map<String, Map<Descriptors.FieldDescriptor, Object>> getOldestEmployee() throws InterruptedException {
    	 final Map<String, Map<Descriptors.FieldDescriptor, Object>> response = new HashMap<>();
    	 final CountDownLatch countDownLatch = new CountDownLatch(1);
    	StreamObserver<EmployeeResponse> emRes = new StreamObserver<EmployeeResponse>() {
    		
    		@Override
    		public void onNext(EmployeeResponse emp) {
    			response.put("oldest_employee", emp.getAllFields());
    		}
    		
    		@Override
    		public void onError(Throwable err) {
    			 countDownLatch.countDown();
    		}
    		
    		@Override
    		public void onCompleted() {
    			 countDownLatch.countDown();
    		}
    	};
    	
    	
    	
    	StreamObserver<EmployeeResponse> responseObserver = asynchronousClient.getOldestEmployee(emRes);
    	TempDB.getEmpFromTempDb().forEach(responseObserver::onNext);
    	
    	responseObserver.onCompleted();
    	boolean await = countDownLatch.await(1, TimeUnit.MINUTES);
    	return await ? response : Collections.emptyMap();
    	
    }
}
