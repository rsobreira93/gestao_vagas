package br.com.sobreiraromulo.gestao_vagas.modules.cadidate.useCase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.sobreiraromulo.gestao_vagas.exceptions.JobNotFoundException;
import br.com.sobreiraromulo.gestao_vagas.exceptions.UserNotFoundException;
import br.com.sobreiraromulo.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.sobreiraromulo.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.sobreiraromulo.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import br.com.sobreiraromulo.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.sobreiraromulo.gestao_vagas.modules.candidate.useCases.ApplyJobCandidateUseCase;
import br.com.sobreiraromulo.gestao_vagas.modules.company.entities.JobEntity;
import br.com.sobreiraromulo.gestao_vagas.modules.company.repositories.JobRepository;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

  @InjectMocks
  private ApplyJobCandidateUseCase applyJobCandidateUseCase;

  @Mock
  private CandidateRepository candidateRepository;

  @Mock
  private ApplyJobRepository applyJobRepository;

  @Mock
  private JobRepository jobRepository;

  @Test
  @DisplayName("Should not be able to apply job with candidate not found")
  public void should_not_be_able_to_apply_job_with_candidate_not_found() {
    try {
      applyJobCandidateUseCase.execute(null, null);
    } catch (Exception e) {
      assertThat(e).isInstanceOf(UserNotFoundException.class);
    }
  }

  @Test
  @DisplayName("Should not be able to apply job with job not found")
  public void should_not_be_able_to_apply_job_with_job_not_found() {
    var idCandidate = UUID.randomUUID();
    var candidate = new CandidateEntity();

    candidate.setId(idCandidate);

    when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

    try {
      applyJobCandidateUseCase.execute(idCandidate, null);
    } catch (Exception e) {
      assertThat(e).isInstanceOf(JobNotFoundException.class);
    }
  }

  @Test
  @DisplayName("Should be able to apply job")
  public void should_be_able_to_apply_job() {
    var idCandidate = UUID.randomUUID();
    var idJob = UUID.randomUUID();

    var candidate = new CandidateEntity();
    var job = new JobEntity();

    candidate.setId(idCandidate);
    job.setId(idJob);

    when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

    when(jobRepository.findById(idJob)).thenReturn(Optional.of(job));

    var applyJob = ApplyJobEntity.builder()
        .candidateId(idCandidate)
        .jobId(idJob)
        .build();

    var applyJobCreated = ApplyJobEntity.builder()
        .id(UUID.randomUUID()).build();

    when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);

    var result = applyJobCandidateUseCase.execute(idCandidate, idJob);

    assertThat(result).hasFieldOrProperty("id");
    assertNotNull(result.getId());
  }

}
