package br.com.sobreiraromulo.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.sobreiraromulo.gestao_vagas.exceptions.UserNotFoundException;
import br.com.sobreiraromulo.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.sobreiraromulo.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;

@Service
public class ProfileCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  public ProfileCandidateResponseDTO execute(UUID idCandidate) {
    var candidate = this.candidateRepository.findById(idCandidate)
        .orElseThrow(() -> {
          throw new UserNotFoundException();
        });

    var candidateResponseDTO = ProfileCandidateResponseDTO.builder()
        .id(candidate.getId())
        .name(candidate.getName())
        .email(candidate.getEmail())
        .username(candidate.getUsername())
        .description(candidate.getDescription())
        .build();

    return candidateResponseDTO;

  }
}
