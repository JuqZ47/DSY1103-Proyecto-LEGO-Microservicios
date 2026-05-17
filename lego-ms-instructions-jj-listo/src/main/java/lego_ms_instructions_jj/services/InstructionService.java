package lego_ms_instructions_jj.services;

import lego_ms_instructions_jj.dto.InstructionRequestDTO;
import lego_ms_instructions_jj.dto.InstructionResponseDTO;
import lego_ms_instructions_jj.exception.ResourceNotFoundException;
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

    public InstructionResponseDTO crearInstruction(InstructionRequestDTO request) {
        Instruction instruction = new Instruction();

        instruction.setSetId(request.getSetId());
        instruction.setTitulo(request.getTitulo());
        instruction.setDescripcion(request.getDescripcion());
        instruction.setPdfUrl(request.getPdfUrl());
        instruction.setPaginas(request.getPaginas());

        Instruction guardada = instructionRepository.save(instruction);

        return convertirAResponse(guardada);
    }

    public List<InstructionResponseDTO> listarInstructions() {
        return instructionRepository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    public InstructionResponseDTO obtenerInstructionPorId(Long id) {
        Instruction instruction = instructionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instruction no encontrada con id: " + id));

        return convertirAResponse(instruction);
    }

    public List<InstructionResponseDTO> obtenerPorSet(Long setId) {
        return instructionRepository.findBySetId(setId)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    public InstructionResponseDTO actualizarInstruction(Long id, InstructionRequestDTO request) {
        Instruction instruction = instructionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instruction no encontrada con id: " + id));

        instruction.setSetId(request.getSetId());
        instruction.setTitulo(request.getTitulo());
        instruction.setDescripcion(request.getDescripcion());
        instruction.setPdfUrl(request.getPdfUrl());
        instruction.setPaginas(request.getPaginas());

        Instruction actualizada = instructionRepository.save(instruction);

        return convertirAResponse(actualizada);
    }

    public void eliminarInstruction(Long id) {
        Instruction instruction = instructionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instruction no encontrada con id: " + id));

        instructionRepository.delete(instruction);
    }

    private InstructionResponseDTO convertirAResponse(Instruction instruction) {
        return new InstructionResponseDTO(
                instruction.getId(),
                instruction.getSetId(),
                instruction.getTitulo(),
                instruction.getDescripcion(),
                instruction.getPdfUrl(),
                instruction.getPaginas()
        );
    }
}
