package com.litong.spring.boot.v158.mp.layui.v255.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.litong.spring.boot.v158.mp.vo.JsonBean;
import com.litong.spring.boot.v158.mp.vo.PageJsonBean;
import com.litong.utils.array.LArrays;
import com.litong.utils.reflection.LReflectionUtils;

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
  private Service service;

  @RequestMapping("list")
  public PageJsonBean<Entity> list(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, Entity e) {
    e = LReflectionUtils.convertEmpytStringToNull(e);
    log.info("pageSize:{},pageNo:{},e {}", pageSize, pageNo, e);
    QueryWrapper<Entity> queryWrapper = new QueryWrapper<>(e);
    Page<Entity> page = new Page<>(pageNo, pageSize);
    IPage<Entity> result = service.page(page, queryWrapper);
    PageJsonBean<Entity> pageJsonBean = new PageJsonBean<>(result);
    return pageJsonBean;
  }

  @RequestMapping("get")
  public JsonBean<Entity> get(Integer id) {
    log.info("get by id {}", id);
    Entity byId = service.getById(id);
    JsonBean<Entity> jsonBean = new JsonBean<>(byId);
    return jsonBean;
  }

  @RequestMapping("del")
  public JsonBean<Void> del(Integer id) {
    String methodName = "delete";
    log.info("{} by id {}", methodName, id);
    boolean b = service.removeById(id);
    return buildJsonBean(methodName, b);
  }

  @RequestMapping("saveOrUpdate")
  public JsonBean<Void> saveOrUpdate(Entity e) {
    String methodName = "saveOrUpdate";
    log.info("{} {}", methodName, e);
    boolean b = service.saveOrUpdate(e);
    return buildJsonBean(methodName, b);
  }

  @RequestMapping("removeByIds")
  public JsonBean<Void> removeByIds(@RequestParam(value = "ids[]") int[] ids) {
    String methodName = "removeByIds";
    List<Integer> idList = LArrays.toList(ids);
    log.info("{} {}", methodName, idList);
    boolean b = service.removeByIds(idList);
    return buildJsonBean(methodName, b);
  }

  private JsonBean<Void> buildJsonBean(String methodName, boolean b) {
    JsonBean<Void> jsonBean = new JsonBean<>();
    if (b) {
      return jsonBean;
    } else {
      jsonBean.setCode(-1);
      jsonBean.setMsg(methodName + " fail");
    }
    return jsonBean;
  }
}
