package com.lanou.module.service.impl;

import com.lanou.module.domain.Module;
import com.lanou.module.mapper.ModuleMapper;
import com.lanou.module.service.ModuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/11/16.
 */
@Service("moduleService")
public class ModuleServiceImpl implements ModuleService {
    @Resource
    private ModuleMapper moduleMapper;

    public List<Module> findAllModule() {
        return moduleMapper.findAllModule();
    }
}
