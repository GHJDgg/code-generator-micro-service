<#assign timeTypeFlag=false>
<#list table.notPkColumns as column>
  <#if column.columnNameLower != "updateTime">
    <#if column.isDateTimeColumn>
      <#assign timeTypeFlag=true>
    </#if>
  </#if>
</#list>
import React from 'react';
import { Relax } from 'plume2';
import { noop, Const } from 'qmkit';
import { Modal, Table } from 'antd';
<#if timeTypeFlag>
import Moment from 'moment';
</#if>
import { IList } from 'typings/globalType';

const confirm = Modal.confirm;
const styles = {
	edit: {
		paddingRight: 10
	}
} as any;

@Relax
export default class InfoList extends React.Component<any, any> {
  props: {
    relaxProps?: {
      loading: boolean;
      total: number;
      pageSize: number;
      dataList: IList;
      current: number;
      checkedIds: IList;
      onSelect: Function;
      onDelete: Function;
      onEdit: Function;
      queryPage: Function;
    };
  };

  static relaxProps = {
    loading: 'loading',
    total: 'total',
    pageSize: 'pageSize',
    dataList: 'dataList',
    current: 'current',
    checkedIds: 'checkedIds',
    onSelect: noop,
    onDelete: noop,
    onEdit: noop,
    queryPage: noop
  };

  render() {
    const {
      loading,
      total,
      pageSize,
      dataList,
      current,
      checkedIds,
      onSelect,
      queryPage
    } = this.props.relaxProps;
    return (
      <Table
        rowKey="${table.idColumn.columnNameLower}"
        loading={loading}
        dataSource={dataList.toJS()}
        columns={this._columns}
        rowSelection={{
          type: 'checkbox',
	        selectedRowKeys: checkedIds.toJS(),
          onChange: (checkedRowKeys) => {
            onSelect(checkedRowKeys);
          }
        }}
        pagination={{
          total,
          pageSize,
          current: current,
          onChange: (pageNum, pageSize) => {
            queryPage({ pageNum: pageNum - 1, pageSize });
          }
        }}
      />
    );
  }

  /**
   * 列表数据的column信息
   */
  _columns = [
<#list table.notPkColumns as column>
  <#if column.columnNameLower != "updateTime" && column.columnNameLower != "updatePerson" && column.columnNameLower != "delFlag">
    {
      key: '${column.columnNameLower}',
      dataIndex: '${column.columnNameLower}',
      title: '${column.columnAlias}',
    <#if column.javaType == "LocalDateTime">
      render: (time) => time ? Moment(time).format(Const.TIME_FORMAT).toString() : '-',
    <#elseif column.javaType == "LocalDate">
      render: (time) => time ? Moment(time).format(Const.DAY_FORMAT).toString() : '-',
    </#if>
    },
  </#if>
</#list>
    {
      key: 'option',
      title: '操作',
      render: (rowInfo) => this._getOption(rowInfo)
    }
  ];

  /**
   * 获取操作项
   */
  _getOption = (rowInfo) => {
    return (
      <div>
        {/*<AuthWrapper functionName={'f_xxx'}>*/}
          <a style={styles.edit}
             onClick={() => this._onEdit(rowInfo.${table.idColumn.columnNameLower})}
          >
            编辑
          </a>
        {/*</AuthWrapper>*/}
        {/*<AuthWrapper functionName={'f_xxx'}>*/}
          <a onClick={() => this._onDelete(rowInfo.${table.idColumn.columnNameLower})}>
            删除
          </a>
        {/*</AuthWrapper>*/}
      </div>
    );
  };

  /**
   * 编辑信息
   */
	_onEdit = (id) => {
		const { onEdit } = this.props.relaxProps;
		onEdit(id);
	};

  /**
   * 单个删除信息
   */
	_onDelete = (id) => {
		const { onDelete } = this.props.relaxProps;
		confirm({
			title: '确认删除',
			content: '是否确认删除？删除后不可恢复。',
			onOk() {
				onDelete(id);
			},
			onCancel() {}
		});
	};
}
