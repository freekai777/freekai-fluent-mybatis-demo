package com.freekai.custom;

import cn.org.atool.fluent.mybatis.If;
import cn.org.atool.fluent.mybatis.base.IEntity;
import cn.org.atool.fluent.mybatis.base.crud.IQuery;
import cn.org.atool.fluent.mybatis.base.crud.IWrapper;
import cn.org.atool.fluent.mybatis.base.entity.IMapping;
import cn.org.atool.fluent.mybatis.base.free.FreeQuery;
import cn.org.atool.fluent.mybatis.base.mapper.IEntityMapper;
import cn.org.atool.fluent.mybatis.base.mapper.IMapper;
import cn.org.atool.fluent.mybatis.base.mapper.IRichMapper;
import cn.org.atool.fluent.mybatis.base.model.SqlOp;
import cn.org.atool.fluent.mybatis.base.provider.SqlKitFactory;
import cn.org.atool.fluent.mybatis.exception.FluentMybatisException;
import cn.org.atool.fluent.mybatis.segment.WhereBase;
import cn.org.atool.fluent.mybatis.segment.model.WrapperData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 自定义mapper，在代码生成时 配置一个supperMapper
 * 或者是在entity实体类上的@FluentMybatis注解中配置superMapper值
 * @FluentMybatis(
 *     table = "user_test",
 *     schema = "test",
 *     superMapper = FreekaiMapper.class
 * )
 *
 *
 * @param <E>
 */
public interface FreekaiMapper<E extends IEntity> extends IEntityMapper<E>, IRichMapper<E> {

    default E findByIdAndIsDelete(Object id, Boolean isDelete) {
        final IMapping mapping = this.mapping();
        IQuery query = mapping.emptyQuery();
        String primary = mapping.primaryId(true);
        // 逻辑删除的字段
        final String loginDeleteColumn = mapping.logicDeleteColumn();
        final WhereBase apply = query.where().apply(primary, SqlOp.EQ, new Object[]{id});
        if(!StringUtils.isEmpty(loginDeleteColumn)){
            apply.and.apply(loginDeleteColumn, SqlOp.EQ, isDelete);
        }
        final List<E> list = this.listEntity(query);
        if (If.isEmpty(list)) {
            return null;
        } else if (list.size() == 1) {
            return list.get(0);
        } else {
            throw new FluentMybatisException("Expected one result (or null) to be returned, but found " + list.size() + " results.");
        }
    }
}
