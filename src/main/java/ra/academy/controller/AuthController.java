package ra.academy.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.academy.dto.request.SignInDto;
import ra.academy.dto.request.SignUpDto;
import ra.academy.dto.response.UserResponse;
import ra.academy.exception.UsernameAndPasswordException;
import ra.academy.service.IUserService;

@RestController
@RequestMapping("api.authentication.com/v1")
@RequiredArgsConstructor
public class AuthController {
    private  final IUserService userService;
    @PostMapping("/sign-in")
    public ResponseEntity<UserResponse> signin (@Valid @RequestBody SignInDto signInDto) throws UsernameAndPasswordException {
        return new ResponseEntity<>(userService.login(signInDto), HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> register(@Valid @ModelAttribute SignUpDto signUpDto){
        userService.register(signUpDto);
        return new ResponseEntity<>("Đăng kí thành công",HttpStatus.CREATED);
    }
}

