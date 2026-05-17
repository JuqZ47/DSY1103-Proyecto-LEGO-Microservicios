package lego_ms_instructions_jj.controller;

import lego_ms_instructions_jj.client.AuthClient;
import lego_ms_instructions_jj.dto.InstructionRequestDTO;
import lego_ms_instructions_jj.dto.InstructionResponseDTO;
import lego_ms_instructions_jj.services.InstructionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructions")
public class InstructionController {

    private final InstructionService instructionService;
    private final AuthClient authClient;

    public InstructionController(InstructionService instructionService, AuthClient authClient) {
        this.instructionService = instructionService;
        this.authClient = authClient;
    }

    @PostMapping
    public InstructionResponseDTO crearInstruction(
            @RequestHeader("Authorization") String token,
            @Valid @RequestBody InstructionRequestDTO request) {

        validar(token);
        return instructionService.crearInstruction(request);
    }

    @GetMapping
    public List<InstructionResponseDTO> listarInstructions(@RequestHeader("Authorization") String token) {
        validar(token);
        return instructionService.listarInstructions();
    }

    @GetMapping("/{id}")
    public InstructionResponseDTO obtenerInstructionPorId(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id) {

        validar(token);
        return instructionService.obtenerInstructionPorId(id);
    }

    @GetMapping("/set/{setId}")
    public List<InstructionResponseDTO> obtenerPorSet(
            @RequestHeader("Authorization") String token,
            @PathVariable Long setId) {

        validar(token);
        return instructionService.obtenerPorSet(setId);
    }

    @PutMapping("/{id}")
    public InstructionResponseDTO actualizarInstruction(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @Valid @RequestBody InstructionRequestDTO request) {

        validar(token);
        return instructionService.actualizarInstruction(id, request);
    }

    @DeleteMapping("/{id}")
    public String eliminarInstruction(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id) {

        validar(token);
        instructionService.eliminarInstruction(id);
        return "Instruction eliminada correctamente";
    }

    private void validar(String token) {
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Token requerido");
        }

        authClient.validarToken(token);
    }
}
