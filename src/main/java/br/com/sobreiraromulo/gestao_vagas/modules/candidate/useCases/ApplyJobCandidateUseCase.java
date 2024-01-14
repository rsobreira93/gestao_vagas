package br.com.sobreiraromulo.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sobreiraromulo.gestao_vagas.exceptions.JobNotFoundException;
import br.com.sobreiraromulo.gestao_vagas.exceptions.UserNotFoundException;
import br.com.sobreiraromulo.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.sobreiraromulo.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class ApplyJobCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private JobRepository jobRepository;

  public void execute(UUID idCandidate, UUID idJob) {
    var candidate = this.candidateRepository.findById(idCandidate)
        .orElseThrow(() -> {
          throw new UserNotFoundException();
        });

    var job = this.jobRepository.findById(idJob)
        .orElseThrow(() -> {
          throw new JobNotFoundException();
        });
  }
}
