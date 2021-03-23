/**
 * 数据解析器
 * 整个包以Parser 以及 FieldTree 为主
 * 具体Parser提供了 定向的数据解析机构，可根据输入样例结构，进行字段拆分解析
 * 扩展方式可实现Parser接口进行扩展
 * FieldTree为主要的字段解析数据结构
 * 操作FieldTree结构，可以通过实现 FieldTreeOperator接口来扩展
 * 对外提供 工厂获取该部分对象
 * 工厂类： org.gds.factory.ParserFactory
 * 该工厂类可通过factoryconfig.ini来控制生成何种Parser
 */
package org.gds.parser;