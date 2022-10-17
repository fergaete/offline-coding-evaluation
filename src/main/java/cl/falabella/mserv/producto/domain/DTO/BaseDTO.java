package cl.falabella.mserv.producto.domain.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.hateoas.Link;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public abstract class BaseDTO {

    @JsonProperty("createdAt")
    @ApiModelProperty(notes = "Fecha creación.", position = 51)
    private LocalDateTime createdAt;

    @JsonProperty("updatedAt")
    @ApiModelProperty(notes = "Fecha última actualización", position = 53)
    private LocalDateTime updatedAt;

    @JsonProperty("_links")
    @ApiModelProperty(notes = "Links", position = 55)
    private List<Link> _links;

    public BaseDTO(LocalDateTime createdAt, LocalDateTime updatedAt, List<Link> _links) {
        this.createdAt      = createdAt;
        this.updatedAt      = updatedAt;
        this._links         = _links;
    }
}