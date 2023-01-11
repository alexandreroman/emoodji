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

CREATE SEQUENCE IF NOT EXISTS EMOODJI_SEQ AS INTEGER MINVALUE 0 START WITH 0 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS EMOODJI (id INT NOT NULL, current INT NOT NULL, PRIMARY KEY (id));
INSERT INTO EMOODJI VALUES (0, nextval('EMOODJI_SEQ')) ON CONFLICT DO NOTHING;
