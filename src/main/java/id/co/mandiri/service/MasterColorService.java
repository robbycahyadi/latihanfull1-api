package id.co.mandiri.service;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.service.ServiceCrudDataTablesPattern;
import id.co.mandiri.dao.MasterColorDao;
import id.co.mandiri.entity.MasterColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MasterColorService implements ServiceCrudDataTablesPattern<MasterColor, String> {

    @Autowired
    private MasterColorDao colorDao;

    @Override
    public MasterColor findId(String s) {
        return colorDao.findId(s);
    }

    @Override
    public List<MasterColor> findAll() {
        return null;
    }

    @Override
    @Transactional
    public MasterColor save(MasterColor value) {
        return colorDao.save(value);
    }

    @Override
    @Transactional
    public MasterColor update(MasterColor value) {
        return colorDao.update(value);
    }

    @Override
    @Transactional
    public boolean remove(MasterColor value) {
        return colorDao.remove(value);
    }

    @Override
    @Transactional
    public boolean removeById(String s) {
        return colorDao.removeById(s);
    }

    @Override
    public DataTablesResponse<MasterColor> datatables(DataTablesRequest<MasterColor> params) {
        List<MasterColor> values = colorDao.datatables(params);
        Long rowCount = colorDao.datatables(params.getValue());
        return new DataTablesResponse<>(values, params.getDraw(), rowCount, rowCount);
    }
}
