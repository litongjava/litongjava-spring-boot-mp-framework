package com.litong.spring.boot.v158.mp.layui.v255.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.litong.spring.boot.v158.mp.utils.LReflectionUtils;
import com.litong.spring.boot.v158.mp.utils.array.ListUtils;
import com.litong.spring.boot.v158.mp.utils.string.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.litong.spring.boot.v158.mp.utils.LayuiUtils;
import com.litong.spring.boot.v158.mp.vo.JsonBean;
import com.litong.spring.boot.v158.mp.vo.PageJsonBean;


import lombok.extern.slf4j.Slf4j;

/**
 * @author bill robot
 * @date 2020年6月10日_上午10:37:50 
 * @version 1.0 
 * @desc
 */
@Slf4j
public class MpCurdController<Service extends IService<Entity>, Entity> {
  @Autowired
  private Service s;

  @RequestMapping("list")
  public PageJsonBean<Entity> list(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, String orderBy, boolean isAsc, Entity e) {
    e = LReflectionUtils.convertEmpytStringToNull(e);
    log.info("pageSize:{},pageNo:{},e {}", pageSize, pageNo, e);
    Map<String, Object> map = LReflectionUtils.convertObjectToMap(e);
    QueryWrapper<Entity> queryWrapper = new QueryWrapper<>();
    if (orderBy != null) {
      queryWrapper.orderBy(true, isAsc, orderBy);
    }

    for (Map.Entry<String, Object> m : map.entrySet()) {
      if (m.getKey().equals("id")) {
        queryWrapper.eq("id", m.getValue());
      } else {
        queryWrapper.like(m.getKey(), m.getValue());
      }
    }

    Page<Entity> page = new Page<>(pageNo, pageSize);
    IPage<Entity> result = s.page(page, queryWrapper);
    PageJsonBean<Entity> pageJsonBean = new PageJsonBean<>(result);
    return pageJsonBean;
  }

  @RequestMapping("listColumn")
  public JsonBean<List<Entity>> listColumn(String column, Entity e) {
    String mName = "listColumn";
    log.info("{},column {} entity {}", mName, column, e);
    QueryWrapper<Entity> queryWrapper = new QueryWrapper<Entity>();
    queryWrapper.select("id", column);
    Map<String, Object> map = LReflectionUtils.convertObjectToMap(e);
    for (Map.Entry<String, Object> m : map.entrySet()) {
      if (m.getKey().equals("id")) {
        queryWrapper.eq("id", m.getValue());
      } else {
        queryWrapper.like(m.getKey(), m.getValue());
      }
    }
    List<Entity> list = s.list(queryWrapper);
    JsonBean<List<Entity>> jsonBean = new JsonBean<>(list);
    return jsonBean;
  }

  @RequestMapping("getById")
  public JsonBean<Entity> get(String id) {
    log.info("get by id {}", id);
    Entity byId = s.getById(id);
    JsonBean<Entity> jsonBean = new JsonBean<>(byId);
    return jsonBean;
  }

  @RequestMapping("removeById")
  public JsonBean<Boolean> removeById(String id) {
    String methodName = "removeById";
    log.info("{} by id {}", methodName, id);
    boolean isNumeric = StringUtils.isNumeric(id);
    boolean b = false;
    if (isNumeric) {
      b = s.removeById(Integer.parseInt(id));
    } else {
      b = s.removeById(id);
    }

    return buildJsonBean(methodName, b);
  }

  @RequestMapping("removeByIds")
  public JsonBean<Boolean> removeByIds(@RequestParam(value = "ids[]") String[] ids) {
    String methodName = "removeByIds";
    log.info("{} {}", methodName, ids);
    if (ids.length < 1) {
      return new JsonBean<Boolean>();
    }
    boolean isNumeric = StringUtils.isNumeric(ids[0]);
    List<? extends Serializable> idList = null;
    if (isNumeric) {
      int[] intIds = new int[ids.length];
      for (int i = 0; i < ids.length; i++) {
        intIds[i] = Integer.parseInt(ids[i]);
      }
      idList = ListUtils.toList(intIds);
    } else {
      idList = ListUtils.toList(ids);
    }
    boolean b = s.removeByIds(idList);
    return buildJsonBean(methodName, b);
  }

  @RequestMapping("save")
  public JsonBean<Boolean> save(Entity e) {
    String methodName = "save";
    log.info("{} {}", methodName, e);
    boolean b = s.saveOrUpdate(e);
    return buildJsonBean(methodName, b);
  }

  @RequestMapping("update")
  public JsonBean<Boolean> update(Entity e) {
    String methodName = "update";
    log.info("{} {}", methodName, e);
    boolean b = s.saveOrUpdate(e);
    return buildJsonBean(methodName, b);
  }

  @RequestMapping("saveOrUpdate")
  public JsonBean<Boolean> saveOrUpdate(Entity e) {
    String methodName = "saveOrUpdate";
    log.info("{} {}", methodName, e);
    boolean b = s.saveOrUpdate(e);
    return buildJsonBean(methodName, b);
  }

  public JsonBean<Boolean> buildJsonBean(String methodName, boolean b) {
    return LayuiUtils.buildJsonBean(methodName, b);
  }
}
