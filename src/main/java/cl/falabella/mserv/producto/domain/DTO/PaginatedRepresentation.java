package cl.falabella.mserv.producto.domain.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

@Builder
@Getter
@ToString
@JsonPropertyOrder({"page", "limit", "pages", "total", "_links", "_embedded"})
@ApiModel(description = "Paginación representada de una entidad")
public class PaginatedRepresentation implements Serializable {

    private static final long serialVersionUID = -8867720910082035295L;

    public static final Integer DEFAULT_PAGE = 0;
    public static final Integer DEFAULT_SIZE = 10;

    @JsonProperty("page")
    @ApiModelProperty(notes = "Página.", position = 1)
    private Integer page;

    @JsonProperty("limit")
    @ApiModelProperty(notes = "Limite por página.", position = 2)
    private Integer limit;

    @JsonProperty("pages")
    @ApiModelProperty(notes = "Total de páginas", position = 3)
    private Integer pages;

    @JsonProperty("total")
    @ApiModelProperty(notes = "Total de elementos.", position = 4)
    private Long total;

    @JsonProperty("_links")
    @ApiModelProperty(notes = "Links", position = 5)
    private Map<String, String> _links;

    @JsonProperty("_embedded")
    @ApiModelProperty(notes = "Elementos paginados.", position = 6)
    private Map<String, Object> _embedded;

    public static Integer totalPages(Integer pages) {
        return pages - 1;
    }
}
