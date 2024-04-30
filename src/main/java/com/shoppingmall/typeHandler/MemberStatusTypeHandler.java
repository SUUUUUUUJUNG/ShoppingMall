package com.shoppingmall.typeHandler;

import com.shoppingmall.domain.enums.MemberStatus;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(MemberStatus.class)
public class MemberStatusTypeHandler extends BaseTypeHandler<MemberStatus> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, MemberStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.name());
    }

    @Override
    public MemberStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String name = rs.getString(columnName);
        return name == null ? null : MemberStatus.valueOf(name.toUpperCase());
    }

    @Override
    public MemberStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String name = rs.getString(columnIndex);
        return name == null ? null : MemberStatus.valueOf(name.toUpperCase());
    }

    @Override
    public MemberStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String name = cs.getString(columnIndex);
        return name == null ? null : MemberStatus.valueOf(name.toUpperCase());
    }
}

