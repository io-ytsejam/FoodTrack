package com.backend.Services;

import com.backend.Dto.UserRegistrationDto;
import com.backend.Models.PersonEntity;
import com.backend.Models.PersonSettingEntity;
import com.backend.Models.RoleEntity;
import com.backend.Models.SettingEntity;
import com.backend.Repositories.PersonEntityRepository;
import com.backend.Repositories.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PersonEntityRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private SettingRepository settingRepository;

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        PersonEntity user = userRepository.findByNickname(nickname);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getNickname(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    public PersonEntity findByNickname(String nickname){
        return userRepository.findByNickname(nickname);
    }

    public PersonEntity save(UserRegistrationDto registration){
        PersonEntity user = new PersonEntity();
        user.setFirstname(registration.getFirstName());
        user.setLastname(registration.getLastName());
        user.setNickname(registration.getNickname());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setRoles(Arrays.asList(new RoleEntity("ROLE_USER")));
        PersonSettingEntity privacy=new PersonSettingEntity();
        privacy.setValue('t');
        privacy.setPersonEntity(user);
        privacy.setSettingEntity(settingRepository.findByNameIgnoreCase("privacy").get());
        user.addPersonSetting(privacy);
        return userRepository.save(user);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<RoleEntity> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}