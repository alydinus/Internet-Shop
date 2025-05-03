package kg.spring.project.internet_shop.controller.api.controllers;

import kg.spring.project.internet_shop.controller.api.LoginApi;
import kg.spring.project.internet_shop.dto.UserLoginDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController implements LoginApi {

  public String login(UserLoginDTO userLoginDTO) {
    return "";
  }
}
