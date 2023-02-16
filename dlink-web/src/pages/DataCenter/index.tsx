import React from 'react';
import { Tabs } from 'antd';
import type { TabsProps } from 'antd';
import MetaDataContainer from './MetaData';
import DataBaseTableList from '../RegistrationCenter/DataBase';
import {l} from "@/utils/intl";

const items: TabsProps['items'] = [
  {
    key: '1',
    label: l('menu.datacenter.metadata'),
    children: <MetaDataContainer />,
  },
  {
    key: '2',
    label: l('menu.registration.database'),
    children: <DataBaseTableList />,
  },
];

const ClusterManage: React.FC = () => <Tabs style={{marginTop: '-24px'}} defaultActiveKey="1" items={items} />;

export default ClusterManage;
