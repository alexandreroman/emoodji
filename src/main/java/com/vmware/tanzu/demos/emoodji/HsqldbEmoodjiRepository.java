/*
 * Copyright (c) 2023 VMware, Inc. or its affiliates
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vmware.tanzu.demos.emoodji;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Profile("!redis")
@ConditionalOnProperty(value = "spring.sql.init.platform", havingValue = "hsqldb")
@EnableAutoConfiguration(exclude = RedisAutoConfiguration.class)
public class HsqldbEmoodjiRepository implements EmoodjiRepository {
    private final JdbcTemplate jdbc;

    public HsqldbEmoodjiRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    @Transactional(readOnly = true)
    public int getCurrent() {
        return jdbc.queryForObject("SELECT e.current FROM EMOODJI e WHERE e.id=0;", Integer.class);
    }

    @Override
    @Transactional
    public int switchToNext() {
        jdbc.update("UPDATE EMOODJI SET current = NEXT VALUE FOR EMOODJI_SEQ;");
        return jdbc.queryForObject("SELECT e.current FROM EMOODJI e WHERE e.id=0;", Integer.class);
    }

    @Override
    public String toString() {
        return "HSQLDB Emoodji Repository";
    }
}
