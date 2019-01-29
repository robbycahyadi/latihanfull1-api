package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.CategoryDevice;
import id.co.mandiri.entity.MasterColor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MasterColorMapperRequestUpdate extends ObjectMapper<MasterColor, MasterColorDTO.MasterColorRequestUpdateDTO> {

    MasterColorMapperRequestUpdate converter = Mappers.getMapper(MasterColorMapperRequestUpdate.class);
}
