package com.tjrn.processosjudiciais;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.tjrn.processosjudiciais.model.Audiencia;
import com.tjrn.processosjudiciais.model.Processo;
import com.tjrn.processosjudiciais.model.Status;
import com.tjrn.processosjudiciais.model.TipoAudiencia;
import com.tjrn.processosjudiciais.service.AudienciaService;
import com.tjrn.processosjudiciais.service.ProcessoService;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class ProcessosjudiciaisApplicationTests {

	@Autowired
	private ProcessoService processoService;

	@Autowired
	private AudienciaService audienciaService;

	@Test
	public void testNumProcessoValidation() {
		Processo processo = new Processo();
		processo.setNumProcesso("1234567-89.2024.0.12.3456"); // processo válido
		processoService.save(processo);
		
		// processo.setNumProcesso("1234567-89.2024.0.12"); // processo inválido
		// processoService.save(processo);
	}

	@Test
	public void testProcessoStatus() {
		Processo processo = new Processo();
		processo.setNumProcesso("8372843-89.2024.0.12.3456");
		processo.setStatus(Status.ATIVO); // Testando status
		processo.setVara("Vara Cível");
		processo.setAssunto("Questionamento contratual");
		processo.setComarca("Parnamirim");
		
		processoService.save(processo);

		Audiencia audiencia = new Audiencia();
		audiencia.setData(LocalDate.of(2025, 6, 25));
		audiencia.setHora(LocalTime.of(10, 0));
		audiencia.setLocal("Centro Judiciário");
		audiencia.setTipoAudiencia(TipoAudiencia.CONCILIACAO);
		audiencia.setProcesso(processo);

		audienciaService.save(audiencia);
	}
	
	@Test
	public void testAudienciaDiaUtil() {
		Audiencia audiencia = new Audiencia();
		audiencia.setData(LocalDate.of(2025, 02, 14)); // Testando dia útil
		audiencia.setHora(LocalTime.of(10, 0));
		audiencia.setLocal("Tribunal de Justiça");
		audiencia.setTipoAudiencia(TipoAudiencia.CONCILIACAO);

		audiencia.setProcesso(new Processo());
		audiencia.getProcesso().setVara("Vara Cível");
		audiencia.getProcesso().setStatus(Status.ATIVO);
		audiencia.getProcesso().setAssunto("Disputa contratual");
		audiencia.getProcesso().setNumProcesso("1234567-12.2024.0.32.3456");
		audiencia.getProcesso().setComarca("Natal");
		
		processoService.save(audiencia.getProcesso());
		audienciaService.save(audiencia);
	}

}
