package lego_ms_instructions_jj.service;

import lego_ms_instructions_jj.model.Instruction;
import lego_ms_instructions_jj.repository.InstructionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructionService {

    private final InstructionRepository instructionRepository;

    public InstructionService(InstructionRepository instructionRepository) {
        this.instructionRepository = instructionRepository;
    }

    public List<Instruction> obtenerInstructions() {
        return instructionRepository.findAll();
    }

    public Instruction obtenerInstructionPorId(Long id) {

        return instructionRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Instruction no encontrada"));
    }

    public List<Instruction> obtenerInstructionsPorSet(Long setId) {
        return instructionRepository.findBySetId(setId);
    }

    public Instruction guardarInstruction(Instruction instruction) {

        if (instruction.getPaginas() <= 0) {
            throw new RuntimeException("La cantidad de paginas debe ser mayor a 0");
        }

        return instructionRepository.save(instruction);
    }

    public Instruction actualizarInstruction(Long id,
                                             Instruction datosInstruction) {

        Instruction instruction = obtenerInstructionPorId(id);

        instruction.setSetId(datosInstruction.getSetId());
        instruction.setTitulo(datosInstruction.getTitulo());
        instruction.setDescripcion(datosInstruction.getDescripcion());
        instruction.setPdfUrl(datosInstruction.getPdfUrl());
        instruction.setPaginas(datosInstruction.getPaginas());

        return instructionRepository.save(instruction);
    }

    public void eliminarInstruction(Long id) {

        Instruction instruction = obtenerInstructionPorId(id);

        instructionRepository.delete(instruction);
    }
}