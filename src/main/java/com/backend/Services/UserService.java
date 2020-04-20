package com.backend.Services;

import com.backend.Dto.UserRegistrationDto;
import com.backend.Models.PersonEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    PersonEntity findByNickname(String nickname);
    PersonEntity save(UserRegistrationDto userRegistrationDto);
}
