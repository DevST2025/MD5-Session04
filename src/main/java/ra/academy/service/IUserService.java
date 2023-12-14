package ra.academy.service;

import ra.academy.dto.request.SignInDto;
import ra.academy.dto.request.SignUpDto;
import ra.academy.dto.response.UserResponse;
import ra.academy.entity.User;
import ra.academy.exception.UsernameAndPasswordException;

public interface IUserService {
    UserResponse login(SignInDto signInDto) throws UsernameAndPasswordException;
    void register(SignUpDto signUpDto);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
