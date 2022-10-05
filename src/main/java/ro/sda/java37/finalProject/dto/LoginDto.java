package ro.sda.java37.finalProject.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginDto {
  @NotNull
  private String username;
  @NotNull
  private String password;
}
