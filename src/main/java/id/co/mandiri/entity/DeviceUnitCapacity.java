package id.co.mandiri.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "device_unit_capacity")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "masterDevices")
public class DeviceUnitCapacity {

    public DeviceUnitCapacity(String id, String name, String code, String description) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
    }

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

    @JsonIgnore
    @OneToMany(mappedBy = "unitCapacity")
    private List<MasterDevice> masterDevices = new ArrayList<>();
}
