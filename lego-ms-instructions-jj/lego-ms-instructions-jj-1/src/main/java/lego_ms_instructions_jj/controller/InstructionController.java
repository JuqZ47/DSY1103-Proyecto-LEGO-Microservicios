package lego_ms_instructions_jj.controller;

import lego_ms_instructions_jj.model.Instruction;
import lego_ms_instructions_jj.service.InstructionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructions")
public class InstructionController {

    private final InstructionService instructionService;

    public InstructionController(InstructionService instructionService) {
        this.instructionService = instructionService;
    }

    @GetMapping
    public List<Instruction> obtenerInstructions() {
        return instructionService.obtenerInstructions();
    }

    @GetMapping("/{id}")
    public Instruction obtenerInstructionPorId(@PathVariable Long id) {
        return instructionService.obtenerInstructionPorId(id);
    }

    @GetMapping("/set/{setId}")
    public List<Instruction> obtenerInstructionsPorSet(@PathVariable Long setId) {
        return instructionService.obtenerInstructionsPorSet(setId);
    }

    @PostMapping
    public Instruction guardarInstruction(@RequestBody Instruction instruction) {
        return instructionService.guardarInstruction(instruction);
    }

    @PutMapping("/{id}")
    public Instruction actualizarInstruction(@PathVariable Long id, @RequestBody Instruction instruction) {
        return instructionService.actualizarInstruction(id, instruction);
    }

    @DeleteMapping("/{id}")
    public String eliminarInstruction(@PathVariable Long id) {
        instructionService.eliminarInstruction(id);
        return "Instruction eliminada correctamente";
    }
}