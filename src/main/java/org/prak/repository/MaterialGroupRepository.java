package org.prak.repository;

import org.prak.model.MaterialGroup;
import org.prak.repository.base.BaseRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialGroupRepository extends BaseRepository<MaterialGroup> {

    @Override
    protected String getQuery() {
        return "SELECT id, name FROM material_group ORDER BY name";
    }

    @Override
    protected MaterialGroup mapResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        return new MaterialGroup(id, name);
    }
}
