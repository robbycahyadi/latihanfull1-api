package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.MasterDevice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MasterDeviceMapperRequestNew extends ObjectMapper<MasterDevice, MasterDeviceDTO.MasterDeviceRequestNewDTO> {

    MasterDeviceMapperRequestNew converter = Mappers.getMapper(MasterDeviceMapperRequestNew.class);

}
