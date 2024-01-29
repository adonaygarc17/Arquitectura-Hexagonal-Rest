package org.acme.infraestructure.output.db.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateDTO {
    public int id;
    public String title;
    public String category;
    public String brand;
    public double discountPercentage;
    public UUID facturadbId;
}
