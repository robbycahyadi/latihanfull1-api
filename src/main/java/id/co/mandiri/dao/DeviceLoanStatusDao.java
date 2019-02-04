package id.co.mandiri.dao;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.dao.DaoCrudDataTablesPattern;
import id.co.mandiri.entity.DeviceLoanStatus;
import id.co.mandiri.repository.DeviceLoanStatusRepository;
import id.co.mandiri.utils.QueryComparator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeviceLoanStatusDao implements DaoCrudDataTablesPattern<DeviceLoanStatus, String> {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private DeviceLoanStatusRepository repository;

    @Override
    public DeviceLoanStatus findId(String s) {
        return repository.findOne(s);
    }

    @Override
    @Deprecated
    public List<DeviceLoanStatus> findAll() {
        return null;
    }

    public Iterable<DeviceLoanStatus> findAlls() {
        return repository.findAll();
    }

    @Override
    public DeviceLoanStatus save(DeviceLoanStatus deviceLoanStatus) {
        return repository.save(deviceLoanStatus);
    }

    @Override
    public DeviceLoanStatus update(DeviceLoanStatus deviceLoanStatus) {
        return repository.save(deviceLoanStatus);
    }

    @Override
    public boolean remove(DeviceLoanStatus deviceLoanStatus) {
        repository.delete(deviceLoanStatus);
        return true;
    }

    @Override
    public boolean removeById(String s) {
        repository.delete(s);
        return true;
    }

    @Override
    public List<DeviceLoanStatus> datatables(DataTablesRequest<DeviceLoanStatus> params) {
        String baseQuery = "select id, name, code, description\n" +
                "from device_loan_status \n" +
                "where 1 = 1 ";

        DeviceLoanStatus param = params.getValue();

        DeviceLoanStatusQueryCompare compare = new DeviceLoanStatusQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        switch (params.getColOrder().intValue()) {
            case 0:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by id asc ");
                else
                    query.append(" order by id desc ");
                break;
            case 1:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by name asc ");
                else
                    query.append(" order by name desc ");
                break;
            case 2:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by code asc ");
                else
                    query.append(" order by code desc ");
                break;
            case 3:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by description asc ");
                else
                    query.append(" order by description desc ");
                break;
            default:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by id asc ");
                else
                    query.append(" order by id desc ");
                break;
        }

        query.append("limit :limit offset :offset");
        values.addValue("offset", params.getStart());
        values.addValue("limit", params.getLength());

        return this.jdbcTemplate.query(query.toString(), values, (resultSet, i) ->
                new DeviceLoanStatus(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code"),
                        resultSet.getString("description")
                ));
    }

    @Override
    public Long datatables(DeviceLoanStatus param) {
        String baseQuery = "select count(*) \n" +
                "from device_loan_status \n" +
                "where 1 = 1 ";

        DeviceLoanStatusQueryCompare compare = new DeviceLoanStatusQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        return this.jdbcTemplate.queryForObject(
                query.toString(),
                values,
                (resultSet, i) -> resultSet.getLong(1)
        );

    }

    private class DeviceLoanStatusQueryCompare implements QueryComparator<DeviceLoanStatus> {

        private MapSqlParameterSource parameterSource;
        private StringBuilder query;

        DeviceLoanStatusQueryCompare(String query) {
            this.parameterSource = new MapSqlParameterSource();
            this.query = new StringBuilder(query);
        }


        @Override
        public StringBuilder getQuery(DeviceLoanStatus param) {
            if (StringUtils.isNoneBlank(param.getId())) {
                query.append(" and lower(id) like :id ");
                parameterSource.addValue("id",
                        new StringBuilder("%")
                                .append(param.getId().toLowerCase())
                                .append("%")
                                .toString());
            }

            if (StringUtils.isNoneBlank(param.getName())) {
                query.append(" and lower(name) like :name ");
                parameterSource.addValue("name", new StringBuilder("%")
                        .append(param.getName().toLowerCase())
                        .append("%")
                        .toString());
            }

            if (StringUtils.isNoneBlank(param.getCode())) {
                query.append(" and lower(code) like :code ");
                parameterSource.addValue("code", new StringBuilder("%")
                        .append(param.getName().toLowerCase())
                        .append("%")
                        .toString());
            }

            if (StringUtils.isNoneBlank(param.getDescription())) {
                query.append(" and lower(description) like :description ");
                parameterSource.addValue("description", new StringBuilder("%")
                        .append(param.getDescription().toLowerCase())
                        .append("%")
                        .toString());
            }
            return query;
        }

        @Override
        public MapSqlParameterSource getParameters() {
            return this.parameterSource;
        }
    }
}
