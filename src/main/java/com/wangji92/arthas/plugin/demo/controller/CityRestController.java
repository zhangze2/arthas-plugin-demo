/*
 * Copyright 2015-2021 the original author or authors. Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */
package com.wangji92.arthas.plugin.demo.controller;

import com.wangji92.arthas.plugin.demo.domain.City;
import com.wangji92.arthas.plugin.demo.mapper.CityMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/cities")
@Api(value = "CityRestController", description = "CityRestController API", position = 100, protocols = "http")
@RestController
public class CityRestController {


    private final CityMapper cityMapper;

    public CityRestController(CityMapper cityMapper){
        this.cityMapper = cityMapper;
    }

    @ApiOperation(
            value = "findByState",
            notes = "findByState：",
            produces="application/json, application/xml",
            consumes="application/json, application/xml",
            response = City.class)
    @GetMapping("{state}")
    City getCity(@PathVariable String state) {
        return cityMapper.findByState(state);
    }

}
