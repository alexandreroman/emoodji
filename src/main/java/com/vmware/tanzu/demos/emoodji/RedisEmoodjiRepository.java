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

import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Profile("redis")
public class RedisEmoodjiRepository implements EmoodjiRepository {
    private final StringRedisTemplate redis;

    public RedisEmoodjiRepository(StringRedisTemplate redis) {
        this.redis = redis;
    }

    @Override
    public int getCurrent() {
        final var i = redis.opsForValue().get("emoodji");
        return i == null ? 0 : Integer.parseInt(i);
    }

    @Override
    public int switchToNext() {
        return redis.opsForValue().increment("emoodji").intValue();
    }

    @Override
    public String toString() {
        return "Redis Emoodji Repository";
    }
}
