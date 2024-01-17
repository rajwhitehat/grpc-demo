package com.example.demo;

import net.devh.boot.grpc.server.service.GrpcService;

import com.demo.schema.EmployeeRequest;
import com.demo.schema.EmployeeResponse;
import com.demo.schema.EmployeeServiceGrpc;

import io.grpc.stub.StreamObserver;

@GrpcService
public class EmployeeService extends EmployeeServiceGrpc.EmployeeServiceImplBase {

@Override
public void getEmployeeById(EmployeeRequest request, StreamObserver<EmployeeResponse> responseObserver) {
EmployeeResponse eRes = TempDB.getEmpFromTempDb().stream().filter(obj-> obj.getEmpId() == request.getEmpId()).findFirst().get();
responseObserver.onNext(eRes);
responseObserver.onCompleted();
}

@Override
public void getEmployeeByGender(EmployeeRequest request, StreamObserver<EmployeeResponse> responseObserver) {
	TempDB.getEmpFromTempDb().stream().filter(obj-> obj.getGender().equals(request.getGender())).forEach(responseObserver::onNext);
	responseObserver.onCompleted();
	}

@Override
	public StreamObserver<EmployeeResponse> getOldestEmployee(StreamObserver<EmployeeResponse> responseObserver) {
StreamObserver<EmployeeResponse> res = new StreamObserver<EmployeeResponse>() {
	
	EmployeeResponse result = null;
	int age = 0;
	
	@Override
	public void onNext(EmployeeResponse emp) {
		if(emp.getAge() > age) {
			age = emp.getAge();
			result = emp;
		}
		
	}
	
	@Override
	public void onError(Throwable t) {
		responseObserver.onError(t);
		
	}
	
	@Override
	public void onCompleted() {
		responseObserver.onNext(result);
		responseObserver.onCompleted();
		
	}
};
return res;
	}

}
