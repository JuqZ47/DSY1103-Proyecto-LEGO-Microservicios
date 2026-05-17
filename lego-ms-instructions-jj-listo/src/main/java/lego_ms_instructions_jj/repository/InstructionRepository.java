package lego_ms_instructions_jj.repository;

import lego_ms_instructions_jj.model.Instruction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstructionRepository extends JpaRepository<Instruction, Long> {

    List<Instruction> findBySetId(Long setId);
}