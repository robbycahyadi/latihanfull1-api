package id.co.mandiri.service;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.service.ServiceCrudDataTablesPattern;
import id.co.mandiri.dao.DeviceBrandDao;
import id.co.mandiri.entity.DeviceBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DeviceBrandService implements ServiceCrudDataTablesPattern<DeviceBrand, String> {

    @Autowired
    private DeviceBrandDao dao;

    @Override
    public DeviceBrand findId(String s) {
        return dao.findId(s);
    }

    @Override
    public List<DeviceBrand> findAll() {
        return null;
    }

    public Iterable<DeviceBrand> findAlls() {
        return dao.finddAlls();
    }

    @Override
    @Transactional
    public DeviceBrand save(DeviceBrand value) {
        return dao.save(value);
    }

    @Override
    @Transactional
    public DeviceBrand update(DeviceBrand value) {
        return dao.update(value);
    }

    @Override
    @Transactional
    public boolean remove(DeviceBrand value) {
        return dao.remove(value);
    }

    @Override
    @Transactional
    public boolean removeById(String s) {
        return dao.removeById(s);
    }

    @Override
    public DataTablesResponse<DeviceBrand> datatables(DataTablesRequest<DeviceBrand> params) {
        List<DeviceBrand> values = dao.datatables(params);
        Long rowCount = dao.datatables(params.getValue());
        return new DataTablesResponse<>(values, params.getDraw(), rowCount, rowCount);
    }
}
