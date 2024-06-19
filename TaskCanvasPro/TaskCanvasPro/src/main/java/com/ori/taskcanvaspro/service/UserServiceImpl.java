package com.ori.taskcanvaspro.service;

import com.ori.taskcanvaspro.config.UserInfoDetails;
import com.ori.taskcanvaspro.dto.LoginDTO;
import com.ori.taskcanvaspro.dto.UserCreateDTO;
import com.ori.taskcanvaspro.dto.UserResponseDTO;
import com.ori.taskcanvaspro.model.entity.Task;
import com.ori.taskcanvaspro.model.entity.User;
import com.ori.taskcanvaspro.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository  userRepository;
    private  final ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder encoder;


    @Override
    public UserResponseDTO register(UserCreateDTO userDto) {
        boolean emailExists = userRepository.existsByEmail(userDto.getEmail());
        if(emailExists){
            //TODO email already exists exception
        }
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        User user = modelMapper.map(userDto, User.class);

        user = userRepository.save(user);
        UserResponseDTO responseDto = modelMapper.map(user, UserResponseDTO.class);
        return responseDto;
    }

    @Override
    public UserResponseDTO login(LoginDTO login) {
        login.setPassword(encoder.encode(login.getPassword()));
        User user = userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
        if(user == null){
            //TODO throw invalid user or password
            throw new IllegalArgumentException("incorrect email or password");
        }
        UserResponseDTO responseDto = modelMapper.map(user, UserResponseDTO.class);
        return responseDto;
    }
    public UserResponseDTO getByEmail(String email){
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(!optionalUser.isEmpty()){
            User user = optionalUser.get();
            UserResponseDTO responseDto = modelMapper.map(user, UserResponseDTO.class);
            return responseDto;
        }else{
            throw new UsernameNotFoundException("email " + email + "not found");
        }

    }

    @Override
    public UserResponseDTO update(UserResponseDTO user) {
        return null;
    }

    @Override
    public UserResponseDTO getUser(Long id) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.map(UserInfoDetails::new)
                .orElseThrow( () -> new UsernameNotFoundException("email " + email + "not found") );

    }
}
