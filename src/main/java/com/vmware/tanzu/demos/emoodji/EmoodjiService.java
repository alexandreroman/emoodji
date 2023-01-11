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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmoodjiService {
    private static final Map<Integer, String> EMOODJI_MAPPER = new HashMap<>(10);

    static {
        EMOODJI_MAPPER.put(0, "\uD83D\uDE16");
        EMOODJI_MAPPER.put(1, "\uD83D\uDE1E");
        EMOODJI_MAPPER.put(2, "\uD83D\uDE14");
        EMOODJI_MAPPER.put(3, "\uD83D\uDE12");
        EMOODJI_MAPPER.put(4, "\uD83D\uDE0C");
        EMOODJI_MAPPER.put(5, "\uD83D\uDE0A");
        EMOODJI_MAPPER.put(6, "\uD83D\uDE04");
        EMOODJI_MAPPER.put(7, "\uD83D\uDE01");
        EMOODJI_MAPPER.put(8, "\uD83D\uDE06");
        EMOODJI_MAPPER.put(9, "\uD83D\uDE02");
    }

    private final Logger logger = LoggerFactory.getLogger(EmoodjiService.class);

    private final EmoodjiRepository repo;

    public EmoodjiService(EmoodjiRepository repo) {
        this.repo = repo;
    }

    String switchToNext() {
        return mapToEmoodji(repo.switchToNext());
    }

    String getCurrent() {
        return mapToEmoodji(repo.getCurrent());
    }

    private String mapToEmoodji(int rawEmoodji) {
        final int index = rawEmoodji % 10;
        final String emoodji = EMOODJI_MAPPER.get(index);
        if (emoodji == null) {
            logger.warn("Unknown emoodji index: {}", index);
            return EMOODJI_MAPPER.get(0);
        }
        return emoodji;
    }
}
