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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class EmoodjiIndexController {
    private final EmoodjiRepository repo;

    public EmoodjiIndexController(EmoodjiRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    ModelAndView index(@Value("${app.title}") String appTitle) {
        return new ModelAndView("index",
                Map.of("repo", repo.toString(),
                        "title", appTitle));
    }
}
