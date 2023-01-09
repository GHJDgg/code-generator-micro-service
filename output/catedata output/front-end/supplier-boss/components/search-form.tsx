import React from 'react';
import { Button, Form, Input, DatePicker } from 'antd';
import { Relax } from 'plume2';
import { noop, Const } from 'qmkit';

const FormItem = Form.Item;
const RangePicker = DatePicker.RangePicker;

@Relax
export default class SearchForm extends React.Component<any, any> {
  props: {
    relaxProps?: {
      onSearch: Function;
    };
  };

  static relaxProps = {
    onSearch: noop,
  };

  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    const { onSearch} = this.props.relaxProps;

    return (
      <div style={{marginTop:10}}>
        <Form className="filter-content" layout="inline">
          <FormItem>
            <Input
              addonBefore={'一级分类id'}
              onChange={(e) => {
                this.setState({
                  cateFirstParentId: e.target.value
                });
              }}
            />
          </FormItem>
          <FormItem>
            <Input
              addonBefore={'一级分类名称'}
              onChange={(e) => {
                this.setState({
                  cateFirstParentName: e.target.value
                });
              }}
            />
          </FormItem>
          <FormItem>
            <Input
              addonBefore={'二级分类id'}
              onChange={(e) => {
                this.setState({
                  cateSecondParentId: e.target.value
                });
              }}
            />
          </FormItem>
          <FormItem>
            <Input
              addonBefore={'二级分类名称'}
              onChange={(e) => {
                this.setState({
                  cateSecondParentName: e.target.value
                });
              }}
            />
          </FormItem>
          <FormItem>
            <Input
              addonBefore={'分类id'}
              onChange={(e) => {
                this.setState({
                  cateId: e.target.value
                });
              }}
            />
          </FormItem>
          <FormItem>
            <Input
              addonBefore={'分类名称'}
              onChange={(e) => {
                this.setState({
                  cateName: e.target.value
                });
              }}
            />
          </FormItem>
          <FormItem>
            <Input
              addonBefore={'销售额'}
              onChange={(e) => {
                this.setState({
                  totalPrice: e.target.value
                });
              }}
            />
          </FormItem>
          <FormItem>
            <Input
              addonBefore={'销量'}
              onChange={(e) => {
                this.setState({
                  totalCount: e.target.value
                });
              }}
            />
          </FormItem>
          <FormItem>
            <Input
              addonBefore={'订单量'}
              onChange={(e) => {
                this.setState({
                  orderCount: e.target.value
                });
              }}
            />
          </FormItem>
          <FormItem>
            <Input
              addonBefore={'购买人数'}
              onChange={(e) => {
                this.setState({
                  buyerCount: e.target.value
                });
              }}
            />
          </FormItem>
          <FormItem>
            <RangePicker
              getCalendarContainer={() =>
                  document.getElementById('page-content')
              }
              format={Const.TIME_FORMAT}
              showTime={true}
              placeholder={['创建时间开始', '创建时间截止']}
              onChange={(date, dateStr) => {
                let createTimeBegin = null;
                let createTimeEnd = null;
                if (date.length > 0) {
                  createTimeBegin = dateStr[0];
                  createTimeEnd = dateStr[1];
                }
                this.setState({ createTimeBegin, createTimeEnd });
              }}
            />
          </FormItem>
          <FormItem>
            <RangePicker
              getCalendarContainer={() =>
                  document.getElementById('page-content')
              }
              format={Const.TIME_FORMAT}
              showTime={true}
              placeholder={['日期开始', '日期截止']}
              onChange={(date, dateStr) => {
                let dayBegin = null;
                let dayEnd = null;
                if (date.length > 0) {
                  dayBegin = dateStr[0];
                  dayEnd = dateStr[1];
                }
                this.setState({ dayBegin, dayEnd });
              }}
            />
          </FormItem>
          <FormItem>
            <Button
              type="primary"
              icon="search"
              onClick={() => {
                const params = this.state;
                onSearch(params);
              }}
            >
              搜索
            </Button>
          </FormItem>
        </Form>
      </div>
    );
  }
}
