/*
 *
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.dinky.mapper;

import org.dinky.db.mapper.SuperMapper;
import org.dinky.model.UserRole;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** user role mapper interface */
@Mapper
public interface UserRoleMapper extends SuperMapper<UserRole> {

    /**
     * @param userId userId
     * @return user role relation
     */
    List<UserRole> getUserRoleByUserId(@Param("userId") int userId);

    /**
     * delete user role relation
     *
     * @param userRoleList list
     * @return int
     */
    int deleteBathRelation(@Param("userRoleList") List<UserRole> userRoleList);

    /**
     * delete user role relation by role id
     *
     * @param roleIds role id
     * @return delete status
     */
    int deleteByRoleIds(@Param("roleIds") List<Integer> roleIds);
}
