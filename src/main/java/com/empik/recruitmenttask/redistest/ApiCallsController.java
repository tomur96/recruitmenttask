//package com.empik.recruitmenttask.redistest;
//
//import com.empik.recruitmenttask.repository.ApiCallsRepository;
//import com.empik.recruitmenttask.repository.UserEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//public class ApiCallsController {
//
//    private final ApiCallsRepository apiCallsRepository;
//
//    public ApiCallsController(ApiCallsRepository apiCallsRepository) {
//        this.apiCallsRepository = apiCallsRepository;
//    }
//
//    @GetMapping(value = "apicalls")
//    public List<UserEntity> getApiCallsList() {
//
//        return (List<UserEntity>) apiCallsRepository.findAll();
//    }
//
//    @PutMapping(value = "/{username}")
//    public void updateApiCalls(@PathVariable("username") String username) {
//        UserEntity userEntity = getApiCallsEntity(username);
//        if (userEntity == null) {
//            userEntity = new UserEntity(username);
//        }
//        userEntity.setCallCount(incrementCallCount(userEntity.callCount));
//        apiCallsRepository.save(userEntity);
//    }
//
//    private UserEntity getApiCallsEntity(String username) {
//        return Optional.of(apiCallsRepository.findById(username)).get().orElse(null);
//    }
//
//    private int incrementCallCount(int callCount) {
//        return callCount+1;
//    }
//}
