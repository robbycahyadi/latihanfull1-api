package id.co.mandiri.controller;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import id.co.mandiri.dto.MasterDeviceDTO;
import id.co.mandiri.dto.MasterDeviceMapperRequestNew;
import id.co.mandiri.dto.MasterDeviceMapperRequestUpdate;
import id.co.mandiri.entity.MasterDevice;
import id.co.mandiri.service.MasterDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/master/master-device")
public class MasterDeviceController {

    @Autowired
    private MasterDeviceService service;

    @PostMapping("/datatables")
    public DataTablesResponse<MasterDevice> datatables(
            @RequestParam(required = false, value = "draw", defaultValue = "0") Long draw,
            @RequestParam(required = false, value = "start", defaultValue = "0") Long start,
            @RequestParam(required = false, value = "length", defaultValue = "10") Long length,
            @RequestParam(required = false, value = "order[0][column]", defaultValue = "0") Long iSortCol0,
            @RequestParam(required = false, value = "order[0][dir]", defaultValue = "asc") String sSortDir0,
            @RequestBody(required = false) MasterDevice params) {

        if (params == null) params = new MasterDevice();
        log.info("draw: {}, start: {}, length: {}, type: {}", draw, start, length, params);
        return service.datatables(
                new DataTablesRequest(draw, length, start, sSortDir0, iSortCol0, params)
        );
    }

    @GetMapping("/list")
    public ResponseEntity<List<MasterDevice>> list() {
        List<MasterDevice> list = service.findAll();
        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/lists")
    public Iterable<MasterDevice> lists() {
        return service.findAlls();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MasterDevice> findById(@PathVariable("id") String id) {
        MasterDevice params = service.findId(id);
        if (params != null) {
            return new ResponseEntity<>(params, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MasterDevice(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/")
    public ResponseEntity<MasterDevice> save(@Valid @RequestBody MasterDeviceDTO.MasterDeviceRequestNewDTO params) {
        MasterDevice value = MasterDeviceMapperRequestNew.converter.convertToEntity(params);
        value = service.save(value);
        return new ResponseEntity<>(value, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<MasterDevice> update(@Valid @RequestBody MasterDeviceDTO.MasterDeviceRequestUpdateDTO params) {
        MasterDevice value = MasterDeviceMapperRequestUpdate.converter.convertToEntity(params);
        value = service.save(value);
        return new ResponseEntity<>(value, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MasterDevice> delete(@PathVariable("id") String id) {
        boolean deleted = service.removeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
