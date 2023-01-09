<#assign timeTypeFlag=false>
<#list table.notPkColumns as column>
  <#if column.columnNameLower != "updateTime">
    <#if column.isDateTimeColumn>
      <#assign timeTypeFlag=true>
    </#if>
  </#if>
</#list>
import React from 'react';
import { Button, Form, Input<#if timeTypeFlag>, DatePicker</#if> } from 'antd';
import { Relax } from 'plume2';
import { noop, Const } from 'qmkit';

const FormItem = Form.Item;
<#if timeTypeFlag>
const RangePicker = DatePicker.RangePicker;
</#if>

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
<#list table.notPkColumns as column>
  <#if column.columnNameLower != "updateTime" && column.columnNameLower != "updatePerson" && column.columnNameLower != "delFlag">
    <#if column.isDateTimeColumn>
          <FormItem>
            <RangePicker
              getCalendarContainer={() =>
                  document.getElementById('page-content')
              }
      <#if column.javaType == "LocalDateTime">
              format={Const.TIME_FORMAT}
              showTime={true}
      <#elseif column.javaType == "LocalDate">
              format={Const.DAY_FORMAT}
      </#if>
              placeholder={['${column.columnAlias}开始', '${column.columnAlias}截止']}
              onChange={(date, dateStr) => {
                let ${column.columnNameLower}Begin = null;
                let ${column.columnNameLower}End = null;
                if (date.length > 0) {
                  ${column.columnNameLower}Begin = dateStr[0];
                  ${column.columnNameLower}End = dateStr[1];
                }
                this.setState({ ${column.columnNameLower}Begin, ${column.columnNameLower}End });
              }}
            />
          </FormItem>
    <#else>
          <FormItem>
            <Input
              addonBefore={'${column.columnAlias}'}
              onChange={(e) => {
                this.setState({
                  ${column.columnNameLower}: e.target.value
                });
              }}
            />
          </FormItem>
    </#if>
  </#if>
</#list>
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
