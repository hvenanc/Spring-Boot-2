package med.vol.api.controller;

import jakarta.validation.Valid;
import med.vol.api.entities.dto.AtualizarPaciente;
import med.vol.api.entities.dto.DadosPaciente;
import med.vol.api.entities.dto.ListarPacientes;
import med.vol.api.entities.Paciente;
import med.vol.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;
    @Transactional
    @PostMapping
    public void cadastrarPaciente(@RequestBody @Valid DadosPaciente dados) {
        repository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<ListarPacientes> buscarPacientes(@PageableDefault(size = 10,sort = "nome")Pageable paginas) {
        return repository.findAllByAtivoTrue(paginas).map(ListarPacientes::new);
    }

    @Transactional
    @PutMapping
    public void atualizarPaciente(@RequestBody @Valid AtualizarPaciente dados) {
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void inativarPaciente(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.inativar();
    }

    @Transactional
    @PutMapping("/{id}")
    public void ativarPaciente(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.ativar();
    }
}
