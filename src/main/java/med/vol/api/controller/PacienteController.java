package med.vol.api.controller;

import jakarta.validation.Valid;
import med.vol.api.entities.dto.paciente.AtualizarPaciente;
import med.vol.api.entities.dto.paciente.DadosPaciente;
import med.vol.api.entities.dto.paciente.DetalharPaciente;
import med.vol.api.entities.dto.paciente.ListarPacientes;
import med.vol.api.entities.Paciente;
import med.vol.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;
    @Transactional
    @PostMapping
    public ResponseEntity<DetalharPaciente> cadastrarPaciente(@RequestBody @Valid DadosPaciente dados, UriComponentsBuilder uriBuilder) {
        var paciente = new Paciente(dados);
        repository.save(paciente);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalharPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<ListarPacientes>> buscarPacientes(@PageableDefault(size = 5,sort = "nome")Pageable paginas) {
        var pacientes = repository.findAllByAtivoTrue(paginas).map(ListarPacientes::new);
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalharPaciente> buscarPacientePorID(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetalharPaciente(paciente));
    }

    @Transactional
    @PutMapping
    public ResponseEntity<DetalharPaciente> atualizarPaciente(@RequestBody @Valid AtualizarPaciente dados) {
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DetalharPaciente(paciente));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Paciente> inativarPaciente(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.inativar();
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<DetalharPaciente> ativarPaciente(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.ativar();
        return ResponseEntity.ok(new DetalharPaciente(paciente));
    }
}
