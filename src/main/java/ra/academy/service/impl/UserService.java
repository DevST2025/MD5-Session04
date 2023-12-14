package ra.academy.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ra.academy.dto.request.SignInDto;
import ra.academy.dto.request.SignUpDto;
import ra.academy.dto.response.UserResponse;
import ra.academy.entity.Role;
import ra.academy.entity.RoleName;
import ra.academy.entity.User;
import ra.academy.exception.UsernameAndPasswordException;
import ra.academy.repository.IRoleRepository;
import ra.academy.repository.IUserRepository;
import ra.academy.service.IUserService;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final UploadService uploadService;

    @Override
    public UserResponse login(SignInDto signInDto) throws UsernameAndPasswordException {
        User userLogin = userRepository.findByUsernameAndPassword(signInDto.getUsername(), signInDto.getPassword())
                .orElseThrow(() -> new UsernameAndPasswordException("Tài khoản hoặc mật khẩu không tồn tại"));
        return modelMapper.map(userLogin, UserResponse.class);
    }

    @Override
    public void register(SignUpDto signUpDto) {
        //Upload avatar
        String avatarUrl = "https://iphonecugiare.com/wp-content/uploads/2020/03/84156601_1148106832202066_479016465572298752_o.jpg";
        if (signUpDto.getFile().isEmpty()) {
            avatarUrl = uploadService.uploadFileToServer(signUpDto.getFile());
        }
        Set<Role> roleSet = new HashSet<>();
        if (signUpDto.getListRoles().isEmpty() || signUpDto.getListRoles() == null) {
            roleSet.add(roleRepository.findByRoleName(RoleName.USER).orElseThrow(() -> new NoSuchElementException("Không tìm thấy tài nguyên")));
        } else {
            signUpDto.getListRoles().forEach(r -> {
                switch (r) {
                    case "admin":
                        roleSet.add(roleRepository.findByRoleName(RoleName.ADMIN).orElseThrow(() -> new NoSuchElementException("Không tìm thấy tài nguyên")));
                    case "pm":
                        roleSet.add(roleRepository.findByRoleName(RoleName.PM).orElseThrow(() -> new NoSuchElementException("Không tìm thấy tài nguyên")));
                    case "leader":
                        roleSet.add(roleRepository.findByRoleName(RoleName.LEADER).orElseThrow(() -> new NoSuchElementException("Không tìm thấy tài nguyên")));
                    case "user":
                    default:
                        roleSet.add(roleRepository.findByRoleName(RoleName.USER).orElseThrow(() -> new NoSuchElementException("Không tìm thấy tài nguyên")));
                }
            });
        }
        User user = modelMapper.map(signUpDto, User.class);
        user.setAvatar(avatarUrl);
        user.setRoles(roleSet);
        userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return userRepository.existsByPhone(phone);
    }
}
