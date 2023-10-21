package com.money.transfer.core.usecase;

import com.money.transfer.core.model.User;
import com.money.transfer.core.usecase.boundary.FindUserByEmailBoundary;
import com.money.transfer.core.usecase.boundary.SaveUserToDbBoundary;
import com.money.transfer.core.util.CpfValidatorUtil;
import com.money.transfer.exception.ResourceViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final BCryptPasswordEncoder bcrypt;

    private final SaveUserToDbBoundary saveUserToDbBoundary;

    private final FindUserByEmailBoundary findUserByEmailBoundary;

    public User createUser(String email, String name, String cpf, String password, String passwordConfirmation) {
        boolean isCpfValid = CpfValidatorUtil.validate(cpf);

        if (!isCpfValid) {
            throw new ResourceViolationException("CPF is invalid");
        }

        boolean doPasswordsMatch = password.equals(passwordConfirmation);

        if (!doPasswordsMatch) {
            throw new ResourceViolationException("Passwords do not match");
        }

        Optional<User> optionalOfUser = findUserByEmailBoundary.findUser(email);

        if (optionalOfUser.isPresent()) {
            throw new ResourceViolationException("User already exists");
        }

        String encodedPassword = bcrypt.encode(password);

        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .email(email)
                .cpf(cpf)
                .password(encodedPassword)
                .build();

        return saveUserToDbBoundary.save(user);
    }
}
