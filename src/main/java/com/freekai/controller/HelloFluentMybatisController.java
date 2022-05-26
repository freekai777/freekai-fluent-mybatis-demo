package com.freekai.controller;

import cn.org.atool.fluent.mybatis.base.free.FreeQuery;
import cn.org.atool.fluent.mybatis.base.model.FieldMapping;
import cn.org.atool.fluent.mybatis.model.StdPagedList;
import com.freekai.dto.SimpleUserTestDTO;
import com.freekai.entity.HelloFluentEntity;
//import com.freekai.mapper.HelloFluentMapper;
import com.freekai.fluent.entity.UserTestEntity;
import com.freekai.fluent.mapper.UserTestMapper;
import com.freekai.fluent.wrapper.UserTestQuery;
import com.freekai.mapper.HelloFluentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
public class HelloFluentMybatisController {

    @Autowired
    private UserTestMapper userTestMapper;

    @PostMapping("/addHello")
    public int addHello(){
        UserTestEntity entity = new UserTestEntity();
        entity.setId(1000*new Random().nextInt(10) + 1);
        final int insert = userTestMapper.insertWithPk(entity);
        return insert;
    }

    @PutMapping("/updateUser")
    public int updateUser(@RequestParam String id){
        final UserTestEntity byIdAndIsDelete = userTestMapper.findByIdAndIsDelete(id, false);
        return 1;
    }

//    @GetMapping("/findByUserName")
//    public int findByUserName(@RequestParam String userName){
//        List<UserTestEntity> list = userTestMapper.findByUserName(userName);
//        return list.size();
//    }

    @GetMapping("/user")
    public UserTestEntity get(@RequestParam Integer userId ){
        final UserTestEntity byId = userTestMapper.findById(userId);
        return byId;
    }

    /**
     * 聚合的语法
     * @return
     */
    @GetMapping("/agg")
    public List<Map<String,Object>> aggregation(){
        final List<Map<String, Object>> maps = userTestMapper.listMaps(userTestMapper.query().select.userName("userNameAlias")
                .count("cc").end().groupBy.userName().end().orderBy.desc("cc").end());
        return maps;
    }

    /**
     * 分页查询
     * @param userName
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/users")
    public StdPagedList<UserTestEntity> list(@RequestParam(required = false, value = "userName") String userName,
                                             @RequestParam(value = "page", defaultValue = "1",required = false) Integer page,
                                             @RequestParam(value = "size", defaultValue = "10",required = false) Integer size){
        final StdPagedList<UserTestEntity> res = userTestMapper.stdPagedEntity(new UserTestQuery().where.userName()
                .like(userName, !StringUtils.isEmpty(userName)).end().limit((page - 1) * size, size));
        return res;
    }

    /**
     * 分页且映射成自定义实体类的查询
     *
     * 查询出来的 字段取的别名一定要求在 映射的实体类中存在。否则会报错！！
     *
     */
    @GetMapping("/users2")
    public StdPagedList<SimpleUserTestDTO> listWithDtos(@RequestParam(required = false, value = "userName") String userName,
                                                @RequestParam(value = "page", defaultValue = "1",required = false) Integer page,
                                                @RequestParam(value = "size", defaultValue = "10",required = false) Integer size){
        final StdPagedList<SimpleUserTestDTO> res = userTestMapper.stdPagedPoJo(SimpleUserTestDTO.class, new UserTestQuery()
                .select.id("idNew").createTime("createTime").end().where.userName()
                .like(userName, !StringUtils.isEmpty(userName)).end().limit((page - 1) * size, size));
        return res;
    }

    /**
     * 自定义sql查询
     * 通过占位符的形式 username
     * @return
     */
    @GetMapping("/custom_sql")
    public List<Map<String,Object>> customQuery(@RequestParam String userName){
        Map<String,String> param = new HashMap<>(16);
        param.put("userNN", userName + "%");

        final FreeQuery freeQuery = new FreeQuery(null).customizedByPlaceholder(" select  * from user_test t where t.user_name like #{userNN} ", param);
        final List<Map<String, Object>> maps = userTestMapper.listMaps(freeQuery);
        return maps;
    }
}
