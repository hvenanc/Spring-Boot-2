package med.vol.api.entities.dto.paciente;

import med.vol.api.entities.Endereco;
import med.vol.api.entities.Paciente;

public record DetalharPaciente(Long id, String nome, String cpf, String email, String telefone, Endereco endereco) {

    public DetalharPaciente(Paciente paciente) {
        this(paciente.getId(),paciente.getNome(),paciente.getCpf(),paciente.getEmail(),paciente.getTelefone(),paciente.getEndereco());
    }
}
