package cl.falabella.mserv.producto.domain.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Builder
@Getter
@ToString
@JsonPropertyOrder({
        "id",
        "sku",
        "name",
        "brand",
        "size",
        "price",
        "createdAt",
        "updatedAt",
        "_links",
        "_embedded"
})
@ApiModel(description = "Product entity")
public class ProductDTO implements Serializable {

    private static final long serialVersionUID = -4125726787225207739L;

    @JsonProperty("id")
    @ApiModelProperty(notes = "Identity", position = 1)
    private UUID id;

    @JsonProperty("sku")
    @ApiModelProperty(notes = "SKU", position = 2)
    private String sku;

    @JsonProperty("name")
    @ApiModelProperty(notes = "Name", position = 3)
    private String name;

    @JsonProperty("brand")
    @ApiModelProperty(notes = "Brand", position = 4)
    private String brand;

    @JsonProperty("size")
    @ApiModelProperty(notes = "Size", position = 5)
    private String size;

    @JsonProperty("price")
    @ApiModelProperty(notes = "Price", position = 6)
    private Double price;

    @JsonProperty("createdAt")
    @ApiModelProperty(notes = "Created at", position = 7)
    private LocalDateTime createdAt;

    @JsonProperty("updatedAt")
    @ApiModelProperty(notes = "Updated at", position = 8)
    private LocalDateTime updatedAt;

    @JsonProperty("_links")
    @ApiModelProperty(notes = "Links", position = 9)
    private Map<String, String> _links;

    @JsonProperty("_embedded")
    @ApiModelProperty(notes = "Embedded", position = 10)
    private Map<String, Object> _embedded;
}