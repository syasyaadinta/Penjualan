package com.tes.Penjualan.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Data Account untuk registrasi user baru.")
//@Compare(message="Password is not matched.", firstField="password", secondField="passwordConfirmation")
public class AccountRegisterDTO {

        private Long customerId;

        @Schema(description = "Username maximum 50 characters.")
//        @UniqueUsername(message="Username is already exist within the database.")
        @NotBlank(message = "Username is required")
        @Size(max = 50, message = "Username can't be more than 50 characters.")
        private String username;

        @Schema(description = "Password yang digunakan untuk register.")
        @NotBlank(message = "Password is required")
        private String password;

        @Schema(description = "Mengkonfirmasi password baru.")
        @NotBlank(message = "Password confirmation is required")
        private String passwordConfirmation;

        @Schema(description = "Role yang digunakan untuk register baru.")
        private String role;

        @Override
        public String toString() {
                return "AccountRegisterDTO{" +
                        "customerId=" + customerId +
                        ", username='" + username + '\'' +
                        ", password='" + password + '\'' +
                        ", passwordConfirmation='" + passwordConfirmation + '\'' +
                        ", role='" + role + '\'' +
                        '}';
        }
}