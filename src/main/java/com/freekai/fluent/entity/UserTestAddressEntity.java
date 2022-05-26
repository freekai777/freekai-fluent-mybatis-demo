package com.freekai.fluent.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.annotation.RefMethod;
import cn.org.atool.fluent.mybatis.annotation.TableField;
import cn.org.atool.fluent.mybatis.annotation.TableId;
import cn.org.atool.fluent.mybatis.base.RichEntity;
import com.freekai.custom.FreekaiMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * UserTestAddressEntity: 数据映射实体定义
 *
 * @author Powered By Fluent Mybatis
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Data
@Accessors(
    chain = true
)
@EqualsAndHashCode(
    callSuper = false
)
@AllArgsConstructor
@NoArgsConstructor
@FluentMybatis(
    table = "user_test_address",
    schema = "test",
    superMapper = FreekaiMapper.class
)
public class UserTestAddressEntity extends RichEntity {
  private static final long serialVersionUID = 1L;

  @TableId(
      value = "id",
      auto = false
  )
  private Integer id;

  @TableField("address")
  private String address;

  @TableField("user_test_id")
  private Integer userTestId;

  @Override
  public final Class entityClass() {
    return UserTestAddressEntity.class;
  }

  /**
   * @see com.freekai.fluent.IEntityRelation#findUserTestV2OfUserTestAddressEntity(UserTestAddressEntity)
   */
  @RefMethod
  public UserTestEntity findUserTestV2() {
    return super.invoke("findUserTestV2", true);
  }
}
