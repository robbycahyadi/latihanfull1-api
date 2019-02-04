package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.DeviceBrand;
import id.co.mandiri.entity.MasterDevice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MasterDeviceMapperRequestUpdate extends ObjectMapper<MasterDevice, MasterDeviceDTO.MasterDeviceRequestUpdateDTO> {

    MasterDeviceMapperRequestUpdate converter = Mappers.getMapper(MasterDeviceMapperRequestUpdate.class);
}
