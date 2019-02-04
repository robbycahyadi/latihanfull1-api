package id.co.mandiri.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "master_device")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MasterDevice {

    @Id
    @GenericGenerator(name = "uuid_gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid_gen")
    @Column(name = "id", nullable = false, length = 64)
    private String id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;
    @Column(name = "code", nullable = false, length = 100)
    private String code;
    @Lob
    @Type(type = "text")
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryDevice category;

    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false)
    private MasterColor color;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private DeviceBrand brand;

    @ManyToOne
    @JoinColumn(name = "condition_id", nullable = false)
    private DeviceCondition condition;

    @ManyToOne
    @JoinColumn(name = "unit_capacity_id", nullable = false)
    private DeviceUnitCapacity unitCapacity;

    @ManyToOne
    @JoinColumn(name = "loan_status_id", nullable = false)
    private DeviceLoanStatus loanStatus;
}
