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

import {Divider, Typography} from 'antd';
import React from 'react';
import {connect} from 'umi';
import {StateType} from '@/pages/DataStudio/model';
import {Scrollbars} from 'react-custom-scrollbars';
import {VERSION} from "@/components/Common/Version";
import {l} from "@/utils/intl";

const {Title, Paragraph, Text} = Typography;

const StudioHome = (props: any) => {


  const {toolHeight} = props;

  return (
    <Scrollbars style={{height: toolHeight}}>
      <Typography style={{padding: '15px'}}>
        <Title level={4}>欢迎使用采集分发子系统</Title>
        <Title level={5}>产品介绍</Title>
        <Paragraph>
          采集分发子系统基于开源产品dinky进行二次开发，以Apache Flink为基础，将Flink CDC作为数据采集的核心工具，提供CDC开发与调试、任务运维管理、元数据注册管理、Flink集群管理等服务能力，致力于全行实时数仓建设的探索与实践。
        </Paragraph>
        <Title level={5}>功能介绍</Title>
        <Paragraph>
          <ul>
            <li>支持多种数据采集开发模式：支持FlinkSQL CDC、流水线配置化（计划中）两种方式进行数据采集任务开发</li>
            <li>沉浸式 FlinkSQL 数据开发：自动提示补全、语法高亮、语句美化、在线调试、语法校验等功能</li>
            <li>支持FlinkSQL多版本开发及多种执行模式：Local、Standalone、Yarn Session、Yarn Per-Job、Yarn Application</li>
            <li>支持 Flink Catalog、数据源元数据在线查询及管理</li>
            <li>支持自动托管的 SavePoint/CheckPoint 恢复及触发机制：最近一次、最早一次、指定一次等</li>
            <li>支持实时任务运维：上线下线、作业信息、集群信息、作业快照、异常信息、历史版本等</li>
            <li>支持Flink实例和集群资源管理：提供集群实例、集群配置的注册、心跳检查、资源回收、yarn集群管理等能力</li>
          </ul>
        </Paragraph>
      </Typography>
    </Scrollbars>
  );
};

export default connect(({Studio}: { Studio: StateType }) => ({
  toolHeight: Studio.toolHeight,
}))(StudioHome);
