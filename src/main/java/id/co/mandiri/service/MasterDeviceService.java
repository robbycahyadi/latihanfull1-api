package id.co.mandiri.service;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.service.ServiceCrudDataTablesPattern;
import id.co.mandiri.dao.MasterDeviceDao;
import id.co.mandiri.entity.MasterDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MasterDeviceService implements ServiceCrudDataTablesPattern<MasterDevice, String> {

    @Autowired
    private MasterDeviceDao dao;

    @Override
    public MasterDevice findId(String s) {
        return dao.findId(s);
    }

    @Override
    public List<MasterDevice> findAll() {
        return null;
    }

    public Iterable<MasterDevice> findAlls() {
        return dao.findAlls();
    }

    @Override
    @Transactional
    public MasterDevice save(MasterDevice value) {
        return dao.save(value);
    }

    @Override
    @Transactional
    public MasterDevice update(MasterDevice value) {
        return dao.update(value);
    }

    @Override
    @Transactional
    public boolean remove(MasterDevice value) {
        return dao.remove(value);
    }

    @Override
    @Transactional
    public boolean removeById(String s) {
        return dao.removeById(s);
    }

    @Override
    public DataTablesResponse<MasterDevice> datatables(DataTablesRequest<MasterDevice> params) {
        List<MasterDevice> values = dao.datatables(params);
        Long rowCount = dao.datatables(params.getValue());
        return new DataTablesResponse<>(values, params.getDraw(), rowCount, rowCount);
    }
}
