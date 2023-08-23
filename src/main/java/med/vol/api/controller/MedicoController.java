package med.vol.api.controller;

import jakarta.validation.Valid;
import med.vol.api.entities.dto.medico.AtualizarMedico;
import med.vol.api.entities.dto.medico.DadosMedico;
import med.vol.api.entities.dto.medico.DetalharMedico;
import med.vol.api.entities.dto.medico.ListarMedicos;
import med.vol.api.entities.Medico;
import med.vol.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity<DetalharMedico> cadatrarMedico(@RequestBody @Valid DadosMedico dados, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(dados);
        repository.save(medico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalharMedico(medico));
    }


    @GetMapping
    public ResponseEntity<Page<ListarMedicos>> buscarMedicos(@PageableDefault(sort = "nome") Pageable paginas) {
        var medicos = repository.findAllByAtivoTrue(paginas).map(ListarMedicos::new);
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalharMedico> exibirMeidcoPorId(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetalharMedico(medico));
    }

    @Transactional
    @PutMapping
    public ResponseEntity<DetalharMedico> atualizarDadosMedicos(@RequestBody @Valid AtualizarMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DetalharMedico(medico));
    }

    //Exclusão Lógica
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Medico> deletarMedico(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.inativar();
        return ResponseEntity.noContent().build();
    }

    //Reativando Medico
    @Transactional
    @PutMapping("{id}")
    public ResponseEntity<DetalharMedico> ativarMedico(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.ativar();
        return ResponseEntity.ok(new DetalharMedico(medico));
    }
}
