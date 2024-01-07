package br.com.sobreiraromulo.gestao_vagas.modules.company.controllers;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sobreiraromulo.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.sobreiraromulo.gestao_vagas.modules.company.useCases.AuthCompanyUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/companies")
public class AuthCompanyController {

  @Autowired
  private AuthCompanyUseCase authCompanyUseCase;

  @PostMapping("/auth")
  public ResponseEntity<Object> auth(@Valid @RequestBody AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
    try {
      var result = this.authCompanyUseCase.execute(authCompanyDTO);

      return ResponseEntity.ok(result);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
  }
}
