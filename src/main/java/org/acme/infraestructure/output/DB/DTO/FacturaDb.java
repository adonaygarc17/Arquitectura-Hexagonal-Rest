package org.acme.infraestructure.output.DB.DTO;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Getter
@Setter
public class FacturaDb extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JdbcTypeCode(Types.VARCHAR)
    public UUID id;
    public double facturaSubtotal;
    public double totalDiscount;
    public double facturaTax;
    public double total;
    @OneToMany(cascade= CascadeType.ALL, mappedBy = "facturaDb",fetch = FetchType.EAGER)
    public List<ProductDb> products = new ArrayList<>();


}
