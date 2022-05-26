package com.freekai.fluent.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.annotation.LogicDelete;
import cn.org.atool.fluent.mybatis.annotation.RefMethod;
import cn.org.atool.fluent.mybatis.annotation.TableField;
import cn.org.atool.fluent.mybatis.annotation.TableId;
import cn.org.atool.fluent.mybatis.base.RichEntity;
import com.freekai.custom.FreekaiMapper;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * UserTestEntity: 数据映射实体定义
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
    table = "user_test",
    schema = "test",
    superMapper = FreekaiMapper.class,
    desc = "userTest表的简单描述"
)
public class UserTestEntity extends RichEntity {
  private static final long serialVersionUID = 1L;

  @TableId(
      value = "id",
      auto = false
  )
  private Integer id;

  @TableField("address_id")
  private Integer addressId;

  @TableField("age")
  private Integer age;

  @TableField("create_time")
  private Date createTime;

  @TableField("tel")
  private String tel;

  @TableField("update_time")
  private Date updateTime;

  @TableField("user_name")
  private String userName;

  @TableField("version")
  private Integer version;

  @TableField(
      value = "is_delete",
      insert = "0",
      desc = "是否删除"
  )
  @LogicDelete
  private Boolean isDelete;

  @Override
  public final Class entityClass() {
    return UserTestEntity.class;
  }

  /**
   * @see com.freekai.fluent.IEntityRelation#findAddressV2OfUserTestEntity(java.util.List)
   */
  @RefMethod("userTestId = id")
  public UserTestAddressEntity findAddressV2() {
    return super.invoke("findAddressV2", true);
  }
}
