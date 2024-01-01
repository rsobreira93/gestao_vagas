package br.com.sobreiraromulo.gestao_vagas.modules.candidate;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "candidates")
public class CandidateEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String name;

  @NotBlank
  @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço")
  private String username;

  @Email(message = "Email inválido")
  private String email;

  @Length(min = 6, max = 20, message = "A senha deve ter no mínimo 6 caracteres e  no máximo 20 caracteres")
  private String password;
  private String description;
  private String curriculum;

  @CreationTimestamp
  private LocalDate createdAt;
}
