package lego_ms_instructions_jj.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class InstructionRequestDTO {

    @NotNull
    private Long setId;

    @NotBlank
    private String titulo;

    @NotBlank
    private String descripcion;

    @NotBlank
    private String pdfUrl;

    @Positive
    private Integer paginas;

    public InstructionRequestDTO() {
    }

    public InstructionRequestDTO(Long setId, String titulo, String descripcion, String pdfUrl, Integer paginas) {
        this.setId = setId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.pdfUrl = pdfUrl;
        this.paginas = paginas;
    }

    public Long getSetId() {
        return setId;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public void setSetId(Long setId) {
        this.setId = setId;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public void setPaginas(Integer paginas) {
        this.paginas = paginas;
    }
}
