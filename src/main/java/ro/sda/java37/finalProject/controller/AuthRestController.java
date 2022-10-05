package ro.sda.java37.finalProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.sda.java37.finalProject.dto.LoginDto;
import ro.sda.java37.finalProject.dto.TokenDto;

@RestController
@RequestMapping(value = "/api/auth", produces = "application/json")
public class AuthRestController {
  public final static String APP_TOKEN = "my token";

  @Autowired
  public AuthenticationManagerBuilder authenticationManager;

  @PostMapping("/signin")
  public TokenDto login(@RequestBody LoginDto loginDto) {
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
      loginDto.getUsername(), loginDto.getPassword());

    authenticationManager.getObject().authenticate(authenticationToken);

    return TokenDto.builder()
      .token(APP_TOKEN)
      .build();
  }

}
