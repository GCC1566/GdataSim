/**
 * 数据匹配器组件
 * 整个组件以策略模式为主体，提供动态的可配置指定匹配算法
 * 对于算法也可进行扩展，只需实现 MatcherAlg接口即可
 * 策略的指定可以根据默认配置文件（factoryconfig.ini）中的 matcherclassalgurl 字段 控制
 *
 * 包含：
 *   匹配算法 MatcherAlg
 *   匹配处理器 Matcher
 */
package org.gds.matcher;