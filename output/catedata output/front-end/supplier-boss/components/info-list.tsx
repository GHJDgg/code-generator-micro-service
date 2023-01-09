import React from 'react';
import { Relax } from 'plume2';
import { noop, Const } from 'qmkit';
import { Modal, Table } from 'antd';
import Moment from 'moment';
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
        rowKey="id"
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
    {
      key: 'cateFirstParentId',
      dataIndex: 'cateFirstParentId',
      title: '一级分类id',
    },
    {
      key: 'cateFirstParentName',
      dataIndex: 'cateFirstParentName',
      title: '一级分类名称',
    },
    {
      key: 'cateSecondParentId',
      dataIndex: 'cateSecondParentId',
      title: '二级分类id',
    },
    {
      key: 'cateSecondParentName',
      dataIndex: 'cateSecondParentName',
      title: '二级分类名称',
    },
    {
      key: 'cateId',
      dataIndex: 'cateId',
      title: '分类id',
    },
    {
      key: 'cateName',
      dataIndex: 'cateName',
      title: '分类名称',
    },
    {
      key: 'totalPrice',
      dataIndex: 'totalPrice',
      title: '销售额',
    },
    {
      key: 'totalCount',
      dataIndex: 'totalCount',
      title: '销量',
    },
    {
      key: 'orderCount',
      dataIndex: 'orderCount',
      title: '订单量',
    },
    {
      key: 'buyerCount',
      dataIndex: 'buyerCount',
      title: '购买人数',
    },
    {
      key: 'createTime',
      dataIndex: 'createTime',
      title: '创建时间',
      render: (time) => time ? Moment(time).format(Const.TIME_FORMAT).toString() : '-',
    },
    {
      key: 'day',
      dataIndex: 'day',
      title: '日期',
      render: (time) => time ? Moment(time).format(Const.TIME_FORMAT).toString() : '-',
    },
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
             onClick={() => this._onEdit(rowInfo.id)}
          >
            编辑
          </a>
        {/*</AuthWrapper>*/}
        {/*<AuthWrapper functionName={'f_xxx'}>*/}
          <a onClick={() => this._onDelete(rowInfo.id)}>
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
