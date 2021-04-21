package com.bfourclass.euopendata.user_verification;

import com.bfourclass.euopendata.user.UserModel;
import com.bfourclass.euopendata.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserVerificationService {

    private final UserVerificationRepository userVerificationRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserVerificationService(UserVerificationRepository userVerificationRepository, UserRepository userRepository) {
        this.userVerificationRepository = userVerificationRepository;
        this.userRepository = userRepository;
    }

    public void save(UserVerification userVerification) {
        userVerificationRepository.save(userVerification);
    }

    public boolean activateUser(String key) {

        UserVerification userVerification = userVerificationRepository.findInstanceByKey(key);

        if (userVerification == null) {
            return false;
        }

        UserModel userModel = userVerification.userModel;
        userModel.activateUser();

        userRepository.save(userModel);

        return true;
    }
}
