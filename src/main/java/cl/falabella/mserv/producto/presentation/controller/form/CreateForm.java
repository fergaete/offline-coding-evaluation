package cl.falabella.mserv.producto.presentation.controller.form;

import cl.falabella.mserv.producto.domain.vo.Brand;
import cl.falabella.mserv.producto.domain.vo.Name;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@JsonPropertyOrder({"name", "brand", "price", "size", "sku", "image"})
@ApiModel(description = "Create form")
public class CreateForm {

    @NotNull(message = "El atributo Name no puede ser vacío.")
    @NotBlank
    @Size(
            min = Name.MIN_LENGTH,
            max = Name.MAX_LENGTH,
            message = "El atributo Name debe poseer un mínimo de " + Name.MAX_LENGTH + " y máximo de " + Name.MIN_LENGTH+ " caracteres."
    )
    private String name;

    @NotNull(message = "El atributo Brand no puede ser vacío.")
    @NotBlank
    @Size(
            min = Brand.MIN_LENGTH,
            max = Brand.MAX_LENGTH,
            message = "El atributo Brand debe poseer un mínimo de " + Brand.MAX_LENGTH + " y máximo de " + Brand.MIN_LENGTH+ " caracteres."
    )
    private String brand;

    @NotNull(message = "El atributo Size no puede ser vacío.")
    @NotBlank
    private String size;

    @NotNull(message = "El atributo SKU no puede ser vacío.")
    @NotBlank
    private String sku;

    @NotNull(message = "El atributo Price no puede ser vacío.")
    @Positive
    private Double price;

    @NotNull(message = "El atributo Image no puede ser vacío.")
    @NotBlank
    @URL
    private String image;
}