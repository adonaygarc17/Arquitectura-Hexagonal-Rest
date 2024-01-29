package org.acme.infraestructure.output.db.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductDb extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String title;
    public  String category;
    public String brand;
    public String thumbnail;
    public double discountPercentage;
    public  double discountTotal;
    public double finalPrice;
    @ManyToOne
    @JoinColumn(name = "facturaDb_id")
    @JsonIgnore
    public FacturaDb facturaDb;

}
