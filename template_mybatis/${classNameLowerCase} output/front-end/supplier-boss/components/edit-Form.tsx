<#assign timeTypeFlag=false>
<#assign tipDemoFlag=true>
<#list table.notPkColumns as column>
  <#if column.columnNameLower != "updateTime">
    <#if column.isDateTimeColumn>
      <#assign timeTypeFlag=true>
    </#if>
  </#if>
</#list>
import React from 'react';
import { Form, Input, DatePicker } from 'antd';
<#if timeTypeFlag>
import moment from 'moment';
</#if>
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
<#list table.notPkColumns as column>
  <#if column.columnNameLower != "createTime" && column.columnNameLower != "createPerson" && column.columnNameLower != "updateTime" && column.columnNameLower != "updatePerson" && column.columnNameLower != "delFlag">
    <#if column.isDateTimeColumn>
        <FormItem {...formItemLayout} label="${column.columnAlias}">
          {
            getFieldDecorator('${column.columnNameLower}', {
              rules: [
                <#if !column.nullable>{ required: true, whitespace: true, message: '请输入${column.columnAlias}' }</#if>
              ],
              onChange: (date, dateStr) => {
                this._changeFormData('${column.columnNameLower}', date ? dateStr : null)
              },
      <#if column.javaType == "LocalDateTime">
              initialValue: this._getInitDate(formData.get('${column.columnNameLower}'), Const.TIME_FORMAT)
            })(
              <DatePicker
                {...this._getDateCommProps(Const.TIME_FORMAT)}
                showTime={true}
      <#elseif column.javaType == "LocalDate">
              initialValue: this._getInitDate(formData.get('${column.columnNameLower}'), Const.DAY_FORMAT)
            })(
              <DatePicker
                {...this._getDateCommProps(Const.DAY_FORMAT)}
      </#if>
                disabledDate={this._disabledDate}
              />)
          }
      <#if tipDemoFlag>
          <Tips title="字段提示信息demo" />
        <#assign tipDemoFlag=false>
      </#if>
        </FormItem>
    <#else>
        <FormItem {...formItemLayout} label="${column.columnAlias}">
          {
            getFieldDecorator('${column.columnNameLower}', {
      <#if column.isNumberColumn>
        <#if !column.nullable>
              rules: [
                { required: true, whitespace: true, message: '请输入${column.columnAlias}' },
              ],
        </#if>
      <#else>
              rules: [
        <#if !column.nullable>
                { required: true, whitespace: true, message: '请输入${column.columnAlias}' },
        </#if>
                { max: ${column.size}, message: '最多${column.size}字符' }
              ],
      </#if>
              onChange: (e) => this._changeFormData('${column.columnNameLower}', e.target.value),
              initialValue: formData.get('${column.columnNameLower}')<#if column.isNumberColumn> || formData.get('${column.columnNameLower}') == 0 ? formData.get('${column.columnNameLower}').toString() : null</#if>
            })(<Input />)
          }
      <#if tipDemoFlag>
          <Tips title="字段提示信息demo" />
        <#assign tipDemoFlag=false>
      </#if>
        </FormItem>
    </#if>
  </#if>
</#list>
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

<#if timeTypeFlag>
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
</#if>
}
