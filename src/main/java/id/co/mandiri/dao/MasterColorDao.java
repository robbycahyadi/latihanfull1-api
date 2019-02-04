package id.co.mandiri.dao;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.dao.DaoCrudDataTablesPattern;
import id.co.mandiri.entity.MasterColor;
import id.co.mandiri.repository.MasterColorRepository;
import id.co.mandiri.utils.QueryComparator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MasterColorDao implements DaoCrudDataTablesPattern<MasterColor, String> {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private MasterColorRepository masterColorRepository;

    @Override
    public MasterColor findId(String s) {
        return masterColorRepository.findOne(s);
    }

    @Override
    @Deprecated
    public List<MasterColor> findAll() {
        return null;
    }

    public Iterable<MasterColor> findAlls() {
        return masterColorRepository.findAll();
    }

    @Override
    public MasterColor save(MasterColor masterColor) {
        return masterColorRepository.save(masterColor);
    }

    @Override
    public MasterColor update(MasterColor masterColor) {
        return masterColorRepository.save(masterColor);
    }

    @Override
    public boolean remove(MasterColor masterColor) {
        masterColorRepository.delete(masterColor);
        return true;
    }

    @Override
    public boolean removeById(String s) {
        masterColorRepository.delete(s);
        return true;
    }

    @Override
    public List<MasterColor> datatables(DataTablesRequest<MasterColor> params) {
        String baseQuery = "select id, name, code, description\n" +
                "from master_color\n" +
                "where 1 = 1 ";

        MasterColor param = params.getValue();

        MasterColorQueryCompare compare = new MasterColorQueryCompare(baseQuery);
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
                new MasterColor(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code"),
                        resultSet.getString("description")
                ));
    }

    @Override
    public Long datatables(MasterColor param) {
        String baseQuery = "select count(*) \n" +
                "from master_color\n" +
                "where 1 = 1 ";

        MasterColorDao.MasterColorQueryCompare compare = new MasterColorDao.MasterColorQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        return this.jdbcTemplate.queryForObject(
                query.toString(),
                values,
                (resultSet, i) -> resultSet.getLong(1)
        );

    }

    private class MasterColorQueryCompare implements QueryComparator<MasterColor> {

        private MapSqlParameterSource parameterSource;
        private StringBuilder query;

        MasterColorQueryCompare(String query) {
            this.parameterSource = new MapSqlParameterSource();
            this.query = new StringBuilder(query);
        }


        @Override
        public StringBuilder getQuery(MasterColor param) {
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
