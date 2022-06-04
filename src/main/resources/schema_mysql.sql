--
--    Copyright 2015-2019 the original author or authors.
--
--    Licensed under the Apache License, Version 2.0 (the "License");
--    you may not use this file except in compliance with the License.
--    You may obtain a copy of the License at
--
--       http://www.apache.org/licenses/LICENSE-2.0
--
--    Unless required by applicable law or agreed to in writing, software
--    distributed under the License is distributed on an "AS IS" BASIS,
--    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
--    See the License for the specific language governing permissions and
--    limitations under the License.
--

drop table if exists city;

CREATE TABLE `city` (
`ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
`name` varchar(255) NOT NULL COMMENT '城市名',
`state` tinyint(1) NOT NULL COMMENT '状态',
`country` varchar(255) NOT NULL COMMENT '国家',
PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='城市信息表';


