import React from 'react';
import { Form, Input, DatePicker } from 'antd';
import moment from 'moment';
import { Const, Tips } from 'qmkit';
import { IMap } from 'typings/globalType';

const FormItem = Form.Item;
const formItemLayout = {
  labelCol: {
    span: 2,
    xs: { span: 24 },
    sm: { span: 6 }
  },
  wrapperCol: {
    span: 24,
    xs: { span: 24 },
    sm: { span: 14 }
  }
};

export default class EditForm extends React.Component<any, any> {
  props: {
    form: any,
    relaxProps?: {
      formData: IMap;
      editFormData: Function;
    };
  };

  render() {
    const { formData } = this.props.relaxProps;
    const { getFieldDecorator } = this.props.form;

    return (
      <Form className="login-form errorFeedback">
        <FormItem {...formItemLayout} label="一级分类id">
          {
            getFieldDecorator('cateFirstParentId', {
              onChange: (e) => this._changeFormData('cateFirstParentId', e.target.value),
              initialValue: formData.get('cateFirstParentId') || formData.get('cateFirstParentId') == 0 ? formData.get('cateFirstParentId').toString() : null
            })(<Input />)
          }
          <Tips title="字段提示信息demo" />
        </FormItem>
        <FormItem {...formItemLayout} label="一级分类名称">
          {
            getFieldDecorator('cateFirstParentName', {
              rules: [
                { max: 255, message: '最多255字符' }
              ],
              onChange: (e) => this._changeFormData('cateFirstParentName', e.target.value),
              initialValue: formData.get('cateFirstParentName')
            })(<Input />)
          }
        </FormItem>
        <FormItem {...formItemLayout} label="二级分类id">
          {
            getFieldDecorator('cateSecondParentId', {
              onChange: (e) => this._changeFormData('cateSecondParentId', e.target.value),
              initialValue: formData.get('cateSecondParentId') || formData.get('cateSecondParentId') == 0 ? formData.get('cateSecondParentId').toString() : null
            })(<Input />)
          }
        </FormItem>
        <FormItem {...formItemLayout} label="二级分类名称">
          {
            getFieldDecorator('cateSecondParentName', {
              rules: [
                { max: 255, message: '最多255字符' }
              ],
              onChange: (e) => this._changeFormData('cateSecondParentName', e.target.value),
              initialValue: formData.get('cateSecondParentName')
            })(<Input />)
          }
        </FormItem>
        <FormItem {...formItemLayout} label="分类id">
          {
            getFieldDecorator('cateId', {
              onChange: (e) => this._changeFormData('cateId', e.target.value),
              initialValue: formData.get('cateId') || formData.get('cateId') == 0 ? formData.get('cateId').toString() : null
            })(<Input />)
          }
        </FormItem>
        <FormItem {...formItemLayout} label="分类名称">
          {
            getFieldDecorator('cateName', {
              rules: [
                { max: 255, message: '最多255字符' }
              ],
              onChange: (e) => this._changeFormData('cateName', e.target.value),
              initialValue: formData.get('cateName')
            })(<Input />)
          }
        </FormItem>
        <FormItem {...formItemLayout} label="销售额">
          {
            getFieldDecorator('totalPrice', {
              onChange: (e) => this._changeFormData('totalPrice', e.target.value),
              initialValue: formData.get('totalPrice') || formData.get('totalPrice') == 0 ? formData.get('totalPrice').toString() : null
            })(<Input />)
          }
        </FormItem>
        <FormItem {...formItemLayout} label="销量">
          {
            getFieldDecorator('totalCount', {
              onChange: (e) => this._changeFormData('totalCount', e.target.value),
              initialValue: formData.get('totalCount') || formData.get('totalCount') == 0 ? formData.get('totalCount').toString() : null
            })(<Input />)
          }
        </FormItem>
        <FormItem {...formItemLayout} label="订单量">
          {
            getFieldDecorator('orderCount', {
              onChange: (e) => this._changeFormData('orderCount', e.target.value),
              initialValue: formData.get('orderCount') || formData.get('orderCount') == 0 ? formData.get('orderCount').toString() : null
            })(<Input />)
          }
        </FormItem>
        <FormItem {...formItemLayout} label="购买人数">
          {
            getFieldDecorator('buyerCount', {
              onChange: (e) => this._changeFormData('buyerCount', e.target.value),
              initialValue: formData.get('buyerCount') || formData.get('buyerCount') == 0 ? formData.get('buyerCount').toString() : null
            })(<Input />)
          }
        </FormItem>
        <FormItem {...formItemLayout} label="日期">
          {
            getFieldDecorator('day', {
              rules: [
                
              ],
              onChange: (date, dateStr) => {
                this._changeFormData('day', date ? dateStr : null)
              },
              initialValue: this._getInitDate(formData.get('day'), Const.TIME_FORMAT)
            })(
              <DatePicker
                {...this._getDateCommProps(Const.TIME_FORMAT)}
                showTime={true}
                disabledDate={this._disabledDate}
              />)
          }
        </FormItem>
      </Form>
    );
  }

  /**
   * 修改表单字段
   */
  _changeFormData = (key, value) => {
    const { editFormData } = this.props.relaxProps;
    editFormData({ key, value });
  };

  /**
   * 获取初始化的日期时间
   */
  _getInitDate(dateStr, dateFormat) {
    return dateStr ? moment(dateStr, dateFormat) : null
  }

  /**
   * 日期时间组件公用属性
   */
  _getDateCommProps(dateFormat) {
    return {
      getCalendarContainer: () => document.getElementById('page-content'),
      allowClear: true,
      format: dateFormat
    }
  }

  /**
   * 不可选的日期
   */
  _disabledDate(current) {
    return current && current.valueOf() > Date.now();
  }
}
