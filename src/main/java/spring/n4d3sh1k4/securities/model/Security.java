package spring.n4d3sh1k4.securities.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "securities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Security {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fin_asset_id")
    private FinAsset finAsset;

    @Column(name = "date_accommodation")
    private LocalDateTime dateAccommodation;

    @Column(name = "date_report")
    private LocalDateTime dateReport;
}