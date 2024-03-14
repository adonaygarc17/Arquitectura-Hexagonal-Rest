package org.acme.infraestructure.output.db.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @Column(name = "id")
    public Long id;
    @Column(name = "title")
    public String title;
    @Column(name = "category")
    public  String category;
    @Column(name = "brand")
    public String brand;
    @Column(name = "thumbnail")
    public String thumbnail;
    @Column(name = "discountpercentage")
    public float discountPercentage;
    @Column(name = "discounttotal")
    public  float discountTotal;
    @Column(name = "finalprice")
    public float finalPrice;
    @ManyToOne
    @JoinColumn(name = "facturaDb_id")
    @JsonBackReference
    public FacturaDb facturaDb;

}
