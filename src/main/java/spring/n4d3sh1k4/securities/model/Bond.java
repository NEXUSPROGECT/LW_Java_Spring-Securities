package spring.n4d3sh1k4.securities.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "bonds")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bond {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fin_asset_id")
    private FinAsset finAsset;

    @Column(name = "data_repayment")
    private LocalDateTime dataRepayment;

    @Column(name = "coupons_amount")
    private Integer couponsAmount;

    @Column(name = "coupons_rate")
    private Double couponsRate;
}