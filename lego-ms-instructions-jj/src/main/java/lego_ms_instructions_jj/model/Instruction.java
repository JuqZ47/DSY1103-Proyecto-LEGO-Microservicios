package lego_ms_instructions_jj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "INSTRUCTIONS")
public class Instruction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long setId;

    private String titulo;

    private String descripcion;

    private String pdfUrl;

    private Integer paginas;

    public Instruction() {
    }

    public Instruction(Long id,
                       Long setId,
                       String titulo,
                       String descripcion,
                       String pdfUrl,
                       Integer paginas) {

        this.id = id;
        this.setId = setId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.pdfUrl = pdfUrl;
        this.paginas = paginas;
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
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