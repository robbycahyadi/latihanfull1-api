package id.co.mandiri.controller;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import id.co.mandiri.dto.DeviceUnitCapacityDTO;
import id.co.mandiri.dto.DeviceUnitCapacityMapperRequestNew;
import id.co.mandiri.dto.DeviceUnitCapacityMapperRequestUpdate;
import id.co.mandiri.entity.DeviceUnitCapacity;
import id.co.mandiri.service.DeviceUnitCapacityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/master/unit-capacity-device")
public class DeviceUnitCapacityController {

    @Autowired
    private DeviceUnitCapacityService service;

    @PostMapping("/datatables")
    public DataTablesResponse<DeviceUnitCapacity> datatables(
            @RequestParam(required = false, value = "draw", defaultValue = "0") Long draw,
            @RequestParam(required = false, value = "start", defaultValue = "0") Long start,
            @RequestParam(required = false, value = "length", defaultValue = "10") Long length,
            @RequestParam(required = false, value = "order[0][column]", defaultValue = "0") Long iSortCol0,
            @RequestParam(required = false, value = "order[0][dir]", defaultValue = "asc") String sSortDir0,
            @RequestBody(required = false) DeviceUnitCapacity params) {

        if (params == null) params = new DeviceUnitCapacity();
        log.info("draw: {}, start: {}, length: {}, type: {}", draw, start, length, params);
        return service.datatables(
                new DataTablesRequest(draw, length, start, sSortDir0, iSortCol0, params)
        );
    }

    @GetMapping("/list")
    public ResponseEntity<List<DeviceUnitCapacity>> list() {
        List<DeviceUnitCapacity> list = service.findAll();
        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/lists")
    public Iterable<DeviceUnitCapacity> lists() {
        return service.findAlls();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceUnitCapacity> findById(@PathVariable("id") String id) {
        DeviceUnitCapacity params = service.findId(id);
        if (params != null) {
            return new ResponseEntity<>(params, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new DeviceUnitCapacity(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/")
    public ResponseEntity<DeviceUnitCapacity> save(@Valid @RequestBody DeviceUnitCapacityDTO.DeviceUnitCapacityRequestNewDTO params) {
        DeviceUnitCapacity value = DeviceUnitCapacityMapperRequestNew.converter.convertToEntity(params);
        value = service.save(value);
        return new ResponseEntity<>(value, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<DeviceUnitCapacity> update(@Valid @RequestBody DeviceUnitCapacityDTO.DeviceUnitCapacityRequestUpdateDTO params) {
        DeviceUnitCapacity value = DeviceUnitCapacityMapperRequestUpdate.converter.convertToEntity(params);
        value = service.save(value);
        return new ResponseEntity<>(value, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeviceUnitCapacity> delete(@PathVariable("id") String id) {
        boolean deleted = service.removeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
