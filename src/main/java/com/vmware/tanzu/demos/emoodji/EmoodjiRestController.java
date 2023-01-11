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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmoodjiRestController {
    private final Logger logger = LoggerFactory.getLogger(EmoodjiRestController.class);
    private final EmoodjiService emoodjiService;

    public EmoodjiRestController(EmoodjiService emoodjiService) {
        this.emoodjiService = emoodjiService;
    }

    @GetMapping(value = "/api/v1/emoodji", produces = MediaType.TEXT_PLAIN_VALUE)
    String current() {
        final String emoodji = emoodjiService.getCurrent();
        logger.info("Get current emoodji: {}", emoodji);
        return emoodji;
    }

    @PostMapping(value = "/api/v1/emoodji", produces = MediaType.TEXT_PLAIN_VALUE)
    String switchToNext() {
        final String emoodji = emoodjiService.switchToNext();
        logger.info("Set new emoodji: {}", emoodji);
        return emoodji;
    }
}
