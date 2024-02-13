package org.acme.infraestructure.output.db.dto;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

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
    @Column(name = "id")
    public UUID id;
    @Column(name = "facturasubtotal")
    public float facturaSubtotal;
    @Column(name = "totaldiscount")
    public float totalDiscount;
    @Column(name = "facturatax")
    public float facturaTax;
    @Column(name = "total")
    public float total;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facturaDb",fetch = FetchType.EAGER)
    public List<ProductDb> products = new ArrayList<>();

}
