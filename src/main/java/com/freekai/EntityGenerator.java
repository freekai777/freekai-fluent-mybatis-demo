package com.freekai;

import cn.org.atool.generator.FileGenerator;
import cn.org.atool.generator.annotation.Relation;
import cn.org.atool.generator.annotation.RelationType;
import cn.org.atool.generator.annotation.Table;
import cn.org.atool.generator.annotation.Tables;
import com.freekai.custom.FreekaiMapper;
import org.junit.Test;

public class EntityGenerator {
    //数据源url
    static final String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&serverTimezone=Asia/Shanghai";

    //数据库用户名
    static final String username = "root";

    //数据库密码
    static final String password = "root";

    @Test
    public void generate() throws Exception {
        //引用配置类，build方法允许有多个配置类
        FileGenerator.build(Empty.class);
    }

    @Tables(
            //设置数据库连接信息
            url = url,username = username,password = password,
            driver = "com.mysql.jdbc.Driver",
            //设置entity类生成src目录，相对于user.dir
            srcDir = "src/main/java",
            //设置entity类的package值
            basePack = "com.freekai.fluent",
            //设置dao接口和实现的src目录，相对于user.dir
            daoDir = "src/main/java",
            //设置哪些表要生成Entity文件
            tables = {@Table(value = {"user_test_address","user_test"}, logicDeleted = "is_delete", superMapper = FreekaiMapper.class)},
            relations = {
            @Relation(method = "findAddressV2", source = "user_test", target = "user_test_address", type = RelationType.OneWay_0_1
                    , where = "id=user_test_id"),
            @Relation(method = "findUserTestV2", source = "user_test_address", target = "user_test", type = RelationType.OneWay_0_1)
            }
    )
    static class Empty{ //类名随便取，只是配置定义的一个载体

    }

}
